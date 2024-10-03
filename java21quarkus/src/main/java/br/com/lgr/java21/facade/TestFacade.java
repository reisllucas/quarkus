package br.com.lgr.java21.facade;

import br.com.lgr.java21.domain.exception.TestException;
import br.com.lgr.java21.service.TestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TestFacade {

    @Inject
    TestService testService;

    // salva na ordem T2 T1 T3
    @Transactional(Transactional.TxType.REQUIRED)
    public void testSuccess() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequiredNew("T2");

        testService.createTransactionRequired("T3");

    }

    // salva na ordem T2
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequiredNew("T2");

        testService.createTransactionRequiredException("T3");

    }

    // não salva
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail2() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequired("T2");

        testService.createTransactionRequiredException("T3");

    }

    // não salva
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail3() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequired("T2");

        testService.createTransactionRequiredNewException("T3");

    }

    // salva na ordem T2
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail4() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequiredNew("T2");

        testService.createTransactionRequiredNewException("T3");

    }

    // salva na ordem T2 T1
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail5() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequiredNew("T2");

        try {
            testService.createTransactionRequiredNewException("T3");
        } catch (TestException te) {
            System.out.println("err");
        }

    }

    // salva na ordem T2
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail6() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequiredNew("T2");

        try {
            testService.createTransactionRequiredException("T3");
        } catch (TestException te) {
            System.out.println("err");
        }

    }

    // salva na ordem T2 T1 t4
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail7() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequiredNew("T2");

        try {
            testService.createTransactionRequiredNewException("T3");
        } catch (TestException te) {
            System.out.println("err");
        }
        testService.createTransactionRequired("T4");
    }

    // salva na ordem T2
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail8() {

        testService.createTransactionRequired("T1");

        testService.createTransactionRequiredNew("T2");

        try {
            testService.createTransactionRequiredException("T3");
        } catch (TestException te) {
            System.out.println("err");
        }
        testService.createTransactionRequired("T4");
    }

    // salva na ordem T2
    @Transactional(Transactional.TxType.REQUIRED)
    public void testFail9() {
        createTransactionRequired("T1");
        createTransactionRequiredNew("T2");
        createTransactionRequiredException("T3");
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void createTransactionRequired(String desc) {
        testService.createNoTransaction(desc);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void createTransactionRequiredNew(String desc) {
        testService.createNoTransaction(desc);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void createTransactionRequiredException(String desc) {
        testService.createNoTransactionException(desc);
    }

}