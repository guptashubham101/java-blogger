package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

@Service
@Transactional
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findOne(int id) {
	
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithBlog(int id) {
		User user = findOne(id);
		List<Blog> blogs= blogRepository.findByUser(user);
		for(Blog blog:blogs){
			List<Item> items= itemRepository.findByBlog(blog,new PageRequest(0, 10, Direction.DESC, "date"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findByRoleName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Transactional
	public User findOneWithBlog(String name) {
		User user = userRepository.findByName(name);
		return findOneWithBlog(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);
		
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
		
	}
}
