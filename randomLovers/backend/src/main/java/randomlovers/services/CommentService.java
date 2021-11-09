package randomlovers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import randomlovers.persistence.dao.CommentDao;
import randomlovers.persistence.model.Comment;

@Service
public class CommentService {

    private CommentDao commentDao;

    @Transactional
    public void addLike(Integer id) {

        Comment comment = commentDao.findById(id);

        comment.setNumberLikes(commentDao.findById(id).getNumberLikes()+1);
        comment.setTotalPoints(commentDao.findById(id).getTotalPoints()+1);

        commentDao.saveOrUpdate(comment);
    }

    @Transactional
    public void addDislike(Integer id) {

        Comment comment = commentDao.findById(id);

        comment.setNumberDislikes(commentDao.findById(id).getNumberDislikes()+1);
        comment.setTotalPoints(commentDao.findById(id).getTotalPoints()+1);

        commentDao.saveOrUpdate(comment);
    }

    @Transactional
    public void addComment(Comment comment) {
        commentDao.saveOrUpdate(comment);

    }


    // getters && setters
    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

}
