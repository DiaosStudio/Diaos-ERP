package com.diaos_erp.fortune.test.impl;

import java.util.HashMap;
import java.util.Map;

import com.diaos_erp.fortune.test.Fortune;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;

/**
 * Very simple Rest.li Resource that serves up a fortune cookie.
 */
@RestLiCollection(name = "fortunes", namespace = "com.diaos_erp.fortune.test")
public class FortunesResource extends CollectionResourceTemplate<Long, Fortune> {
	// Create trivial db for fortunes
	static Map<Long, String> fortunes = new HashMap<Long, String>();
	static {
		fortunes.put(1L, "Today is your lucky day.");
		fortunes.put(2L, "There's no time like the present.");
		fortunes.put(3L, "Don't worry, be happy.");
	}

	@Override
	public Fortune get(Long key) {
		// Retrieve the requested fortune
		String fortune = fortunes.get(key);
		if (fortune == null)
			fortune = "Your luck has run out. No fortune for id=" + key;

		// return an object that represents the fortune cookie
		return new Fortune().setFortune(fortune);
	}
}