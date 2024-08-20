package com.enotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enotes.entities.User;
import com.enotes.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		System.out.println(user);
		boolean f=service.existEmailCheck(user.getEmail());
		if(f) {
			session.setAttribute("msg", "Email already exists");
		}
		else {
			User saveUser=service.saveUser(user);
			if(saveUser!=null) {
				session.setAttribute("msg", "Register success");
			}
			else {
				session.setAttribute("msg", "Something went wrong");
			}
		}
		return "redirect:/register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/add")
	public String addNotes() {
		return "addNotes";
	}
	
	@GetMapping("/edit")
	public String editNotes() {
		return "editNotes";
	}
	
	@GetMapping("/view")
	public String viewNotes() {
		return "viewNotes";
	}
}
