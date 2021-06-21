package com.example.gram_pdo;

public class ServiceviewModel {
    String fees, servicename;

    public ServiceviewModel() {
    }

    public ServiceviewModel(String fees, String servicename) {
        this.fees = fees;
        this.servicename = servicename;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }
}
