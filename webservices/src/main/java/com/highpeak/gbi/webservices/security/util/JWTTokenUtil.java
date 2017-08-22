/*
package com.highpeak.gbi.webservices.security.util;

import com.highpeak.gbi.webservices.security.JWTUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author sandesha, Created on 22/08/17
 *//*

@Component
public class JWTTokenUtil {

    static final String CLAIM_KEY_ID = "id";
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "iat";
    static final String CLAIM_KEY_EXPIRED = "exp";
    static final String CLAIM_KEY_AUTHORITIES = "authorities";
    static final String CLAIM_KEY_DEVICE_TOKEN = "jti";

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    static final Long expiration = 604800l;
    static final String secret = "halaMadrid";

    public String generateToken( UserDetails userDetails )
    {
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_KEY_USERNAME, ((JWTUser) userDetails).getMobileNumber());
        Calendar now = Calendar.getInstance();
        claims.put(CLAIM_KEY_CREATED, now.getTimeInMillis());
        //add 30 days of validity
        now.add(Calendar.DATE, 30);
        claims.put(CLAIM_KEY_EXPIRED, now.getTimeInMillis());

        return doGenerateToken(claims);
    }

    private String doGenerateToken( Map<String, Object> claims )
    {
        final Date createdDate = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

        return Jwts.builder().setClaims(claims).setIssuedAt(createdDate)
                .setExpiration(new Date((Long) claims.get(CLAIM_KEY_EXPIRED)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getUsernameFromToken( String token )
    {
        String username;
        try
        {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }
        catch( Exception e )
        {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken( String token )
    {
        Claims claims;
        try
        {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }
        catch( Exception e )
        {
            claims = null;
        }
        return claims;
    }
    public Date getCreatedDateFromToken( String token )
    {
        Date created;
        try
        {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        }
        catch( Exception e )
        {
            created = null;
        }
        return created;
    }

    private Boolean isTokenExpired( String token )
    {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public Date getExpirationDateFromToken( String token )
    {
        Date expiration;
        try
        {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        }
        catch( Exception e )
        {
            expiration = null;
        }
        return expiration;
    }

    public Boolean validateToken( String token, UserDetails userDetails )
    {
        JWTUser user = (JWTUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}
*/
