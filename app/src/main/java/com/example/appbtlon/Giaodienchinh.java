package com.example.appbtlon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Giaodienchinh extends AppCompatActivity {

    String nameDB = "QLdiem.db"; //Khai báo tên Database
    SQLiteDatabase database;

    Button CreateDTB,DeleteDTB,btngiangvien,btnKhoa,btnlop,btnmon,btndiem,btnsinhvien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodienchinh);

        CreateDTB=(Button) findViewById(R.id.CreateDTB);
        DeleteDTB=(Button) findViewById(R.id.DeleteDTB);

        btngiangvien=(Button) findViewById(R.id.btngiangvien);
        btnKhoa=(Button) findViewById(R.id.btnKhoa);
        btnlop=(Button) findViewById(R.id.btnlop);
        btnmon=(Button) findViewById(R.id.btnmon);
        btndiem=(Button) findViewById(R.id.btndiem);
        btnsinhvien=(Button) findViewById(R.id.btnsinhvien);

        CreateDTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCreateDB(nameDB);
            }
        });
        DeleteDTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDeleteBD(nameDB);
            }
        });

        btngiangvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "CREATE TABLE TBGIANGVIEN (MAGV TEXT PRIMARY KEY,TENGV TEXT);";
                if(doAction(sql)==true){
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBGIANGVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBGIANGVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "CREATE TABLE TBKHOA (MAKHOA TEXT PRIMARY KEY,TENKHOA TEXT);";
                if(doAction(sql)==true){
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBKHOA] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBKHOA] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "CREATE TABLE TBLOP (MALOP TEXT PRIMARY KEY,TENLOP TEXT,MAKHOA TEXT,FOREIGN KEY (MAKHOA) REFERENCES TBKHOA(MAKHOA));";
                if(doAction(sql)==true){
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBLOP] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBLOP] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "CREATE TABLE TBMON (MAMON TEXT PRIMARY KEY,TENMON TEXT,MAKHOA TEXT,MAGV TEXT,FOREIGN KEY (MAKHOA) REFERENCES TBKHOA(MAKHOA),FOREIGN KEY (MAGV) REFERENCES TBGIANGVIEN(MAGV));";
                if(doAction(sql)==true){
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBMON] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBMON] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "CREATE TABLE TBSINHVIEN (MASV TEXT PRIMARY KEY,TENSV TEXT,GIOITINH TEXT,MALOP TEXT,FOREIGN KEY (MALOP) REFERENCES TBLOP(MALOP));";
                if(doAction(sql)==true){
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBSINHVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBSINHVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "CREATE TABLE TBDIEM (DIEM TEXT PRIMARY KEY,MASV TEXT,MAMON TEXT,MAKHOA TEXT,MALOP TEXT," +
                        "FOREIGN KEY (MASV) REFERENCES TBSINHVIEN(MASV)," +
                        "FOREIGN KEY (MAMON) REFERENCES TBMON(MAMON)," +
                        "FOREIGN KEY (MAKHOA) REFERENCES TBKHOA(MAKHOA),"+
                        "FOREIGN KEY (MALOP) REFERENCES TBLOP(MALOP));";
                if(doAction(sql)==true){
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBDIEM] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Giaodienchinh.this,"Tạo Table [TBDIEM] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public boolean doAction(String sql)
    {
        try
        {
            database = openOrCreateDatabase(nameDB, MODE_PRIVATE,null);
            database.execSQL(sql);
            return true;

        }

        catch(Exception ex){
            return false;
        }
        finally {
            database.close();
        }

    }

    //Tạo Database
    public void doCreateDB(String nameDB){

        try {

            database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
            Toast.makeText(Giaodienchinh.this,"Tạo Database thành công",Toast.LENGTH_SHORT).show();
        }

        catch (Exception ex)
        {
            Toast.makeText(Giaodienchinh.this,"Không thể tạo Database",Toast.LENGTH_SHORT).show();
        }

    }
    //Xóa Database
    public void doDeleteBD(String nameDB){
        String msg="";
        try {
            if(deleteDatabase(nameDB)==true)
            {
                msg="Xóa Database thành công";
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Xóa KHÔNG thành công",Toast.LENGTH_LONG).show();
        }
    }

    //menu
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.mnGiangvien:
                Intent intengiangvien=new Intent(Giaodienchinh.this,Maingiangvien.class);
                startActivity(intengiangvien);
                break;
            case R.id.mnKhoa:
                Intent intentkhoa=new Intent(Giaodienchinh.this,Mainkhoa.class);
                startActivity(intentkhoa);
                break;
            case R.id.mnLop:
                Intent intentlop=new Intent(Giaodienchinh.this,Mainlop.class);
                startActivity(intentlop);
                break;
            case R.id.mnMon:
                Intent intentmon=new Intent(Giaodienchinh.this,Mainmon.class);
                startActivity(intentmon);
                break;
            case R.id.mnSinhvien:
                Intent intensinhvien=new Intent(Giaodienchinh.this,Mainsinhvien.class);
                startActivity(intensinhvien);
                break;
            case R.id.mnDiem:
                Intent intentdiem=new Intent(Giaodienchinh.this,Maindiem.class);
                startActivity(intentdiem);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}