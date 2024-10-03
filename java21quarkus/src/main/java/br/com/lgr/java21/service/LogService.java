package br.com.lgr.java21.service;

import br.com.lgr.java21.domain.exception.RestClientCommunitacionException;
import br.com.lgr.java21.domain.record.LogEntityRecord;
import br.com.lgr.java21.domain.record.LogRecord;
import br.com.lgr.java21.repostiory.http.LogRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LogService {

    @Inject
    @RestClient
    LogRepository logRepository;

    public LogEntityRecord create(LogRecord logRecord) {
        try {
            return logRepository.create(logRecord);
        } catch (ResteasyWebApplicationException rwae) {
            throw new BadRequestException(rwae.unwrap().getResponse());
        }
    }

    public LogEntityRecord update(UUID id, LogRecord logRecord) {
        try {
            return logRepository.update(id, logRecord);
        } catch (ResteasyWebApplicationException rwae) {
            if (rwae.getResponse().getStatus() == 404) {
                throw new NotFoundException(rwae.unwrap().getResponse());
            } else if (rwae.getResponse().getStatus() == 400) {
                throw new BadRequestException(rwae.unwrap().getResponse());
            } else {
                throw new RestClientCommunitacionException();
            }
        }
    }

    public void delete(UUID id) {
        try {
            logRepository.delete(id);
        } catch (ResteasyWebApplicationException rwae) {
            if (rwae.getResponse().getStatus() == 404) {
                throw new NotFoundException(rwae.unwrap().getResponse());
            } else if (rwae.getResponse().getStatus() == 400) {
                throw new BadRequestException(rwae.unwrap().getResponse());
            } else {
                throw new RestClientCommunitacionException();
            }
        }
    }

    public LogEntityRecord findById(UUID id) {
        try {
            return logRepository.findById(id);
        } catch (ResteasyWebApplicationException rwae) {
            if (rwae.getResponse().getStatus() == 404) {
                throw new NotFoundException(rwae.unwrap().getResponse());
            } else if (rwae.getResponse().getStatus() == 400) {
                throw new BadRequestException(rwae.unwrap().getResponse());
            } else {
                throw new RestClientCommunitacionException();
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
