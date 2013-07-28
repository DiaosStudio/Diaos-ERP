package com.diaos_erp.startup;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import com.diaos_erp.dao.ServiceInjector;
import com.diaos_erp.util.ClassUtil;
import com.diaos_erp.util.DateUtil;

public class DiaoSLauncher
{
    private static Logger logger = Engine.getLogger(DiaoSLauncher.class);

    private static Component com = new Component();

    public static void main(String[] args)
    {
        logger.info("===================== begin launcher ===================");
        List<StartUpItem> list = null;
        DiaoSLauncher launcher = new DiaoSLauncher();

        File startup = null;
        if (null != args && args.length == 1)
        {
            startup = new File(args[0]);
            if (startup.exists())
            {
                list = launcher.loadLaunchItemFromFile(startup);
            }
        }

        if (null == list)
        {
            list = launcher.loadLaunchItemFromDB();
        }

        // 初始化容器
        launcher.initComponent();

        // 分别启动各个启动项
        if (null != list)
        {
            for (StartUpItem item : list)
            {
                if (item.isApp())
                {
                    if (!launcher.launchApp(item) && !item.isIgnoreErr())
                    {
                        // 如果设置了不忽略错误则退出系统
                        logger.info("[INFO]launch " + item.getItemName() + "(" + item.getItemCode()
                                + ") error and exit. ");
                        System.exit(0);
                    }
                }
                else
                {
                    if (!launcher.launchNoApp(item) && !item.isIgnoreErr())
                    {
                        // 如果设置了不忽略错误则退出系统
                        logger.info("[INFO]launch " + item.getItemName() + "(" + item.getItemCode()
                                + ") error and exit. ");
                        System.exit(0);
                    }
                }
            }
        }

        // 启动容器
        if (!launcher.startComponent())
        {
            logger.info("[INFO]start component fail and exit. ");
            System.exit(0);
        }
        logger.info("====================== end launcher ====================");
    }

    /**
     * 启动容器
     * 
     * @return boolean
     */
    private boolean startComponent()
    {
        boolean ret = false;
        Long curTime = DateUtil.getCurrentTime().getTime();
        try
        {
            com.start();
            Long costTime = DateUtil.getCurrentTime().getTime() - curTime;
            logger.info("[INFO]initialize component -------------------- cost " + costTime);
            ret = true;
        }
        catch (Exception e)
        {
            // e.printStackTrace();
            logger.info("[ERROR]start component fail. " + e.getMessage());
        }

        return ret;
    }

    /**
     * 初始化容器
     * 
     * @return boolean
     */
    private boolean initComponent()
    {
        boolean ret = false;
        Long curTime = DateUtil.getCurrentTime().getTime();

        com.getServers().add(Protocol.HTTP, 9090);
        com.getClients().add(Protocol.HTTP);
        // com.getClients().add(Protocol.HTTPS);
        com.getClients().add(Protocol.RIAP);
        com.getClients().add(Protocol.CLAP);

        Long costTime = DateUtil.getCurrentTime().getTime() - curTime;
        logger.info("[INFO]initialize component -------------------- cost " + costTime);
        ret = true;
        return ret;
    }

    /**
     * 执行非Application的启动项
     * 
     * @param item
     * @return boolean
     */
    private boolean launchNoApp(StartUpItem item)
    {
        boolean ret = false;
        Long curTime = DateUtil.getCurrentTime().getTime();

        Class cls = ClassUtil.getClass(item.getCls());

        if (null != cls)
        {
            Object obj = ClassUtil.getObject(cls);

            if (null != obj)
            {
                Method met = ClassUtil.getMethod(cls, item.getMethod(), null);
                if (null != met)
                {
                    try
                    {
                        met.invoke(obj, null);
                        Long costTime = DateUtil.getCurrentTime().getTime() - curTime;
                        logger.info("[INFO]launch " + item.getItemCode() + " success. ");
                        logger.info("[INFO]launch " + item.getItemCode() + " -------------------- cost " + costTime);
                        ret = true;
                    }
                    catch (Exception e)
                    {
                        // e.printStackTrace();
                        logger.info("[ERROR]class " + cls.getName() + "'s method " + item.getMethod()
                                + " invoke error. ");
                    }
                }
            }
            else
            {
                logger.info("[ERROR]class " + item.getCls() + " is not a subclass of Application. ");
            }
        }
        return ret;
    }

    /**
     * 加载Application到Host中
     * 
     * @param item
     * @return boolean
     */
    private boolean launchApp(StartUpItem item)
    {
        boolean ret = false;
        Long curTime = DateUtil.getCurrentTime().getTime();

        Class cls = ClassUtil.getClass(item.getCls());

        if (null != cls)
        {
            Object obj = ClassUtil.getObject(cls);

            if (null != obj && obj instanceof Application)
            {
                com.getDefaultHost().attach("/", (Application) obj);

                Long costTime = DateUtil.getCurrentTime().getTime() - curTime;
                logger.info("[INFO]attact application " + item.getCls() + " success. ");
                logger.info("[INFO]attact application " + item.getItemName() + " -------------------- cost " + costTime);
                ret = true;
            }
            else
            {
                logger.info("[ERROR]class " + item.getCls() + " is not a subclass of Application. ");
            }
        }
        return ret;
    }

    /**
     * 从配置文件中读取启动配置
     * 
     * @param cfgFile
     * @return List<StartUpItem>
     */
    private List<StartUpItem> loadLaunchItemFromFile(File cfgFile)
    {
        Long curTime = DateUtil.getCurrentTime().getTime();
        List<StartUpItem> retlist = new ArrayList<StartUpItem>();
        Long costTime = DateUtil.getCurrentTime().getTime() - curTime;
        logger.info("[INFO]load startup config from file -------------------- cost " + costTime + "ms");
        return retlist;
    }

    /**
     * 从配置库中读取启动配置
     * 
     * @return List<StartUpItem>
     */
    private List<StartUpItem> loadLaunchItemFromDB()
    {
        Long curTime = DateUtil.getCurrentTime().getTime();

        List<StartUpItem> retlist = ServiceInjector.getService(StartUpDaoIf.class).getStartUp();

        Long costTime = DateUtil.getCurrentTime().getTime() - curTime;
        logger.info("[INFO]load startup config from DB -------------------- cost " + costTime + "ms");
        return retlist;
    }
}
