package randomlovers.persistence.dao;

import org.springframework.stereotype.Repository;
import randomlovers.persistence.model.Topic;

@Repository
public class TopicDao extends GenericDao<Topic> {

    public TopicDao() {
        super(Topic.class);
    }

}
