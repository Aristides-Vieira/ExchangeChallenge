package com.exchange.challenge.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableCaching
@CacheConfig
@EnableScheduling
public class ClearCache {

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRate = 60000)
    public void clearAllRates() {
        cacheManager.getCache("response").clear();
    }

    @Scheduled(fixedRate = 60000)
    public void clearRatesList() {
        cacheManager.getCache("ratesList").clear();
    }

}
