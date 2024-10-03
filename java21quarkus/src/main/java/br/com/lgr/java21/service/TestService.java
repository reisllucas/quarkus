package br.com.lgr.java21.service;

import br.com.lgr.java21.domain.exception.TestException;
import br.com.lgr.java21.repostiory.TestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TestService {

    @Inject
    TestRepository testRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public void createTransactionRequired(String desc) {
        testRepository.create(desc);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void createTransactionRequiredNew(String desc) {
        testRepository.create(desc);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void createTransactionRequiredException(String desc) {
        testRepository.create(desc);
        throw new TestException();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void createTransactionRequiredNewException(String desc) {
        testRepository.create(desc);
        throw new TestException();
    }

    public void createNoTransaction(String desc) {
        testRepository.create(desc);
    }

    public void createNoTransactionException(String desc) {
        testRepository.create(desc);
        throw new TestException();
    }

}
