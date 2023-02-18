package com.yakbas.report;

import java.util.Objects;

public class Cache {
    private String type;
    int level;
    int size;
    int numSharing;

    public Cache() {
    }

    public Cache(String type, int level, int size, int numSharing) {
        this.type = type;
        this.level = level;
        this.size = size;
        this.numSharing = numSharing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumSharing() {
        return numSharing;
    }

    public void setNumSharing(int numSharing) {
        this.numSharing = numSharing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cache cache)) return false;
        return getLevel() == cache.getLevel() &&
                getSize() == cache.getSize() &&
                getNumSharing() == cache.getNumSharing() &&
                getType().equals(cache.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLevel(), getSize(), getNumSharing());
    }
}
