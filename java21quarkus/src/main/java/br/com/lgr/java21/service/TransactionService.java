package br.com.lgr.java21.service;

import br.com.lgr.java21.domain.TransactionEntity;
import br.com.lgr.java21.domain.record.TransactionMessageRecord;
import br.com.lgr.java21.repostiory.TransactionRepository;
import br.com.lgr.java21.repostiory.message.TransactionMessageRepository;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@ApplicationScoped
@RunOnVirtualThread
public class TransactionService {

    @Inject
    TransactionMessageRepository transactionMessageRepository;

    @Inject
    TransactionRepository transactionRepository;

    private static final Log LOGGER = LogFactory.getLog(TransactionService.class);

    public TransactionMessageRecord send(TransactionMessageRecord transactionRecord) {

        LOGGER.info(">>>> thread name: " + Thread.currentThread().getName());

        transactionMessageRepository.send(transactionRecord);

        return transactionRecord;
    }

    public TransactionEntity create(TransactionMessageRecord transactionRecord) {

        return transactionRepository.persist(transactionRecord);
    }
}
