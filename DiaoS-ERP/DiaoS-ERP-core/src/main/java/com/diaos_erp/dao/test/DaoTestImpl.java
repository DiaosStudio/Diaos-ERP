package com.diaos_erp.dao.test;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.guice.transactional.Transactional;

import com.diaos_erp.dao.test.domain.DaoTestDO;
import com.google.inject.Inject;

public class DaoTestImpl implements DaoTestIf {
	@Inject
	private SqlSession session;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.diaos_erp.dao.test.DaoTestIf#getDO(int)
	 */
	@Override
	@Transactional
	public DaoTestDO getDO(int id) {
		return session.selectOne(
				"com.diaos_erp.dao.test.DaoTestMapper.selectTest", id);
	}
}
