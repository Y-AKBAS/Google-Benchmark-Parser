package com.yakbas.report;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Context {
    Date date;
    String hostName;
    String executable;
    int numCpus;
    int mhzPerCpu;
    List<Cache> caches;
    String libraryBuildType;

    public Context() {
    }

    public Context(Date date, String hostName, String executable, int numCpus,
                   int mhzPerCpu, List<Cache> caches) {
        this.date = date;
        this.hostName = hostName;
        this.executable = executable;
        this.numCpus = numCpus;
        this.mhzPerCpu = mhzPerCpu;
        this.caches = caches;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLibraryBuildType() {
        return libraryBuildType;
    }

    public void setLibraryBuildType(String libraryBuildType) {
        this.libraryBuildType = libraryBuildType;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public int getNumCpus() {
        return numCpus;
    }

    public void setNumCpus(int numCpus) {
        this.numCpus = numCpus;
    }

    public int getMhzPerCpu() {
        return mhzPerCpu;
    }

    public void setMhzPerCpu(int mhzPerCpu) {
        this.mhzPerCpu = mhzPerCpu;
    }

    public List<Cache> getCaches() {
        return caches;
    }

    public void setCaches(List<Cache> caches) {
        this.caches = caches;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Context context)) return false;
        return getNumCpus() == context.getNumCpus() &&
               // getMhzPerCpu() == context.getMhzPerCpu() &&
                getHostName().equals(context.getHostName()) &&
                getExecutable().equals(context.getExecutable()) &&
                getCaches().equals(context.getCaches()) &&
                getLibraryBuildType().equals(context.getLibraryBuildType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
