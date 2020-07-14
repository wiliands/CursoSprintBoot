package br.com.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.repository.UserRepository;
import br.com.springboot.security.AccountCredentialsVO;
import br.com.springboot.security.jwt.JwtTokenProvider;
import br.com.springboot.util.CustomMediaType;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository repository;

	@ApiOperation(value = "Authentication a user by credentials")
	@PostMapping(value = "/signin",
				 produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE},
				 consumes = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<Map<Object, Object>> signin(@RequestBody AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			var user = repository.findByUsername(username);
			var token = "";
			if(user != null) {
				token = tokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException(String.format("Username: %s not found", username));
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			
			return ResponseEntity.ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid user name or password supplied");
		}
	}
	
	
}
