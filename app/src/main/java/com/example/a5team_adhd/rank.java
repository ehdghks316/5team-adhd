package com.example.a5team_adhd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;


public class rank extends Activity {


    private ListView listview ;

    private ListViewAdapter_rank adapter;

    Handler handler;
    private String[] Title = {"6/7","4/5","4/4"};
    private String[] Context = {"아무나","초고수만~~","초보만!!"};
    String temp;
     ArrayList arrid = new ArrayList();
     ArrayList arrscore = new ArrayList();

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.rank);




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

                if(words2[2].equals("[랭킹]")){

                    System.out.println(words2[3]);	// 방번호

                    for(int i = 3; i < words2.length-1; i = i+2){
                        arrid.add(words2[i]);
                        arrscore.add(words2[i+1]);

                    }


                    adapter = new ListViewAdapter_rank();

                    //
                    listview = (ListView) findViewById(R.id.List_view_rank); //어뎁터 할당

                    listview.setAdapter(adapter); //adapter를 통한 값 전달



                    for(int i=0; i<arrid.size();i++){
                        adapter.addVO(arrid.get(i)+"",arrscore.get(i)+"");
                    }
                    adapter.notifyDataSetChanged();













                }

            }
        };


        new Thread() {
            public void run() {
                ChatClient2.pw.println(testroomlist.id+"&&[랭킹]&&");
                ChatClient2.pw.flush();
            }
        }.start();






    }

}


