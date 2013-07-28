package com.diaos_erp.dao;

import java.util.List;

import org.mybatis.guice.XMLMyBatisModule;

import com.diaos_erp.startup.StartUpDaoIf;
import com.diaos_erp.startup.StartUpItem;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ServiceInjector
{
    private static Injector injector;

    public static Injector getInjector()
    {
        if (null == injector)
        {
            injector = Guice.createInjector(
            //
            // new XMLMyBatisModule()
                    // {
                    // @Override
                    // protected void initialize()
                    // {
                    // setEnvironmentId("staff.development");
                    // setClassPathResource("com/diaos_erp/dao/env/mybatis-staff.xml");
                    // // addProperties(getProperties());
                    //
                    // bind(DaoTestIf.class).to(DaoTestImpl.class).in(Scopes.SINGLETON);
                    //
                    // }
                    // },
                    // 配置库
                    new XMLMyBatisModule()
                    {
                        @Override
                        protected void initialize()
                        {
                            setEnvironmentId("config.development");
                            setClassPathResource("com/diaos_erp/dao/env/mybatis-config.xml");
                            // addProperties(getProperties());
                        }
                    });
        }

        return injector;
    }

    public static <T> T getService(Class<T> clz)
    {
        return getInjector().getInstance(clz);
    }

    public static void main(String[] args)
    {
        List<StartUpItem> list = ServiceInjector.getService(StartUpDaoIf.class).getStartUp();
        System.out.println(list.size());
    }
}
