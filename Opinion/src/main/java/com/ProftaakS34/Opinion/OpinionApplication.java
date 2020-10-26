package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.Data.Repositories.PostRepository;
import com.ProftaakS34.Opinion.Data.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpinionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PostRepository postRepository, UserRepository userRepository){
		return args -> {
			userRepository.save(new User("Potatoman", "Yes"));
			postRepository.save(new Post("Potatoes", userRepository.findById((long) 1.0).get()));
		};
	}
}
