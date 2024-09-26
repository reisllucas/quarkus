package br.com.lgr.java21.repostiory.message;

import br.com.lgr.java21.domain.record.TransactionMessageRecord;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@ApplicationScoped
public class TransactionMessageRepository {

    @Channel("transaction-channel")
    Emitter<TransactionMessageRecord> transactionEmitter;
    Log LOG = LogFactory.getLog(TransactionMessageRepository.class);


    public void send(TransactionMessageRecord transaction) {

        transactionEmitter.send(transaction).toCompletableFuture().join();

    }

/*    public void sendWithResponse(TransactionRecord transactionRecord) {
        Uni.createFrom().completionStage(transactionEmitter.send(transactionRecord))
                .onItem().transform(obj -> {
                    System.out.println(obj);
                    LOG.info("sended ok transaction");
                    return Response.ok("response ok").build();
                })
                .onFailure().recoverWithItem(() -> {
                    LOG.info("sended error transaction");
                    return Response.status(403).build();
                }).await().indefinitely();
    }*/
}
