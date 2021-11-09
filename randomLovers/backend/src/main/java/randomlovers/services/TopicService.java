package randomlovers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import randomlovers.persistence.dao.CommentDao;
import randomlovers.persistence.dao.TopicDao;
import randomlovers.persistence.model.Comment;
import randomlovers.persistence.model.Topic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TopicService {

    private TopicDao topicDao;
    private CommentDao commentDao;

    @Transactional
    public void addTopic(Topic topic) {
        topicDao.saveOrUpdate(topic);
    }

    public Topic getTopicById(Integer id) {
        return topicDao.findById(id);
    }

    public List<Topic> getAllTopics(){
        return topicDao.findAll();
    }

    @Transactional
    public void addOptionA(Integer id) {

        Topic topic = topicDao.findById(id);

        topic.setOptionA(topicDao.findById(id).getOptionA()+1);
        topic.setTotalVotes(topicDao.findById(id).getTotalVotes()+1);

        topicDao.saveOrUpdate(topic);
    }

    @Transactional
    public void addOptionB(Integer id) {

        Topic topic = topicDao.findById(id);

        topic.setOptionB(topicDao.findById(id).getOptionB()+1);
        topic.setTotalVotes(topicDao.findById(id).getTotalVotes()+1);

        topicDao.saveOrUpdate(topic);
    }

    @Transactional
    public void addComment(Integer id, Comment comment) {
        Topic topic = topicDao.findById(id);
        topicDao.saveOrUpdate(topic);
    }

    public List<Comment> getAllComments(Integer id) {

        List<Comment> commentList = commentDao.findAll();
        List<Comment> result = new LinkedList<>();
        for (Comment comment: commentList) {
            if(comment.getTopic().getId() == id){
                result.add(comment);
            }

        }
        return result;
    }

    //getters &6 setters
    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
