package com.yakbas.util;

import com.yakbas.report.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportReader {

    private static final Logger logger = LoggerFactory.getLogger(ReportReader.class);
    private static final Path REPORT_PATH = Paths.get("src", "main", "resources", "report");

    public static List<Report> getReports() {
        try (Stream<Path> pathStream = Files.walk(REPORT_PATH)) {

            return pathStream.map(Path::toFile)
                .filter(file -> file.getName().startsWith("bench"))
                .map(file -> Jackson.fromJson(file, Report.class))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Exception occurred during report reading. Message: {}" + e.getMessage());
        }

        return Collections.emptyList();
    }
}
