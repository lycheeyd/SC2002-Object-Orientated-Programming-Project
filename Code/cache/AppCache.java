package com.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
* The AppCache class is an abstract class that provides basic caching functionality.
* It implements CacheFetcher and CacheEditer interfaces to handle fetching and editing cache items.
* @param <K> The type of keys in the cache.
* @param <T> The type of values stored in the cache.
* @param <T2> The type of object stored in the values. T and T2 may be the same.
* @see CacheFetcher
* @see CacheEditer
*/
public abstract class AppCache<K, T, T2> implements CacheFetcher<K, T, T2> , CacheEditer<K, T2> {

    /**
    * Constructs an AppCache parent object.
    */
    protected AppCache() {};

    /**
    * The map storing the cache items with their respective keys.
    */
    protected Map<K, T> cacheItems = new HashMap<>();
    
    /**
    * Retrieves all items in the cache.
    *
    * @return A map containing all items in the cache.
    */
    @Override
    public Map<K, T> getAllItems() {
        return cacheItems;
    }

    /**
    * Retrieves a list of items from the cache that match the specified filters.
    *
    * @param filters The filters to apply when fetching items.
    * @return A list of filtered items.
    */
    @SafeVarargs
    @Override
    public final List<T2> getFilteredItems(Predicate<Object>... filters) {
        Predicate<Object> combinedFilters = Stream.of(filters)
                                            .reduce(Predicate::and)
                                            .orElse(t2 -> true);

        List<Object> flattenedList = new ArrayList<>();

        cacheItems.values().forEach(item -> {
            if (item instanceof List) {
                ((List<?>) item).stream()
                                .filter(combinedFilters)
                                .forEach(flattenedList::add);
            } else {
                if (combinedFilters.test(item)) {
                    flattenedList.add(item);
                }
            }
        });
        return (List<T2>) flattenedList;
    }

    /**
    * Prints a list of items from the cache that match the specified filters.
    *
    * @param filters The filters to apply when printing items.
    */
    @SafeVarargs
    @Override
    public final void printFilteredItems(Predicate<Object>... filters) {
        Predicate<Object> combinedFilters = Stream.of(filters)
                                            .reduce(Predicate::and)
                                            .orElse(t2 -> true);

        cacheItems.values().forEach(item -> {
            if (item instanceof List) {
                ((List<?>) item).stream()
                                .filter(combinedFilters)
                                .forEach(System.out::println);
            } else {
                if (combinedFilters.test(item)) {
                    System.out.println(item);
                }
            }
        });
    }

}
