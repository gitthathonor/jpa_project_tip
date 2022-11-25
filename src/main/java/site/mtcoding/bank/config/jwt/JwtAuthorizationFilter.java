package site.mtcoding.bank.config.jwt;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import site.mtcoding.bank.config.auth.LoginUser;
import site.mtcoding.bank.domain.user.User;
import site.mtcoding.bank.domain.user.UserRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
      UserRepository userRepository) {
    super(authenticationManager);
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    log.debug("디버그2 : doFilterInternal 호출됨");
    String header = request.getHeader(JwtProperties.HEADER_STRING);
    log.debug("디버그2 : header : " + header);
    if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
      log.debug("디버그2 : 토큰이 잘못됨. header가 없거나, Bearer없음");
      chain.doFilter(request, response);
      return;
    }
    String token = request.getHeader(JwtProperties.HEADER_STRING)
        .replace(JwtProperties.TOKEN_PREFIX, "");
    log.debug("디버그2 : token : " + token);

    try {
      String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
          .getClaim("username").asString();
      log.debug("디버그2 : 서명검증완료 username : " + username);

      Optional<User> userOP = userRepository.findByUsername(username);

      if (userOP.isPresent()) {
        log.debug("디버그2 : 해당 유저가 존재함");

        LoginUser loginUser = new LoginUser(userOP.get());

        User userPS = userOP.get();
        log.debug("디버그2 : DB username : " + userPS.getUsername());
        log.debug("디버그2 : DB password : " + userPS.getPassword());

        // 강제로 SecurityContextHolder에 토큰의 정보를 집어넣는 작업
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser,
            loginUser.getPassword(), loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.debug("디버그2 : 1");
        chain.doFilter(request, response);
        log.debug("디버그2 : 2");
        return;
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.debug("디버그2 : 3");
    }

    log.debug("디버그2 : 4");
  }
}
