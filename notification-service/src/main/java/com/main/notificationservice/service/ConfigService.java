package com.main.notificationservice.service;

public interface ConfigService {
    String getValue(String key);
    void setValue(String key, String value);
}
