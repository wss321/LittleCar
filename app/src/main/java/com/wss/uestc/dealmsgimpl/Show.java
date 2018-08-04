package com.wss.uestc.dealmsgimpl;

import android.widget.TextView;

import com.wss.uestc.CtrlActivity;
import com.wss.uestc.dealmsg.Deal;

public class Show implements Deal {
    @Override
    public void dealmsg(String msg){
        show(msg);
    }

    private void show(String msg){

        //H123456123456T123456D1234格式
        //如果不够就补0
        if(msg.length()<25)
            msg += patchZero(25-msg.length());

        final String dis = msg.substring(21, 25)+"cm";
        final String hum = msg.substring(1, 4)+'.'+msg.substring(4, 7)+'%';
        final String temp = msg.substring(7, 10)+'.'+msg.substring(10, 13)+'℃';
        final String time = msg.substring(14, 16)+':'+msg.substring(16, 18)+':'+msg.substring(18, 20);
        CtrlActivity.handler.post(new Runnable() {
            @Override
            public void run() {
                CtrlActivity.TempTxt.setText(temp);
                CtrlActivity.DisTxt.setText(dis);
                CtrlActivity.HumTxt.setText(hum);
                CtrlActivity.TimeTxt.setText(time);
            }
        });


    }

    //补0函数
    private String patchZero(int num){

        int i;
        String str="";
        for(i=0;i<num;i++)
            str +="0";
        return str;

    }
}
