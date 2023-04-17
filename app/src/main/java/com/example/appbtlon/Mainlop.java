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

public class Mainlop extends AppCompatActivity {
    String nameDB = "QLdiem.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listLop;
    ListView lvLop;
    String maLop,tenLop;
    Button btnThemlop,btnSuaLop,btnXoaLop;
    EditText edtMalop,edtTenlop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlop);

        edtMalop=(EditText) findViewById(R.id.edtMalop);
        edtTenlop=(EditText) findViewById(R.id.edtTenlop);




        lvLop=(ListView) findViewById(R.id.lvLop);

        btnThemlop=(Button) findViewById(R.id.btnThemLop);
        btnSuaLop=(Button) findViewById(R.id.btnsuaLop);
        btnXoaLop=(Button) findViewById(R.id.btnXoaLop);


        hienThiLop();

        lvLop.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClassLop lop = (ClassLop) listLop.get(i);
                edtMalop.setText(lop.maLop);
                edtTenlop.setText(lop.tenLop);

                return false;
            }
        });


        btnThemlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maLop = edtMalop.getText().toString();
                tenLop = edtTenlop.getText().toString();


                String sql = "INSERT INTO TBLOP VALUES('"+maLop+"','"+tenLop+"')";
                if(doAction(sql)==true){
                    Toast.makeText(Mainlop.this,"Thêm [TBLOP] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainlop.this,"Thêm [TBLOP] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiLop();
                doClear();

            }
        });
        btnSuaLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maLop = edtMalop.getText().toString();
                tenLop = edtTenlop.getText().toString();

                String sql = "UPDATE TBLOP SET MALOP = '"+maLop+"',TENLOP = '"+tenLop+"' WHERE MALOP = '"+maLop+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainlop.this,"Sửa [TBLOP] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainlop.this,"Sửa [TBLOP] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiLop();
                doClear();

            }
        });
        btnXoaLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maLop = edtMalop.getText().toString();
                tenLop = edtTenlop.getText().toString();

                String sql = "DELETE FROM TBLOP WHERE MALOP = '"+maLop+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Mainlop.this,"Xóa [TBLOP] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Mainlop.this,"Xóa [TBLOP] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienThiLop();
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
        edtMalop.setText("");
        edtTenlop.setText("");

        edtMalop.findFocus();
    }
    public void hienThiLop()
    {
        listLop = new ArrayList();
        String sql = "SELECT * FROM TBLOP";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listLop.add(new ClassLop(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listLop);
        lvLop.setAdapter(adapter);
    }

}