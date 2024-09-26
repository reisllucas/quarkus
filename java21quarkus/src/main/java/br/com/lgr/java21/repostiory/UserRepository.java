package br.com.lgr.java21.repostiory;

import br.com.lgr.java21.domain.UserEntity;
import br.com.lgr.java21.domain.exception.UserNotFoundException;
import br.com.lgr.java21.domain.record.UserRecord;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {

    public UserEntity findUser(UUID userId) {
        UserEntity user = findById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return findAll()
                .page(page, pageSize)
                .list();
    }

    public List<UserRecord> findProjection() {

        return findAll().project(UserRecord.class).list();
    }

    public List<UserRecord> findAllHQL() {

        PanacheQuery<UserRecord> query = find("""
                select u.username as username,
                        g.name as groupName 
                from UserEntity u 
                left join GroupEntity g 
                 on u.group.groupId = g.groupId
                 order by u.username
                """)
                .project(UserRecord.class);

        return query.list();//
    }

}
