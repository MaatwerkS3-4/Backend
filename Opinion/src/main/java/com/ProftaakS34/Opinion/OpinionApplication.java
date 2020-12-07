package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.CommentRepository;
import com.ProftaakS34.Opinion.data.repository.PostRepository;
import com.ProftaakS34.Opinion.data.repository.UserRepository;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PostService;
import com.ProftaakS34.Opinion.domain.service.UserService;
import com.ProftaakS34.Opinion.web.api.cors.CORSFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.io.Console;

@SpringBootApplication
public class OpinionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PostService postService, UserService userService, CommentService commentService){
		return args -> {
			User user = userService.saveUser("Potatoman", "Yes");
			User user2 = userService.saveUser("Potatoman2", "Yes2");
			User user3 = userService.saveUser("Potatoman3", "Yes3");

			Post post = postService.savePost(user.getId(), "Potatoes");
			Post post2 = postService.savePost(user2.getId(), "Beans");
			Post post3 = postService.savePost(user3.getId(), "Hmmm");
			postService.postComment(post.getId(), user.getId(), "I like potatoes");
			postService.postComment(post.getId(), user2.getId(), "I like potatoes2");
			postService.postComment(post2.getId(), user3.getId(), "I like potatoes3");
			postService.postComment(post2.getId(), user.getId(), "I like potatoes4");
			postService.postComment(post2.getId(), user2.getId(), "I like potatoes5");
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
