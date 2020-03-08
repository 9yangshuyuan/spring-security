package com.example.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","logout").permitAll()
                .antMatchers("/leve1/**").hasRole("vip1")
                .antMatchers("/leve2/**").hasRole("vip2")
                .antMatchers("/leve3/**").hasRole("vip3");
        // 定制登录页面
        http.formLogin();
        // 注销成功跳转页面
        http.logout().logoutSuccessUrl("/");
        // 创建cookie 默认2周
        http.rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //下面这两行配置表示在内存中配置了两个用户
        auth.inMemoryAuthentication()
                .withUser("admin").roles("vip1").password(passwordEncoder.encode("123"))
                .and()
                .withUser("root").roles("vip1","vip2","vip3").password(passwordEncoder.encode("123"));
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
