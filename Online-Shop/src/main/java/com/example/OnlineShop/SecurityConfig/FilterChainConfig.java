package com.example.OnlineShop.SecurityConfig;

import java.io.PrintWriter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;



@Configuration
@EnableWebSecurity
public class FilterChainConfig {
	  private UserDetailsConfig useDetailsService;
	    
	    @Autowired
		public FilterChainConfig(UserDetailsConfig useDetailsService) {
			this.useDetailsService = useDetailsService;
		}


		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
	    	
	    	http
	    	.cors(Customizer.withDefaults())
	    	.csrf(AbstractHttpConfigurer::disable)	
	    	
	    	.authorizeHttpRequests(auth->{
	    		auth.requestMatchers("/","/home", "/register","/login","/login?error=true",
	    							"/categories","/filter","/filter/count","/product",
	    							"/product/review").permitAll()
	    		.requestMatchers(HttpMethod.OPTIONS).permitAll()
	    		.anyRequest().authenticated();
	    		})

	    		
	    	.formLogin(formLogin ->
			formLogin
				
				.loginProcessingUrl("/login")
				.usernameParameter("login")
				.passwordParameter("password")
				.failureHandler((request, response, exception) -> {
		            response.setStatus(HttpServletResponse.SC_OK); 
		            response.setContentType("application/json;charset=UTF-8"); 
		       
		            PrintWriter writer = response.getWriter();
		            writer.write("{ \"error\": true, \"message\": \"Authentication failed\" }");
		            writer.flush();
		        })
				.successHandler((request, response, authentication) -> {
		            response.setStatus(HttpServletResponse.SC_OK); 
		            response.setContentType("application/json;charset=UTF-8"); 

		           
		            PrintWriter writer = response.getWriter();
		            writer.write("{ \"success\": true, \"message\": \"Authentication successful\" }");
		            writer.flush();
		        })
				.permitAll()
				
		)
		.logout(logout ->
			logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.permitAll()
		);
	
	    	
	    	return http.build();
	    };
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(16);
	    }
	    public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    	builder.userDetailsService(useDetailsService).passwordEncoder(passwordEncoder());
	    }
	    
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.addAllowedOrigin("http://localhost:3000");
	        configuration.addAllowedHeader("*");
	        configuration.addAllowedMethod("*");
	        configuration.setAllowCredentials(true);
	      
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);

	        return source;
	    }
}
