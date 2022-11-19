package com.abtesting.rbac.service;

import com.abtesting.rbac.entity.User;
import com.abtesting.rbac.exceptions.NonRetryableException;
import com.abtesting.rbac.model.jwt.JwtRequest;
import com.abtesting.rbac.model.jwt.JwtResponse;
import com.abtesting.rbac.utils.StringUtils;
import com.abtesting.rbac.validators.CommonValidators;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.abtesting.rbac.exceptions.StaticErrorsEnum.INVALID_TOKEN;
import static com.abtesting.rbac.exceptions.StaticErrorsEnum.JWT_EXPIRED;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.token.ttl}")
    private Integer JWT_TOKEN_TTL;

    /**
     * generates JWT Token
     *
     * @param tokenPayload
     * @return JwtResponse
     */
    public JwtResponse generateToken(JwtRequest tokenPayload) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> claims = objectMapper.convertValue(tokenPayload, Map.class);
        return new JwtResponse(createToken(claims, tokenPayload.getUserId()));
    }

    /**
     * checks if token is valid or not, if valid return true else false,
     *
     * @param token, userId
     * @return Boolean
     */
    public Boolean validateToken(String token, String userId) {
        CommonValidators.validateUserId(userId);
        return StringUtils.equals(userId, extractUserId(token)) && !isTokenExpired(token) && validateTokenFromDB(token, userId);
    }

    /**
     * generates token based on claims and userId
     *
     * @param claims
     * @param subject
     * @return String
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_TTL))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * @param token
     * @return String
     */
    public String extractUserId(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (SignatureException | MalformedJwtException err) {
            throw new NonRetryableException(INVALID_TOKEN);
        } catch (ExpiredJwtException err) {
            throw new NonRetryableException(JWT_EXPIRED);
        }
    }

    /**
     * checks if token is expired or not
     *
     * @param token
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * @param token
     * @return {expiration Date of the token}
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return {value of the claim asked}
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * @param token
     * @return {body of the token}
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Validates token with Db
     *
     * @param userId
     * @param token
     * @return Boolean
     */
    private Boolean validateTokenFromDB(String token, String userId) {
        User user = CommonValidators.validateUserIdAndGetUserFromDb(userId);
        if (user.getAccess() != null && StringUtils.equals(user.getTokenFromAccess(), token)) {
            return true;
        }
        return false;
    }

    /**
     * accepts token and returns list of access assigned to that user
     *
     * @param token (String)
     * @return List<String>
     */
    public List<String> getAccessListFromToken(String token) {
        return this.extractClaim(token, claims -> claims.get("accessValues", List.class));
    }
}