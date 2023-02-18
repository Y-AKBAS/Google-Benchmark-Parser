package com.yakbas.db.storage;

import com.yakbas.db.domain.CustomReportEntity;
import com.yakbas.db.repo.CustomReportRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomReportStorageService {

    private final CustomReportRepository repository;

    public CustomReportStorageService(CustomReportRepository repository) { this.repository = repository; }

    public void save(@NonNull CustomReportEntity entity) {
        repository.save(entity);
    }

    public List<CustomReportEntity> findByLike(final String likeString) {
        return repository.findByRequestNameLike(likeString);
    }

}
