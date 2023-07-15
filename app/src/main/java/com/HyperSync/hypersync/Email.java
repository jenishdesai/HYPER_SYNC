package com.HyperSync.hypersync;

public class Email {
    private String email,id,company,admin;

    public Email() {
    }

    public Email(String email, String id, String company,String admin) {
        this.email = email;
        this.id = id;
        this.company = company;
        this.admin = admin;
    }

    public String getEmail() { return email; }

    public String getId() { return id; }

    public String getCompany() { return company; }

    public String getAdmin() { return admin; }
}
