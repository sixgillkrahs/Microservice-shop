package com.main.notificationservice.service.impl;

import com.main.notificationservice.repository.ConfigRepository;
import com.main.notificationservice.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigRepository configRepository;

    @Override
    public String getValue(String key) {
        return configRepository.findByKey(key).getValue();
    }

    @Override
    public void setValue(String key, String value) {

    }
}
