package br.com.lgr.java21.controller;

import br.com.lgr.java21.facade.TestFacade;
import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
@Authenticated
public class TestController {

    @Inject
    TestFacade testFacade;

    @POST
    @Path("/success")
    public void testSuccess() {
        testFacade.testSuccess();
    }

    @POST
    @Path("/fail")
    public void testFail() {
        testFacade.testFail();
    }

    @POST
    @Path("/fail/2")
    public void testFail2() {
        testFacade.testFail2();
    }

    @POST
    @Path("/fail/3")
    public void testFail3() {
        testFacade.testFail3();
    }

    @POST
    @Path("/fail/4")
    public void testFail4() {
        testFacade.testFail4();
    }

    @POST
    @Path("/fail/5")
    public void testFail5() {
        testFacade.testFail5();
    }

    @POST
    @Path("/fail/6")
    public void testFail6() {
        testFacade.testFail6();
    }

    @POST
    @Path("/fail/7")
    public void testFail7() {
        testFacade.testFail7();
    }

    @POST
    @Path("/fail/8")
    public void testFail8() {
        testFacade.testFail8();
    }

    @POST
    @Path("/fail/9")
    public void testFail9() {
        testFacade.testFail9();
    }

}
