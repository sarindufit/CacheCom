package com.cachecom.lfu;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cachecom.Cache;
import com.cachecom.store.CacheCom;

public class LfuTest {
	
	// Please change the eviction policy to lfu in cachecom.properties file before executing this test
	
	@Test
	public void testLfuCacheLFUItemsIsDeleted () {
		CacheCom<String> cc = new CacheCom<String>();
		Cache<String> lfuCache = cc.buildCache();
		
		lfuCache.put("1", "XXX");
		lfuCache.put("2", "YYY");
		lfuCache.put("3", "ZZZ");
		
		System.out.println("Cache Entry" + lfuCache.get("1"));
		
		System.out.println("Cache Entry" + lfuCache.get("2"));
		System.out.println("Cache Entry" + lfuCache.get("2"));
		
		System.out.println("Cache Entry" + lfuCache.get("3"));
		System.out.println("Cache Entry" + lfuCache.get("3"));
		System.out.println("Cache Entry" + lfuCache.get("3"));
		
		lfuCache.put("4", "AAA");
		
		assertEquals(3, lfuCache.size());
		assertNull(lfuCache.get("1"));
	}
}
