package net.javaguides.springboot_search_rest_api.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username) {
        return Jwts.builder() // 返回一个 JWT Builder 对象
                .setSubject(username) // 设置 JWT 的主题（subject）。 这里通常把用户名放进去
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key) //给 JWT 签名。 //key 是密钥  用于生成数字签名    防止 token 被篡改
                .compact(); // 编码 header   编码 payload    生成 signature  拼接成最终字符串
    }

    public static String parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }



    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = parseToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) //设置验证签名用的密钥
                .build()
                .parseClaimsJws(token) //返回 ClaimsJws 对象  里面包含： header payload(claims)  signature
                .getBody() //获取 JWT 的 payload。
                .getExpiration();
    }
}

