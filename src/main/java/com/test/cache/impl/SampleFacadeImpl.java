package com.test.cache.impl;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;

import org.springframework.stereotype.Component;

import com.test.cache.SampleFacade;
import com.test.cache.SitePojo;

@Component(value = "sampleFacade")
@CacheDefaults(cacheName = "default")
public class SampleFacadeImpl implements SampleFacade
{

   @Override
   @CacheResult(cacheName = "site")
	public SitePojo getSiteForUid(final String uid)
	{
		System.out.println("getting the site for uid: " + uid);
		
		final SitePojo pojo = new SitePojo();
		pojo.setUid(uid);
		pojo.setUrl(uid);
		
		return pojo;
	}

	@Override
	@CacheResult(cacheName = "site")
	public SitePojo getCurrentSite()
	{
		System.out.println("getting current website ");
		
		final SitePojo pojo = new SitePojo();
		pojo.setUid("currentpojo");
		pojo.setUrl("currentpojo");
		
		return pojo;
	}

	
}
