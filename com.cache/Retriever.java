package com.cache;

import java.util.List;

public interface Retriever<T> {
    List<T> retrieve();
}
