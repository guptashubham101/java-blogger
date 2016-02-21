package com.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Blog;
import com.spring.entity.Item;
import com.spring.entity.Role;
import com.spring.entity.User;
import com.spring.repository.BlogRepository;
import com.spring.repository.ItemRepository;
import com.spring.repository.RoleRepository;
import com.spring.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@PostConstruct
	public void init(){
		System.out.println("init db running");
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog blogJava = new Blog();
		blogJava.setName("Java blog");
		blogJava.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJava.setUser(userAdmin);
		blogRepository.save(blogJava);
		
		Item item1 = new Item();
		item1.setBlog(blogJava);
	    item1.setTitle("first");
	    item1.setLink("http://www.javavids.com/");
	    item1.setDate(new Date());
	    
	    itemRepository.save(item1);
	}
	
}
