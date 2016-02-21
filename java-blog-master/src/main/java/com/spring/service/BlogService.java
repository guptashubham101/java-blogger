package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Blog;
import com.spring.entity.Item;
import com.spring.entity.User;
import com.spring.repository.BlogRepository;
import com.spring.repository.ItemRepository;
import com.spring.repository.UserRepository;

@Service
@Transactional
@EnableScheduling
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RssService rssService;
	
	public void saveItems(Blog blog) {
		List<Item> items=  rssService.getItems(blog.getUrl());
		for(Item item : items){
			
			Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
			if(savedItem == null){
			item.setBlog(blog);
			itemRepository.save(item);
			}
		}
	}
	
	@Scheduled(fixedDelay=360000)
	public void reloadBlogs() {
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);
		}
	}

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		System.out.println(blog);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}
 
     @PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog")Blog blog) {
		blogRepository.delete(blog);
		
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

	

}
