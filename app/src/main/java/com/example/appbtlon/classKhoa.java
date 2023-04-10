package com.example.appbtlon;

public class classKhoa {
    String maKhoa;
    String tenKhoa;
    public classKhoa(String maKhoa,String tenKhoa){
        this.maKhoa = maKhoa;
        this.tenKhoa=tenKhoa;
    }

    public String toString()
    {
        String msg = "";
        msg += "Mã khóa: "+maKhoa+"\n";
        msg += "Tên khóa: "+tenKhoa+"\n";
        return msg;
    }
}
