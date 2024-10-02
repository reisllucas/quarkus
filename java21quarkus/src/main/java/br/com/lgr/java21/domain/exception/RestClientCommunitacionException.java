package br.com.lgr.java21.domain.exception;

public class RestClientCommunitacionException extends RuntimeException{

    private static final String ERROR = "RestClient Communication Error";

    public RestClientCommunitacionException() {
        super(ERROR);
    }
}
