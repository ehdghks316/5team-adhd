package com.example.a5team_adhd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class realmain extends Activity {
    public static Handler handler2;
    TextView tv;
    //static String line = null;
    String line = null;
    Button button3;
    EditText edit;
    String temp;
    Handler handler;
    ChatClient2 a;
    String id;
    String pw = "1";

    Button log_bt;
    Button member_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realmain);



        //a = new ChatClient2(handler);
        //a.start();

        log_bt = (Button)findViewById(R.id.log_bt);
        member_bt= (Button)findViewById(R.id.member_bt);

        log_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent=new Intent(realmain.this,Login.class);
                startActivity(intent);

            }
        });


    }
}
