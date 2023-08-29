package leejuyeon.pacemaker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import leejuyeon.pacemaker.entity.User;
import leejuyeon.pacemaker.enums.Authority;
import leejuyeon.pacemaker.enums.Error;
import leejuyeon.pacemaker.exception.UserException;
import leejuyeon.pacemaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_AUTHORITY = "authority";
    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 30;   // 1000ms(1초) * 60초 * 60분 * 24시간 * 30일

    // 그런데 여기가 서비스 레이어가 아닌데, 이런식으로 리포지토리 가져다 사용해도 되나요??
    private final UserRepository userRepository;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    // 토큰 생성
    public String generateToken(Long id, String email, Authority authority) {

        Claims claims = Jwts.claims();
        claims.put(KEY_ID, id);
        claims.put(KEY_EMAIL, email);
        claims.put(KEY_AUTHORITY, authority.toString());

        Date issueDate = new Date();
        Date expirationDate = new Date(issueDate.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issueDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // 토큰 유효성 검증 & 파싱해서 Claim 정보 가져오기
    private Claims validateTokenAndGetClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new UserException(Error.INVALID_TOKEN);
        }
    }

    public String getUserEmail(String token) {
        return validateTokenAndGetClaims(token).get(KEY_EMAIL).toString();
    }

    public Authentication getAuthentication(String token) {
        User user = userRepository.findByEmail(getUserEmail(token))
                .orElseThrow(() -> new UserException(Error.USER_NOT_FOUND));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

}
