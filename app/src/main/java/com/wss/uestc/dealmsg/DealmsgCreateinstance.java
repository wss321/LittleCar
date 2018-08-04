package com.wss.uestc.dealmsg;

import com.wss.uestc.dealmsgimpl.Show;

public class DealmsgCreateinstance {
    public static Deal createinstance(String msg) {
        Deal deal = null;
        String type = msg.substring(0, 1);
        switch (type) {
            case "H":
                deal = new Show();
                break;
        }
        return deal;
    }
}
