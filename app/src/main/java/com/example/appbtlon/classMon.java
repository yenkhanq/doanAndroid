package com.example.appbtlon;

public class classMon {
    String maKhoa;
    String maGV;
    String maMon;
    String tenMon;

    public classMon(String maMon, String tenMon, String maKhoa, String maGV)
    {

        this.maMon=maMon;
        this.tenMon=tenMon;
        this.maKhoa=maKhoa;
        this.maGV=maGV;
    }

    public  String toString()
    {
        String msg = "";

        msg+="Mã Môn : " + this.maMon + "\n";
        msg+="Tên Môn : " + this.tenMon +"\n";
        msg+="Mã Khóa : " + this.maKhoa + "\n";
        msg+="Mã Giảng viên : " + this.maGV + "\n";


        return msg;
    }
}
