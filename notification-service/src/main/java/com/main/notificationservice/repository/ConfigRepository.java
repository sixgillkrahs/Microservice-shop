package com.main.notificationservice.repository;

import com.main.notificationservice.model.config.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Long> {
    Config findByKey(String key);
}
