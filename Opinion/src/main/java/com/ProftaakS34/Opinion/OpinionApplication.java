package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.domain.model.Comment;
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
			User user = userService.saveUser("test user 1", "Yes");
			User user2 = userService.saveUser("test user 2", "Yes2");
			User user3 = userService.saveUser("test user 3", "Yes3");

			Discussion discussion = discussionService.postDiscussion(user.getId(), "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "\n" +
					"\n" +
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec in convallis sapien, sit amet suscipit felis. Donec laoreet mauris vitae dolor egestas, vitae consequat arcu commodo. Vivamus laoreet mi id mauris suscipit condimentum. Morbi faucibus sed arcu sit amet convallis. Cras placerat sed odio et pharetra. Morbi vel lacus nisi. Phasellus cursus gravida orci vitae ullamcorper. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse vel vehicula lorem. Donec a sagittis arcu. Curabitur ut massa id nunc sodales ornare feugiat nec velit. Aenean posuere ultrices neque sed condimentum. Sed augue ante, ultrices vulputate ante eu, imperdiet auctor nulla.\n" +
					"\n" +
					"Aliquam orci dui, venenatis quis purus nec, auctor pellentesque nisl. Donec venenatis dolor in quam lobortis posuere. In consectetur diam non lectus malesuada pulvinar. Sed nec pellentesque nulla. Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas mattis metus in risus pulvinar luctus. Sed dignissim interdum pharetra. In vehicula sed nunc sit amet gravida. Mauris et tortor vel mi pretium pellentesque. Vivamus ut tortor vestibulum, aliquam erat non, dictum purus. Proin vel vestibulum augue. ");
			Discussion discussion2 = discussionService.postDiscussion(user2.getId(), "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur efficitur elit quis vestibulum consequat. Duis tincidunt pellentesque libero sed suscipit. Nullam nibh dolor, aliquam et interdum eget, iaculis eu arcu. Cras vitae dui leo. Donec elementum justo nisi, id viverra erat fringilla in. Phasellus et consectetur orci. Integer lobortis posuere congue. ");
			Discussion discussion3 = discussionService.postDiscussion(user3.getId(), "eque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit", "\n" +
					"\n" +
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vel lobortis eros. Morbi vestibulum ipsum tortor, at porta ligula volutpat in. Donec tincidunt enim a sapien aliquam semper. Donec egestas dolor quis convallis iaculis. Proin dolor nulla, imperdiet sit amet vulputate non, tempor nec justo. Sed et ex suscipit, blandit odio sed, mattis sem. Aliquam erat volutpat. Etiam non sem sed dui egestas porttitor vel sit amet massa. Ut rutrum ultrices volutpat. Curabitur sit amet dignissim ante, in posuere sapien. Integer sit amet augue varius, bibendum lacus eu, varius felis. Nulla sed magna tellus. Suspendisse in nisl in purus aliquam ornare. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Ut aliquam tempor efficitur.\n" +
					"\n" +
					"Vivamus sed elit eu ex efficitur dapibus. Cras et dolor quis sem aliquam pulvinar vitae auctor sapien. Nullam id efficitur quam. Praesent lectus justo, blandit et enim vel, volutpat porta dolor. Nulla et sem bibendum, tristique sapien sed, viverra est. Nulla molestie ornare ipsum eu rhoncus. Vestibulum bibendum metus sed est ullamcorper blandit. Quisque eget purus maximus, sagittis orci vel, congue nulla. Maecenas maximus facilisis tortor a aliquam. Mauris nec elit sagittis, convallis ante nec, ultricies neque. Donec vehicula interdum diam, dignissim accumsan lacus. In id tincidunt leo.\n" +
					"\n" +
					"Maecenas laoreet enim sed quam tincidunt efficitur. Quisque scelerisque neque sit amet nisi porta congue. Suspendisse egestas sem et pretium elementum. Nulla facilisi. Vivamus et mi tincidunt, ornare turpis laoreet, sodales felis. Phasellus efficitur tortor et lacus mattis, a eleifend eros fermentum. Nullam aliquam elit ut metus efficitur convallis. ");

			Comment comment = commentService.saveComment(discussion.getId(), user.getId(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus non tincidunt massa. Aenean mattis, ligula convallis blandit porttitor, nulla urna molestie tellus, ut euismod orci felis. ");
			Comment comment2 = commentService.saveComment(discussion.getId(), user2.getId(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus vel elementum neque. Donec. ");
			Comment comment3 = commentService.saveComment(discussion2.getId(), user3.getId(), "Lorem ipsum dolor sit. ");
			Comment comment4 = commentService.saveComment(discussion2.getId(), user.getId(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus iaculis vitae ante eu rhoncus. Mauris eget nunc orci. Ut malesuada blandit tristique. Sed a mauris ut massa varius suscipit at et. ");
			Comment comment5= commentService.saveComment(discussion2.getId(), user2.getId(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc tincidunt turpis. ");

			Comment reply = commentService.saveReply(discussion.getId(), user.getId(), comment2.getId(), "\n" +
					"\n" +
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec et turpis in risus ornare eleifend at id purus. Vivamus porttitor sem eros, id iaculis ante suscipit ac. Nam in maximus sem. Curabitur tincidunt id augue ut fringilla. Morbi sed mi rhoncus, volutpat sem eu, porttitor magna. Aliquam erat volutpat. Nulla ac risus elementum, bibendum neque ut, dignissim lacus. Aenean ut lectus eu diam facilisis pharetra a pharetra erat. Nam tempus neque sed risus volutpat, non commodo eros lacinia. Cras tincidunt est quis est faucibus, vitae congue velit pretium. Etiam vitae ultricies lacus. Pellentesque dolor urna, elementum sit amet viverra eu, finibus quis nulla.\n" +
					"\n" +
					"Nulla nunc purus, cursus id sagittis a, finibus a leo. Vivamus nec risus a leo posuere aliquam eget non massa. Vivamus eget iaculis nisi. Phasellus tincidunt consequat finibus. Sed ut consectetur lacus, vitae pharetra leo. Mauris et nisi in sapien vulputate lobortis. Praesent sagittis interdum lectus, non bibendum neque rutrum ut. Nullam neque nibh, lobortis ut mattis at, laoreet in est. Mauris quam justo, commodo ut eros nec, ultricies fermentum lacus. Praesent sapien nibh, placerat vitae iaculis quis, varius id leo. Pellentesque vel sollicitudin tortor, id ullamcorper turpis. Fusce sit amet auctor justo. Integer maximus a justo a egestas. Donec aliquet odio laoreet ligula rhoncus, nec volutpat risus laoreet. ");
			Comment reply2 = commentService.saveReply(discussion2.getId(), user3.getId(), comment3.getId(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum viverra urna quis ex sollicitudin porta. Sed pretium cursus fringilla. Donec ornare fringilla metus. In pulvinar erat at fermentum tristique. Morbi convallis lectus eget purus pretium pretium. Vestibulum rutrum purus non placerat imperdiet. Sed neque lorem, dapibus ut erat at, congue hendrerit dui. Fusce erat justo, mollis eget rhoncus id, luctus vitae libero. Praesent gravida est condimentum, dictum felis a, tristique risus. Vestibulum velit tortor, cursus a augue sit amet, porta pulvinar odio. Vestibulum urna tortor, maximus nec mattis id, dictum in quam. Aenean finibus sagittis dolor eget consequat. ");

			Comment reply3 = commentService.saveReply(discussion.getId(), user2.getId(), reply.getId(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
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
