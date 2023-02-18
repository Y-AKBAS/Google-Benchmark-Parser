package com.yakbas.report;

import java.util.Objects;

public class Benchmark {
    String name;
    int familyIndex;
    int perFamilyInstanceIndex;
    String runName;
    String runType;
    int repetitions;
    int repetitionIndex;
    int threads;
    int iterations;
    double realTime;
    double cpuTime;
    String timeUnit;

    public Benchmark() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFamilyIndex() {
        return familyIndex;
    }

    public void setFamilyIndex(int familyIndex) {
        this.familyIndex = familyIndex;
    }

    public int getPerFamilyInstanceIndex() {
        return perFamilyInstanceIndex;
    }

    public void setPerFamilyInstanceIndex(int perFamilyInstanceIndex) {
        this.perFamilyInstanceIndex = perFamilyInstanceIndex;
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getRepetitionIndex() {
        return repetitionIndex;
    }

    public void setRepetitionIndex(int repetitionIndex) {
        this.repetitionIndex = repetitionIndex;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public double getRealTime() {
        return realTime;
    }

    public void setRealTime(double realTime) {
        this.realTime = realTime;
    }

    public double getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(double cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benchmark benchmark)) return false;
        return getFamilyIndex() == benchmark.getFamilyIndex() &&
                getPerFamilyInstanceIndex() == benchmark.getPerFamilyInstanceIndex() &&
                getRepetitions() == benchmark.getRepetitions() &&
                getRepetitionIndex() == benchmark.getRepetitionIndex() &&
                getThreads() == benchmark.getThreads() &&
                getIterations() == benchmark.getIterations() &&
                Double.compare(benchmark.getRealTime(), getRealTime()) == 0 &&
                Double.compare(benchmark.getCpuTime(), getCpuTime()) == 0 &&
                getName().equals(benchmark.getName()) &&
                getRunName().equals(benchmark.getRunName()) &&
                getRunType().equals(benchmark.getRunType()) &&
                getTimeUnit().equals(benchmark.getTimeUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFamilyIndex(), getPerFamilyInstanceIndex(), getRunName(), getRunType(), getRepetitions(), getRepetitionIndex(), getThreads(), getIterations(), getRealTime(), getCpuTime(), getTimeUnit());
    }
}
