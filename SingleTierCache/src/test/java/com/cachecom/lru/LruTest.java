package com.cachecom.lru;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cachecom.Cache;
import com.cachecom.store.CacheCom;

public class LruTest {

	// Please change the eviction policy to lru in cachecom.properties file before executing this test
	@Test
	public void testLruCacheLRUItemisDeleted () {
		CacheCom<String> cc = new CacheCom<String>();
		Cache<String> cache = cc.buildCache();
		
		cache.put("1", "XXX");
		cache.put("2", "YYY");
		cache.put("3", "ZZZ");
		cache.put("4", "AAA");
		assertEquals(3, cache.size());
		assertNull(cache.get("1"));
	}	
}
