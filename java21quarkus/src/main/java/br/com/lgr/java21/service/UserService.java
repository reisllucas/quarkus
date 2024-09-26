package br.com.lgr.java21.service;

import br.com.lgr.java21.domain.GroupEntity;
import br.com.lgr.java21.domain.UserEntity;
import br.com.lgr.java21.domain.exception.UserNotFoundException;
import br.com.lgr.java21.domain.record.UserRecord;
import br.com.lgr.java21.repostiory.GroupRepository;
import br.com.lgr.java21.repostiory.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    GroupRepository groupRepository;

    @Transactional
    public UserEntity create(UserRecord userRecord) {
        UserEntity userEntity = new UserEntity();
        userEntity.username = userRecord.username();
        if (userRecord.groupName() != null && !userRecord.groupName().isBlank()) {
            Optional<GroupEntity> group = groupRepository.findByName(userRecord.groupName());
            if (!group.isPresent()) {
                group = Optional.of(groupRepository.create(userRecord.groupName()));
            }
            userEntity.group = group.get();
        }
        userRepository.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return userRepository.findAll(page, pageSize);
    }

    public UserEntity findById(UUID userId) {

        return userRepository.findByIdOptional(userId).
                orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public UserEntity update(UUID userId, UserRecord userRecord) {

        UserEntity user = userRepository.findUser(userId);
        Optional<GroupEntity> group = groupRepository.findOrCreate(userRecord);
        if (group.isPresent()) {
            user.group = group.get();
        }
        user.username = userRecord.username();
        userRepository.persist(user);

        return user;

    }

    @Transactional
    public void delete(UUID userId) {

        var dbUser = findById(userId);
        userRepository.delete(dbUser);

    }

    public List<UserRecord> findAllProjection() {
        return userRepository.findProjection();
    }

    public List<UserRecord> findAllHQL() {
        return userRepository.findAllHQL();
    }
}
