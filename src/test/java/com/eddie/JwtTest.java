package com.eddie;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void test() {

        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("id",1);
        claims.put("username","eddie");

        String token = JWT.create()
                .withClaim("user", claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//添加过期时间
                .sign(Algorithm.HMAC256("eddie"));//指定算法 配置密钥

        System.out.println(token);
    }


    @Test
    public void testParse(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImVkZGllIn0s" +
                "ImV4cCI6MTcyNTg0MjI3Mn0.cA0PG9k05bbVNNOatvpk7x4aryrJgVxffCYkb03ajZc";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("eddie")).build();

        DecodedJWT verify = jwtVerifier.verify(token);//验证token 生成解析后的JWT对象

        Map<String, Claim> claims = verify.getClaims();
        System.out.println(claims.get("user"));

    }
}
