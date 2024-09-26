package br.com.lgr.java21.repository;

import br.com.lgr.java21.domain.GroupEntity;
import br.com.lgr.java21.domain.record.UserRecord;
import br.com.lgr.java21.repostiory.GroupRepository;
import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@Mock
@ApplicationScoped
public class MockGroupRepository extends GroupRepository {

    @Override
    public Optional<GroupEntity> findByName(String name) {
        if (name.equalsIgnoreCase("grupo02")) return Optional.empty();
        GroupEntity group = new GroupEntity();
        group.name = name;
        group.groupId = UUID.randomUUID();
        return Optional.of(group);

    }

    @Override
    public GroupEntity create(String name) {

        GroupEntity group = new GroupEntity();
        group.name = name;
        group.groupId = UUID.randomUUID();

        return group;
    }

    @Override
    public Optional<GroupEntity> findOrCreate(UserRecord userRecord) {
        if (userRecord.groupName() == null)
            return Optional.empty();

        return Optional.of(new GroupEntity(UUID.randomUUID(), userRecord.groupName()));
    }
}
