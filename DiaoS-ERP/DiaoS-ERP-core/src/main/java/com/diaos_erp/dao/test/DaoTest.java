package com.diaos_erp.dao.test;

import com.diaos_erp.dao.ServiceInjector;
import com.diaos_erp.dao.test.domain.DaoTestDO;

public class DaoTest {
	public static void main(String[] args) {
		DaoTestIf daotest = ServiceInjector.getService(DaoTestIf.class);
		DaoTestDO daotestdo = daotest.getDO(1);
		if (null != daotestdo) {
			System.out.println(daotestdo.getName());
		}
	}
}
