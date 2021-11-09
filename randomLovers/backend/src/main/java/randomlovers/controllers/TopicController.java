package randomlovers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import randomlovers.persistence.model.Comment;
import randomlovers.persistence.model.Topic;
import randomlovers.services.TopicService;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/topics")
public class TopicController {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/all"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Topic>> listTopics() {
        return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/hot"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Topic>> listHotTopics() {
        List<Topic> topicsList = topicService.getAllTopics();
        Collections.sort(topicsList, Comparator.comparingInt(Topic::getTotalVotes).reversed());
        List<Topic> hotTopics = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            hotTopics.add(topicsList.get(i));
        }

        return new ResponseEntity<>(hotTopics, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> showTopic(@PathVariable Integer id) {

        Topic topic = topicService.getTopicById(id);
        if (topic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> showRandomTopic() {
        Integer id = (int) (Math.round((Math.random()*15)));
        Topic topic = topicService.getTopicById(id);

        if (topic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "like/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> addLike(@PathVariable Integer id){
        topicService.addOptionA(id);
        return new ResponseEntity<>(topicService.getTopicById(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "dislike/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> addDislike(@PathVariable Integer id){
        topicService.addOptionB(id);
        return new ResponseEntity<>(topicService.getTopicById(id),HttpStatus.OK);
    }

}
