package com.example.a5team_adhd;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient2 extends Thread{
    public static Socket sock = null;
    public static BufferedReader br = null;
    public static PrintWriter pw = null;
    public static Handler handler;

    public ChatClient2(Handler handler){
        this.handler = handler;

    }

    @Override
    public void run() {

        try {

            sock = new Socket("203.234.62.88", 9998);//아아디,포트
            pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(sock + " client 소켓정보");
          //  pw.println("hong&&0");
         //   pw.flush();
           // InputThread2 it = new InputThread2(sock,br);
           // it.start();
            String line = null;
         //   pw.println("hong&&1"+"&&[입장]&&"+"hello~");
          //  pw.flush();
         //   pw.println("새로고침");
       //     pw.flush();

            while((line = br.readLine()) != null) {
                 System.out.println(line + "  서버에서 받음");
                Log.d("aabb", line + " 서버에서 받음");

                Message m = new Message();

                Bundle bundle = new Bundle();

                bundle.putString("msg", line);

                m.setData(bundle);

                handler.sendMessage(m);


            }

            System.out.println("클라이언트 접속 종료");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(pw != null) {
                    pw.close();
                }
                if(br != null) {
                    br.close();
                }
                if(sock != null) {
                    sock.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

       // ChatClient2 a = new ChatClient2();
      //  a.start();

    }
}

/*
class InputThread2 extends Thread{
    private Socket sock = null;
    private BufferedReader br = null;

    Handler handler;

    public InputThread2(Socket sock,BufferedReader br) {
        this.sock = sock;
        this.br = br;
    }
    public void run() {
        try {
            String line = null;

            while((line = br.readLine()) != null) {
               // System.out.println(line + "  서버에서 받음");
                Log.d("aabb", line + " 서버에서 받음");

                Message m = new Message();

                Bundle bundle = new Bundle();

                bundle.putString("msg", line);

                m.setData(bundle);

                handler.sendMessage(m);
                p_main0.handler2.sendMessage(m);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
              try {
                if(br != null) {
                  br.close();
                }
                if(sock != null) {
                    sock.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


*/
