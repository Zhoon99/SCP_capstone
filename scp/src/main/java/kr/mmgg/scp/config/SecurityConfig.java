package kr.mmgg.scp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.mmgg.scp.config.oauth2.CustomOauth2UserService;

//https://ozofweird.tistory.com/entry/Spring-Boot-Spring-Boot-JWT-OAuth2-2
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomOauth2UserService customOauth2UserService;

	// 비밀번호 암호화 빈으로 등록
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/static/files/**"); // files에 있는 모든 파일들은 시큐리티 적용을 무시한다.
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); // 정적 리소스들에 대해서 시큐리티 적용 무시
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().disable()
				.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.logout().logoutSuccessUrl("/")
				.and()
				.oauth2Login()
				.userInfoEndpoint()
				.userService(customOauth2UserService);

	}
}
