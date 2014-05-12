package com.test.cache.impl;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.test.cache.SampleFacade;
import com.test.cache.config.TestConfig;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@ContextConfiguration(classes = {TestConfig.class})
@TransactionConfiguration(transactionManager = "transactionManager")
public class SampleFacadeImplIntegrationTest extends AbstractJUnit4SpringContextTests {

	@Resource(name = "sampleFacade")
	private SampleFacade sampleFacade;
	
	@Test
	public void testGetSiteForUid() {
		for (int i = 0; i < 10; i++) {
			assertNotNull(sampleFacade.getSiteForUid("uid"));
		}
	}
	
	@Test
	public void testGetCurrentSite() {
		for (int i = 0; i < 10; i++) {
			assertNotNull(sampleFacade.getCurrentSite());
		}
	}
	
}
