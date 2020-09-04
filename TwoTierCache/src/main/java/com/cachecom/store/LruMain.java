package com.cachecom.store;

import com.cachecom.Cache;

public class LruMain {

	public static void main(String args []) {
		
		CacheCom<String> cc = new CacheCom<String>();
		Cache<String> cache = cc.buildCache();
		
		cache.put("1", "Sarindu1");
		cache.put("2", "Sarindu2");
		cache.put("3", "Sarindu3");

		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
		System.out.println(cache.get("3"));

		System.out.println("Reached end");

		System.out.println("Started again");
		cache.put("4", "Sarindu4");
		System.out.println(cache.size());
		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
		System.out.println(cache.get("3"));
		System.out.println(cache.get("4"));		
		
		System.out.println(cache.size());
 		
	}
}
