package com.HyperSync.hypersync;

public class Company {
    String firstname,lastname,userEmail,userPhone,CompContact,GST,designation,CompAddress;

    public Company() {}

    public Company(String firstname, String lastname, String userEmail, String userPhone, String CompContact,String GST,String designation,String CompAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.CompContact = CompContact;
        this.GST = GST;
        this.designation = designation;
        this.CompAddress = CompAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUserEmail() { return userEmail; }

    public String getUserPhone() {
        return userPhone;
    }

    public String getCompContact() { return CompContact; }

    public String getGST() { return GST; }

    public String getDesignation() { return designation; }

    public String getCompAddress() { return CompAddress; }
}
