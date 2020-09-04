package com.cachecom.collections;

import java.io.Serializable;

import com.cachecom.inmemory.lfu.LfuCacheEntry;

/**
 * @author Sarindu
 *
 *	This is a DoublyLinkedList which is used to maintain the lfu cache items
 * @param <V>
 */
public class LfuFrequencyOrder <V> implements Serializable {
    
    /**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private LfuCacheEntry<V> head;
    
    private LfuCacheEntry<V> tail;
    
    private int size = 0;
    
    
    /**
     * @param lfuCacheEntry
     * Add an item to the top
     */
    public void addLast(LfuCacheEntry<V> lfuCacheEntry) {
    	if (size == 0) {
    		head = lfuCacheEntry;
    		tail = lfuCacheEntry;
    	} else {
    		tail.next = lfuCacheEntry;
    		lfuCacheEntry.prev = tail;
    		tail = lfuCacheEntry;
    	}    	
    	size++;
    }
    
    /**
     * @param lfuCacheEntry
     * Add an item to the last
     */
    public void addFirst(LfuCacheEntry<V> lfuCacheEntry) {
    	if (size == 0) {
    		head = lfuCacheEntry;
    		tail = lfuCacheEntry;
    	} else {
    		head.prev = lfuCacheEntry;
    		lfuCacheEntry.next = head;
    		head = lfuCacheEntry;
    	}    	
    	size++;
    }    
    
    /**
     * @param lfuCacheEntry
     * remove an item
     */
    public void remove(LfuCacheEntry<V> lfuCacheEntry) {
    	
		if (lfuCacheEntry.prev != null) {
			lfuCacheEntry.prev.next = lfuCacheEntry.next;
		} else {
			head = lfuCacheEntry.next;
		}

		if (lfuCacheEntry.next != null) {
			lfuCacheEntry.next.prev = lfuCacheEntry.prev;
		} else {
			tail = lfuCacheEntry.prev;
		}    	
    	
		size--;
    }
    
    /**
     * @return
     * Checking whether the list is empty
     */
    public boolean isEmpty() {
    	return size <= 0;
    }
    
    /**
     * @return
     * retrieving the size of the list
     */
    public int size() {
    	return size;
    }
    
    /**
     * @return
     * retrieve the head
     */
    public LfuCacheEntry<V> head() {
    	return head;
    }
    
    /**
     * @return
     * retrieve the tail
     */
    public LfuCacheEntry<V> tail() {
    	return tail;
    }    
}