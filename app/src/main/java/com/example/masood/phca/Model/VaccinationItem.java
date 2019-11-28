package com.example.masood.phca.Model;

public class VaccinationItem {
    private String name;
    private String status;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public VaccinationItem(){

    }
    public VaccinationItem(String name, String status , String date) {
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
