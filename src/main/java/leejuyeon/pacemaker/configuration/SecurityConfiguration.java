package leejuyeon.pacemaker.configuration;

import leejuyeon.pacemaker.OAuth.KakaoOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final KakaoOAuth2UserService kakaoOAuth2UserService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .authorizeHttpRequests((authorizeHttpRequests) ->
            authorizeHttpRequests
                .anyRequest().authenticated()
        )
        .oauth2Login((oauth2Login) ->
            oauth2Login
                .userInfoEndpoint((userInfoEndpoint) ->
                    userInfoEndpoint.userService(kakaoOAuth2UserService)
                )
        );

    return http.build();
  }

}
