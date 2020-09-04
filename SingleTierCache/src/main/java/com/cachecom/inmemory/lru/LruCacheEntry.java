package com.cachecom.inmemory.lru;

/**
 * @author Sarindu
 *	This class represents an item saved in the lru cache
 * @param <V>
 */
public class LruCacheEntry<V> {
	private String key;
	
	private V value;
	
	public LruCacheEntry<V> next;
	
	public LruCacheEntry<V> prev;
	
	public LruCacheEntry (String key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public String key() {
		return key;
	};
	
	public V value() {
		return value;
	}
	
	public LruCacheEntry<V> next() {
		return next;
	}
	
	public LruCacheEntry<V> prev() {
		return prev;
	}

	public void setNext(LruCacheEntry<V> next) {
		this.next = next;
	}

	public void setPrev(LruCacheEntry<V> prev) {
		this.prev = prev;
	}
}