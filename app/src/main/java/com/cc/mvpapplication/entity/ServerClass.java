package com.cc.mvpapplication.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {


    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new MyRunnable(socket)).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    static class MyRunnable implements Runnable{

        Socket socket = null;

        MyRunnable(Socket socket){
            this.socket =socket;
        }


        @Override
        public void run() {
            String line = null;
            InputStream inputStream;
            OutputStream outputStream;
            String str = "hello world";
            try {
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                outputStream.write(str.getBytes());
                outputStream.flush();
                socket.shutdownOutput();
                while ((line = br.readLine())!=null){
                    System.out.println(line);
                }

                outputStream.close();
                br.close();
                inputStream.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
