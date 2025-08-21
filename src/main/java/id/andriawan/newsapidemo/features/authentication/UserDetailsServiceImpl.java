package id.andriawan.newsapidemo.features.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserSecurity loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: get role here
        return userRepository.findByEmail(username)
                .map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
