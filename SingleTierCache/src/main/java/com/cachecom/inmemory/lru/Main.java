package com.cachecom.inmemory.lru;

import com.cachecom.util.PropertyLoader;

public class Main {

	public static void main(String aregs[]) throws InterruptedException {
		
		System.out.println("111111111111111111111" + PropertyLoader.getInstance().getValue("name"));

		InMemoryLruCache<String> cache = new InMemoryLruCache<String>(3);

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
	}

}
