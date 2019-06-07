package com.example.a5team_adhd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.a5team_adhd.testroomlist.id;


public class make_room extends Activity {
    Button makeroom_bt;
    Handler handler;
    String temp;
    TextView room_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.make_room);

        ChatClient2.handler = handler = new Handler() {

            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Bundle bundle = msg.getData();

                temp = bundle.getString("msg");

                Log.d("aabb", temp +  "   testroomlist 에서받음");

                String str = temp;
                String[] words2 = str.split("&&");
                System.out.println(words2[0] + " id");	// id
                System.out.println(words2[1] + " 방번호");	// 방번호
                System.out.println(words2[2] + " 프로토콜");	// [] 프로토콜

                if(words2[2].equals("[방만들기]")){

                    String loginid = words2[0]+"&&"+words2[1];


                    Intent intent=new Intent(make_room.this,MainChat.class);
                    intent.putExtra("roomid",loginid);
                    startActivity(intent);

                }


            }
        };

        room_subject =(TextView)findViewById(R.id.room_subject);
        makeroom_bt =(Button)findViewById(R.id.makeroom_bt);



        makeroom_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   // 채팅


                new Thread() {
                    public void run() {
                        ChatClient2.pw.println(id+"&&[방만들기]&&"+room_subject.getText());
                        ChatClient2.pw.flush();
                    }
                }.start();



            }
        });

    }

}


