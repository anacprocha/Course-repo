package randomlovers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import randomlovers.persistence.model.Comment;
import randomlovers.persistence.model.Topic;
import randomlovers.services.CommentService;
import randomlovers.services.TopicService;
import randomlovers.services.UserService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;
    private TopicService topicService;
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> listAllComents(@PathVariable Integer id){
        return new ResponseEntity<>(topicService.getAllComments(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "like/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addLike(@PathVariable Integer id){
        commentService.addLike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "dislike/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addDislike(@PathVariable Integer id){
        commentService.addDislike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> addComment(@Valid @RequestBody String content, BindingResult bindingResult, @PathVariable Integer id){

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Comment newComment = new Comment();
        newComment.setTopic(topicService.getTopicById(id));
        newComment.setContent(content);
        newComment.setUser(userService.getUserById((int)(Math.random()*5+1)));

        topicService.addComment(id,newComment);
        commentService.addComment(newComment);

        return new ResponseEntity<>(topicService.getTopicById(id),HttpStatus.OK);
    }

    // getters && setters
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
