package com.example.appbtlon;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Maindiem extends AppCompatActivity {
    String nameDB = "QLdiem.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listDiem,listSV,listMON,listKhoa,ListLop;
    ListView lvDiem;
    String Diem,maSv,maMon,maKhoa,maLop;
    Button btnthemDiem,btnsuaDiem,btnXoaDiem;
    EditText edtDiem;
    Spinner spnSV,spnMon,spnKhoa,spnLop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindiem);

        edtDiem=(EditText) findViewById(R.id.edtDiem);

        lvDiem=(ListView) findViewById(R.id.lvDiem);

        spnSV=(Spinner) findViewById(R.id.snpMaSinhvien);
        spnMon=(Spinner) findViewById(R.id.spnmaMon);
        spnKhoa=(Spinner) findViewById(R.id.spnmaKhoa);
        spnLop=(Spinner) findViewById(R.id.spnMaLop);

        btnthemDiem=(Button) findViewById(R.id.btnthemDiem);
        btnsuaDiem=(Button) findViewById(R.id.btnSuaDiem);
        btnXoaDiem=(Button) findViewById(R.id.btnXoaDiem);

        hienthiDiem();
        hienThispinerMASV();
        hienThispinerMAMon();
        hienThispinerKHOA();
        hienThispinerLOP();

        lvDiem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                classDiem diem = (classDiem) listDiem.get(i);
                edtDiem.setText(diem.Diem);


                return false;
            }
        });
        spnSV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classSinhvien sinhvien = (classSinhvien) listSV.get(i);
                maSv=sinhvien.maSv;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Maindiem.this, "Hãy chọn điểm", Toast.LENGTH_SHORT).show();
            }
        });
        spnMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classMon mon = (classMon) listMON.get(i);
                maMon=mon.maMon;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Maindiem.this, "Hãy chọn Môn", Toast.LENGTH_SHORT).show();
            }
        });
        spnKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classKhoa khoa = (classKhoa) listKhoa.get(i);
                maKhoa=khoa.maKhoa;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Maindiem.this, "Hãy chọn Khóa", Toast.LENGTH_SHORT).show();
            }
        });
        spnLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ClassLop lop = (ClassLop) ListLop.get(i);
                maLop=lop.maLop;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Maindiem.this, "Hãy chọn lỚP", Toast.LENGTH_SHORT).show();
            }
        });

        btnthemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Diem = edtDiem.getText().toString();




                String sql = "INSERT INTO TBDIEM VALUES('"+Diem+"','"+maSv+"','"+maMon+"','"+maKhoa+"','"+maLop+"')";
                if(doAction(sql)==true){
                    Toast.makeText(Maindiem.this,"Thêm [TBDIEM] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Maindiem.this,"Thêm [TBDIEM] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiDiem();
                doClear();

            }
        });
        btnsuaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Diem = edtDiem.getText().toString();

                String sql = "UPDATE TBDIEM SET DIEM = '"+Diem+"',MASV = '"+maSv+"',MAMON = '"+maMon+"',MAKHOA='"+maKhoa+"',MALOP='"+maLop+"' WHERE DIEM = '"+Diem+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Maindiem.this,"Sửa [TBDIEM] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Maindiem.this,"Sửa [TBDIEM] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiDiem();
                doClear();

            }
        });
        btnXoaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Diem = edtDiem.getText().toString();

                String sql = "DELETE FROM TBDIEM WHERE DIEM = '"+Diem+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Maindiem.this,"Xóa [TBDIEM] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Maindiem.this,"Xóa [TBDIEM] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiDiem();
                doClear();

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
    public void doClear()
    {
        edtDiem.setText("");


        edtDiem.findFocus();
    }
    public void hienthiDiem()
    {
        listDiem = new ArrayList();
        String sql = "SELECT * FROM TBDIEM";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listDiem.add(new classDiem(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listDiem);
        lvDiem.setAdapter(adapter);
    }
    public void hienThispinerMASV(){

        listSV=new ArrayList();

        String sql="Select * From TBSINHVIEN Order By MASV";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listSV.add(new classSinhvien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listSV);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnSV.setAdapter(adapter);
    }
    public void hienThispinerMAMon(){

        listMON=new ArrayList();

        String sql="Select * From TBMON Order By MAMON";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listMON.add(new classMon(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listMON);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnMon.setAdapter(adapter);
    }
    public void hienThispinerKHOA(){

        listKhoa=new ArrayList();

        String sql="Select * From TBKHOA Order By MAKHOA";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listKhoa.add(new classKhoa(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listKhoa);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnKhoa.setAdapter(adapter);
    }
    public void hienThispinerLOP(){

        ListLop=new ArrayList();

        String sql="Select * From TBLOP Order By MALOP";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                ListLop.add(new ClassLop(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,ListLop);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnLop.setAdapter(adapter);
    }
}