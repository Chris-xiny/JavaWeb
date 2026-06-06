package com.ChrisXin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    // 原始密钥字符串 "Chrisxin" 的 Base64 编码结果
    private static final String BASE64_SECRET = "Q2hyaXN4aW5DaHJpc3hpbkRocmlzeGluQ2hyaXN4aW4=";
    // 有效期：1小时（单位：毫秒）
    private static final long EXPIRATION = 3600 * 1000L;

    // 将 Base64 字符串解码为字节数组，再生成签名密钥
    private static final SecretKey SECRET_KEY;

    static {
        byte[] decodedKey = Base64.getDecoder().decode(BASE64_SECRET);
        SECRET_KEY = Keys.hmacShaKeyFor(decodedKey);
    }

    /**
     * 生成 JWT 令牌
     * @param claims 要存储在 payload 中的自定义数据（如 userId, username 等）
     * @return 生成的令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + EXPIRATION);

        return Jwts.builder()
                .setClaims(claims)               // 设置自定义载荷
                .setIssuedAt(now)                // 签发时间
                .setExpiration(exp)              // 过期时间
                .signWith(SECRET_KEY, Jwts.SIG.HS256)  // 签名算法
                .compact();
    }

    /**
     * 解析 JWT 令牌，获取 Claims
     * @param token 令牌字符串
     * @return 解析成功的 Claims 对象
     * @throws io.jsonwebtoken.JwtException 如果令牌无效、过期或签名错误
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
