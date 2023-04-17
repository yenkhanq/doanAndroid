package com.example.appbtlon;

public class classSinhvien {
    String maSv;
    String TenSV;
    String Gioitinh;
    String maLop;
    String maKhoa;

    public classSinhvien(String maSv, String TenSV, String Gioitinh,String maLop,String maKhoa)
    {

        this.maSv=maSv;
        this.TenSV=TenSV;
        this.Gioitinh=Gioitinh;
        this.maLop=maLop;
        this.maKhoa=maKhoa;
    }

    public  String toString()
    {
        String msg = "";

        msg+="Mã Sinh viên : " + this.maSv + "\n";
        msg+="Tên Sinh viên : " + this.TenSV +"\n";
        msg+="Giới Tính : " + this.Gioitinh + "\n";
        msg+="Mã Lớp : " + this.maLop + "\n";
        msg+="Mã Khóa : " + this.maKhoa + "\n";


        return msg;
    }
}
