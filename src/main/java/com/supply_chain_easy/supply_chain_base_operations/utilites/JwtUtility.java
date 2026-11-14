package com.supply_chain_easy.supply_chain_base_operations.utilites;

import com.supply_chain_easy.supply_chain_base_operations.constants.SystemConstant;
import com.supply_chain_easy.supply_chain_base_operations.models.Role;
import com.supply_chain_easy.supply_chain_base_operations.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JwtUtility{

    private final Key key;

    public JwtUtility(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(User user){
        String userEmail= user.getEmail();
        List<String> roleNames = user.getRoles().stream().map(Role::getRoleName).toList();

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", userEmail);
        claims.put("roles", roleNames);

        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis()+ SystemConstant.JWT_EXPIRATION_TIME))
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }
}
