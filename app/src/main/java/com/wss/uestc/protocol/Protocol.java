package com.wss.uestc.protocol;

public class Protocol {
    private static String forword;
    private static String back;
    private static String left;
    private static String right;
    private static String stop;
    private static String settime;

    public static String getSettime() {
        return settime;
    }

    public static void setSettime(String settime) {
        Protocol.settime = settime;
    }



    public static String getForword() {
        return forword;
    }

    public static void setForword(String forword) {
        Protocol.forword = forword;
    }

    public static String getBack() {
        return back;
    }

    public static void setBack(String back) {
        Protocol.back = back;
    }

    public static String getLeft() {
        return left;
    }

    public static void setLeft(String left) {
        Protocol.left = left;
    }

    public static String getRight() {
        return right;
    }

    public static void setRight(String right) {
        Protocol.right = right;
    }

    public static String getStop() {
        return stop;
    }

    public static void setStop(String stop) {
        Protocol.stop = stop;
    }


}
