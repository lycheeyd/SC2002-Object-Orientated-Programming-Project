package com.cache;

import java.util.Optional;
import java.util.function.Predicate;

public interface CacheEditer<K, T> {
    void addItem(K key, T item);
    void removeItem(K key, Optional<Predicate<T>>... filters);
}
