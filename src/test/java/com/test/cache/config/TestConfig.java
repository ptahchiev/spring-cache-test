package com.test.cache.config;

import javax.annotation.Resource;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

import org.ehcache.jcache.JCacheConfiguration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
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
public class TestConfig extends CachingConfigurerSupport
{
   @Resource
   public ApplicationContext context;
   
   @Override
   @Bean
   public KeyGenerator keyGenerator() {
       return new SimpleKeyGenerator();
   }
   
   @Override
   @Bean
   public CacheManager cacheManager() {
		return new JCacheCacheManager(jCacheManager());
   }

	protected javax.cache.CacheManager jCacheManager() {
		javax.cache.CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
		final MutableConfiguration<Object, Object> mutableConfiguration
				= new MutableConfiguration<Object, Object>();
		mutableConfiguration.setStoreByValue(false); // Otherwise value has to be Serializable
		cacheManager.createCache("site",
				new JCacheConfiguration<Object, Object>(mutableConfiguration));

		return cacheManager;
	}



   @Bean(name = { "defaultCacheManagerFactoryBean", "cacheManagerFactoryBean" })
   protected JCacheManagerFactoryBean defaultCacheManagerFactoryBean() {
       return new JCacheManagerFactoryBean();
   }
}
