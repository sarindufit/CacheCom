package com.cachecom.inmemory.lru;

import java.util.HashMap;
import java.util.Map;

import com.cachecom.Cache;
import com.cachecom.collections.LruAccessOrder;

/**
 * @author Sarindu
 *	This class represents an in memory lru cache
 * @param <V>
 */
public class InMemoryLruCache<V> implements Cache<V>{

	Map<String, LruCacheEntry<V>> cache;
	
	LruAccessOrder<V> inMemoryAccessOrder;
	
	private int MAX_CAPACITY;
	
	public InMemoryLruCache(int capacity) {
		MAX_CAPACITY = capacity;
		cache = new HashMap<String, LruCacheEntry<V>>();
		inMemoryAccessOrder = new LruAccessOrder<V>();
	}
	
	/* (non-Javadoc)
	 * @see com.cachecom.Cache#get(java.lang.String)
	 */
	@Override
	public V get(String key) {
		if (!cache.containsKey(key)) {
			return null;
		}
		
		LruCacheEntry<V> cacheObject = cache.get(key);
		inMemoryAccessOrder.remove(cacheObject);
		inMemoryAccessOrder.addFirst(cacheObject);
		return cacheObject.value();
	}
	

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, V value) {

		LruCacheEntry<V> cacheObject = new LruCacheEntry<V>(key, value); 
		
		if (cache.size() == MAX_CAPACITY) {
			String lruObjectKey = inMemoryAccessOrder.tail().key();
			cache.remove(lruObjectKey);
		}
		
		cache.put(key, cacheObject);
		inMemoryAccessOrder.addFirst(cacheObject);
	}
	
	/* (non-Javadoc)
	 * @see com.cachecom.Cache#size()
	 */
	@Override
	public int size() {
		return cache.size();
	}
}
