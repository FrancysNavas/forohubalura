package com.aluracursos.forohubalura.infra.security;

import com.aluracursos.forohubalura.domain.usuario.Usuario;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer("ForoH")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException( "Error al generar el  token jwt", exception);
        }
    }

    public String getSubject(String token) {
        System.out.println("Procesando token: " + token);
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = com.auth0.jwt.JWT.require(algorithm)
                    .withIssuer("ForoH")
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTCreationException exception) {
            System.out.println(exception.toString());
        }
        assert verifier != null;
        if(verifier.getSubject() == null){
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    public Instant generarFechaDeExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
