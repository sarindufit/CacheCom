package com.cachecom.store;

import com.cachecom.Cache;
import com.cachecom.inmemory.lfu.InMemoryLfuCache;
import com.cachecom.inmemory.lru.InMemoryLruCache;
import com.cachecom.util.PropertyLoader;

/**
 * @author Sarindu
 *
 *	Cachecom is the entry point to the Cache. This is used to build a cache based on the configurations given in the properties
 *  file placed in the classpath
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
