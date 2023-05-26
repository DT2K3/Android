package com.example.lab3;

public class Student {
    private String name;
    private int mssv;
    private String address;

    public Student(String name, int mssv, String address) {
        this.name = name;
        this.mssv = mssv;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMssv() {
        return mssv;
    }

    public void setAge(int mssv) {
        this.mssv = mssv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
