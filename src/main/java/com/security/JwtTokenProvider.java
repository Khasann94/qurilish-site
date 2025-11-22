package com.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

/**
 * Util class that provides methods for generation, validation, etc. of JWT token.
 */

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    public boolean validateToken(String token, HttpServletRequest httpServletRequest) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Claims claims = jwsClaims.getBody();
            if (claims.get("ip") == null || (httpServletRequest.getLocalAddr() != null && !claims.get("ip").toString().equals(httpServletRequest.getLocalAddr())))
                return false;
//            if (claims.get("userAgent") == null || (httpServletRequest.getHeader("User-Agent") != null && !claims.get("userAgent").toString().equals(httpServletRequest.getHeader("User-Agent"))))
//                return false;
            return !claims.getExpiration().before(new Date());
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
            httpServletRequest.setAttribute("expired", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT com.exception");
        } catch (IllegalArgumentException ex) {
            System.out.println("Jwt claims string is empty");
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret); // Decode the Base64 secret
        return Keys.hmacShaKeyFor(keyBytes); // Generate the signing key
    }

    public String createToken(String username, List<String> roles, List<String> permissions, HttpServletRequest request) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", roles);
        claims.put("ip", request.getLocalAddr());
        claims.put("userAgent", request.getHeader("User-Agent"));
        claims.put("permissions", permissions);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setSubject(String.format("%s", username))//
                .setClaims(claims)//
                .setIssuer("VIRTUAL")//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, getSignKey())//
                .compact();
    }

}
