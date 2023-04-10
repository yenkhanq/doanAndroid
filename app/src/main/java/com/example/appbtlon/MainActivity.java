package com.example.appbtlon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tentaikhoan,matkhau;
    Button btndangnhap,btndangky,btnthoat;
    String tentk,matkhautk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            khaibao();
            ControlButton();

    }
    private void ControlButton(){
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Thông báo !");
                builder.setMessage("Bạn có muốn thoát app ?");
                builder.setIcon(R.drawable.canhbao);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setTitle("Hộp thoại xử lý");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.customsdialogdangky);
                EditText edtTK =(EditText)dialog.findViewById(R.id.dktaikhoan);
                EditText edtMK =(EditText)dialog.findViewById(R.id.dkmatkhau);
                Button btnHuy=(Button)dialog.findViewById(R.id.thoatdangky);
                Button btnDongY=(Button)dialog.findViewById(R.id.dangkytaikhoan);
                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tentk=edtTK.getText().toString().trim();
                        matkhautk=edtMK.getText().toString().trim();
                        tentaikhoan.setText(tentk);
                        matkhau.setText(matkhautk);
                        Toast.makeText(MainActivity.this,"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tentk=tentaikhoan.getText().toString().trim();
                if(tentaikhoan.getText().length()!=0 && matkhau.getText().length()!=0){
                    if(tentaikhoan.getText().toString().equals(tentk) && matkhau.getText().toString().equals(matkhautk)){
                        Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,Giaodienchinh.class);
                        startActivity(intent);

                    }
                    else if(tentaikhoan.getText().toString().equals("luuyenkhang")&& matkhau.getText().toString().equals("luuyenkhang")){
                        Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,Giaodienchinh.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(MainActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(MainActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void khaibao(){
        tentaikhoan=(EditText) findViewById(R.id.tentaikhoan);
        matkhau=(EditText) findViewById(R.id.matKhau);
        btndangnhap=(Button) findViewById(R.id.dangnhap);
        btndangky=(Button) findViewById(R.id.dangky);
        btnthoat=(Button) findViewById(R.id.btnthoat);
    }

}