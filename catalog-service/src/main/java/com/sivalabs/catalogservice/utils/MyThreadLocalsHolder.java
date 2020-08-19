package com.sivalabs.catalogservice.utils;

public class MyThreadLocalsHolder {
    private static final ThreadLocal<String> CORRELATION_ID = new ThreadLocal<>();

    private MyThreadLocalsHolder(){
    }

    public static void setCorrelationId(String correlationId) {
        CORRELATION_ID.set(correlationId);
    }

    public static String getCorrelationId() {
        return CORRELATION_ID.get();
    }

    public static void removeCorrelationId() {
        CORRELATION_ID.remove();
    }

}
