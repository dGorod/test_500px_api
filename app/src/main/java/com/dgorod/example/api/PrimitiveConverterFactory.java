package com.dgorod.example.api;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Converter factory for Retrofit 2 which is convert response to primitive type classes.
 * For now supports String, Integer, Long, Double, Float and Boolean.
 *
 * https://gist.github.com/bradhawk/b6aa8f6f9e05d148a216
 *
 * Created by bradhawk on 1/29/2016.
 * Forked by dmig on 17-Feb-16.
 */
public class PrimitiveConverterFactory extends Converter.Factory {

    public synchronized static PrimitiveConverterFactory create() {
        return new PrimitiveConverterFactory();
    }

    private PrimitiveConverterFactory() { }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        } else if(type == Integer.class) {
            return new Converter<ResponseBody, Integer>() {
                @Override
                public Integer convert(ResponseBody value) throws IOException {
                    return Integer.valueOf(value.string());
                }
            };
        } else if(type == Long.class) {
            return new Converter<ResponseBody, Long>() {
                @Override
                public Long convert(ResponseBody value) throws IOException {
                    return Long.valueOf(value.string());
                }
            };
        } else if(type == Double.class) {
            return new Converter<ResponseBody, Double>() {
                @Override
                public Double convert(ResponseBody value) throws IOException {
                    return Double.valueOf(value.string());
                }
            };
        } else if(type == Float.class) {
            return new Converter<ResponseBody, Float>() {
                @Override
                public Float convert(ResponseBody value) throws IOException {
                    return Float.valueOf(value.string());
                }
            };
        } else if(type == Boolean.class) {
            return new Converter<ResponseBody, Boolean>() {
                @Override
                public Boolean convert(ResponseBody value) throws IOException {
                    return Boolean.valueOf(value.string());
                }
            };
        }
        return null;
    }
}
