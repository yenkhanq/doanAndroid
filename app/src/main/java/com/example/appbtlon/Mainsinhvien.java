package com.example.appbtlon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    ArrayList listLop,listSinhvien,listKhoa;
    ListView lvSV;
    String maSV,tenSV,GioitinhSV,Malop,maKhoa;
    Button btnthemSV,btnsuaSV,btnXoaSV;
    EditText edtmaSV,edtTenSv,edtGioiTinh;
    Spinner spnMaLop,spnMakhoa;
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

        spnMakhoa=(Spinner) findViewById(R.id.spnerMaKhoa);
        hienThiSpinnerMaLop();
        hienThiSpinnerMaKhoa();
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
                Toast.makeText(Mainsinhvien.this, "Hãy chọn Lớp", Toast.LENGTH_SHORT).show();
            }
        });
        spnMakhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classKhoa makhoa = (classKhoa) listKhoa.get(i);
                maKhoa=makhoa.maKhoa;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Mainsinhvien.this, "Hãy chọn Khóa", Toast.LENGTH_SHORT).show();
            }
        });

        btnthemSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maSV = edtmaSV.getText().toString();
                tenSV = edtTenSv.getText().toString();
                GioitinhSV = edtGioiTinh.getText().toString();



                String sql = "INSERT INTO TBSINHVIEN VALUES('"+maSV+"','"+tenSV+"','"+GioitinhSV+"','"+Malop+"','"+maKhoa+"')";
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

                String sql="Update TBSINHVIEN ";
                sql+="Set MASV='"+maSV+"',TENSV='"+tenSV+"',MAKHOA = '"+maKhoa+"',GIOITINH = '"+GioitinhSV+"',MALOP='"+Malop+"',MAKHOA='"+maKhoa+"'";
                sql+=" Where MASV='"+maSV+"'";

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
                listSinhvien.add(new classSinhvien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listSinhvien);
        lvSV.setAdapter(adapter);
    }
    public void hienThiSpinnerMaLop(){

        listLop=new ArrayList();

        String sql="Select * From TBLOP Order By MALOP";

        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listLop.add(new ClassLop(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listLop);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnMaLop.setAdapter(adapter);
    }
    public void hienThiSpinnerMaKhoa(){

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
        spnMakhoa.setAdapter(adapter);
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
            case R.id.mnCHINH:
                Intent inchinh=new Intent(Mainsinhvien.this,Giaodienchinh.class);
                startActivity(inchinh);
                break;
            case R.id.mnGiangvien:
                Intent intengiangvien=new Intent(Mainsinhvien.this,Maingiangvien.class);
                startActivity(intengiangvien);
                break;
            case R.id.mnKhoa:
                Intent intentkhoa=new Intent(Mainsinhvien.this,Mainkhoa.class);
                startActivity(intentkhoa);
                break;
            case R.id.mnLop:
                Intent intentlop=new Intent(Mainsinhvien.this,Mainlop.class);
                startActivity(intentlop);
                break;
            case R.id.mnMon:
                Intent intentmon=new Intent(Mainsinhvien.this,Mainmon.class);
                startActivity(intentmon);
                break;
            case R.id.mnSinhvien:
                Intent intensinhvien=new Intent(Mainsinhvien.this,Mainsinhvien.class);
                startActivity(intensinhvien);
                break;
            case R.id.mnDiem:
                Intent intentdiem=new Intent(Mainsinhvien.this,Maindiem.class);
                startActivity(intentdiem);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}