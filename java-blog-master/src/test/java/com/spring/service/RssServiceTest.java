package com.spring.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.spring.entity.Item;

public class RssServiceTest {
	
	private RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() {
		List<Item> items = rssService.getItems(new File("test-rss/javavids.xml"));
		assertEquals(10,items.size());
		Item firstItem = items.get(0);
		assertEquals("How to solve Source not found error during debug in Eclipse", firstItem.getTitle());
	}

}
