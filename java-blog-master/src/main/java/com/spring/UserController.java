package com.spring;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.entity.Blog;
import com.spring.entity.User;
import com.spring.service.BlogService;
import com.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@ModelAttribute("user")
	public User constructUser(){
		return new User();
	}
	
	@ModelAttribute("blog")
	public Blog constructBlog(){
		return new Blog();
	}
	
	@RequestMapping("/users")
	public String users(Model model){
		model.addAttribute("users",userService.findAll());
		return "/WEB-INF/jsp/users.jsp";
	}
	
	@RequestMapping("/users/{id}")
	public String detail(Model model,@PathVariable int id){
		model.addAttribute("user",userService.findOneWithBlog(id));
		return "/WEB-INF/jsp/userdetail.jsp";
		
	}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "/WEB-INF/jsp/user-register.jsp";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,BindingResult result){
		if(result.hasErrors()){
			return "/WEB-INF/jsp/user-register.jsp";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/account")
	public String account(Model model,Principal principal){
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithBlog(name));
		return "/WEB-INF/jsp/userdetail.jsp";
	}
	
	@RequestMapping(value="/account",method=RequestMethod.POST)
	public String doAddBlog(Model model,@Valid @ModelAttribute("blog") Blog blog, BindingResult result ,Principal principal){
		if(result.hasErrors())
			return account(model,principal);
		String name= principal.getName();
		blogService.save(blog,name);
		return "redirect:/account.html";
	}
	
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id){
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";
	}
	
	@RequestMapping("/users/remove/{id}")
	public String removeUser(@PathVariable int id){
		userService.delete(id);
		return "redirect:/users.html";
	}
	@ResponseBody
	@RequestMapping("/register/available")
	
	public String avilable(@RequestParam String username){
		Boolean available = userService.findOne(username) == null;
		return available.toString();
	}
}
