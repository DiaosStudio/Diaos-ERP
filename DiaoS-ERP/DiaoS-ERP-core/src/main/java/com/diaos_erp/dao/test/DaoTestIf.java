package com.diaos_erp.dao.test;

import org.mybatis.guice.transactional.Transactional;

import com.diaos_erp.dao.test.domain.DaoTestDO;

public interface DaoTestIf {

	@Transactional
	public abstract DaoTestDO getDO(int id);

}