package com.example.a5team_adhd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Handler handler;
    public static String temp;
    String pw = "1";
    String loginid;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = (Button)findViewById(R.id.login);
        final EditText idText =(EditText)findViewById(R.id.idText);
        final EditText pwText =(EditText)findViewById(R.id.pwText);


                ChatClient2.handler = handler = new Handler() {

                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);

                        Bundle bundle = msg.getData();

                        temp = bundle.getString("msg");

                        Log.d("aabb", temp +  "   p_main0 에서받음");

                        String str = temp;
                        String[] words2 = str.split("&&");
                        System.out.println(words2[0] + " id");	// id
                        System.out.println(words2[1] + " 방번호");	// 방번호
                        System.out.println(words2[2] + " 프로토콜");	// [] 프로토콜
                        //  System.out.println(words2[3] + " 내용");	// 내용

                        if(words2[2].equals("[id]")){
                            id = words2[0]+"&&"+words2[1];
                            Log.d("aabb", id +  "   p_main0 id");
                        }else if(words2[2].equals("[로그인성공]")){





                            Intent intent=new Intent(Login.this, testroomlist.class);
                            intent.putExtra("roomid",loginid);
                            startActivity(intent);

                        }else if(words2[2].equals("[로그인실패]")){

                            Toast.makeText(getApplicationContext(),"로그인실패",Toast.LENGTH_LONG);
                            Log.d("aabb", "로그인실패");
                        }
                    }
                };

        ChatClient2 a = new ChatClient2(handler);
        a.start();


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new Thread() {
                    public void run() {
                        loginid = idText.getText()+"&&0";
                        pw = pwText.getText()+"";
                        ChatClient2.pw.println(loginid+"&&[로그인]&&"+pw+"&&"+id);
                        ChatClient2.pw.flush();

                    }
                }.start();


            }
        });

    }

}