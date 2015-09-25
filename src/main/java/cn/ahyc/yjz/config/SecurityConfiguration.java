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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by sanlli on 15/9/25.
 */
@Configuration
public class SecurityConfiguration extends WebMvcConfigurerAdapter {

      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
      }

      @Bean
      public ApplicationSecurity applicationSecurity() {
            return new ApplicationSecurity();
      }

      @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
      protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

            @Autowired
            private SecurityProperties security;

            @Autowired
            private DataSource dataSource;

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                  http.authorizeRequests().antMatchers("/resources/**").permitAll()
                          .anyRequest().fullyAuthenticated()
                          .and().formLogin().loginPage("/login")
                          .failureUrl("/login?error").permitAll();
            }

            @Override
            public void configure(AuthenticationManagerBuilder auth) throws Exception {
                  auth.jdbcAuthentication().dataSource(this.dataSource);
            }

      }


}
