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

public class Mainsinhvien extends AppCompatActivity {
    String nameDB = "QLdiem.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listLop,listSinhvien;
    ListView lvSV;
    String maSV,tenSV,GioitinhSV,Malop;
    Button btnthemSV,btnsuaSV,btnXoaSV;
    EditText edtmaSV,edtTenSv,edtGioiTinh;
    Spinner spnMaLop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsinhvien);

        edtmaSV=(EditText) findViewById(R.id.edtmasv);
        edtTenSv=(EditText) findViewById(R.id.edttensv);
        edtGioiTinh=(EditText) findViewById(R.id.edtgioiTinh);

        btnthemSV=(Button) findViewById(R.id.btnthemSV);
        btnsuaSV=(Button) findViewById(R.id.btnSuaSV);
        btnXoaSV=(Button) findViewById(R.id.btnXoaSV);

        lvSV=(ListView) findViewById(R.id.lvSv);

        spnMaLop=(Spinner) findViewById(R.id.spnerMaLop);
        hienThiSpinnerMaLop();

        hienThiSinhvien();

        lvSV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                classSinhvien Sinhvien = (classSinhvien) listSinhvien.get(i);
                edtmaSV.setText(Sinhvien.maSv);
                edtTenSv.setText(Sinhvien.TenSV);
                edtGioiTinh.setText(Sinhvien.Gioitinh);


                return false;
            }
        });
        spnMaLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ClassLop malop = (ClassLop) listLop.get(i);
                Malop=malop.maLop;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Mainsinhvien.this, "Hãy chọn Lop", Toast.LENGTH_SHORT).show();
            }
        });

        btnthemSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maSV = edtmaSV.getText().toString();
                tenSV = edtTenSv.getText().toString();
                GioitinhSV = edtGioiTinh.getText().toString();



                String sql = "INSERT INTO TBSINHVIEN VALUES('"+maSV+"','"+tenSV+"','"+GioitinhSV+"','"+Malop+"')";
                if(doAction(sql)==true){
                    Toast.makeText(Mainsinhvien.this,"Thêm [TBSINHVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainsinhvien.this,"Thêm [TBSINHVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiSinhvien();
                doClear();

            }
        });
        btnsuaSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maSV = edtmaSV.getText().toString();
                tenSV = edtTenSv.getText().toString();
                GioitinhSV = edtGioiTinh.getText().toString();

                String sql = "UPDATE TBSINHVIEN SET MASV = '"+maSV+"',TENSV = '"+tenSV+"',GIOITINH = '"+GioitinhSV+"',MALOP='"+Malop+"' WHERE MASV = '"+maSV+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainsinhvien.this,"Sửa [TBSINHVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainsinhvien.this,"Sửa [TBSINHVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiSinhvien();
                doClear();

            }
        });
        btnXoaSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maSV = edtmaSV.getText().toString();
                tenSV = edtTenSv.getText().toString();
                GioitinhSV = edtGioiTinh.getText().toString();

                String sql = "DELETE FROM TBSINHVIEN WHERE MASV = '"+maSV+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainsinhvien.this,"Xóa [TBSINHVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainsinhvien.this,"Xóa [TBSINHVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiSinhvien();
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
        edtmaSV.setText("");
        edtTenSv.setText("");

        edtGioiTinh.setText("");

        edtmaSV.findFocus();
    }
    public void hienThiSinhvien()
    {
        listSinhvien = new ArrayList();
        String sql = "SELECT * FROM TBSINHVIEN";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listSinhvien.add(new classSinhvien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listSinhvien);
        lvSV.setAdapter(adapter);
    }
    public void hienThiSpinnerMaLop(){

        listLop=new ArrayList();

        String sql="Select * From TBLOP Order By MALOP"; //hien thi ma lop vs ma khoa

        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listLop.add(new ClassLop(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listLop);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnMaLop.setAdapter(adapter);
    }

}