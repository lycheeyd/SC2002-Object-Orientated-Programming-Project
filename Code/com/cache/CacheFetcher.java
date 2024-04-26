package com.cache;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


/**
* The CacheFetcher interface defines methods for fetching items from a cache.
*
* @param <K>  The type of keys in the cache.
* @param <T>  The type of items to be fetched from the cache.
* @param <T2> The type of items to be returned after filtering.
*/
public interface CacheFetcher<K, T, T2> {

    /**
    * Retrieves an item from the cache using the specified key and optional filters.
    *
    * @param key     The key of the item to retrieve from the cache.
    * @param filters Optional filters to apply when fetching the item. Compulsory for MenuCache.
    * @return The retrieved item from the cache.
    */
    T getItem(K key, Optional<Predicate<Object>>... filters);

    /**
    * Prints all items in the cache using the provided function for formatting.
    *
    * @param function The function to format the items for printing.
    */
    void printAllItems(Function<T2, ?> function);

    // Implemented as concrete final methods shared by all Cache storages.

    /**
    * Retrieves all items in the cache.
    *
    * @return A map containing all items in the cache.
    */
    Map<K, T> getAllItems();    

    /**
    * Retrieves a list of items from the cache that match the specified filters.
    *
    * @param filters The filters to apply when fetching items.
    * @return A list of filtered items.
    */
    List<T2> getFilteredItems(Predicate<Object>... filters);

    /**
    * Prints a list of items from the cache that match the specified filters.
    *
    * @param filters The filters to apply when printing items.
    */
    void printFilteredItems(Predicate<Object>... filters);
    
}
