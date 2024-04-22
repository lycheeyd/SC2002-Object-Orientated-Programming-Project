package com.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class AppCache<K, T, T2> implements CacheFetcher<K, T, T2> , CacheEditer<K, T2> {

    protected Map<K, T> cacheItems = new HashMap<>();

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
