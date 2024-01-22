package com.avaaj.blog.service;

import com.avaaj.blog.payload.LoginDto;
import com.avaaj.blog.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
	String register(RegisterDto registerDto);
}
