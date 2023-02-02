/*
 * *
 * Created by DuoSecondPorject on 7/5/22, 8:44 PM
 * Copyright (c) $today.year.All rights reserved.
 * Last modified 7/5/22, 8:43 PM
 * /
 */

package com.sitnclean.carwash.Dashboard;

public class orderMotor {

    String id, email, nama, hp,date,time, ourservice, ourservice1, ourservice2, address, plat, othinformation, total, status;

    public orderMotor (String id,String nama, String email, String hp,String date,String time,String ourservice, String ourservice1, String ourservice2 ,String address, String plat, String othinformation,String total, String status) {
        this.id = id;
        this.nama = nama;
        this.hp = hp;
        this.date = date;
        this.time = time;
        this.email = email;
        this.ourservice = ourservice;
        this.ourservice1 = ourservice1;
        this.ourservice2 = ourservice2;
        this.address = address;
        this.plat = plat;
        this.othinformation = othinformation;
        this.total = total;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getDate() {return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() {return time; }

    public void setTime(String time) { this.date = time; }

    public String getOurservice() {return ourservice; }

    public void setOurservice(String ourservice) { this.ourservice = ourservice; }

    public String getOurservice1() {return ourservice1; }

    public void setOurservice1(String ourservice1) { this.ourservice1 = ourservice1; }

    public String getOurservice2() {return ourservice2; }

    public void setOurservice2(String ourservice2) { this.ourservice2 = ourservice2; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getOthinformation() { return othinformation; }

    public void setOthinformation(String othinformation) { this.othinformation = othinformation; }

    public String getTotal() { return total; }

    public void setTotal(String total) { this.total = total;}

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

}
