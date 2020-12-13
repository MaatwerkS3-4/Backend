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

			Discussion discussion = discussionService.postDiscussion(user.getId(), "Etiam luctus scelerisque purus, non venenatis urna. Nam efficitur ante et justo pharetra.... ",
					"Aenean elementum quam odio, eleifend auctor nunc mattis nec. Ut convallis porta laoreet. Integer consequat leo id orci gravida bibendum. Integer condimentum facilisis elit, a vulputate arcu vulputate eget. Maecenas id tempus tortor. Proin facilisis finibus est a bibendum. Integer quis nisi placerat, varius nibh sit amet, dictum mauris. Proin porttitor turpis eget urna tempor pretium.\n" +
					"\n" +
					"Nulla vitae sodales orci, quis congue risus. Phasellus nec urna purus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam consequat aliquet tempor. Nam condimentum justo dolor, id dignissim felis molestie in. Maecenas fermentum efficitur feugiat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");

			Discussion discussion2 = discussionService.postDiscussion(user2.getId(), "Vestibulum at risus felis. Donec a rutrum nisi. Ut vel fringilla lectus, consectetur posuere odio. Quisque hendrerit?", "Curabitur mauris turpis, varius eu condimentum nec, rhoncus quis tellus. Phasellus pellentesque risus nibh, ullamcorper tristique purus tincidunt non. Aliquam ac magna et sem luctus aliquam. Sed ac mattis ipsum. Quisque faucibus turpis neque, vitae scelerisque nisl consectetur ut. Ut aliquam eu est sit amet consequat. Sed gravida ex sit amet varius cursus. Praesent imperdiet porttitor felis, non ullamcorper purus. Curabitur dignissim dui nec nibh laoreet, ac varius magna varius. Praesent viverra neque a dolor dignissim efficitur. Nulla vulputate varius nibh nec finibus.");
			Discussion discussion3 = discussionService.postDiscussion(user3.getId(), "Etiam suscipit pretium quam, id euismod. ",
					"Nullam posuere mattis metus. Vivamus sit amet felis in metus gravida convallis vitae a lorem. Fusce id ligula vel mi iaculis consequat rutrum nec orci. Fusce egestas sagittis ornare. Curabitur at orci posuere turpis euismod cursus ac sit amet ante. Aliquam blandit lacus non suscipit pellentesque. Fusce egestas nisi ex, vel pulvinar magna porta sit amet. Maecenas scelerisque in mi a porttitor. Phasellus venenatis, ante a tempus ultricies, dui felis pellentesque dui, et rhoncus nisl ligula quis lacus. Suspendisse efficitur ligula eget mi varius imperdiet. Donec eu ornare libero, eget varius augue. Pellentesque porta porta maximus.\n" +
					"\n" +
					"Donec porttitor nunc sem, luctus blandit mi porttitor a. Cras ornare dictum dui, ut viverra ex posuere ac. Mauris rhoncus, turpis at fermentum pulvinar, quam libero tincidunt odio, vel pretium quam ipsum eu massa. Nam nec commodo mauris. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce ullamcorper molestie tellus, vel condimentum diam aliquet eu. Pellentesque lacus velit, pulvinar vel viverra eu, commodo nec orci. Nullam feugiat in lorem sit amet facilisis. Vivamus magna urna, elementum in tellus at, volutpat mollis nisi. Cras laoreet metus vitae tortor tristique tristique. Nulla ipsum purus, fringilla tristique mi et, efficitur viverra dolor. Fusce iaculis turpis eu tellus blandit sollicitudin. Suspendisse sem velit, maximus et massa sit amet, maximus mattis erat. Nunc augue lacus, egestas eu iaculis ac, aliquam sed elit.\n" +
					"\n" +
					"Pellentesque facilisis est sit amet erat cursus blandit. Duis vel pulvinar nunc. Ut sagittis nec mauris vitae pellentesque. Duis volutpat sem sit amet risus rhoncus, nec lacinia justo egestas. Duis consequat, nunc ac commodo rhoncus, dui ipsum imperdiet neque, ac bibendum lectus purus commodo quam. Pellentesque fringilla metus vel dignissim faucibus. Integer venenatis tellus at placerat gravida. Morbi lectus nunc, tempor et massa eget, auctor sagittis felis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus semper scelerisque mi a ornare. Suspendisse eget scelerisque felis, ac accumsan erat. Morbi sagittis efficitur orci, sed lacinia enim mattis in. ");

			Comment comment = commentService.saveComment(discussion.getId(), user.getId(), "Donec id tristique metus, in dignissim elit. Mauris mauris ex, pretium quis diam ac, faucibus commodo enim. Etiam efficitur posuere velit, sed efficitur ligula mattis sit amet. Curabitur.");
			Comment comment6 = commentService.saveComment(discussion.getId(), user.getId(), "Ut quis porta ex, in lacinia massa. Morbi iaculis nulla a turpis pulvinar, eu varius risus feugiat. Pellentesque eu sem et nulla pharetra imperdiet. Nullam convallis lacus.");
			Comment comment2 = commentService.saveComment(discussion.getId(), user2.getId(), "In nisi arcu, facilisis eu vestibulum in, imperdiet vel felis.");
			Comment comment7 = commentService.saveComment(discussion.getId(), user2.getId(), "Fusce condimentum, nibh eu volutpat facilisis, mi.");
			Comment comment3 = commentService.saveComment(discussion2.getId(), user3.getId(), "Suspendisse sed sem porta, molestie mauris in, rhoncus tortor. Etiam mattis semper sodales. Pellentesque vulputate, massa et ultricies molestie, felis mi condimentum massa, sed varius tellus mi eu eros. Maecenas efficitur interdum.");
			Comment comment4 = commentService.saveComment(discussion2.getId(), user.getId(), "Quisque lacinia libero sit amet augue egestas accumsan. Donec eget viverra ligula. Maecenas convallis dictum interdum. Sed accumsan accumsan blandit. Aliquam at dolor facilisis, commodo metus vitae, pulvinar orci. Aenean et eros at odio laoreet scelerisque eget ac justo. Nulla lacus.");
			Comment comment5 = commentService.saveComment(discussion2.getId(), user2.getId(), "Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. ");

			Comment reply = commentService.saveReply(discussion.getId(), user.getId(), comment2.getId(), "Maecenas urna erat, viverra ac gravida sit amet, porta nec ex. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis ut semper sapien. Proin mollis justo neque, nec volutpat leo pharetra vel. Curabitur leo felis, commodo nec nisi ac, laoreet bibendum ligula. Nulla ornare posuere orci. ");
			Comment reply2 = commentService.saveReply(discussion2.getId(), user3.getId(), comment3.getId(), "Curabitur ut tempus erat, vel dapibus enim. Quisque commodo. ");
			Comment reply3 = commentService.saveReply(discussion.getId(), user2.getId(), reply.getId(), "Quisque accumsan sit amet justo nec dignissim. Sed quis dui at arcu eleifend porta. Morbi ut sodales velit. Nulla mollis commodo auctor. Nullam.");

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
