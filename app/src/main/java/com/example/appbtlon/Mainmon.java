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

public class Mainmon extends AppCompatActivity {
    String nameDB = "QLdiem.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listMon,listKhoa,listGV;
    ListView lvMon;
    String maMon,tenMon,maKhoa,maGV;
    Button btnThemMon,btnSuaMon,btnXoaMon;
    EditText edtMamon,edtTenmon;
    Spinner spnerMaKhoa,spnMaGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmon);

        edtMamon=(EditText) findViewById(R.id.edtMamon);
        edtTenmon=(EditText) findViewById(R.id.edtTenMon);

        spnerMaKhoa=(Spinner) findViewById(R.id.spnerMaKhoa);
        spnMaGV=(Spinner) findViewById(R.id.spnMaGV);

        lvMon=(ListView) findViewById(R.id.lvMon);

        btnThemMon=(Button) findViewById(R.id.btnthemMon);
        btnSuaMon=(Button) findViewById(R.id.btnsuaMon);
        btnXoaMon=(Button) findViewById(R.id.btnXoaMon);

        hienThiSpinnerMAKHOA();
        hienThiSpinnerMAGV();
        hienThiMon();

        lvMon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                classMon mon = (classMon) listMon.get(i);
                edtMamon.setText(mon.maMon);
                edtTenmon.setText(mon.tenMon);

                return false;
            }
        });
        spnerMaKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classKhoa makhoa = (classKhoa) listKhoa.get(i);
                maKhoa=makhoa.maKhoa;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Mainmon.this, "Hãy chọn Khóa", Toast.LENGTH_SHORT).show();
            }
        });
        spnMaGV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classGiangvien giangvien = (classGiangvien) listGV.get(i);
                maGV=giangvien.maGV;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Mainmon.this, "Hãy chọn Giảng viên", Toast.LENGTH_SHORT).show();
            }
        });

        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maMon = edtMamon.getText().toString();
                tenMon = edtTenmon.getText().toString();


                String sql = "INSERT INTO TBMON VALUES('"+maMon+"','"+tenMon+"','"+maKhoa+"','"+maGV+"')";
                if(doAction(sql)==true){
                    Toast.makeText(Mainmon.this,"Thêm [TBMON] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainmon.this,"Thêm [TBMON] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiMon();
                doClear();

            }
        });
        btnSuaMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maMon = edtMamon.getText().toString();
                tenMon = edtTenmon.getText().toString();

                String sql = "UPDATE TBMON SET MAMON = '"+maMon+"',TENMON = '"+tenMon+"',MAKHOA = '"+maKhoa+"',MAGV = '"+maGV+"' WHERE MAMON = '"+maMon+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainmon.this,"Sửa [TBMON] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainmon.this,"Sửa [TBMON] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiMon();
                doClear();

            }
        });
        btnXoaMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maMon = edtMamon.getText().toString();
                tenMon = edtTenmon.getText().toString();

                String sql = "DELETE FROM TBMON WHERE MAMON = '"+maMon+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainmon.this,"Xóa [TBMON] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainmon.this,"Xóa [TBMON] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiMon();
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
        edtMamon.setText("");
        edtTenmon.setText("");

        edtMamon.findFocus();
    }
    public void hienThiMon()
    {
        listMon = new ArrayList();
        String sql = "SELECT * FROM TBMON";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listMon.add(new classMon(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listMon);
        lvMon.setAdapter(adapter);
    }
    public void hienThiSpinnerMAKHOA(){

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
        spnerMaKhoa.setAdapter(adapter);
    }
    public void hienThiSpinnerMAGV(){

        listGV=new ArrayList();

        String sql="Select * From TBGIANGVIEN Order By MAGV";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listGV.add(new classGiangvien(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listGV);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnMaGV.setAdapter(adapter);
    }
}