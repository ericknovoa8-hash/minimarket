package com.proyecto.Minimarket.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyecto.Minimarket.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class JwtValidationFilder extends OncePerRequestFilter{
    /**
    * servicio de jwt
    */
    private final JwtService jwtService;

    @Override
    /** 
    * iniciamos el filtro de seguridad 
    * Detiene las peticiones que llegan de posman para verificar el token 
    * decide si la deja pasar o no
    */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        String autHeader = request.getHeader("Authorization");

        if (autHeader == null || !autHeader.startsWith("Bearer")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\" : \"Header is missing in the request\"}");
            return; //cortamos la peticion para que no llegue a controller
        }
        String token = autHeader.replace("Bearer ", "");
                     
        try {
            if (jwtService.isTokenValid(token)){
                String username =jwtService.extractUsername(token);
                Long userId = jwtService.extractUserId(token);
                Long rolId = jwtService.extractRolId(token);
                request.setAttribute("username", username);
                request.setAttribute("userId", userId);
                request.setAttribute("rolId", rolId);
    //seteamos los atributos del payload para el alcance del controller
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Token is invalid or expired\"}");
                }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"validation failed\"}");
      
        }
    }
    
}
