package com.wss.uestc.config;

import java.net.Socket;

public class Config {
    private static String ServerIP;
    private static int ServerPort;
    private static Socket TcpSock;

    public static String getServerIP() {
        return ServerIP;
    }

    public static void setServerIP(String serverIP) {
        ServerIP = serverIP;
    }

    public static int getServerPort() {
        return ServerPort;
    }

    public static void setServerPort(int serverPort) {
        ServerPort = serverPort;
    }

    public static Socket getTcpSock() {
        return TcpSock;
    }

    public static void setTcpSock(Socket tcpSock) {
        TcpSock = tcpSock;
    }



}
