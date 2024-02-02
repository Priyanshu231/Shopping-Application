package com.bej.product.util;

import com.bej.product.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public String createToken(User user){
        // Create the token and add customer Id in the claims
        return null;

    }




}
