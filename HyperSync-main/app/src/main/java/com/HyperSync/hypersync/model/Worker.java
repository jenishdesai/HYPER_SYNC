package com.HyperSync.hypersync.model;

public class Worker {
    private String email,id,company,worker;

    public Worker() {
    }

    public Worker(String email, String id, String company, String worker) {
        this.email = email;
        this.id = id;
        this.company = company;
        this.worker = worker;
    }

    public String getEmail() { return email; }

    public String getId() { return id; }

    public String getCompany() { return company; }

    public String getWorker() { return worker; }
}
