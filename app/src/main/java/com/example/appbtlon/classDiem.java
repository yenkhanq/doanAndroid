package com.example.appbtlon;

public class classDiem {
    String Diem;

    String maSV;
    String maMon;
    String maKhoa;
    String maLop;

    public classDiem(String Diem, String maSV,String maMon,String maKhoa, String maLop)
    {

        this.Diem=Diem;
        this.maSV=maSV;
        this.maMon=maMon;
        this.maKhoa=maKhoa;
        this.maLop=maLop;

    }

    public  String toString()
    {
        String msg = "";

        msg+="Điểm : " + this.Diem + "\n";
        msg+="Mã Sinh Viên : " + this.maSV +"\n";
        msg+="Mã Môn : " + this.maMon + "\n";
        msg+="Mã Khóa : " + this.maKhoa +"\n";
        msg+="Mã Lớp : " + this.maLop + "\n";


        return msg;
    }
}
