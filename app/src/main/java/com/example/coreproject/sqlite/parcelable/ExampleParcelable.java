package com.example.coreproject.sqlite.parcelable;

public class ExampleParcelable {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String alamat;

    public ExampleParcelable() {
    }

    public ExampleParcelable(long id, String name, String email, String phone, String alamat) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.alamat = alamat;
    }

    public ExampleParcelable(String name, String email, String phone, String alamat) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.alamat = alamat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
