package com.cachecom.inmemory.lru;

import java.util.HashMap;
import java.util.Map;

import com.cachecom.Cache;
import com.cachecom.collections.LruAccessOrder;
import com.cachecom.disk.lfu.DiskLfuCache;
import com.cachecom.disk.lru.DiskLruCache;
import com.cachecom.util.PropertyLoader;

/**
 * @author Sarindu
 *	This class contains the methods which helps to get and put cache items of lru cache
 * @param <V>
 */
public class InMemoryLruCache<V> implements Cache<V>{

	Map<String, LruCacheEntry<V>> cache;
	
	LruAccessOrder<V> inMemoryAccessOrder;
	
	private int MAX_CAPACITY;
	
	Cache<V> diskCache;
	
	public InMemoryLruCache(int capacity) {
		MAX_CAPACITY = Integer.parseInt(PropertyLoader.getInstance().getValue("cache.inmemory.maxcapacity"));
		
		cache = new HashMap<String, LruCacheEntry<V>>();
		inMemoryAccessOrder = new LruAccessOrder<V>();
		
		if("lfu".equals(PropertyLoader.getInstance().getValue("cache.disk.evictionpolicy"))) {
			diskCache = new DiskLfuCache<V>(Integer.parseInt(PropertyLoader.getInstance().getValue("cache.disk.maxcapacity"))); 
		} else {
			diskCache = new DiskLruCache<V>(Integer.parseInt(PropertyLoader.getInstance().getValue("cache.disk.maxcapacity")));
		}
	}
	
	@Override
	public V get(String key) {
		if (!cache.containsKey(key)) {
			
			V diskCacheValue = diskCache.get(key);
			
			if (diskCacheValue != null) {
				put(key, diskCacheValue);
				return diskCacheValue;
			} else {
				return null;
			}
		}
		
		LruCacheEntry<V> cacheObject = cache.get(key);
		inMemoryAccessOrder.remove(cacheObject);
		inMemoryAccessOrder.addFirst(cacheObject);
		return cacheObject.value();
	}
	

	@Override
	public void put(String key, V value) {

		LruCacheEntry<V> cacheObject = new LruCacheEntry<V>(key, value); 
		
		if (cache.size() == MAX_CAPACITY) {
			String lruObjectKey = inMemoryAccessOrder.tail().key();
			diskCache.put(lruObjectKey, cache.get(lruObjectKey).value());
			
			inMemoryAccessOrder.remove(cache.get(lruObjectKey));
			cache.remove(lruObjectKey);
		}
		
		cache.put(key, cacheObject);
		inMemoryAccessOrder.addFirst(cacheObject);
	}
	
	@Override
	public int size() {
		return cache.size();
	}
}
