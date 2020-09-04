package com.cachecom.disk.lru;

import java.util.HashMap;
import java.util.Map;

import com.cachecom.Cache;
import com.cachecom.collections.LruAccessOrder;
import com.cachecom.disk.service.DiskSerializationService;
import com.cachecom.inmemory.lru.LruCacheEntry;

public class DiskLruCache<V> implements Cache<V>{

	private int MAX_CAPACITY;
	
	public DiskLruCache(int capacity) {
		MAX_CAPACITY = capacity;
		
		Map<String, LruCacheEntry<V>> cache = new HashMap<String, LruCacheEntry<V>>();
		DiskSerializationService.serializaLruCache(cache);
		
		LruAccessOrder<V> diskAccessOrder = new LruAccessOrder<V>();
		DiskSerializationService.serializaLruAccessOrder(diskAccessOrder);
	}
	
	/* (non-Javadoc)
	 * @see com.cachecom.Cache#get(java.lang.String)
	 */
	@Override
	public V get(String key) {
		Map<String, LruCacheEntry<V>> diskCache = DiskSerializationService.deserializeLruCache();
		LruAccessOrder<V> lruAccessOrder = DiskSerializationService.deserializeLruAccessOrder();
		
		if (!diskCache.containsKey(key)) {
			return null;
		}
		
		LruCacheEntry<V> cacheObject = diskCache.get(key);
		lruAccessOrder.remove(cacheObject);
		lruAccessOrder.addFirst(cacheObject);
		DiskSerializationService.serializaLruAccessOrder(lruAccessOrder);
		return cacheObject.value();		
	}

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, V value) {
		
		LruCacheEntry<V> cacheObject = new LruCacheEntry<V>(key, value); 
		
		Map<String, LruCacheEntry<V>> diskCache = DiskSerializationService.deserializeLruCache();
		LruAccessOrder<V> lruAccessOrder = DiskSerializationService.deserializeLruAccessOrder();
		
		if (diskCache.size() == MAX_CAPACITY) {
			String lruObjectKey = lruAccessOrder.tail().key();
			diskCache.remove(lruObjectKey);
		}
		
		diskCache.put(key, cacheObject);
		lruAccessOrder.addFirst(cacheObject);
		
		DiskSerializationService.serializaLruAccessOrder(lruAccessOrder);
		DiskSerializationService.serializaLruCache(diskCache);
	}

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#size()
	 */
	@Override
	public int size() {
		Map<String, LruCacheEntry<V>> diskCache = DiskSerializationService.deserializeLruCache();
		return diskCache.size();
	}

}
