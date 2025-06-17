package ru.study.demoapp.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;

@Configuration
public class MyCacheManagerConfiguration {

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration redisConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(10))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                );
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(redisConfig)
                .withCacheConfiguration("moviesCache", redisConfig)
                .build();
    }

    @Bean
    public CacheManager simpleCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                new ConcurrentMapCache("books")
        ));
        return cacheManager;
    }

    @Primary
    @Bean
    public CompositeCacheManager compositeCacheManager(
            CacheManager redisCacheManager,
            CacheManager simpleCacheManager
    ) {
        CompositeCacheManager manager = new CompositeCacheManager(redisCacheManager, simpleCacheManager);
        manager.setFallbackToNoOpCache(true);
        return manager;
    }
}
