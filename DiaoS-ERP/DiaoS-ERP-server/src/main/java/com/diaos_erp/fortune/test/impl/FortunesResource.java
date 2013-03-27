package com.diaos_erp.fortune.test.impl;

import com.diaos_erp.dao.ServiceInjector;
import com.diaos_erp.dao.test.DaoTestIf;
import com.diaos_erp.dao.test.domain.DaoTestDO;
import com.diaos_erp.fortune.test.Fortune;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;

/**
 * Very simple Rest.li Resource that serves up a fortune cookie.
 */
@RestLiCollection(name = "fortunes", namespace = "com.diaos_erp.fortune.test")
public class FortunesResource extends
		CollectionResourceTemplate<Integer, Fortune> {

	@Override
	public Fortune get(Integer key) {
		// Retrieve the requested fortune
		// String fortune = fortunes.get(key);
		DaoTestDO testDo = ServiceInjector.getService(DaoTestIf.class).getDO(
				key);

		String fortune = null;
		if (testDo == null)
			fortune = "Your luck has run out. No fortune for id=" + key;
		else
			fortune = testDo.getName();

		// return an object that represents the fortune cookie
		return new Fortune().setFortune(fortune);
	}
}