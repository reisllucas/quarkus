package br.com.lgr.java21.domain.exception;

public class KafkaCommunitacionException extends RuntimeException{

    private static final String ERROR = "Kafka communication ERROR";

    public KafkaCommunitacionException() {
        super(ERROR);
    }
}
