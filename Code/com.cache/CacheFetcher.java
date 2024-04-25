package com.cache;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CacheFetcher<K, T, T2> {

    T getItem(K key, Optional<Predicate<Object>>... filters);
    void printAllItems(Function<T2, ?> function);

    // Implemented as concrete final methods shared by all Cache storages.
    Map<K, T> getAllItems();    
    List<T2> getFilteredItems(Predicate<Object>... filters);
    void printFilteredItems(Predicate<Object>... filters);
    
}
