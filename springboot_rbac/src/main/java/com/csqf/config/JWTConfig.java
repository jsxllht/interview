package com.csqf.config;

import com.csqf.pojo.dto.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTConfig {

    @Value("${config.jwt.secret}")  //ashdjakhsdhaslkdhalsjdlasjdlaksjdlkasjdlasjdlkasdjlasjdaslkdjasl1
    private String secret;
    @Value("${config.jwt.expire}")  // 600
    private long expire;

    /**
     *  把指定的 UserDTO 对象 生成 token[jwt]
     */
    public  String generateJwt(LoginDTO member){

        // 加密
        byte[] keyBytes = secret.getBytes();
        // 获得密钥对象
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT") //令牌类型
                //.setHeaderParam("alg", "HS256") //签名算法
                .setIssuedAt(new Date()) //签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expire*1000)) //过期时间
                .claim("id", member.getId())
                .claim("userName", member.getUserName())
                .claim("avatarUrl", member.getAvatarUrl())
                .claim("roleid",member.getRoleid())
                .signWith(key, SignatureAlgorithm.HS256).compact();

        return token;
    }

    /**
     * 解析jwt
     * @param jwtToken
     * @return
     */
    public LoginDTO checkJwt(String jwtToken){

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(jwtToken);
        // map
        Claims claims = claimsJws.getBody();
        Long id = claims.get("id",Long.class);
        String userName = claims.get("userName",String.class);
        String avatarUrl = claims.get("avatarUrl",String.class);
        Integer roleid = claims.get("roleid",Integer.class);

        return LoginDTO.builder()
                .id(id)
                .roleid(roleid)
                .avatarUrl(avatarUrl)
                .userName(userName)
                .build();
    }



}

