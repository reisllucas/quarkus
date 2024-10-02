package br.com.lgr.java21.controller.schedule;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ApplicationScoped
public class SchedulerController {

    private static final Log LOGGER = LogFactory.getLog(SchedulerController.class);

    @Scheduled(every = "60s", identity = "scheduled-task-job")
    void schedule() {
        LOGGER.info("Scheduled run");
    }



}
