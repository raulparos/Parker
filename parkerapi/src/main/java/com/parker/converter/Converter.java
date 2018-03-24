package com.parker.converter;

public interface Converter<SOURCE, TARGET> {
    void convert(SOURCE source, TARGET target);
}
