package com.wss.uestc.recv;

import com.wss.uestc.CtrlActivity;
import com.wss.uestc.config.Config;
import com.wss.uestc.dealmsg.Deal;
import com.wss.uestc.dealmsg.DealmsgCreateinstance;

import java.io.DataInputStream;
import java.io.IOException;

public class TcpRecv implements Runnable {
    @Override
    public void run() {
        while(true){
            try {
                //收消息
                DataInputStream dis = new DataInputStream(Config.getTcpSock().getInputStream());
                byte[] buf = new byte[1024];
                int count = dis.read(buf);
                //处理消息08300000280000
                if (count>0){
                    String str = new String(buf, 0, count);
                    Deal deal = DealmsgCreateinstance.createinstance(str);
                    if(deal!=null){
                        deal.dealmsg(str);
                    }
                }

            }
            //如果出错就退出
            catch (Exception e) {
                CtrlActivity.RecvThreadOverFlag = true;
                CtrlActivity.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        CtrlActivity.getCurrentCtrlAct().finish();
                    }
                });
                break;
                //e.printStackTrace();
            }

        }

    }
}
