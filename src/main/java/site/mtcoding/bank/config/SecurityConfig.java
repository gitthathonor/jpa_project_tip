package site.mtcoding.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import site.mtcoding.bank.config.enums.UserEnum;
import site.mtcoding.bank.handler.LoginHandler;

// SecurityFilterChain
@Configuration
public class SecurityConfig {

    // configuration파일에서는 생성자 주입 X, @Autowired를 통해서 사용해라
    @Autowired
    private LoginHandler loginHandler;

    // 스프링 시큐리티에서 로그인 시에 패스워드를 해시로 변환해주기 위해서 필요. 시큐리티 쓸거면 무조건 붙여야 한다.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable(); // csrf란? 쿠키랑 비슷하게 이 유저가 사이트가 원하는 방법으로 제대로 들어온 것인지를 구분하는 어떤 값을 통해서 확인하는 정책.
        // 포스트맨 테스트 시에 문제가 되기 때문에 disable() 걸어놓는다.

        http.authorizeRequests()
                .antMatchers("/api/transaction/**").authenticated()
                .antMatchers("/api/user/**").authenticated()
                .antMatchers("/api/account/**").authenticated()
                .antMatchers("/api/admin/**").hasRole("ROLE_" + UserEnum.ADMIN) // hasRole()이 새로 생겼다.
                .anyRequest().permitAll()
                .and()
                .formLogin() // x-www-urlencoded(post)으로 날려야 함
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/api/login")
                .successHandler(loginHandler)
                .failureHandler(loginHandler);

        return http.build();
    }

}
