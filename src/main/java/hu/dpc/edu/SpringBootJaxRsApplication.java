package hu.dpc.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableWebSecurity
public class SpringBootJaxRsApplication {

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder
//				.inMemoryAuthentication()
//				.withUser("bela").password("cangetin").roles("USER", "MANAGER");
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJaxRsApplication.class, args);
	}
}
