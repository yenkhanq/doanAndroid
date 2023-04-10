package com.example.appbtlon;

public class classGiangvien {
    String maGV;
    String tenGV;
    public classGiangvien(String maGV,String tenGV){
        this.maGV = maGV;
        this.tenGV=tenGV;
    }

    public String toString()
    {
        String msg = "";
        msg += "Mã GV: "+maGV+"\n";
        msg += "Tên GV: "+tenGV+"\n";
        return msg;
    }
}
