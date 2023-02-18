package com.yakbas.report;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Report {
    private Context context;
    private List<Benchmark> benchmarks;
    private static int reportCount;

    public Report() {
        reportCount++;
    }

    public Report(Context context, List<Benchmark> benchmarks) {
        this.context = context;
        this.benchmarks = benchmarks;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Benchmark> getBenchmarks() {
        return benchmarks;
    }

    public void setBenchmarks(List<Benchmark> benchmarks) {
        this.benchmarks = benchmarks;
    }

    @JsonIgnore
    public static int getReportCount() {
        return reportCount;
    }
}
