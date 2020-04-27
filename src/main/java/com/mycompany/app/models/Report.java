package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.mycompany.app.utilities.UniqueIdGenerator;

public class Report {

    private int pid;
    private String name;

    public Report() {
    }

    public Report(int pid, String name) {
        this.pid = pid;
        this.name = name;
    }

    @JsonProperty("pid")
    public int getPid() {
        return this.pid;
    }

    public int setPid() {
        int id = UniqueIdGenerator.getUniqueID();
        this.pid = id;
        return pid;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + " pid='" + getPid() + "'" + ", name='" + getName() + "'" + "}";
    }
}