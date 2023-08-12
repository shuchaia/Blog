package com.shuchaia.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName JwtUtil
 * @Description JWT 工具类
 * @Author shuchaia
 * @Date 2023/6/30 17:00
 * @Version 1.0
 */
public class JwtUtil {
    // Token的有效时间
    // 60 * 60 *1000 一个小时
    public static final Long JWT_TTL = 60 * 60 * 1000L;
    // 设置秘钥明文
    public static final String JWT_KEY = "diaock";

    /**
     * 生成 uuid
     *
     * @return uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成jwt
     *
     * @param subject token中要存放的数据（json格式，注意不要是私密信息）
     * @return jwt
     */
    public static String createJWT(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成jwt
     *
     * @param subject   token中要存放的数据（json格式，注意不要是私密信息）
     * @param ttlMillis token超时时间
     * @return jwt
     */
    public static String createJWT(String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    /**
     * @param subject   token中要存放的数据（json格式，注意不要是私密信息）
     * @param ttlMillis token超时时间
     * @param uuid      uuid
     * @return jwt
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) //唯一的id
                .setSubject(subject) // 主题 可以是JSON数据
                .setIssuer("sg") // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     *
     * @param id        用户ID
     * @param subject   token中要存放的数据（json格式，注意不要是私密信息）
     * @param ttlMillis token超时时间
     * @return jwt
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static void main(String[] args) throws Exception {
        String jwt = createJWT("123456");
        System.out.println(jwt);
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return secretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt 待解析的jwt
     * @return Claims类型的数据
     * @throws Exception 抛出异常
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}