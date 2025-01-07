package com.aluracursos.forohubalura.infra.security;

import com.aluracursos.forohubalura.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        // Log para comprobar qué hay en el header Authorization
        System.out.println("Authorization Header: " + authHeader);

        try {
            // Validar que el encabezado Authorization no sea nulo y que comience con Bearer
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Obtener el token y validar que no esté vacío
                var token = authHeader.replace("Bearer ", "").trim();

                    if (!token.isEmpty()) {

                        // Obtener el nombre de usuario del token
                        var nombreUsuario = tokenService.getSubject(token);

                        if (nombreUsuario != null) {
                            // Buscar el usuario asociado al nombre del token
                            var usuario = usuarioRepository.findByNombre(nombreUsuario);

                            // Asegurarse de que el usuario no sea null antes de autenticar
                            if (usuario != null) {
                                var authentication = new UsernamePasswordAuthenticationToken(
                                        usuario,
                                        null,
                                        usuario.getAuthorities() // Asegura que las autoridades están configuradas
                                );
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                            }
                        }
                    }

            }
        } catch (Exception e) {
            // Capturar cualquier excepción y loguear el error
            System.err.println("Error procesando el token JWT: " + e.getMessage());
        }

        // Continuar con el siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

}
