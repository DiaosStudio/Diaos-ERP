package com.diaos_erp.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.engine.Engine;

public class ClassUtil
{
    private static Logger logger = Engine.getLogger(ClassUtil.class);

    /**
     * 根据ClassName获取Class对象
     * 
     * @param cls
     * @return
     */
    public static Class getClass(String cls)
    {
        Class c = null;
        if (!StringUtil.isEmpty(cls))
        {
            try
            {
                c = ClassUtil.class.forName(cls);
            }
            catch (ClassNotFoundException e)
            {
                // e.printStackTrace();
                logger.log(Level.WARNING, "[ERROR]class " + cls + " is not exist.", e);
            }
        }
        return c;
    }

    /**
     * 获取某个Class的实例
     * 
     * @param cls
     *            类的Class对象
     * @return Object 预得到的类的实例
     */
    public static Object getObject(Class cls)
    {
        return getObject(cls, null, null);
    }

    /**
     * 获取某个Class的实例
     * 
     * @param cls
     *            类的Class对象
     * @param parameters
     *            要传入的参数列表
     * @return Object 预得到的类的实例
     */
    public static Object getObject(Class cls, Object[] parameters)
    {
        Class[] parameterTypes = null;
        if (null != parameters)
        {
            parameterTypes = new Class[parameters.length];
            for (int i = 0; i < parameters.length; i++)
            {
                parameterTypes[0] = parameters[i].getClass();
            }
        }
        return getObject(cls, parameterTypes, parameters);
    }

    /**
     * 获取某个Class的实例
     * 
     * @param cls
     *            类的Class对象
     * @param parameterTypes
     *            构造方法的参数类型列表
     * @param parameters
     *            要传入的参数列表
     * @return Object 预得到的类的实例
     */
    public static Object getObject(Class cls, Class[] parameterTypes, Object[] parameters)
    {
        Object obj = null;
        Constructor con = null;
        if (null != cls)
        {
            try
            {
                con = cls.getDeclaredConstructor(parameterTypes);
                obj = con.newInstance(parameters);
            }
            catch (Exception e)
            {
                // e.printStackTrace();
                logger.log(Level.WARNING, "[ERROR]class " + cls.getName() + " create instance fail. ", e);
            }
        }
        return obj;
    }

    /**
     * 获取类中某个方法对象
     * 
     * @param cls
     * @param methodName
     * @param parameterTypes
     * @return Method
     */
    public static Method getMethod(Class cls, String methodName, Class[] parameterTypes)
    {
        Method met = null;
        if (null != cls && !StringUtil.isEmpty(methodName))
        {
            try
            {
                met = cls.getDeclaredMethod(methodName, parameterTypes);
                if (null == met)
                {
                    logger.log(Level.WARNING, "[ERROR]class " + cls.getName() + "'s method " + methodName
                            + " is not exist. ");
                }
            }
            catch (Exception e)
            {
                // e.printStackTrace();
                logger.log(Level.WARNING, "[ERROR]class " + cls.getName() + "'s method " + methodName
                        + " is not exist. ", e);
            }
        }
        return met;
    }

    public static void main(String[] args)
    {
        ClassUtil.getObject(ClassUtil.class, null, null);
    }
}
