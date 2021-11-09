package randomlovers.persistence.dao;

import org.springframework.stereotype.Repository;
import randomlovers.persistence.model.Comment;

@Repository
public class CommentDao extends GenericDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }

}
