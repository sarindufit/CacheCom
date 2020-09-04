package com.cachecom.collections;

import java.io.Serializable;

import com.cachecom.inmemory.lru.LruCacheEntry;

/**
 * @author Sarindu
 * A double linked list which is used to maintain the lru cache items
 * @param <V>
 */
public class LruAccessOrder <V> implements Serializable {
	
    /**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1L;

	private LruCacheEntry<V> head;
    
    private LruCacheEntry<V> tail;
    
    private int size = 0;
    
    
    /**
     * @param lruCacheEntry
     * 
     * Add an item to last
     */
    public void addLast(LruCacheEntry<V> lruCacheEntry) {
    	if (size == 0) {
    		head = lruCacheEntry;
    		tail = lruCacheEntry;
    	} else {
    		tail.next = lruCacheEntry;
    		lruCacheEntry.prev = tail;
    		tail = lruCacheEntry;
    	}    	
    	size++;
    }
    
    /**
     * @param lruCacheEntry
     * 
     * Add an item to top
     */
    public void addFirst(LruCacheEntry<V> lruCacheEntry) {
    	if (size == 0) {
    		head = lruCacheEntry;
    		tail = lruCacheEntry;
    	} else {
    		head.prev = lruCacheEntry;
    		lruCacheEntry.next = head;
    		head = lruCacheEntry;
    	}    	
    	size++;
    }    
    
    /**
     * @param lruCacheEntry
     * remove an item
     */
    public void remove(LruCacheEntry<V> lruCacheEntry) {
    	
		if (lruCacheEntry.prev != null) {
			lruCacheEntry.prev.next = lruCacheEntry.next;
		} else {
			head = lruCacheEntry.next;
		}

		if (lruCacheEntry.next != null) {
			lruCacheEntry.next.prev = lruCacheEntry.prev;
		} else {
			tail = lruCacheEntry.prev;
		}    	
    	
		size--;
    }
    
    /**
     * @return
     * checking whether the list is empty
     */
    public boolean isEmpty() {
    	return size <= 0;
    }
    
    public int size() {
    	return size;
    }
    
    public LruCacheEntry<V> head() {
    	return head;
    }
    
    public LruCacheEntry<V> tail() {
    	return tail;
    }    
}