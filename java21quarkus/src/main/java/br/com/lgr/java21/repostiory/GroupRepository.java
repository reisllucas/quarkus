package br.com.lgr.java21.repostiory;

import br.com.lgr.java21.domain.GroupEntity;
import br.com.lgr.java21.domain.record.UserRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class GroupRepository implements PanacheRepositoryBase<GroupEntity, UUID> {

    public Optional<GroupEntity> findByName(String name) {

        List<GroupEntity> ge = find("name", name).list();
        return ge.stream().findFirst();

    }

    public GroupEntity create(String name) {

        GroupEntity group = new GroupEntity();
        group.name = name;
        persist(group);

        return group;

    }

    public Optional<GroupEntity> findOrCreate(UserRecord userRecord) {
        if (userRecord.groupName() != null && !userRecord.groupName().isBlank()) {

            Optional<GroupEntity> group = findByName(userRecord.groupName());
            if (!group.isPresent()) {
                group = Optional.of(create(userRecord.groupName()));
            }
            return group;

        }
        return Optional.empty();
    }

}
