package leejuyeon.pacemaker.configuration;

import leejuyeon.pacemaker.enums.Authority;
import leejuyeon.pacemaker.security.JWTAuthenticationFilter;
import leejuyeon.pacemaker.security.OAuth2SuccessHandler;
import leejuyeon.pacemaker.service.KakaoOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final KakaoOAuth2UserService kakaoOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JWTAuthenticationFilter JWTAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic((httpBasic) -> httpBasic.disable())   // 기본 인증은 사용 x -> OAuth & 토큰 인증 사용할거라
                .csrf((csrf) -> csrf.disable())   // Stateless 는 CSRF 보호가 필요 없다
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)   // 토큰은 세션 x
                )
                .oauth2Login((oauth2Login) ->
                        oauth2Login
                                .userInfoEndpoint((userInfoEndpoint) ->
                                        userInfoEndpoint.userService(kakaoOAuth2UserService)
                                )
                                .successHandler(oAuth2SuccessHandler)   // OAuth 인증 성공시 JWT 토큰 발급
                )
                .addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .anyRequest().hasAuthority(Authority.ROLE_USER.getRole())
                );

        return http.build();
    }

}
