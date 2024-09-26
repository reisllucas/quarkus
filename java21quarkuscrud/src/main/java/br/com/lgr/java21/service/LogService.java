package br.com.lgr.java21.service;

import br.com.lgr.java21.domain.LogEntity;
import br.com.lgr.java21.domain.record.LogRecord;
import br.com.lgr.java21.repository.LogRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LogService {

    @Inject
    LogRepository logRepository;

    @Transactional
    public LogEntity create(LogRecord record) {

        if (record == null)
            throw new BadRequestException("Body should not be null");

        LogEntity log = new LogEntity();
        log.dsLog = record.dsLog();
        log.dateTime = ZonedDateTime.now();

        logRepository.persist(log);

        return log;
    }

    public LogEntity findById(UUID id) {
        return logRepository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public LogEntity update (UUID id, LogRecord record) {

        if (id == null )
            throw new BadRequestException("Id should not be null");

        if (record == null)
            throw new BadRequestException("Body should not be null");

        LogEntity log = findById(id);

        log.dsLog = record.dsLog();
        logRepository.persist(log);

        return log;

    }

    @Transactional
    public void delete(UUID id) {

        if (id == null )
            throw new BadRequestException("Id should not be null");

        LogEntity log = findById(id);

        logRepository.delete(log);
    }

    public List<LogEntity> findAll() {

        return logRepository.findAll()
                .list();
    }

    public List<LogEntity> findByDsLog(String dsLog) {
        return logRepository.findByDsLog(dsLog);
    }

}
