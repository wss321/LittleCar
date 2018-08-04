package com.wss.uestc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wss.uestc.protocol.Protocol;

public class SettingActivity extends AppCompatActivity {

    private Button BtSetting;

    private EditText ET_forwardProtocol;
    private EditText ET_backProtocol;
    private EditText ET_stopProtocol;
    private EditText ET_leftProtocol;
    private EditText ET_rightProtocol;
    private EditText ET_timeProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolBar();
        initView();
        showProtocol();
        buttonListen();


    }
    private void initToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titleText = findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        titleText.setText("设置协议");
        setSupportActionBar(toolbar);
    }

    private void initView(){
        BtSetting = findViewById(R.id.bt_Setsetting);
        ET_forwardProtocol = findViewById(R.id.edit_setForward);
        ET_backProtocol = findViewById(R.id.edit_setBack);
        ET_leftProtocol = findViewById(R.id.edit_setLeft);
        ET_rightProtocol = findViewById(R.id.edit_setRight);
        ET_timeProtocol = findViewById(R.id.edit_setSettime);
        ET_stopProtocol = findViewById(R.id.edit_setStop);

    }
    //显示当前协议信息
    private void showProtocol() {
        ET_forwardProtocol.setText(Protocol.getForword());
        ET_backProtocol.setText(Protocol.getBack());
        ET_leftProtocol.setText(Protocol.getLeft());
        ET_rightProtocol.setText(Protocol.getRight());
        ET_stopProtocol.setText(Protocol.getStop());
        ET_timeProtocol.setText(Protocol.getSettime());
    }
    private void buttonListen(){
        BtSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                修改协议配置
                 */
                setProtocol();
                Toast.makeText(SettingActivity.this, "设置成功", Toast.LENGTH_LONG).show();

            }

            private void setProtocol(){
                Protocol.setForword(ET_forwardProtocol.getText().toString());
                Protocol.setStop(ET_stopProtocol.getText().toString());
                Protocol.setLeft(ET_leftProtocol.getText().toString());
                Protocol.setBack(ET_backProtocol.getText().toString());
                Protocol.setRight(ET_rightProtocol.getText().toString());
                Protocol.setSettime(ET_timeProtocol.getText().toString());
            }
        });
    }
}
