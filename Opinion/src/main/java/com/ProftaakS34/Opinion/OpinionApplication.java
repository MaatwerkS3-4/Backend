package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.domain.service.UserService;
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
	CommandLineRunner runner(DiscussionService discussionService, UserService userService, CommentService commentService){
		return args -> {
			User user = userService.saveUser("Potatoman", "Yes");
			User user2 = userService.saveUser("Potatoman2", "Yes2");
			User user3 = userService.saveUser("Potatoman3", "Yes3");

			Discussion discussion = discussionService.postDiscussion(user.getId(), "Potatoes", "Test description");
			Discussion discussion2 = discussionService.postDiscussion(user2.getId(), "Beans", "Test description 2");
			Discussion discussion3 = discussionService.postDiscussion(user3.getId(), "Hmmm", "Test description 3");
			discussionService.postComment(discussion.getId(), user.getId(), "I like potatoes");
			discussionService.postComment(discussion.getId(), user2.getId(), "I like potatoes2");
			discussionService.postComment(discussion2.getId(), user3.getId(), "I like potatoes3");
			discussionService.postComment(discussion2.getId(), user.getId(), "I like potatoes4");
			discussionService.postComment(discussion2.getId(), user2.getId(), "I like potatoes5");
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
