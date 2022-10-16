package com.example.jwt.Configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt.Service.JwtService;
import com.example.jwt.Utils.Utils;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private JwtService js;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = utils.getUserNameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println(e);
			} catch (ExpiredJwtException e) {
				System.out.println(e);
			}
		} else {
			System.out.println("JWT token doesn't start with Bearer!");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails ud = js.loadUserByUsername(username);
			
			if (utils.validateToken(jwtToken, ud)) {
				UsernamePasswordAuthenticationToken upa = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(upa);
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
