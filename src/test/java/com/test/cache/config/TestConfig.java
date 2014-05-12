package com.test.cache.config;

import javax.annotation.Resource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching(mode = AdviceMode.PROXY)
@ComponentScan(basePackages = { "com.test" })
public class TestConfig implements CachingConfigurer
{
   @Resource
   public ApplicationContext context;
   
   @Override
   @Bean(name = { "defaultKeyGenerator", "keyGenerator" })
   public KeyGenerator keyGenerator() {
       return new SimpleKeyGenerator();
   }
   
   @Override
   @Bean(name = { "defaultCacheManager", "cacheManager" })
   public CacheManager cacheManager() {
       final JCacheCacheManager cacheManager = new JCacheCacheManager();
       cacheManager.setCacheManager((javax.cache.CacheManager) context.getBean("cacheManagerFactoryBean"));

       return cacheManager;
   }

   @Bean(name = { "defaultCacheManagerFactoryBean", "cacheManagerFactoryBean" })
   protected JCacheManagerFactoryBean defaultCacheManagerFactoryBean() {
       return new JCacheManagerFactoryBean();
   }
}
