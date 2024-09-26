package br.com.lgr.java21.repository;

import br.com.lgr.java21.domain.LogEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LogRepository implements PanacheRepositoryBase<LogEntity, UUID> {


    public List<LogEntity> findByDsLog(String dsLog) {

        if (dsLog == null || dsLog.equalsIgnoreCase(""))
            return findAll().list();

        String search = "%" + dsLog.toUpperCase() + "%";

        return list("upper(dsLog) like ?1", search);

    }
}
