package com.todos.encoders;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for(int i=1;i<=10;i++) {
		String encoded = encoder.encode("password123#!");
		System.out.println(encoded);
		}
	}
}
