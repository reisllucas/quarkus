package br.com.lgr.java21.repostiory;

import br.com.lgr.java21.domain.TransactionEntity;
import br.com.lgr.java21.domain.record.TransactionMessageRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<TransactionEntity, UUID> {

    private static final Log LOGGER = LogFactory.getLog(TransactionRepository.class);

    public TransactionEntity persist(TransactionMessageRecord transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.dsTransaction = transaction.transction().dsTransaction();
        transactionEntity.username = transaction.username();
        transactionEntity.dateTime = transaction.dateTime();

        persist(transactionEntity);

        LOGGER.info("Transaction saved");

        return transactionEntity;
    }
}
