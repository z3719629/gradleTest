package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/5/31.
 */
@Configuration
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId("my_rest_api").stateless(false);
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
//                .and().logout()
//                .logoutUrl("/oauth/logout")
//                .logoutSuccessHandler(customLogoutSuccessHandler)
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll();
                //.authenticated();
//                .formLogin()
//                .loginProcessingUrl("/lgn")
//                .defaultSuccessUrl("/home")
//                .failureForwardUrl("/lgn_error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**","/lgn", "/static/**").permitAll()
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(customAuthenticationEntryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/safe/**").authenticated();
        http
                .csrf().disable()
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers()
                .antMatchers("/test/**")
                .and()
                .authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                .antMatchers("/test/**").authenticated();//配置order访问控制，必须认证过后才可以访问
        // @formatter:on
    }
}
