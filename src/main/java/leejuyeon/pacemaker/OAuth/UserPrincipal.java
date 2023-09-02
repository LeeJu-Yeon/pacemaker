package leejuyeon.pacemaker.OAuth;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import leejuyeon.pacemaker.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class UserPrincipal implements OAuth2User {

  private User user;

  public UserPrincipal(User user) {
    this.user = user;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getName() {
    return user.getEmail();
  }

}
