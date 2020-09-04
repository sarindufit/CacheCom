package com.cachecom.inmemory.lfu;

import java.io.Serializable;

public class LfuCacheEntry <V> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
