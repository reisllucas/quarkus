package br.com.lgr.java21.service;

import br.com.lgr.java21.domain.record.LogEntityRecord;
import br.com.lgr.java21.domain.record.LogRecord;
import br.com.lgr.java21.repostiory.http.LogRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LogService {

    @Inject
    @RestClient
    LogRepository logRepository;

    public LogEntityRecord create(LogRecord record) {
        try {
            return logRepository.create(record);
        } catch (ClientWebApplicationException cwae) {
            throw new BadRequestException(cwae.getResponse());
        }

    }

    public LogEntityRecord update(UUID id, LogRecord record) {
        try {
            return logRepository.update(id, record);
        } catch (ClientWebApplicationException cwae) {
            if (cwae.getResponse().getStatus() == 404) {
                throw new NotFoundException(cwae.getResponse());
            } else if (cwae.getResponse().getStatus() == 400) {
                throw new BadRequestException(cwae.getResponse());
            } else {
                throw new RuntimeException("Error");
            }
        }
    }

    public void delete(UUID id) {
        try {
            logRepository.delete(id);
        } catch (ClientWebApplicationException cwae) {
            if (cwae.getResponse().getStatus() == 404) {
                throw new NotFoundException(cwae.getResponse());
            } else if (cwae.getResponse().getStatus() == 400) {
                throw new BadRequestException(cwae.getResponse());
            } else {
                throw new RuntimeException("Error");
            }
        }
    }

    public LogEntityRecord findById(UUID id) {
        try {
            return logRepository.findById(id);
        } catch (ClientWebApplicationException cwae) {
            if (cwae.getResponse().getStatus() == 404) {
                throw new NotFoundException(cwae.getResponse());
            } else if (cwae.getResponse().getStatus() == 400) {
                throw new BadRequestException(cwae.getResponse());
            } else {
                throw new RuntimeException("Error");
            }
        }
    }

    public List<LogEntityRecord> findByDsLog(String dsLog) {
        return logRepository.findByDsLog(dsLog);
    }

    public List<LogEntityRecord> findAll(String dsLog) {
        return logRepository.findAll(dsLog);
    }
}
