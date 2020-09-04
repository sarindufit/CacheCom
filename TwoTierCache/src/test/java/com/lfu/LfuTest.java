package com.lfu;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cachecom.Cache;
import com.cachecom.store.CacheCom;

public class LfuTest {
	
	@Test
	public void testLfuCache () {
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
		assertEquals("XXX", lfuCache.get("1"));
		assertNull(lfuCache.get("5"));
	}
}