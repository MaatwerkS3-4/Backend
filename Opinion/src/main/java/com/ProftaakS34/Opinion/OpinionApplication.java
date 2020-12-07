package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.CommentRepository;
import com.ProftaakS34.Opinion.data.repository.PostRepository;
import com.ProftaakS34.Opinion.data.repository.UserRepository;
import com.ProftaakS34.Opinion.web.api.cors.CORSFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpinionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository){
		return args -> {
			userRepository.save(new UserDAO("Potatoman", "Yes"));
			postRepository.save(new PostDAO("Potatoes", userRepository.findById((long) 1.0).get()));
			postRepository.save(new PostDAO("Beans", userRepository.findById((long) 1.0).get()));
			commentRepository.save(new CommentDAO("I like potatoes", userRepository.findById((long) 1.0).get(), postRepository.findById((long) 2.0).get()));
		};
	}

	@Bean
	public FilterRegistrationBean corsFilterRegistration() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CORSFilter());
		registrationBean.setName("CORS Filter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
