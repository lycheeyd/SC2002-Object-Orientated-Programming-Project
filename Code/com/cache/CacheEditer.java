package com.cache;

import java.util.Optional;
import java.util.function.Predicate;


/**
* The CacheFetcher interface defines methods for editing items from a cache.
*
* @param <K> The type of keys in the cache.
* @param <T> The type of values stored in the cache.
*/
public interface CacheEditer<K, T> {

    /**
    * Adds an item to the cache with the specified key.
    *
    * @param key  The key to associate with the item in the cache.
    * @param item The item to add to the cache.
    */
    void addItem(K key, T item);

    /**
    * Removes an item from the cache using the specified key and optional filters.
    *
    * @param key     The key of the item to remove from the cache.
    * @param filters Optional filters to apply when removing the item. Compulsory for MenuCache.
    */
    void removeItem(K key, Optional<Predicate<Object>>... filters);
}
