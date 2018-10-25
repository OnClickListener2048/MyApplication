package com.example.administrator.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.init(getApplicationContext());
        setContentView(R.layout.activity_main);
        //first commit
        //second commit
        //third commit
        //four commit
        //five commit
        //six commit
        //seven
        //eight
        //nine
        //ten
        //eleven
        //twelve
        //thirteen
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.order_item_qrcode, generateData()) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

            }
        });
    }

    private List<String> generateData() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i + "eqwewqeq");
        }
        return arrayList;
    }

    public void base64ToFile(String fileName, String fileData) {
        File file = null;
        //创建文件目录
        String filePath = Environment.getExternalStorageDirectory() + "/sms_file";
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            boolean mkdirs = dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {

            byte[] bytes = EncodeUtils.base64Decode(fileData);
            file = new File(filePath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
