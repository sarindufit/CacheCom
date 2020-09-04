package com.cachecom.store;

import com.cachecom.Cache;
import com.cachecom.inmemory.lfu.InMemoryLfuCache;
import com.cachecom.inmemory.lru.InMemoryLruCache;
import com.cachecom.util.PropertyLoader;

/**
 * @author Sarindu
 *	This is the entry point to cache. YOu can use this class to build a cache
 * @param <V>
 */
public class CacheCom<V> {

private int cacheInMemoryMaxCapacity;
	
	private String	cacheInMemoryEvictionPolicy;
	
	public CacheCom() {
		cacheInMemoryMaxCapacity = Integer.parseInt(PropertyLoader.getInstance().getValue("cache.inmemory.maxcapacity"));
		cacheInMemoryEvictionPolicy = PropertyLoader.getInstance().getValue("cache.inmemory.evictionpolicy");
	}
	
	public Cache<V> buildCache() {
		if ("lfu".equals(cacheInMemoryEvictionPolicy)) {
			return new InMemoryLfuCache<V>(cacheInMemoryMaxCapacity);
			
		} else {
			return new InMemoryLruCache<V>(cacheInMemoryMaxCapacity);			
		}
	}
}
