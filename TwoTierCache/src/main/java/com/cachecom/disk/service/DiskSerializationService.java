package com.cachecom.disk.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

import com.cachecom.collections.LfuFrequencyOrder;
import com.cachecom.collections.LruAccessOrder;
import com.cachecom.inmemory.lfu.LfuCacheEntry;
import com.cachecom.inmemory.lru.LruCacheEntry;

/**
 * @author Sarindu
 *	This class contains a set of helper methods to serialize and deserialize caches and helper collections to 
 * disk
 */
public class DiskSerializationService {

	/**
	 * @param cacheMap
	 * 
	 * Serialize LRU Cache
	 */
	public static <V> void serializaLruCache (Map<String, LruCacheEntry<V>> cacheMap) {
		try {
			FileOutputStream fos = new FileOutputStream("lruCache.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cacheMap);
			oos.close();
			fos.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * Deserialize LRU cache from disk
	 */
	public static <V> Map<String, LruCacheEntry<V>> deserializeLruCache () {
		try {
			FileInputStream fis; fis = new FileInputStream("lruCache.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			@SuppressWarnings("unchecked")
			Map<String, LruCacheEntry<V>> map = (Map<String, LruCacheEntry<V>>) ois.readObject();
			ois.close();
			fis.close();
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param cacheMap
	 * Serialize LFU Cache
	 */
	public static <V> void serializaLfuCache (Map<String, LfuCacheEntry<V>> cacheMap) {
		
		try {
			FileOutputStream fos =
	                new FileOutputStream("lfuCache.ser");
	         ObjectOutputStream oos = new ObjectOutputStream(fos);
	         oos.writeObject(cacheMap);
	         oos.close();
	         fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * @return
	 * 
	 * deserialize LFU Cache from disk
	 */
	public static <V> Map<String, LfuCacheEntry<V>> deserializeLfuCache () {
		try {
	        FileInputStream fis = new FileInputStream("lfuCache.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        
	        @SuppressWarnings("unchecked")
	        Map<String, LfuCacheEntry<V>> map = (Map<String, LfuCacheEntry<V>>) ois.readObject();
	        ois.close();
	        fis.close();
	        return map;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	/**
	 * @param lruCacheOrder
	 * Serialize doubly linked list which is used to maintain the access order of lru cache
	 */
	public static <V> void serializaLruAccessOrder (LruAccessOrder<V> lruCacheOrder) {
		try {
			FileOutputStream fos =
	                new FileOutputStream("lruAccessOrder.ser");
	             ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(lruCacheOrder);
             oos.close();
             fos.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * derialize doubly linked list which is used to maintain the access order of lru cache
	 */
	public static <V> LruAccessOrder<V> deserializeLruAccessOrder () {
		
		try {
	        FileInputStream fis = new FileInputStream("lruAccessOrder.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	       
	        @SuppressWarnings("unchecked")
			LruAccessOrder<V> lruCacheAccessOrder = (LruAccessOrder<V>) ois.readObject();
	        ois.close();
	        fis.close();
	        return lruCacheAccessOrder;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static <V> void serializaLfuFrequencyOrder (TreeMap<Integer, LfuFrequencyOrder<V>> lfuFrequencyOrder) {
		
		try {
			FileOutputStream fos =
	                new FileOutputStream("lfuFrequencyOrder.ser");
	             ObjectOutputStream oos = new ObjectOutputStream(fos);
	         oos.writeObject(lfuFrequencyOrder);
	         oos.close();
	         fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static <V> TreeMap<Integer, LfuFrequencyOrder<V>> deserializeLfuFrequencyOrder () {
		try {
	        FileInputStream fis = new FileInputStream("lfuFrequencyOrder.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        
	        @SuppressWarnings("unchecked")
	        TreeMap<Integer, LfuFrequencyOrder<V>> lfuFrequencyOrder = (TreeMap<Integer, LfuFrequencyOrder<V>>) ois.readObject();
	        ois.close();
	        fis.close();
	        return lfuFrequencyOrder;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	public static <V> void serializaLfuCounts (Map<String, Integer> lfuCounts) {
		try {
			FileOutputStream fos = new FileOutputStream("lfucounts.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lfuCounts);
			oos.close();
			fos.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public static <V> Map<String, Integer> deserializeLfuCounts () {
		try {
			FileInputStream fis; fis = new FileInputStream("lfucounts.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			@SuppressWarnings("unchecked")
			Map<String, Integer> map = (Map<String, Integer>) ois.readObject();
			ois.close();
			fis.close();
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}		
}
