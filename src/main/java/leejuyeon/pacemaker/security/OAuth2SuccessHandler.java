package leejuyeon.pacemaker.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leejuyeon.pacemaker.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        String token = tokenProvider.generateToken(user.getId(), user.getEmail(), user.getAuthority());

        response.addHeader("Authorization", "Bearer " + token);
        System.out.println(response.getHeader("Authorization"));
        System.out.println("토큰 넣었어요");

    }

}
