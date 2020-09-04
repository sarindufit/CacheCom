package com.cachecom;

public interface Cache <V> {
	/**
	 * @param key
	 * @return cache item for rhe key
	 */
	V get(String key);
	
	/**
	 * @param key
	 * @param value
	 * 
	 * put an item to the cache
	 */
	void put(String key, V value);
		
	/**
	 * @return
	 * retrieving the size of the cache
	 */
	int size();
	
}
