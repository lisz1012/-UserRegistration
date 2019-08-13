package com.lisz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lisz.mapper.User;
import com.lisz.service.UserService;

@Controller //这里不能写成@RestController
public class MainController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String register () {
		return "register";
	}
	
	@PostMapping("/register")
	public String createUser(@ModelAttribute User user) {
		try {
			userService.create(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "list";
	}
	
	@GetMapping("/list/{id}")
	@ResponseBody
	public Object listById(@PathVariable int id, Model model) {
		return userService.findById(id);
	}
	
	@GetMapping("/listByUsername/{username}")
	public String listById(@PathVariable String username, Model model) {
		List<User> users = userService.findByUsername(username);
		model.addAttribute("users", users);
		return "list";
	}
	
	@GetMapping("/listByPage/{pageNum}/{pageSize}")
	public String listByPage(@PathVariable int pageNum, @PathVariable int pageSize, Model model) {
		List<User> users = userService.findAllByPage(pageNum, pageSize);
		model.addAttribute("users", users);
		return "list";
	}
	
	/*
	 * curl -H "Accept: application/json" -H "Content-pplication/json" -X PUT -d '{"id":"10", "username":"ccc", "password":"12345", "email":"bbb@gmail.com", "phoneNumber":"12345678"}' http://localhost/UserRegistration/update
	 */
	@PutMapping(value = "/update", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody User user, Model model) { //接受JSON必须得用@RequestBody，而不能用@ModelAttribute，表单过来的数据可以用@ModelAttribute
		try {
			userService.update(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	//curl -v -X DELETE http://localhost/UserRegistration/delete/11
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		try {
			userService.deleteById(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
