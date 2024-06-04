package application.config;

import application.entity.users.User;
import application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static application.utils.Constant.ERROR_USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException(ERROR_USER_NOT_FOUND);
        }
        return new UserDetailsImpl(user);
    }
}
