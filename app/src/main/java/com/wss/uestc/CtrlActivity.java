package com.wss.uestc;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.io.OutputStream;
import com.wss.uestc.config.Config;
import com.wss.uestc.protocol.Protocol;

import java.net.Socket;

public class CtrlActivity extends AppCompatActivity {

    private Button BtForward;
    private Button BtBack;
    private Button BtLeft;
    private Button BtRight;
    private Button BtStop;
    private Button BtSettime;

    private EditText ET_Time;

    static public TextView TempTxt;
    static public TextView HumTxt;
    static public TextView DisTxt;
    static public TextView TimeTxt;
    static public Handler handler;
    static public Boolean RecvThreadOverFlag = false;
    static private CtrlActivity currentCtrlAct;

    public static CtrlActivity getCurrentCtrlAct() {
        return currentCtrlAct;
    }
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctrl);
        NavigationView navView = findViewById(R.id.nav_view);
        initToolBar();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_navigator);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_setting:
                        //进入设置画面
//                Toast.makeText(CtrlActivity.this, "设置", Toast.LENGTH_LONG).show();
                        Intent setting_intent = new Intent(CtrlActivity.this, SettingActivity.class);
                        startActivity(setting_intent);

                        break;
                    case R.id.nav_about:
                        //进入关于画面
//                Toast.makeText(CtrlActivity.this, "关于", Toast.LENGTH_LONG).show();
                        Intent about_intent = new Intent(CtrlActivity.this, AboutActivity.class);
                        startActivity(about_intent);
                        break;
                }
                return true;
            }
        });

//
//        //创建handler
        handler = new Handler();

        initView();
        buttonListen();



}
    private void initToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titleText = findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        titleText.setText("小车控制系统");
        setSupportActionBar(toolbar);
    }
    private void initView(){
        currentCtrlAct = CtrlActivity.this;
        BtForward = findViewById(R.id.bt_forward);
        BtBack = findViewById(R.id.bt_back);
        BtLeft = findViewById(R.id.bt_left);
        BtRight = findViewById(R.id.bt_right);
        BtStop = findViewById(R.id.bt_stop);
        BtSettime = findViewById(R.id.bt_settime);

        ET_Time = findViewById(R.id.edit_time);

        TempTxt = findViewById(R.id.tempTxt);
        TimeTxt = findViewById(R.id.timeTxt);
        HumTxt = findViewById(R.id.humTxt);
        DisTxt = findViewById(R.id.disTxt);
    }

    private void buttonListen(){
        BtForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] Bytebuf = Protocol.getForword().getBytes();
                Socket CarTcp = Config.getTcpSock();
                OutputStream stream = null;
                try {
                    stream = CarTcp.getOutputStream();
                    stream.write(Bytebuf);
                } catch (IOException e) {
                    Toast.makeText(CtrlActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                    //e.printStackTrace();
                }

            }
        });
        BtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] Bytebuf = Protocol.getBack().getBytes();
                Socket CarTcp = Config.getTcpSock();
                OutputStream stream = null;
                try {
                    stream = CarTcp.getOutputStream();
                    stream.write(Bytebuf);
                } catch (IOException e) {
                    Toast.makeText(CtrlActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                    //e.printStackTrace();
                }
            }
        });
        BtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] Bytebuf = Protocol.getStop().getBytes();
                Socket CarTcp = Config.getTcpSock();
                OutputStream stream = null;
                try {
                    stream = CarTcp.getOutputStream();
                    stream.write(Bytebuf);
                } catch (IOException e) {
                    Toast.makeText(CtrlActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                    //e.printStackTrace();
                }
            }
        });
        BtLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] Bytebuf = Protocol.getLeft().getBytes();
                Socket CarTcp = Config.getTcpSock();
                OutputStream stream = null;
                try {
                    stream = CarTcp.getOutputStream();
                    stream.write(Bytebuf);
                } catch (IOException e) {
                    Toast.makeText(CtrlActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                    //e.printStackTrace();
                }
            }
        });
        BtRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] Bytebuf = Protocol.getRight().getBytes();
                Socket CarTcp = Config.getTcpSock();
                OutputStream stream = null;
                try {
                    stream = CarTcp.getOutputStream();
                    stream.write(Bytebuf);
                } catch (IOException e) {
                    Toast.makeText(CtrlActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                    //e.printStackTrace();
                }
            }
        });
        BtSettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*1.保存时间信息
                 *2.发送设置时间信息
                 */

                String time = ET_Time.getText().toString();
                time = time.substring(0,2)+time.substring(3,5)+time.substring(6,8);
                byte[] Bytebuf = (Protocol.getSettime()+time+"|").getBytes();
                Socket CarTcp = Config.getTcpSock();
                OutputStream stream = null;

                try {
                    stream = CarTcp.getOutputStream();
                    stream.write(Bytebuf);
                    ET_Time.setText("");
                    Toast.makeText(CtrlActivity.this, "设置时间成功", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            MainActivity.getTh().interrupt();//结束线程
            this.finish();  //结束activity
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

//    //建立菜单main
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main,menu);
//        return true;
//    }
//    //点击菜单功能

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.setting_item:
//                //进入设置画面
////                Toast.makeText(CtrlActivity.this, "设置", Toast.LENGTH_LONG).show();
//                Intent setting_intent = new Intent(CtrlActivity.this, SettingActivity.class);
//                startActivity(setting_intent);
//
//                break;
//            case R.id.about_item:
//                //进入关于画面
////                Toast.makeText(CtrlActivity.this, "关于", Toast.LENGTH_LONG).show();
//                Intent about_intent = new Intent(CtrlActivity.this, AboutActivity.class);
//                startActivity(about_intent);
//                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
