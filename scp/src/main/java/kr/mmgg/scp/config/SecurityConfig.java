package kr.mmgg.scp.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kr.mmgg.scp.security.CustomUserDetailsService;
import kr.mmgg.scp.security.TokenAuthenticationFilter;
import kr.mmgg.scp.security.oauth2.CustomOAuth2UserService;
import kr.mmgg.scp.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import kr.mmgg.scp.security.oauth2.OAuth2AuthenticationFailureHandler;
import kr.mmgg.scp.security.oauth2.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;

//https://ozofweird.tistory.com/entry/Spring-Boot-Spring-Boot-JWT-OAuth2-2
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true) // 인가 처리 옵션 설정
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService;

	private final CustomOAuth2UserService customOAuth2UserService;

	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	// 비밀번호 암호화 빈으로 등록
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 토큰 필터 빈으로 등록
	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	// 구글 로그인에서 받아온 쿠키 저장
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	// 토큰으로 인증한 유저 정보 가져오기 위한 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/static/files/**"); // files에 있는 모든 파일들은 시큐리티 적용을 무시한다.
		web.ignoring().mvcMatchers("/controller/FileHandlingController.java"); // files에 있는 모든 파일들은 시큐리티 적용을 무시한다.
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); // 정적 리소스들에 대해서 시큐리티 적용 무시
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// csrf 비활성화
		http.csrf().disable();
		http
				// 토큰은 사용하기위해 sesseion 비활성화
				.cors()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				// 로그인폼 비활성화
				.formLogin().loginPage("/customlogin").permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/", "/test", "/topic/**", "/app/**", "/chat/**").permitAll()
				.antMatchers("/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				.antMatchers("/auth/**", "/oauth2/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.oauth2Login()
				.authorizationEndpoint()
				// 클라이언트 처음 로그인 시도 URI
				.baseUri("/oauth2/authorization")
				.authorizationRequestRepository(cookieAuthorizationRequestRepository())
				.and()
				.userInfoEndpoint()
				.userService(customOAuth2UserService)
				.and()
				.successHandler(oAuth2AuthenticationSuccessHandler)
				.failureHandler(oAuth2AuthenticationFailureHandler)
				.and()
				.httpBasic();
		// UsernamePasswordAuthenticationFilter 필터가 작동하기 전에 먼저
		// tokenAuthenticationFilter이 작동하도록 설정
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
