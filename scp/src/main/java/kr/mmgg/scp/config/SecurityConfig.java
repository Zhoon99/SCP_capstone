package kr.mmgg.scp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import kr.mmgg.scp.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.anyRequest() // 어떤 요청도 다 허용
				.permitAll()
				// .and()
				// .formLogin() // 로그인페이지
				// .loginPage("/login")
				// .defaultSuccessUrl("/")
				// .loginProcessingUrl("/login") // PrincipalDetailsService << 설정 되면 자동으로 낚아챔
				// .and()
				// .logout()
				// .logoutSuccessUrl("/login")
				.and()
				.oauth2Login() // oauth2 로그인
				.loginPage("/login")
				.userInfoEndpoint()
				.userService(principalOauth2UserService);
		// .anyRequest().permitAll()
		// .and()
		// .formLogin()
		// .loginPage("/login")
		// .loginProcessingUrl("/login") // 시큐리티가 로그인을 낚아채는곳
		// .defaultSuccessUrl("/") // 로그인성공시 돌아가는 주소
	}
}
