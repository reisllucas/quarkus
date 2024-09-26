package br.com.lgr.java21.repository;

import br.com.lgr.java21.domain.UserEntity;
import br.com.lgr.java21.domain.record.UserRecord;
import br.com.lgr.java21.repostiory.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mock
@ApplicationScoped
public class MockUserRepository extends UserRepository {

    @Override
    public void persist(UserEntity user) {
        user.userId = UUID.randomUUID();
        if (user.group != null) {
            user.group.groupId = UUID.randomUUID();
        }
    }

    @Override
    public List<UserEntity> findAll(Integer page, Integer pageSize) {

        return Arrays.asList(
                new UserEntity(UUID.randomUUID(),"usuario01"),
                new UserEntity(UUID.randomUUID(), "usuario02")
        );
    }

    @Override
    public List<UserRecord> findProjection() {

        return Arrays.asList(
                new UserRecord("usuario01", "groupo01"),
                new UserRecord("usuario02", "grupo02")
        );
    }

    @Override
    public List<UserRecord> findAllHQL() {

        return Arrays.asList(
                new UserRecord("usuario01", "groupo01"),
                new UserRecord("usuario02", "grupo02")
        );
    }

    @Override
    public Optional<UserEntity> findByIdOptional(UUID uuid) {

        if (uuid.equals(UUID.fromString("7f0455cc-b97f-4f60-abdb-8649576bee17")))
            return Optional.empty();

        return Optional.of(new UserEntity(uuid, "usuario01"));

    }

    @Override
    public UserEntity findUser(UUID userId) {
        UserEntity user = new UserEntity(userId, "usuario01");
        return user;
    }

    @Override
    public void delete(UserEntity user) {
        
    }
}
