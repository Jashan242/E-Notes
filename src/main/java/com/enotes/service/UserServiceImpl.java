package com.enotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.enotes.entities.User;
import com.enotes.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User saveUser(User u) {
		u.setRole("ROLE_USER");
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		User us=userRepo.save(u);
		return us;
	}
	
	public boolean existEmailCheck(String email) {
		return userRepo.existsByEmail(email);
	}
	
	public void removeSessionMessage() {
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}
}
