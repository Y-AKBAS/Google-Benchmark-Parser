package com.yakbas.db.domain;

import com.yakbas.report.Benchmark;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = CustomReportEntity.NAME_CUSTOM_REPORTS)
public class CustomReportEntity {

    public static final String NAME_CUSTOM_REPORTS = "custom_reports_backup";
    public static final String NAME_REQUEST_NAME = "requestName";
    public static final String NAME_ITERATION = "iteration";
    public static final String NAME_REAL_TIME = "realTime";
    public static final String NAME_CPU_TIME = "cpuTime";
    public static final String NAME_MHZ_PER_CPU = "mhzPerCpu";
    public static final String NAME_SCHEME_TYPE = "schemeType";
    public static final String NAME_BATCHING_ENABLED = "batchingEnabled";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;

    @Column(name = NAME_REQUEST_NAME) private String a_requestName;
    @Column(name = NAME_ITERATION) private int b_iteration;
    @Column(name = NAME_REAL_TIME) private double c_realTime;
    @Column(name = NAME_CPU_TIME) private double d_cpuTime;
    @Column(name = NAME_MHZ_PER_CPU) private int e_mhzPerCpu;
    @Column(name = NAME_SCHEME_TYPE) private String f_schemeType;
    @Column(name = NAME_BATCHING_ENABLED) private Boolean g_batchingEnabled;

    public CustomReportEntity() {
    }

    public CustomReportEntity(Benchmark benchmark, int mhzPerCpu, String schemeType, Boolean batchingEnabled) {
        this.a_requestName = benchmark.getName();
        this.b_iteration = benchmark.getIterations();
        this.c_realTime = benchmark.getRealTime();
        this.d_cpuTime = benchmark.getCpuTime();
        this.e_mhzPerCpu = mhzPerCpu;
        this.f_schemeType = schemeType;
        this.g_batchingEnabled = batchingEnabled;
    }

    public long getId() {
        return id;
    }

    public String getRequestName() {
        return a_requestName;
    }

    public int getMhzPerCpu() {
        return e_mhzPerCpu;
    }

    public int getIteration() {
        return b_iteration;
    }

    public double getRealTime() {
        return c_realTime;
    }

    public double getCpuTime() {
        return d_cpuTime;
    }

    public String getSchemeType() {
        return f_schemeType;
    }

    public Boolean getBatchingEnabled() {
        return g_batchingEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomReportEntity that)) {
            return false;
        }
        return b_iteration == that.b_iteration && g_batchingEnabled == that.g_batchingEnabled &&
            a_requestName.equals(that.a_requestName) && Objects.equals(f_schemeType, that.f_schemeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a_requestName, b_iteration, f_schemeType, g_batchingEnabled);
    }
}
