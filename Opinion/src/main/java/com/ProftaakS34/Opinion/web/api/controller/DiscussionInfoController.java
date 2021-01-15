package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.authentication.AuthenticationService;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.web.api.dto.DiscussionInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/discussion_info")
@Api(tags = "DiscussionInfo")
public class DiscussionInfoController {

    private final DiscussionService discussionService;
    private final CommentService commentService;
    private final UserMapper userMapper;
    private final DiscussionMapper discussionMapper;
    private final AuthenticationService authenticationService;

    @Autowired
    public DiscussionInfoController(DiscussionService discussionService, CommentService commentService, UserMapper userMapper, DiscussionMapper discussionMapper, AuthenticationService authenticationService) {
        this.discussionService = discussionService;
        this.commentService = commentService;
        this.userMapper = userMapper;
        this.discussionMapper = discussionMapper;
        this.authenticationService = authenticationService;
    }

    /**
     * Retrieves info about all existing discussions and returns it as a {@link List< DiscussionInfoDTO >}
     *
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the info about found discussions {@link List< DiscussionInfoDTO >}
     */
    @ApiOperation(
            value = "Get info about all existing discussions"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - the list info about existing discussions has been returned")
    })
    @GetMapping
    private ResponseEntity<List<DiscussionInfoDTO>> getAllDiscussionInfos(HttpServletRequest request){
        String jwt = request.getHeader("Authorization");
        List<DiscussionInfoDTO> resource = new ArrayList<>();
        for(Discussion p : discussionService.findAllDiscussions()){
            resource.add(toDTO(p,jwt));
        }

        return ResponseEntity.ok(resource);
    }

    /**
     * Retrieves info about a discussion by it's id and returns it as a {@link DiscussionInfoDTO}
     *
     * @param discussionId The id of the discussion for which to retrieve info
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the found information {@link DiscussionInfoDTO}
     */
    @ApiOperation(
            value = "Get info about a specific discussion by it's id"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Ok - the info about the specific discussion has been returned")
    })
    @GetMapping("/id/{discussionId}")
    public ResponseEntity<DiscussionInfoDTO> getDiscussionInfoById(@PathVariable long discussionId, HttpServletRequest request){
        Discussion discussion = discussionService.findDiscussionById(discussionId);
        String jwt = request.getHeader("Authorization");
        DiscussionInfoDTO resource = toDTO(discussion, jwt);
        return ResponseEntity.ok(resource);
    }

    /**
     * Retrieves info about a discussion by it's subject and returns it as a {@link DiscussionInfoDTO}
     *
     * @param subject The subject of the discussion for which to retrieve info
     * @return The {@link ResponseEntity} with status {@code 200 (ok)} with as body the found information {@link DiscussionInfoDTO}
     */
    @ApiOperation(
            value = "Get info about a specific discussion by it's subject"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Ok - the info about the specific discussion has been returned")
    })
    @GetMapping("/subject/{subject}")
    public ResponseEntity<DiscussionInfoDTO> getDiscussionInfoById(@PathVariable String subject, HttpServletRequest request){
        Discussion discussion = discussionService.findDiscussionBySubject(subject);
        String jwt = request.getHeader("Authorization");
        DiscussionInfoDTO resource = toDTO(discussion, jwt);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/user/")
    public ResponseEntity<List<DiscussionInfoDTO>> getDiscussionInfosByPoster(HttpServletRequest request) {
        try {
            String jwt = request.getHeader("Authorization");
            long userId = Long.parseLong(authenticationService.getId(jwt));
            List<Discussion> discussions = discussionService.findDiscussionsByPoster(userId);
            List<DiscussionInfoDTO> discussionInfos = new ArrayList<>();
            for (Discussion discussion : discussions) {
                discussionInfos.add(toDTO(discussion, jwt));
            }
            return ResponseEntity.status(HttpStatus.OK).body(discussionInfos);
        } catch (Exception e ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private DiscussionInfoDTO toDTO(Discussion model, String jwt){

        int parCount = commentService.getNumberOfParticipantsByDiscussion(model);
        int comCount = model.getComments().size();
        int upvoteCount = model.getUpvoters().size();
        boolean userUpvoted = false;

        if (jwt!=null && !jwt.equals("null") && authenticationService.userLoggedIn(jwt)) {
            long id = Long.parseLong(authenticationService.getId(jwt));
            for (User upvoter : model.getUpvoters()) {
                if (upvoter.getId() == id) userUpvoted = true;
            }
        }

        return new DiscussionInfoDTO(
                model.getId(),
                model.getSubject(),
                userMapper.toDTO(model.getPoster()),
                parCount,
                comCount,
                model.getTimeStamp(),
                userUpvoted,
                upvoteCount,
                model.getTags());
    }
}
