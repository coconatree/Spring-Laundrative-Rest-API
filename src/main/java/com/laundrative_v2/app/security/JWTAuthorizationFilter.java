package com.laundrative_v2.app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.laundrative_v2.app.exception.EmailNotFoundException;
import com.laundrative_v2.app.exception.TokenNotFound;
import com.laundrative_v2.app.repository.customerRepo.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
    private CustomerRepo repository;


    public JWTAuthorizationFilter(
            AuthenticationManager authenticationManager,
            CustomerRepo repository
            )
    {
        super(authenticationManager);
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        String header = request.getHeader(JWTProperties.HEADER_NAME);

        if(header == null || !header.startsWith(JWTProperties.PREFIX))
        {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request)
    {
        String token = request.getHeader(JWTProperties.HEADER_NAME);

        if(token != null)
        {
            String email = JWT.require(Algorithm.HMAC512(JWTProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JWTProperties.PREFIX, ""))
                    .getSubject();

            if(email != null)
            {
                CustomerPrincipal principal = CustomerPrincipal.from(repository.findByEmailCustom(email));

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, principal.getAuthorities());
                return auth;
            }
            throw new EmailNotFoundException("Please Authenticate Again", HttpStatus.UNAUTHORIZED);
        }
        throw new TokenNotFound("Token Not Found", HttpStatus.UNAUTHORIZED);
    }
}
