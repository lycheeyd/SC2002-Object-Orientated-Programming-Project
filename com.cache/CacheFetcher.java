package com.cache;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CacheFetcher<K, T, T2> {

    T getItem(K key, Optional<Predicate<T2>>... filters);

    // Implemented as concrete final methods shared by all Cache storages.    
    List<T2> getFilteredItems(Predicate<Object>... filters);
    void printFilteredItems(Predicate<Object>... filters);
    
    void printAllItems(Function<T2, ?> function);
}