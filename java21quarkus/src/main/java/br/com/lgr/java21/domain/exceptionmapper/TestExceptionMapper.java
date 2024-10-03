package br.com.lgr.java21.domain.exceptionmapper;

import br.com.lgr.java21.domain.exception.TestException;
import br.com.lgr.java21.domain.exception.UserNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TestExceptionMapper implements ExceptionMapper<TestException> {

    @Override
    public Response toResponse(TestException e) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Test exception").build();
    }

}
