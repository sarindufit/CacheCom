package com.cachecom.inmemory.lfu;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.cachecom.Cache;
import com.cachecom.collections.LfuFrequencyOrder;

/**
 * @author Sarindu
 *
 * This class is used to represen the lfu cache
 * @param <V>
 */
public class InMemoryLfuCache<V> implements Cache<V> {
	
	private Map<String, LfuCacheEntry<V>> cache = new HashMap<String, LfuCacheEntry<V>>();
	
    private Map<String, Integer> counts = new HashMap<>();
    
    private TreeMap<Integer, LfuFrequencyOrder<V>> frequencies = new TreeMap<Integer, LfuFrequencyOrder<V>>();
    
    private int MAX_CAPACITY;

    public InMemoryLfuCache(int capacity) {
        MAX_CAPACITY = capacity;
    }
	
	/* (non-Javadoc)
	 * @see com.cachecom.Cache#get(java.lang.String)
	 */
	@Override
	public V get(String key) {
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
        return cache.get(key).value();
	}

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, V value) {
        if (!cache.containsKey(key)) {

        	LfuCacheEntry<V> lfuCacheEntry = new LfuCacheEntry<V>(key, value);

            if (cache.size() == MAX_CAPACITY) {

                int lowestCount = frequencies.firstKey();
                LfuCacheEntry<V> cacheEntryToDelete = frequencies.get(lowestCount).head();
                frequencies.get(lowestCount).remove(cacheEntryToDelete);
                
                String keyToDelete = cacheEntryToDelete.key();
                removeIfListEmpty(lowestCount);
                cache.remove(keyToDelete);
                counts.remove(keyToDelete);
            }

            cache.put(key, lfuCacheEntry);
            counts.put(key, 1);
            frequencies.computeIfAbsent(1, k -> new LfuFrequencyOrder<V>()).addLast(lfuCacheEntry);
        }		
	}

	/* (non-Javadoc)
	 * @see com.cachecom.Cache#size()
	 */
	@Override
	public int size() {
		return cache.size();
	}
	
    private void removeIfListEmpty(int frequency) {
        if (frequencies.get(frequency).size() == 0) {
            frequencies.remove(frequency);
        }
    }	

}
