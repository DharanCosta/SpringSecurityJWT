package br.com.iteris.component;

import br.com.iteris.domain.dtos.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String BEAR = "Bearer ";

    private final JwtHelper jwtHelper;

    public JwtFilter(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }
// FAZER A AUTORIZAÇÃO PARA TODOS OS ENDPOINTS DA APLICAÇÃO
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith(BEAR)){
            filterChain.doFilter(request, response);
            return;
        }

        AuthenticatedUserDetails authenticatedUserDetails =
                jwtHelper.validateAndGetUser(authorizationHeader.replace(BEAR, ""));

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        authenticatedUserDetails,
                        null,
                        authenticatedUserDetails.roles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}