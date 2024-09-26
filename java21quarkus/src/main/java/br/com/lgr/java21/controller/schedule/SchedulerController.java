package br.com.lgr.java21.controller.schedule;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ApplicationScoped
public class SchedulerController {

    Log LOG = LogFactory.getLog(SchedulerController.class);

    @Scheduled(every = "60s", identity = "scheduled-task-job")
    void schedule() {
        LOG.info("Scheduled run");
    }



}
