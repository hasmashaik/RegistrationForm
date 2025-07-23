package com.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  //This tells Spring that the class contains bean definitions.
public class AppConfig {
	@Bean 
	//Spring to register this method’s return value as a Spring bean in the application context.That means it can be injected anywhere using @Autowired.
    public PasswordEncoder passwordEncoder() {//PasswordEncoder--->It’s an interface from Spring Security used to encode and verify passwords securely
        return new BCryptPasswordEncoder(); //BCryptPasswordEncoder()-Recommended for storing and verifying user passwords.
	}

}
/*Why is this important?
When users register or change their password, the application must encrypt their plain-text password before saving it to the database.

During login, the input password is again encoded and compared with the stored encoded password.

Spring Security will use this bean automatically if it's available in the context. 
 * */
