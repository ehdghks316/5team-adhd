package com.example.a5team_adhd;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class room extends Activity {


    private ListView listview ;

    private ListViewAdapter adapter;

    private int[] img = {R.drawable.p1,R.drawable.p2,R.drawable.p3};

    private String[] Title = {"6/7","4/5","4/4"};

    private String[] Context = {"아무나","초고수만~~","초보만!!"};

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//변수 초기화

        adapter = new ListViewAdapter();

        listview = (ListView) findViewById(R.id.List_view);

//어뎁터 할당

        listview.setAdapter(adapter);

//adapter를 통한 값 전달

        for(int i=0; i<img.length;i++){

            adapter.addVO(ContextCompat.getDrawable(this,img[i]),Title[i],Context[i]);

        }

    }

}


