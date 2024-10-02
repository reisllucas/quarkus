package br.com.lgr.java21.controller.message;

import br.com.lgr.java21.domain.TransactionEntity;
import br.com.lgr.java21.domain.exception.KafkaCommunitacionException;
import br.com.lgr.java21.domain.record.TransactionMessageRecord;
import br.com.lgr.java21.service.TransactionService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

public class TransactionMessageController {

    @Inject
    TransactionService transactionService;

    @Incoming("transaction-recive-channel")
    @Transactional
    public TransactionEntity reciveTransaction(TransactionMessageRecord transaction) {
        if (transaction.transction().dsTransaction().equalsIgnoreCase("error"))
            throw new KafkaCommunitacionException();

        return transactionService.create(transaction);

    }
}
