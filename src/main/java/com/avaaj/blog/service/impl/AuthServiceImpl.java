package com.avaaj.blog.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.avaaj.blog.exception.BlogAPIException;
import com.avaaj.blog.repository.RoleRepository;
import com.avaaj.blog.repository.UserRepository;
import com.avaaj.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avaaj.blog.entity.Role;
import com.avaaj.blog.entity.User;
import com.avaaj.blog.payload.LoginDto;
import com.avaaj.blog.payload.RegisterDto;
import com.avaaj.blog.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager,
							UserRepository userRepository,
							RoleRepository roleRepository,
							PasswordEncoder passwordEncoder,
							JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	public String login(LoginDto loginDto) {
		
		Authentication authentication = authenticationManager
										.authenticate(new UsernamePasswordAuthenticationToken(
										loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {
		
		// Validation:-
		// 1. Add check for username exists  in database.
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
		}
		
		// 2. Add check for email already exists or not.
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email address already exists!");
		}
		
		// entity user...
		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "User registered successfully!";
	}
	

}
