package com.diaos_erp.dao;

import org.mybatis.guice.XMLMyBatisModule;

import com.diaos_erp.dao.test.DaoTestIf;
import com.diaos_erp.dao.test.DaoTestImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;

public class ServiceInjector {
	private static Injector injector;

	public static Injector getInjector() {
		if (null == injector) {
			injector = Guice.createInjector(
			//
					new XMLMyBatisModule() {
						@Override
						protected void initialize() {
							setEnvironmentId("staff.development");
							setClassPathResource("com/diaos_erp/dao/env/mybatis-staff.xml");
							// addProperties(getProperties());

							bind(DaoTestIf.class).to(DaoTestImpl.class).in(
									Scopes.SINGLETON);
							
						}
					},
					// 内存库
					new XMLMyBatisModule() {
						@Override
						protected void initialize() {
							setEnvironmentId("session.development");
							setClassPathResource("com/diaos_erp/dao/env/mybatis-staff.xml");
							// addProperties(getProperties());

							bind(DaoTestIf.class).to(DaoTestImpl.class).in(
									Scopes.SINGLETON);
						}
					});
		}

		return injector;
	}

	public static <T> T getService(Class<T> clz) {
		return getInjector().getInstance(clz);
	}
}
