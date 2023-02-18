package com.yakbas.initializer;

import com.yakbas.db.domain.CustomReportEntity;
import com.yakbas.db.storage.CustomReportStorageService;
import com.yakbas.service.CustomReportService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DbInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);
    private final CustomReportStorageService customReportStorageService;
    private final CustomReportService customReportService;

    public DbInitializer(CustomReportStorageService customReportStorageService,
                         CustomReportService customReportService
    ) {
        this.customReportStorageService = customReportStorageService;
        this.customReportService = customReportService;
    }

    @PostConstruct
    public void insertReports() {
        final Set<CustomReportEntity> entities = customReportService.readCustomReports();
        logger.info("Found {} entities", entities.size());
        entities.forEach(customReportStorageService::save);
        List<CustomReportEntity> byLike = customReportStorageService.findByLike("%BookOnPlatform%");
        logger.info("ByLike size: {}", byLike.size());
    }
}
