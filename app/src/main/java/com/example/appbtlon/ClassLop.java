package com.example.appbtlon;

public class ClassLop {
    String maKhoa;
    String maLop;
    String tenLop;

    public ClassLop(String maLop, String tenLop, String maKhoa)
    {

        this.maLop=maLop;
        this.tenLop=tenLop;
        this.maKhoa=maKhoa;
    }

    public  String toString()
    {
        String msg = "";

        msg+="Mã Lớp : " + this.maLop + "\n";
        msg+="Tên Lớp : " + this.tenLop +"\n";
        msg+="Mã Khóa : " + this.maKhoa + "\n";

        return msg;
    }
}
