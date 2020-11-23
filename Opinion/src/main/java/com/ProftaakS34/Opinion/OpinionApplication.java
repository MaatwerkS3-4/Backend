package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Data.Entities.Comment;
import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.Data.Repositories.CommentRepository;
import com.ProftaakS34.Opinion.Data.Repositories.PostRepository;
import com.ProftaakS34.Opinion.Data.Repositories.UserRepository;
import com.ProftaakS34.Opinion.REST.CORS.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
			userRepository.save(new User("Potatoman", "Yes"));
			postRepository.save(new Post("Potatoes", userRepository.findById((long) 1.0).get()));
			postRepository.save(new Post("Beans", userRepository.findById((long) 1.0).get()));
			commentRepository.save(new Comment("I like potatoes", userRepository.findById((long) 1.0).get(), postRepository.findById((long) 2.0).get()));
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
