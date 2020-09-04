package com.cachecom.inmemory.lfu;


public class Main {
	
	public static void main (String [] args) {
		InMemoryLfuCache<String> lfuCache = new InMemoryLfuCache<String>(3);
		
		lfuCache.put("1", "Sarindu");
		lfuCache.put("2", "Piyumi");
		lfuCache.put("3", "Dulein");
		
		System.out.println("Cache Entry" + lfuCache.get("1"));
		
		System.out.println("Cache Entry" + lfuCache.get("2"));
		System.out.println("Cache Entry" + lfuCache.get("2"));
		
		System.out.println("Cache Entry" + lfuCache.get("3"));
		System.out.println("Cache Entry" + lfuCache.get("3"));
		System.out.println("Cache Entry" + lfuCache.get("3"));
		
		lfuCache.put("4", "Darshana");
		
		System.out.println("Now cache size" + lfuCache.size());
		
		System.out.println(lfuCache.get("1"));
	}

}
