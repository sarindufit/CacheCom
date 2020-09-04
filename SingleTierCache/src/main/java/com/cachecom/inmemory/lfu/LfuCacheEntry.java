package com.cachecom.inmemory.lfu;

/**
 * @author Sarindu
 *	This represents an item saved in the lfu cache
 * @param <V>
 */
public class LfuCacheEntry <V> {

	private String key;
	
	private V value;
	
	public LfuCacheEntry<V> next;
	
	public LfuCacheEntry<V> prev;
	
	public LfuCacheEntry (String key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public String key() {
		return key;
	};
	
	public V value() {
		return value;
	}
	
	public LfuCacheEntry<V> next() {
		return next;
	}
	
	public LfuCacheEntry<V> prev() {
		return prev;
	}

	public void setNext(LfuCacheEntry<V> next) {
		this.next = next;
	}

	public void setPrev(LfuCacheEntry<V> prev) {
		this.prev = prev;
	}
}
