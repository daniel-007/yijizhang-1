package cn.ahyc.yjz.config;
/**
 * SecurityConfiguration
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/25
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

/**
 * Created by sanlli on 15/9/25.
 */
@Configuration
public class SecurityConfiguration {

		@Bean
		public ApplicationSecurity applicationSecurity() {
				return new ApplicationSecurity();
		}

		@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
		protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

				@Autowired
				private DataSource dataSource;

				@Override
				protected void configure(HttpSecurity http) throws Exception {
						http.csrf().csrfTokenRepository(csrfTokenRepository());
						http.authorizeRequests().antMatchers("/resources/**").permitAll()
									.anyRequest().fullyAuthenticated()
									.and().formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
									.and().logout().logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll()
									.and().sessionManagement().maximumSessions(10).expiredUrl("/login?expired");
				}

				@Override
				public void configure(AuthenticationManagerBuilder auth) throws Exception {
						auth.jdbcAuthentication().dataSource(this.dataSource);
				}

				private CsrfTokenRepository csrfTokenRepository() {
						HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
						repository.setSessionAttributeName("_csrf");
						return repository;
				}
		}
}