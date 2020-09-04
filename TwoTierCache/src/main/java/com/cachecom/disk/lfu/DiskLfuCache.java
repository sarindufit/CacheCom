package com.cachecom.disk.lfu;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.cachecom.Cache;
import com.cachecom.collections.LfuFrequencyOrder;
import com.cachecom.disk.service.DiskSerializationService;
import com.cachecom.inmemory.lfu.LfuCacheEntry;

public class DiskLfuCache<V> implements Cache<V> {	
	
    private int MAX_CAPACITY;

    public DiskLfuCache(int capacity) {
        MAX_CAPACITY = capacity;
        
    	Map<String, LfuCacheEntry<V>> cache = new HashMap<String, LfuCacheEntry<V>>();
    	DiskSerializationService.serializaLfuCache(cache);
    	
        Map<String, Integer> lfuCounts = new HashMap<>();
        DiskSerializationService.serializaLfuCounts(lfuCounts);
        
        TreeMap<Integer, LfuFrequencyOrder<V>> frequencies = new TreeMap<Integer, LfuFrequencyOrder<V>>();
        DiskSerializationService.serializaLfuFrequencyOrder(frequencies);
    }	

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#get(java.lang.String)
	 */
	@Override
	public V get(String key) {
		Map<String, LfuCacheEntry<V>> cache = DiskSerializationService.deserializeLfuCache();
		Map<String, Integer> counts = DiskSerializationService.deserializeLfuCounts();
		TreeMap<Integer, LfuFrequencyOrder<V>> frequencies = DiskSerializationService.deserializeLfuFrequencyOrder();
		
        if (!cache.containsKey(key)) {
            return null;
        }
        
        // Move item from one frequency list to next. Not O(1) due to list iteration.
        int frequency = counts.get(key);
        
        frequencies.get(frequency).remove(cache.get(key));
        
        if (frequencies.get(frequency).size() == 0) {
            frequencies.remove(frequency);  // remove from map if list is empty
        }
        
        frequencies.computeIfAbsent(frequency + 1, k -> new LfuFrequencyOrder<V>()).addLast(cache.get(key));

        counts.put(key, frequency + 1);
        
        DiskSerializationService.serializaLfuCounts(counts);
        DiskSerializationService.serializaLfuFrequencyOrder(frequencies);
        
        return cache.get(key).value();
	}

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, V value) {
		
		Map<String, LfuCacheEntry<V>> cache = DiskSerializationService.deserializeLfuCache();
		Map<String, Integer> counts = DiskSerializationService.deserializeLfuCounts();
		TreeMap<Integer, LfuFrequencyOrder<V>> frequencies = DiskSerializationService.deserializeLfuFrequencyOrder();		
		
        if (!cache.containsKey(key)) {

        	LfuCacheEntry<V> lfuCacheEntry = new LfuCacheEntry<V>(key, value);

            if (cache.size() == MAX_CAPACITY) {

                int lowestCount = frequencies.firstKey();
                LfuCacheEntry<V> cacheEntryToDelete = frequencies.get(lowestCount).head();
                frequencies.get(lowestCount).remove(cacheEntryToDelete);
                
                String keyToDelete = cacheEntryToDelete.key();
                
                if (frequencies.get(lowestCount).size() == 0) {
                    frequencies.remove(lowestCount);  // remove from map if list is empty
                }
                
                cache.remove(keyToDelete);
                counts.remove(keyToDelete);
            }

            cache.put(key, lfuCacheEntry);
            counts.put(key, 1);
            frequencies.computeIfAbsent(1, k -> new LfuFrequencyOrder<V>()).addLast(lfuCacheEntry);
            
            DiskSerializationService.serializaLfuCounts(counts);
            DiskSerializationService.serializaLfuFrequencyOrder(frequencies);
            DiskSerializationService.serializaLfuCache(cache);
        }			
		
	}

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#size()
	 */
	@Override
	public int size() {
		Map<String, LfuCacheEntry<V>> cache = DiskSerializationService.deserializeLfuCache();
		return cache.size();
	}

}
