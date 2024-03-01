package aopu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * JWT工具类
 */
public class JwtUtil {
    //设置有效期
    public static final long JWT_TTL = 60*60*1000*24L;
    //设置密钥明文
    public static final String JWT_KEY = "whc";

    /**
     *创建token
     * @param id
     * @param subject
     * @param ttlMills
     * @return
     */
    public static String createJWT (String id,String subject,Long ttlMills){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        if (ttlMills == null){
            ttlMills = JwtUtil.JWT_TTL;
        }
        long expMills = nowMills + ttlMills;
        Date expDate = new Date(expMills);
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)  //唯一的ID
                .setSubject(subject)  //主题 可以是JSON数据
                .setIssuer("whc")   // 签发者
                .setIssuedAt(now)   // 签发时间
                .signWith(signatureAlgorithm,secretKey)  // 使用HS256对称加密算法签名，第二个参数为密钥
                .setExpiration(expDate);    // 设置过期时间
        return builder.compact();

    }

    /**
     * 生成加密后的密钥SecretKey
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKeySpec key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

    /**
     * 解析
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
