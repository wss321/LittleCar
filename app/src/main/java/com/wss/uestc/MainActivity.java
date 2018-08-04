package com.wss.uestc;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.wss.uestc.config.Config;
import com.wss.uestc.protocol.Protocol;
import com.wss.uestc.recv.TcpRecv;

import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private Button BtConnect;
    private EditText ET_ServerIP;
    private EditText ET_ServerPort;
    private static String ServerIP;
    private static int ServerPort;
    private Boolean ConnectFlag = false;

    public static Thread getTh() {
        return th;
    }

    private static Thread th;

    public boolean isIP(String addr) {

        String rexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        return addr.matches(rexp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            /*
            1.保存端口号和IP
            2.建立连接
            3.初始化协议
            4.打开控制页面
             */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(mainToolBar);
        initToolBar();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());
        BtConnect = (Button) findViewById(R.id.bt_connect);
        ET_ServerIP = findViewById(R.id.edit_TCPServerIP);
        ET_ServerPort = findViewById(R.id.edit_TCPServerPort);

        BtConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                1.保存端口号与ip
                 */
                if(ET_ServerIP.getText().toString().trim().equals("")||ET_ServerPort.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "请输入IP地址和端口号！", Toast.LENGTH_LONG).show();
                }
                else{
                    ServerIP = ET_ServerIP.getText().toString();
                    ServerPort = Integer.parseInt(ET_ServerPort.getText().toString());
                /*
                2.建立连接
                 */

                    if (isIP(ServerIP) && ServerPort > 0 && ServerPort < 65536) {
                        try {
//                    Toast.makeText(MainActivity.this, ServerIP+":"+ServerPort, Toast.LENGTH_LONG).show();
                            Socket sock = new Socket(ServerIP, ServerPort);
                            Config.setTcpSock(sock);
                            Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_LONG).show();
                            ConnectFlag = true;
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "连接失败！", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        if (ConnectFlag) {
                            //3.初始化控制协议
                            Protocol.setForword("9W|");
                            Protocol.setBack("9S|");
                            Protocol.setLeft("9A|");
                            Protocol.setRight("9D|");
                            Protocol.setStop("9P|");
                            Protocol.setSettime("9ST");
                            //4.打开控制界面
                            Intent intent = new Intent(MainActivity.this, CtrlActivity.class);
                            startActivity(intent);
                            TcpRecv t = new TcpRecv();
                            th = new Thread(t);
                            th.start();
                        }
                    } else
                        Toast.makeText(MainActivity.this, "请输入合法的IP和端口号！", Toast.LENGTH_LONG).show();
                    //如果连接成功就进入控制页面，并启动接收线程

                }
            }
        });
    }
    //初始化ToolBar
    private void initToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titleText = findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        titleText.setText("建立连接");
        setSupportActionBar(toolbar);
    }
}
