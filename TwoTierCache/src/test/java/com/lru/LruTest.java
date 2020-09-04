package com.lru;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cachecom.Cache;
import com.cachecom.store.CacheCom;

public class LruTest {

	@Test
	public void testLruCache () {
		CacheCom<String> cc = new CacheCom<String>();
		Cache<String> cache = cc.buildCache();
		
		cache.put("1", "XXX");
		cache.put("2", "YYY");
		cache.put("3", "ZZZ");
		cache.put("4", "AAA");
		assertEquals(3, cache.size());
		assertEquals("XXX", cache.get("1"));
	}	
}
