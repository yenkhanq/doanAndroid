package com.example.appbtlon;

public class ClassLop {

    String maLop;
    String tenLop;

    public ClassLop(String maLop, String tenLop)
    {

        this.maLop=maLop;
        this.tenLop=tenLop;

    }

    public  String toString()
    {
        String msg = "";

        msg+="Mã Lớp : " + this.maLop + "\n";
        msg+="Tên Lớp : " + this.tenLop +"\n";


        return msg;
    }
}
