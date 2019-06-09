package com.example.a5team_adhd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;


public class board extends Activity {


    private ListView listview ;

    private ListViewAdapter_board adapter;

    Handler handler;
    private String[] Title = {"6/7","4/5","4/4"};
    private String[] Context = {"아무나","초고수만~~","초보만!!"};
    String temp;
     ArrayList arrid = new ArrayList();
     ArrayList arrsubject = new ArrayList();
    ArrayList pic = new ArrayList();

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.board);




        ChatClient2.handler = handler = new Handler() {

            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Bundle bundle = msg.getData();

                temp = bundle.getString("msg");

                Log.d("aabb", temp +  "   rank 에서받음");

                String str = temp;
                String[] words2 = str.split("&&");
                System.out.println(words2[0] + " id");	// id
                System.out.println(words2[1] + " 방번호");	// 방번호
                System.out.println(words2[2] + " 프로토콜");	// [] 프로토콜

                if(words2[2].equals("[게시판]")){

                    System.out.println(words2[3]);	// 방번호

                    for(int i = 3; i < words2.length-1; i = i+3){
                        arrid.add(words2[i]);
                        arrsubject.add(words2[i+1]);
                        pic.add(words2[i+2]);

                    }


                    adapter = new ListViewAdapter_board();

                    //
                    listview = (ListView) findViewById(R.id.List_view_board); //어뎁터 할당

                    listview.setAdapter(adapter); //adapter를 통한 값 전달



                    for(int i=0; i<arrid.size();i++){
                        adapter.addVO(arrid.get(i)+"",arrsubject.get(i)+"",pic.get(i)+"");
                    }
                    adapter.notifyDataSetChanged();













                }

            }
        };


        new Thread() {
            public void run() {
                ChatClient2.pw.println(testroomlist.id+"&&[게시판]&&");
                ChatClient2.pw.flush();
            }
        }.start();






    }

}


