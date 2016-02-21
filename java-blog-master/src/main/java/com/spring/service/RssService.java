package com.spring.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import com.spring.entity.Item;
import com.spring.rss.ObjectFactory;
import com.spring.rss.TRss;
import com.spring.rss.TRssChannel;
import com.spring.rss.TRssItem;

@Service
public class RssService {
	
	public List<Item> getItems(File file){
		return getItems(new StreamSource(file));
	}
	
	public List<Item> getItems(String url){
		return getItems(new StreamSource(url));
	}
	
	
	public List<Item> getItems(Source source){
		ArrayList<Item> list = new ArrayList<>();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source,TRss.class);
			TRss rss = jaxbElement.getValue();
			List<TRssChannel> channels = rss.getChannel();
			for(TRssChannel channel:channels){
				List<TRssItem> items =  channel.getItem();
				for(TRssItem rssitem:items){
					Item item = new Item();
					item.setTitle(rssitem.getTitle());
					item.setDescription(rssitem.getDescription());
					item.setLink(rssitem.getLink());
					Date date =new SimpleDateFormat("EEE,dd MMM yyy HH:mm:ss",Locale.ENGLISH).parse(rssitem.getPubDate());
					item.setDate(date);
					list.add(item);
				}
			}
		} catch (JAXBException | ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
