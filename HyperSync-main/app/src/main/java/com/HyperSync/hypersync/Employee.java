package com.HyperSync.hypersync;

import java.io.Serializable;

public class Employee implements Serializable {
    private String firstname,lastname,phone,email,id,worker,userName,company;

    public Employee(){};

    public Employee(String firstname, String lastname, String phone, String email, String id, String worker,String company) {
        userName = firstname +" "+ lastname;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.worker = worker;
        this.company = company;
    }

    public String getPhone() { return phone; }

    public String getEmail() { return email; }

    public String getId() { return id; }

    public String getWorker() { return worker; }

    public String getUserName() { return userName; }

    public String getCompany() { return company; }
}
