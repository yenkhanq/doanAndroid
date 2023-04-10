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
import android.widget.Toast;

import java.util.ArrayList;

public class Mainkhoa extends AppCompatActivity {

    String nameDB = "QLdiem.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listKhoa;
    ListView lvKhoa;
    String maKhoa,tenKhoa;
    Button btnThemKhoa,btnSuaKhoa,btnXoaKhoa;
    EditText edtMaKhoa,edtTenKhoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainkhoa);

        edtMaKhoa=(EditText) findViewById(R.id.edtMaKhoa);
        edtTenKhoa=(EditText) findViewById(R.id.edtTenKhoa);

        btnThemKhoa=(Button) findViewById(R.id.btnthemKhoa);
        btnSuaKhoa=(Button) findViewById(R.id.btnSuaKhoa);
        btnXoaKhoa=(Button) findViewById(R.id.btnXoaKhoa);

        lvKhoa=(ListView) findViewById(R.id.lvKhoa);
        hienthiKhoa();

        btnThemKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhoa = edtMaKhoa.getText().toString();
                tenKhoa = edtTenKhoa.getText().toString();
                String sql = "INSERT INTO TBKHOA VALUES('"+maKhoa+"','"+tenKhoa+"')";
                if(doAction(sql)==true){
                    Toast.makeText(Mainkhoa.this,"Thêm [TBKHOA] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainkhoa.this,"Thêm [TBKHOA] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiKhoa();
                doClear();
            }
        });
        btnSuaKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhoa = edtMaKhoa.getText().toString();
                tenKhoa = edtTenKhoa.getText().toString();
                String sql = "UPDATE TBKHOA SET TENKHOA = '"+maKhoa+"' WHERE MAKHOA = '"+tenKhoa+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainkhoa.this,"Sửa [TBKHOA] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainkhoa.this,"Sửa [TBKHOA] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiKhoa();
                doClear();
            }
        });
        btnXoaKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhoa = edtMaKhoa.getText().toString();
                String sql = "DELETE FROM TBKHOA WHERE MAKHOA = '"+maKhoa+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainkhoa.this,"Xóa [TBKHOA] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainkhoa.this,"Xóa [TBKHOA] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiKhoa();
                doClear();
            }
        });
        lvKhoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                classKhoa khoa = (classKhoa) listKhoa.get(i);
                edtMaKhoa.setText(khoa.maKhoa);
                edtTenKhoa.setText(khoa.tenKhoa);
                return false;
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
    public void hienthiKhoa()
    {
        listKhoa = new ArrayList();
        String sql = "SELECT * FROM TBKHOA";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listKhoa.add(new classKhoa(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listKhoa);
        lvKhoa.setAdapter(adapter);
    }
    public void doClear()
    {
        edtMaKhoa.setText("");
        edtTenKhoa.setText("");
        edtMaKhoa.findFocus();
    }

}