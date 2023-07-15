package com.HyperSync.hypersync;

public class Employee {
    private String firstname,lastname,phone,email,id,admin;

    public Employee(){};

    public Employee(String firstname, String lastname, String phone, String email, String id, String admin) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.admin = admin;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() { return email; }

    public String getId() { return id; }

    public String getAdmin() { return admin; }
}
