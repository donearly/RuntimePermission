package com.example.sail.filetest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btn;

    private final int REQUESTCODE = 101 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE){
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                CreateDir();
            }
        }
    }

    private void test() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int check = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (check == PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUESTCODE);
            }else{
                CreateDir();
            }
        }else{
            CreateDir();
        }
    }

    private void CreateDir(){
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"test001");
        Log.d("TAG", appDir.getAbsolutePath());
        if (!appDir.exists()){
            boolean isSuccess = appDir.mkdirs();
            Log.d("TAG",  appDir.getAbsolutePath());
        }
    }
}
