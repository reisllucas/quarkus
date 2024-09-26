package br.com.lgr.java21.controller.message;

import br.com.lgr.java21.domain.record.TransactionMessageRecord;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class TransactionRecordDeserializer extends ObjectMapperDeserializer<TransactionMessageRecord> {

    public TransactionRecordDeserializer() {
        super(TransactionMessageRecord.class);
    }
}
