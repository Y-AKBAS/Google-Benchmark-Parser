package com.yakbas.service;

import com.yakbas.db.domain.CustomReportEntity;
import com.yakbas.report.Benchmark;
import com.yakbas.report.Report;
import com.yakbas.util.Jackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomReportService {

    private static final Logger logger = LoggerFactory.getLogger(CustomReportService.class);

    private static final Path REPORT_PATH = Paths.get("src", "main", "resources", "report");
    private static final Path CUSTOM_REPORT_PATH = Paths.get("src", "main", "resources", "custom_report");
    private static final int DECIMAL_LENGTH = 3;

    public <T> void writeCustomReports(String fileName, T value) {
        Path filePath = Paths.get(CUSTOM_REPORT_PATH + "/" + fileName + ".wsd");
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Jackson.toJson(filePath.toFile(), value);
    }

    public Set<CustomReportEntity> readCustomReports() {
        try (Stream<Path> pathStream = Files.walk(REPORT_PATH)) {

            return pathStream.map(Path::toFile)
                .filter(file -> file.getName().startsWith("bench"))
                .map(CustomReportService::mapCustomReports)
                .reduce(new HashSet<>(), (f, s) -> {
                    f.addAll(s);
                    return f;
                });

        } catch (Exception e) {
            logger.error("Exception occurred during report reading. Message: {}", e.getMessage());
        }
        return Collections.emptySet();
    }

    private static Set<CustomReportEntity> mapCustomReports(File file) {
        String fileName = file.getName();
        String processedFileName = fileName.replace("'", "");
        String[] fileInfo = processedFileName.split("_");
        String schemeType = getSchemeType(Integer.parseInt(check(fileInfo[1]) ? fileInfo[1] : "-1"));
        Boolean batchingEnabled = check(fileInfo[2]) ? Boolean.parseBoolean(fileInfo[2]) : null;

        Optional<Report> report = Jackson.fromJson(file, Report.class);

        if (report.isPresent()) {
            int mhzPerCpu = report.get().getContext().getMhzPerCpu();
            return report.get()
                .getBenchmarks()
                .stream()
                .peek(clearRequestName())
                .peek(clearDecimals(DECIMAL_LENGTH))
                .map(benchmark -> new CustomReportEntity(benchmark, mhzPerCpu, schemeType, batchingEnabled))
                .collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    private static Consumer<Benchmark> clearRequestName() {
        return benchmark -> {
            String[] split = benchmark.getName().split("/");
            benchmark.setName(split[0]);
        };
    }

    private static Consumer<Benchmark> clearDecimals(int length) {
        return benchmark -> {
            double cpuTime = benchmark.getCpuTime();
            double realTime = benchmark.getRealTime();
            double pow = Math.pow(10.0, length);

            int trimmedCpuTime = (int)(cpuTime * pow);
            int trimmedRealTime = (int)(realTime * pow);

            double newCpuTime = (double)trimmedCpuTime / pow;
            double newRealTime = (double)trimmedRealTime / pow;

            benchmark.setCpuTime(newCpuTime);
            benchmark.setRealTime(newRealTime);
        };
    }

    private static boolean check(String input) {
        return !(input.equals("context") || input.equals("public") || input.startsWith("10") || input.startsWith("50"));
    }

    private static String getSchemeType(int num) {
        return switch (num) {
            case 1 -> SCHEME_TYPE.BFV.name();
            case 2 -> SCHEME_TYPE.CKKS.name();
            case 3 -> SCHEME_TYPE.BGV.name();
            default -> null;
        };
    }

    private enum SCHEME_TYPE {
        BFV,
        CKKS,
        BGV
    }
}
