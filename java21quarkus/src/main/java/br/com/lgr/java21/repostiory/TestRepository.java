package br.com.lgr.java21.repostiory;

import br.com.lgr.java21.domain.TestEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TestRepository implements PanacheRepositoryBase<TestEntity, UUID> {

    public TestEntity create(String desc) {

        TestEntity testEntity = new TestEntity();
        testEntity.description = desc;

        persist(testEntity);

        return  testEntity;
    }

}
