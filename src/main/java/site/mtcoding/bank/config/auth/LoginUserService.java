package site.mtcoding.bank.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import site.mtcoding.bank.config.exception.CustomApiException;
import site.mtcoding.bank.domain.user.User;
import site.mtcoding.bank.domain.user.UserRepository;

@Service
public class LoginUserService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 이 세션은 sessionScope.getAttribute로 찾을 수 없다.
        // 사용하지 않는 세션으로 두고, 내가 세션의 어떤 것이 필요하면 나만의 세션을 하나 만들어서 사용한다.(jsp)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.debug("디버그 : loadUserByUsername 실행됨");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomApiException("username을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST));
        return new LoginUser(user);
    }

}
