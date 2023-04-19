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
import android.widget.Toast;

import java.util.ArrayList;

public class Maingiangvien extends AppCompatActivity {
    String nameDB = "QLdiem.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listGiangvien;
    ListView lvGV;
    String maGV,tenGV;
    Button btnthemGV,btnSuaGV,btnXoaGv;
    EditText edtMaGv,edtTENGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maingiangvien);

        edtMaGv=(EditText) findViewById(R.id.edtMaGv);
        edtTENGV=(EditText) findViewById(R.id.edtTENGV);

        btnthemGV=(Button) findViewById(R.id.btnThemGV);
        btnSuaGV=(Button) findViewById(R.id.btnSuaGV);
        btnXoaGv=(Button) findViewById(R.id.btnXoaGV);

        lvGV=(ListView) findViewById(R.id.lvGV);
        hienthiGV();

        btnthemGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maGV = edtMaGv.getText().toString();
                tenGV = edtTENGV.getText().toString();
                String sql = "INSERT INTO TBGIANGVIEN VALUES('"+maGV+"','"+tenGV+"')";
                if(doAction(sql)==true){
                    Toast.makeText(Maingiangvien.this,"Thêm [TBGIANGVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Maingiangvien.this,"Thêm [TBGIANGVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiGV();
                doClear();
            }
        });
        btnSuaGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maGV = edtMaGv.getText().toString();
                tenGV = edtTENGV.getText().toString();
                String sql="Update TBGIANGVIEN ";
                sql+="Set MAGV='"+maGV+"',TENGV='"+tenGV+"'";
                sql+=" Where MAGV='"+maGV+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Maingiangvien.this,"Sửa [TBGIANGVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Maingiangvien.this,"Sửa [TBGIANGVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiGV();
                doClear();
            }
        });
        btnXoaGv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maGV = edtMaGv.getText().toString();
                String sql = "DELETE FROM TBGIANGVIEN WHERE MAGV = '"+maGV+"'";
                if(doAction(sql)==true){
                    Toast.makeText(Maingiangvien.this,"Xóa [TBGIANGVIEN] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Maingiangvien.this,"Xóa [TBGIANGVIEN] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthiGV();
                doClear();
            }
        });
        lvGV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                classGiangvien giangvien = (classGiangvien) listGiangvien.get(i);
                edtMaGv.setText(giangvien.maGV);
                edtTENGV.setText(giangvien.tenGV);
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
    public void hienthiGV()
    {
        listGiangvien = new ArrayList();
        String sql = "SELECT * FROM TBGIANGVIEN";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listGiangvien.add(new classGiangvien(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listGiangvien);
        lvGV.setAdapter(adapter);
    }
    public void doClear()
    {
        edtMaGv.setText("");
        edtTENGV.setText("");
        edtMaGv.findFocus();
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
                Intent inchinh=new Intent(Maingiangvien.this,Giaodienchinh.class);
                startActivity(inchinh);
                break;
            case R.id.mnGiangvien:
                Intent intengiangvien=new Intent(Maingiangvien.this,Maingiangvien.class);
                startActivity(intengiangvien);
                break;
            case R.id.mnKhoa:
                Intent intentkhoa=new Intent(Maingiangvien.this,Mainkhoa.class);
                startActivity(intentkhoa);
                break;
            case R.id.mnLop:
                Intent intentlop=new Intent(Maingiangvien.this,Mainlop.class);
                startActivity(intentlop);
                break;
            case R.id.mnMon:
                Intent intentmon=new Intent(Maingiangvien.this,Mainmon.class);
                startActivity(intentmon);
                break;
            case R.id.mnSinhvien:
                Intent intensinhvien=new Intent(Maingiangvien.this,Mainsinhvien.class);
                startActivity(intensinhvien);
                break;
            case R.id.mnDiem:
                Intent intentdiem=new Intent(Maingiangvien.this,Maindiem.class);
                startActivity(intentdiem);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}