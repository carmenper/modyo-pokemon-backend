package com.cemp.modyo.pokemon.pojo;

import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomOptional<T> {

    private static final CustomOptional<?> EMPTY = new CustomOptional<>();

    private final T value;

    private CustomOptional() {
        this.value = null;
    }

    public static <T> CustomOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public static<T> CustomOptional<T> empty() {
        @SuppressWarnings("unchecked")
        CustomOptional<T> t = (CustomOptional<T>) EMPTY;
        return t;
    }

    public static <T> CustomOptional<T> of(T value) {
        return new CustomOptional<>(value);
    }

    private CustomOptional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public T orElseNull() {
        return value != null ? value : null;
    }

}
