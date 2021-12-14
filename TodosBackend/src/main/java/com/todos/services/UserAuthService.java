package com.todos.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todos.jwt.JwtTokenUtil;
import com.todos.models.User;
import com.todos.models.UserDTO;
import com.todos.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtUtil;

	public void registerUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("user with this username does not exist");
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
	}
	
	public UserDTO loginUser(User user) throws Exception
	{
		authenticate(user.getUsername(),user.getPassword());
		User currUser = userRepository.findByUsername(user.getUsername());
		final UserDetails userDetails = new org.springframework.security.core.userdetails.User(currUser.getUsername(),currUser.getPassword(),new ArrayList<>());
		final String token = jwtUtil.generateToken(userDetails);
		
		UserDTO result = new UserDTO(currUser.getUserid(), currUser.getUsername(), currUser.getMobile(), token);
		return result;
	}
	
	private void authenticate(String username, String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e)
		{
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	
}
