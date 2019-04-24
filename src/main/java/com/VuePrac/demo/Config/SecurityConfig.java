package com.VuePrac.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//  允許所有用戶訪問"/"和"/index.html"
	     http.csrf().disable()
	     	.authorizeRequests()	     		  
	              .antMatchers("/login").permitAll()
	              .anyRequest().authenticated()   // 其他地址的訪問均需驗證權限
	              .and()
	              .formLogin()
	             .loginPage("/login") 
	             //.successHandler(new AuthenticationSuccessHandler())	             
	             .defaultSuccessUrl("/CheckBoard")	             
	             //.successForwardUrl("/InquireIndicator/MainPage")  //  登錄頁
	               .failureUrl("/login-error").permitAll()	               
	               .and()
	                .logout()
	                .permitAll()
	                 .logoutSuccessUrl("/login")
	                 .and().csrf().disable();
	}
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication()     // 驗證資訊存放於記憶體
            .passwordEncoder(pwdEncoder)
            .withUser("admin") 
                .password(pwdEncoder.encode("admin123"))
                .roles("ADMIN", "MEMBER")
            .and()
            .withUser("caterpillar")
                .password(pwdEncoder.encode("12345678"))
                .roles("MEMBER");
    }
}
