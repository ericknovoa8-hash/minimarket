package com.proyecto.Minimarket.service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JwtService {
    /**
     * Llaves secretas para generar la firma
     */
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    /**
     * Tiempo de expiracion del token en ms
     */
    @Value("${security.jwt.token-expiration}")
    private Long tokenExpiration;

    /**
     * Generamos firma secreta para generar el jwt
     */
    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE664.decode(secretKey);

    }
    /**
     * Genera un jwt
     * @param userId
     * @param rolId
     * @param userName
     * @return String jwt
     */

    public String generateToken(Long userId, Long rolId, String userName) {
        return Jwts.builder()//se empieza a construir el jwt
                .claims(Map.of("userId", userId))

                .claims(Map.of("rolId", rolId))
                .subject(userName)//pertenese al token
                .issuedAt(new Date())// fcha de creacon
               .expiration(new Date(System.currentTimeMillis() + tokenExpiration))// Expiracion del token

               .signWith(getSigninKey())//Firma con llave
               .compact();//Construlle el string final
    }
    /**
     * valida si el tooken es valido y si a expirado
     * @param token
     * @return Boolean
     */
    public Boolean isTokenValid(String token){
        try{
            //El parser intenta descifrar la firma con nuestra llave secreta
            Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token);
            return true;

        }catch(JwtException e){
            log.error("Token is invalid: " + e.getMessage());
            return false;

        }catch(Exception e){
            log.error("Ocurrio un error inesperado: " + e.getMessage());
            return false;
        }
    }
        /**
         * Extrae todos los claims (payload) del token
         */
        public <T> T extractClaims(String token, Function<Claims, T> resolver){
            final Claims claims = Jwt.parser()
            .verifyWith(getSigninKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
            return resolver.apply(claims);
        

    }
    /**
     * 
     */
    public Long extractUserId(String token){
        return extractClaims(token, claims -> claims.get(claimName: "userId", requiretType: Long.class));

    }
    /**
     * Extraer el id del rol del user
     * 
     * 
     */


}
