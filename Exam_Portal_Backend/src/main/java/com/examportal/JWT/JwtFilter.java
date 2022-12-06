package com.examportal.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
@CrossOrigin("*")
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private StudentUserDetailsService service;
	
	Claims claim = null;
	private String userName = null;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {

		if(httpServletRequest.getServletPath().matches("/user/login|/user/register")) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		else {
			String autorizationHeader = httpServletRequest.getHeader("Authorization");
			String token = null;
			
			if(autorizationHeader!=null && autorizationHeader.startsWith("Bearer ")) {
				token = autorizationHeader.substring(7);
				System.out.println("JwtFilter Token: "+token);
				userName = jwtUtil.extractUsername(token);
				System.out.println("JwtFilter Username: "+userName);
				claim = jwtUtil.extractAllClaims(token);
				System.out.println("JwtFilter Claim: "+claim);
			}
			
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = service.loadUserByUsername(userName);
				System.out.println("JwtFilter UserDetails:"+userDetails);
				if(jwtUtil.validateToken(token, userDetails)) {
					System.out.println("JwtFilter if: ");
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}
	
	public boolean isAdmin() {
		return "admin".equalsIgnoreCase((String) claim.get("role"));
	}
	
	public boolean isUser() {
		return "user".equalsIgnoreCase((String) claim.get("role"));
	}
	
	public String getCurrentuser() {
		return userName;
	}
}
