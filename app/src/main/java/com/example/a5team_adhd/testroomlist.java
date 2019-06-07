package com.example.a5team_adhd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


public class testroomlist extends Activity {


    private ListView listview ;

    private ListViewAdapter_main adapter;

    Handler handler;
    private String[] Title = {"6/7","4/5","4/4"};
    private String[] Context = {"아무나","초고수만~~","초보만!!"};

    ArrayList num = new ArrayList();
    ArrayList subject = new ArrayList();
    ArrayList memberid = new ArrayList();
    ArrayList min = new ArrayList();
    ArrayList max = new ArrayList();
    ArrayList status = new ArrayList();
    ChatClient2 a;
    Button reload;
    Button roommake;
    static String id;
    TextView room_subject;
    RadioButton member4;
    Button makeroom_bt;




    String temp;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_room_main);

        reload = (Button)findViewById(R.id.button5);
        roommake = (Button)findViewById(R.id.button4);

        member4 = (RadioButton)findViewById(R.id.member4);


        Intent intent=new Intent(this.getIntent());
        String s =intent.getStringExtra("roomid");
        id = s;

        Log.d("aabb", id  +  "   testroomlist id");



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

                if(words2[2].equals("[새로고침]")){

                    System.out.println(words2[3]);	// 방번호
                    System.out.println(words2[4]);	// 제목
                    System.out.println(words2[5]);	// 최소인원
                    System.out.println(words2[6]);	// 최대인원
                    System.out.println(words2[7]);	// 게임상태
                    System.out.println(words2[8]);	// 방장


                    num.add(words2[3]);
                    subject.add(words2[4]);
                    min.add(words2[5]);
                    max.add(words2[6]);
                    status.add(words2[7]);
                    memberid.add(words2[8]);



                } else if(words2[2].equals("[끝]")){

                    adapter = new ListViewAdapter_main();

                    //
                    listview = (ListView) findViewById(R.id.List_view_main); //어뎁터 할당

                    listview.setAdapter(adapter); //adapter를 통한 값 전달



                    for(int i=0; i<num.size();i++){
                        adapter.addVO(min.get(i)+"/"+max.get(i),subject.get(i)+"",num.get(i)+"");
                    }
                    adapter.notifyDataSetChanged();

                    num.clear();
                    subject.clear();
                    memberid.clear();
                    min.clear();
                    max.clear();
                    status.clear();

                } else if(words2[2].equals("[입장성공]")){

                    /*new Thread() {
                        public void run() {
                            ChatClient2.pw.println(id+"&&[방입장]&&");
                            ChatClient2.pw.flush();
                        }
                    }.start();*/
                    Log.d("aabb","입장성공");
                    testroomlist.id = words2[0]+"&&"+words2[1];
                    Intent intent=new Intent(getApplicationContext(),MainChat.class);
                    intent.putExtra("roomid",testroomlist.id);
                    startActivity(intent);



                }else if(words2[2].equals("[입장실패]")){
                    /*new Thread() {
                        public void run() {
                            ChatClient2.pw.println(id+"&&[방입장]&&");
                            ChatClient2.pw.flush();
                        }
                    }.start();*/
                    Log.d("aabb","입장실패");
                }


            }
        };

        /*a = new ChatClient2(handler);
        a.start();*/
        //Thread mainThread = Thread.currentThread();


        new Thread() {
                     public void run() {
                         a.pw.println(id+"&&[새로고침]&&");
                         a.pw.flush();
                     }
                  }.start();


        reload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   // 채팅
                new Thread() {
                    public void run() {
                        a.pw.println(id+"&&[새로고침]&&");
                        a.pw.flush();
                    }
                }.start();
            }
        });

        roommake.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   // 채팅

                Intent intent=new Intent(testroomlist.this,make_room.class);
                //intent.putExtra("roomid",loginid);
                startActivity(intent);

            }
        });


    }

}


