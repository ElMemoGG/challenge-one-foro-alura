package com.foro.alura.api.infra.security;

import com.foro.alura.api.domain.usuario.UsuarioRepository;
import com.foro.alura.api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener el token del header
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null){

            var token = authHeader.replace("Bearer ", "");
            System.out.println(token);
            var username = tokenService.getSubject(token); // extract username
            if(username != null){
                //Token valido
                var usuario = usuarioRepository.findByEmail(username);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); // forzamos un inicio de session
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
