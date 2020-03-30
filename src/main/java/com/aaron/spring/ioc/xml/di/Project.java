package com.aaron.spring.ioc.xml.di;

import java.util.*;

public class Project {

    private String name;

    private String type;

    private List<Worker> workerList = new ArrayList<Worker>();

    private Set<Worker> workerSet;

    private Worker[] workers;

    public Set<Worker> getWorkerSet() {
        return workerSet;
    }

    public void setWorkerSet(Set<Worker> workerSet) {
        this.workerSet = workerSet;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void setWorkers(Worker[] workers) {
        this.workers = workers;
    }

    public Map<String, Worker> getWorkerMap() {
        return workerMap;
    }

    public void setWorkerMap(Map<String, Worker> workerMap) {
        this.workerMap = workerMap;
    }

    private Map<String,Worker> workerMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", workerList=" + workerList +
                ", workerSet=" + workerSet +
                ", workers=" + Arrays.toString(workers) +
                ", workerMap=" + workerMap +
                '}';
    }
}
