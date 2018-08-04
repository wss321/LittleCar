package com.wss.uestc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolBar();
    }
    private void initToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titleText = findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        titleText.setText("关于");
        setSupportActionBar(toolbar);
    }
}
