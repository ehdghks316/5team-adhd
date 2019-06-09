package com.example.a5team_adhd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MainChat extends Activity {
    DrawLine drawLine = null;
  //  public  BufferedReader br = null;
   // public  PrintWriter pw = null;
    private EditText host, port, name;
    private Button btn;
    private String hostv, namev;
    private TextView mCountDown;
    private int portv;
    public static Handler handler;
    public static String  id;
    String encodedImage = null;
    public String temp;
    String return_msg;
    String colortemp = "0";
    public int count = 60; // 카운트 값
    public int count2 = 10; // 카운트 값
    public int count3 = 10;
    public int membercount = 4;
    public int drawcount= 0;
    ImageView iv11;
    ImageView iv12;
    ImageView iv13;
    ImageView iv14;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv10;
    TextView out;
    int tempcount;
    boolean mready = false;
    private ListView listview ;
    String indexmember;
    int vote1;
    int vote2;
    int vote3;
    int vote4;
    TextView tv41;
    TextView tv51;
    TextView tv61;
    TextView tv71;
    LinearLayout ll;
    LinearLayout ll1;
    LinearLayout ll2;
    LinearLayout ll3;
    LinearLayout ll4;
    String temp5;
    RelativeLayout can;

    public int allcount =0;
    int colorRed;
    public boolean king = false;

    public boolean votecheck = false;
    String score;



    private ListViewAdapter adapter;
    private String[] Title = {"O","O","X"};
    ArrayList member = new ArrayList();
    ArrayList readymember = new ArrayList();
    ArrayList arrimg = new ArrayList();
    ArrayList arrtext = new ArrayList();
    ArrayList bit = new ArrayList();
    ArrayList drawmember = new ArrayList();
    ArrayList vote = new ArrayList();
    ArrayList votecount= new ArrayList();
    ArrayList llarr= new ArrayList();
    ArrayList arrencode= new ArrayList();
    String mainid;

    Context a;

    protected void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.room);
        Intent intent=new Intent(this.getIntent());
        String s =intent.getStringExtra("roomid");
        String[] words2 = s.split("&&");
        //System.out.println(words2[0] + " id");	// id
        mainid = words2[0];

        id = s;
       //id = "김동환&&"+s;
       Log.d("aabb", id +  "  id");
         a = getApplicationContext();
        colorRed = getResources().getColor(R.color.color3);
         out=(TextView)findViewById(R.id.out);
        can = (RelativeLayout)findViewById(R.id.llCanvas);
         tv41 =(TextView)findViewById(R.id.textView41);
        tv51 =(TextView)findViewById(R.id.textView51);
        tv61 =(TextView)findViewById(R.id.textView61);
        tv71 =(TextView)findViewById(R.id.textView71);
        tv10 = (TextView)findViewById(R.id.textView10);
        vote.add(tv41);
        vote.add(tv51);
        vote.add(tv61);
        vote.add(tv71);

    /*    votecount.add(0);
        votecount.add(0);
        votecount.add(0);
        votecount.add(0);*/

        ll = (LinearLayout)findViewById(R.id.linear);
        ll1 = (LinearLayout)findViewById(R.id.ll1);
        ll2 = (LinearLayout)findViewById(R.id.ll2);
        ll3 = (LinearLayout)findViewById(R.id.ll3);
        ll4 = (LinearLayout)findViewById(R.id.ll4);
        llarr.add(ll1);
        llarr.add(ll2);
        llarr.add(ll3);
        llarr.add(ll4);






        iv11 = (ImageView)findViewById(R.id.imageView11);
        iv12 = (ImageView)findViewById(R.id.imageView12);
        iv13 = (ImageView)findViewById(R.id.imageView13);
        iv14 = (ImageView)findViewById(R.id.imageView14);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);
        tv6 = (TextView)findViewById(R.id.textView6);
        tv7 = (TextView)findViewById(R.id.textView7);
        arrtext.add(tv4);
        arrtext.add(tv5);
        arrtext.add(tv6);
        arrtext.add(tv7);




        arrimg.add(iv11);
        arrimg.add(iv12);
        arrimg.add(iv13);
        arrimg.add(iv14);

        //member.add("홍승재");
        //readymember.add("X");



        final TextView chat = (TextView) findViewById(R.id.chat);
        final ScrollView sv = (ScrollView) findViewById(R.id.scrollView01);
        final EditText input = (EditText) findViewById(R.id.input);
        Button transfer = (Button) findViewById(R.id.transfer);
        final Button ready = (Button) findViewById(R.id.ready);
        mCountDown = (TextView) findViewById(R.id.tv_countdown);
        mCountDown.setText(String.valueOf(count));





        ChatClient2.handler = handler = new Handler() {

            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                Bundle bundle = msg.getData();

                temp = bundle.getString("msg");

                String str = temp;
                String[] words2 = str.split("&&");

              /*  System.out.println(words2[0]);	// id
                System.out.println(words2[1]);	// 방번호
                System.out.println(words2[2]);	// [] 프로토콜
                System.out.println(words2[3]);	// 내용*/


                Log.d("aabb", bundle.getString("msg") + " 핸들러에서 받은 메시지");


                if (words2[2].equals("[메시지]")) {
                    chat.append(words2[0] +" : " +words2[3]+ "\n");
                }else if(words2[2].equals("[준비완료]")){

                    // id&&방번호&&[준비완료]&&0

                 //   Log.d("aabb", member.get(0)+" 0번째");
                //    Log.d("aabb", member.get(1)+" 1번째");
                    int index = member.indexOf(words2[0]);
                //    Log.d("aabb", index + "  index");
                    readymember.set(index, "O");

                    adapter = new ListViewAdapter();
                    listview = (ListView) findViewById(R.id.List_view); //어뎁터 할당
                    listview.setAdapter(adapter);//adapter를 통한 값 전달

                    for(int i=0; i<member.size(); i++){

                        adapter.addVO(readymember.get(i)+"",member.get(i)+"");

                    }

                    for(int i = 0 ; i < member.size(); i++){
                        if(readymember.get(i).equals("X")){

                            mready = false;
                            break;
                        }
                        mready = true;
                    }

                    if(mready){
                        for(int i =0; i< member.size(); i++){
                            TextView tv = (TextView) arrtext.get(i);
                            tv.setText(member.get(i)+"");
                        }
                      /*  tv4.setText(member.get(0)+"");
                        tv5.setText(member.get(1)+"");
                        tv6.setText(member.get(2)+"");
                        tv7.setText(member.get(3)+"");*/

                       // autoCountHandler.postDelayed(autoCountRunnable, 1000);  // 카운트 시작 메서드
                        new Thread() {
                                     public void run() {
                                            ChatClient2.pw.println(id+"&&[게임시작]&&"+"start");
                                         ChatClient2.pw.flush();
                                     }
                                 }.start();
                        mready= false;
                    }


                }else if(words2[2].equals("[준비취소]")){
                    int index = member.indexOf(words2[0]);
                    readymember.set(index, "X");

                    adapter = new ListViewAdapter();
                    listview = (ListView) findViewById(R.id.List_view); //어뎁터 할당
                    listview.setAdapter(adapter);//adapter를 통한 값 전달

                    for(int i=0; i<member.size(); i++){

                        adapter.addVO(readymember.get(i)+"",member.get(i)+"");

                    }
                }else if(words2[2].equals("[게임시작]")){
                    allcount =0;
                    allcount++;
                    Log.d("aabb","게임시작 프로토콜");

                    if(mainid.equals(member.get(0))){
                        king = true;
                        Log.d("aabb", "너님 방장이욘");
                        //너가 방장
                    }

                    if(king){
                    new Thread() {
                        public void run() {
                            ChatClient2.pw.println(id + "&&[주제]&&11");
                            ChatClient2.pw.flush();
                        }
                    }.start();}
                    autoCountHandler.postDelayed(autoCountRunnable, 1000);
                }else if(words2[2].equals("[그림]")){

                    tempcount++;
                   /* System.out.println(words2[0]);	// id
                    System.out.println(words2[1]);	// 방번호
                    System.out.println(words2[2]);	// [] 프로토콜
                    System.out.println(words2[3]);	// 내용*/
                   Log.d("aabb","그림 들어옴");
                   Log.d("aabb", membercount +"  " + drawcount);

                   //int index = member.indexOf(words2[0]);

                       // if(drawcount == membercount-1){

                            String encodedImage = words2[3];
                            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            arrencode.add(encodedImage);
                            votecount.add(0);
                           drawmember.add(words2[0]);
                            bit.add(decodedByte);
                            Log.d("aabb", tempcount + "  tempcount");
                    Log.d("aabb", member.size() + "  membersize");

                            if(tempcount == member.size()){
                                Log.d("aabb", "  그림뿌려");
                                for(int i =0; i< member.size(); i++){
                                    int index = member.indexOf(drawmember.get(i));
                                    TextView tv = (TextView) arrtext.get(index);
                                    ImageView iv = (ImageView) arrimg.get(index);
                                    TextView votetv = (TextView) vote.get(index);




                                    tv.setText(member.get(index)+"");
                                    votetv.setText("0");
                                 //   tv.setVisibility(View.VISIBLE);

                                    Bitmap bitt = (Bitmap)bit.get(i);
                                    iv.setImageBitmap(bitt);
                                //    iv.setVisibility(View.VISIBLE);
                                }
                                ll.setVisibility(View.VISIBLE);
                                autoCountHandler2.postDelayed(autoCountRunnable2, 1000);  // 카운트 시작 메서드
                                Log.d("aabb", "  투표시작");
                                tempcount = 0;

                              //  drawmember.clear();

                            }

                }else if(words2[2].equals("[방인원]")){

                   /* System.out.println(words2[0]);	// id
                    System.out.println(words2[1]);	// 방번호
                    System.out.println(words2[2]);	// [] 프로토콜
                    System.out.println(words2[3]);	// 내용*/

                   Log.d("aabb"," 방인원 프로토콜");

                   for(int i =3 ; i < words2.length; i = i+2){
                       Log.d("aabb", words2[i] +  "  방인원");
                       if (member.indexOf(words2[i]) == -1) {   //기존에 있는 경우는 추가않함
                           member.add(words2[i]);
                           readymember.add("X");
                       }

                   }

                    adapter = new ListViewAdapter();
                    listview = (ListView) findViewById(R.id.List_view); //어뎁터 할당
                    listview.setAdapter(adapter);//adapter를 통한 값 전달

                    for(int i=0; i<member.size(); i++){

                        adapter.addVO(readymember.get(i)+"",member.get(i)+"");
                        Log.d("aabb", member.get(i)+"  들어온멤버");
                    }

                //    membercount = member.size();

                }else if(words2[2].equals("[투표]")){

                   /* System.out.println(words2[0]);	// id
                    System.out.println(words2[1]);	// 방번호
                    System.out.println(words2[2]);	// [] 프로토콜
                    System.out.println(words2[3]);	// 내용*/

                    int index = member.indexOf(words2[3]);
                    Log.d("aabb", index + "  index값");

                    String st = votecount.get(index)+"";
                    int temp2 = Integer.parseInt(st);
                    temp2 = temp2+1;
                    Log.d("aabb", temp2+ "  temp2값");

                    votecount.set(index, temp2);

                    TextView tv = (TextView)vote.get(index);
                    tv.setText(temp2+"");

                }else if(words2[2].equals("[주제]")){

                  /* System.out.println(words2[0]);	// id
                    System.out.println(words2[1]);	// 방번호
                    System.out.println(words2[2]);	// [] 프로토콜
                    System.out.println(words2[3]);	// 내용*/
                    Log.d("aabb", words2[3] + "  주제");
                  tv10.setText(words2[3]);


                }
                else if(words2[2].equals("[방초기화]")){

                  /* System.out.println(words2[0]);	// id
                    System.out.println(words2[1]);	// 방번호
                    System.out.println(words2[2]);	// [] 프로토콜
                    System.out.println(words2[3]);	// 내용*/

                    for(int i = 0 ; i < readymember.size(); i++){
                        readymember.set(i,"X");
                    }

                    adapter = new ListViewAdapter();
                    listview = (ListView) findViewById(R.id.List_view); //어뎁터 할당
                    listview.setAdapter(adapter);//adapter를 통한 값 전달

                    for(int i=0; i<member.size(); i++){

                        adapter.addVO(readymember.get(i)+"",member.get(i)+"");
                        Log.d("aabb", member.get(i)+"  들어온멤버");
                    }


                }
                else if(words2[2].equals("[나가기]")){

                   /* System.out.println(words2[0]);	// id
                    System.out.println(words2[1]);	// 방번호
                    System.out.println(words2[2]);	// [] 프로토콜
                    System.out.println(words2[3]);	// 내용*/

        member.clear();
        readymember.clear();


                    Log.d("aabb"," 나가기 프로토콜");

                    for(int i =3 ; i < words2.length; i = i+2){
                        Log.d("aabb", words2[i] +  "  방인원");
                      //  if (member.indexOf(words2[i]) == -1) {   //기존에 있는 경우는 추가않함
                            member.add(words2[i]);
                            readymember.add("X");
                      //  }

                    }

                    adapter = new ListViewAdapter();
                    listview = (ListView) findViewById(R.id.List_view); //어뎁터 할당
                    listview.setAdapter(adapter);//adapter를 통한 값 전달

                    for(int i=0; i<member.size(); i++){

                        adapter.addVO(readymember.get(i)+"",member.get(i)+"");
                        Log.d("aabb", member.get(i)+"  들어온멤버");
                    }




                }

              //  Toast.makeText(getApplicationContext(), bundle.getString("msg"), Toast.LENGTH_SHORT);
            }
        };

       // ChatClient2 a = new ChatClient2(handler);
        // a.start();

        new Thread() {
            public void run() {
                ChatClient2.pw.println(id+"&&[방인원]&&");
                ChatClient2.pw.flush();
            }
        }.start();

       //  autoCountHandler.postDelayed(autoCountRunnable, 1000);  //-----------------------------------------------------------------------------------------------------------------

        transfer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   // 채팅
                if (input.getText().toString() != null && !input.getText().toString().equals("")) {
                   return_msg = input.getText().toString();
                    input.setText(""); // clear edit text view
                    Log.d("aabb", return_msg +  " : 이 메시지를 서버로");
                    try {



                        new Thread() {
                            public void run() {
                                ChatClient2.pw.println(id+"&&[메시지]&&"+return_msg);
                                ChatClient2.pw.flush();
                            }
                        }.start();


                    }catch (Exception e){
                        Log.d("aabb", e.getMessage());
                    }
                }
            }
        });

        ready.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if(colortemp.equals("0")) { // 준비 안된 상태일 때 준비상태로

                        ready.setTextColor(Color.GREEN);
                        colortemp = "1";


                        new Thread() {
                            public void run() {
                                ChatClient2.pw.println(id+"&&[준비완료]&&"+return_msg);
                                ChatClient2.pw.flush();
                            }
                        }.start();

                    }else {
                        ready.setTextColor(Color.WHITE);
                        colortemp = "0";
                        new Thread() {
                            public void run() {
                                ChatClient2.pw.println(id+"&&[준비취소]&&"+return_msg);
                                ChatClient2.pw.flush();
                            }
                        }.start();
                }


            }
        });


        out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new Thread() {
                    public void run() {
                        ChatClient2.pw.println(id+"&&[나가기]&&"+ListViewAdapter_main.allsubject);
                        ChatClient2.pw.flush();
                    }
                }.start();


                Intent intent=new Intent(MainChat.this,testroomlist.class);
                String loginid = mainid+"&&"+"0";
                intent.putExtra("roomid",loginid);
                startActivity(intent);

            }
        });


        iv11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 indexmember = tv4.getText()+"";
                 Log.d("aabb", indexmember + "  indexmember");
                Log.d("aabb", mainid + "  mainid");

            if(!votecheck && !mainid.equals(indexmember)) {
                new Thread() {
                    public void run() {
                        ChatClient2.pw.println(id + "&&[투표]&&" + indexmember);
                        ChatClient2.pw.flush();
                    }
                }.start();
              //  LinearLayout li = (LinearLayout)llarr.get(0);
                ll1.setBackgroundResource(R.drawable.customborder);
                votecheck = true;
            }


            }
        });

        iv12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                indexmember = tv5.getText()+"";
                if(!votecheck && !mainid.equals(indexmember)) {
                    new Thread() {
                        public void run() {
                            ChatClient2.pw.println(id + "&&[투표]&&" + indexmember);
                            ChatClient2.pw.flush();
                        }
                    }.start();
                    ll2.setBackgroundResource(R.drawable.customborder);
                    votecheck = true;
                }

            }
        });

        iv13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                indexmember = tv6.getText()+"";
                if(!votecheck && !mainid.equals(indexmember)) {
                    new Thread() {
                        public void run() {
                            ChatClient2.pw.println(id + "&&[투표]&&" + indexmember);
                            ChatClient2.pw.flush();
                        }
                    }.start();
                    ll3.setBackgroundResource(R.drawable.customborder);
                    votecheck = true;
                }


            }
        });

        iv14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                indexmember = tv7.getText()+"";
                if(!votecheck && !mainid.equals(indexmember)) {
                    new Thread() {
                        public void run() {
                            ChatClient2.pw.println(id + "&&[투표]&&" + indexmember);
                            ChatClient2.pw.flush();
                        }
                    }.start();
                    ll4.setBackgroundResource(R.drawable.customborder);
                    votecheck = true;
                }

            }
        });


    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        //hasFocus : 앱이 화면에 보여졌을때 true로 설정되어 호출됨.
        //만약 그리기 뷰 전역변수에 값이 없을경우 전역변수를 초기화 시킴.
        if(hasFocus && drawLine == null)
        {
            //그리기 뷰가 보여질(나타날) 레이아웃 찾기..
            RelativeLayout llCanvas = (RelativeLayout)findViewById(R.id.llCanvas);
            if(llCanvas != null) //그리기 뷰가 보여질 레이아웃이 있으면...
            {
                //그리기 뷰 레이아웃의 넓이와 높이를 찾아서 Rect 변수 생성.
                Rect rect = new Rect(0, 0,
                        llCanvas.getMeasuredWidth(), llCanvas.getMeasuredHeight());

                //그리기 뷰 초기화..
                drawLine = new DrawLine(this, rect);

                //그리기 뷰를 그리기 뷰 레이아웃에 넣기 -- 이렇게 하면 그리기 뷰가 화면에 보여지게 됨.
                llCanvas.addView(drawLine);
            }

            //이건.. 상단 메뉴(RED, BLUE ~~~)버튼 설정...
            //일단 초기값은 0번(RED)으로.. ^^
            resetCurrentMode(0);
        }

        super.onWindowFocusChanged(hasFocus);
    }


    //코딩 하기 쉽게 하기 위해서.. 사용할 상단 메뉴 버튼들의 아이디를 배열에 넣는다..
    private int[] btns = {R.id.btnRED, R.id.btnBLUE, R.id.btnGREEN, R.id.btnBLACK, R.id.btnClear};
    //코딩 하기 쉽게 하기 위해서.. 상단 메뉴 버튼의 배열과 똑같이 실제 색상값을 배열로 만든다.

    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.rgb(255,228,225)};



    void resetCurrentMode(int curMode)
    {
        for(int i=0;i<btns.length;i++)
        {
            //이건.. 배열 뒤지면서... 버튼이 있는지 체크..
            Button btn = (Button)findViewById(btns[i]);
            if(btn != null)
            {
                //버튼 있으면 배경색과 글자색 변경..
                //만약 선택한 버튼값과 찾은 버튼이 동일하면 회색배경에 흰색글자 버튼으로 변경.
                //동일하지 않으면 흰색배경에 회색글자 버튼으로 변경.
             //   btn.setBackgroundColor(i==curMode?0xff555555:0xffffffff);
            //    btn.setTextColor(i==curMode?0xffffffff:0xff555555);
            }
        }

        //만약 그리기 뷰가 초기화 되었으면, 그리기 뷰에 글자색을 알려줌..
        if(drawLine != null) drawLine.setLineColor(colors[curMode]);
    }

    public void btnClick(View view)
    {
        if(view == null) return;


        Log.d("aabb",drawLine.bitmap+"");


        for(int i=0;i<btns.length;i++)
        {
            //배열 뒤지면서 클릭한 버튼이 있는지 확인..
            if(btns[i] == view.getId())
            {
                //만약 선택한 버튼이 있으면.. 버튼모양 및 그리기 뷰 설정을 하기 위해서 함수 호출..
                resetCurrentMode(i);

                //더이상 처리를 할 필요가 없으니까.. for문을 빠져 나옴..
                break;
            }
        }
    }


    private Handler autoCountHandler = new Handler();
    private Runnable autoCountRunnable = new Runnable() {

        public void run() {

            count--;
            mCountDown.setText(String.valueOf(count));
            if(count > 0) {
                autoCountHandler.postDelayed(autoCountRunnable, 1000);
            } else {
                if (autoCountHandler != null) {
                    autoCountHandler.removeCallbacks(autoCountRunnable);



                    can.setVisibility(View.GONE);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    drawLine.bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();
                    encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    encodedImage = encodedImage.replaceAll(System.getProperty("line.separator"), ",");
                //    byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
               //     Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

               //     iv11.setImageBitmap(decodedByte);
                //    iv11.setVisibility(View.VISIBLE);
                    Log.d("aabb","  비트맵"+encodedImage + "  비트맵");
                    count = 60;

                    new Thread() {
                        public void run() {
                            ChatClient2.pw.println(id+"&&[그림]&&"+encodedImage);
                            ChatClient2.pw.flush();
                        }
                    }.start();

                    drawLine.bitmap.eraseColor(Color.TRANSPARENT);





                }
            }
        }
    };


    private Handler autoCountHandler2 = new Handler();
    private Runnable autoCountRunnable2 = new Runnable() {
        public void run() {

            count2--;
            mCountDown.setText(String.valueOf(count2));
            if(count2 > 0) {
                autoCountHandler2.postDelayed(autoCountRunnable2, 1000);
            } else {
                if (autoCountHandler2 != null) {

               /*     for(int i=0; i<member.size(); i++){
                        ImageView iv = (ImageView)arrimg.get(i);
                        TextView tv = (TextView)arrtext.get(i);
                        iv.setVisibility(View.GONE);
                        tv.setVisibility(View.GONE);

                    }*/




                    autoCountHandler2.removeCallbacks(autoCountRunnable2);


                    String a = Collections.max(votecount)+"";
                    Log.d("aabb", a + "   투표수 가장많은 값");


                    //결과
                    for(int i = 0 ; i < votecount.size(); i++){
                        String b = votecount.get(i)+"";
                        if(b.equals(a)){
                             score = drawmember.get(i)+"";
                             temp5 = arrencode.get(i)+"";
                             if(score.equals(mainid)){
                                 if(tv10.getText().equals(mainid))Toast.makeText(getApplicationContext(),"+10",Toast.LENGTH_LONG).show();
                                 new Thread() {
                                     public void run() {
                                         ChatClient2.pw.println(id+"&&[점수]&&"+score+"&&"+temp5+"&&"+tv10.getText());
                                         ChatClient2.pw.flush();
                                     }
                                 }.start();
                             }

                            LinearLayout li = (LinearLayout)llarr.get(i);
                            li.setBackgroundResource(R.drawable.customborder2);
                        }
                    }


                    autoCountHandler3.postDelayed(autoCountRunnable3, 500);  // 카운트 시작 메서드
                    Log.d("aabb","결과시작");
                count2 = 10;

                }

            }
        }
    };

    private Handler autoCountHandler3 = new Handler();
    private Runnable autoCountRunnable3 = new Runnable() {          //1등작품
        public void run() {

            count3--;
            mCountDown.setText(String.valueOf(count3));
            if(count3> 0) {
                autoCountHandler3.postDelayed(autoCountRunnable3, 500);
            } else {
                if (autoCountHandler3 != null) {

               /*     for(int i=0; i<member.size(); i++){
                        ImageView iv = (ImageView)arrimg.get(i);
                        TextView tv = (TextView)arrtext.get(i);
                        iv.setVisibility(View.GONE);
                        tv.setVisibility(View.GONE);

                    }*/
                    //  ll.setVisibility(View.GONE);
                    //  drawmember.clear();
                    //   votecount.clear();
                    //    votecheck = false;
                    can.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.GONE);
                    drawmember.clear();
                    votecount.clear();
                    bit.clear();
                    arrencode.clear();
                    votecheck = false;

                    for (int i = 0; i < llarr.size(); i++) {
                        //   String b = votecount.get(i)+"";
                        //  if(b.equals(a)){
                        LinearLayout li = (LinearLayout) llarr.get(i);
                        li.setBackgroundResource(R.drawable.customborder3);
                        //    }
                    }

                    autoCountHandler3.removeCallbacks(autoCountRunnable3);

                    if (king) {
                        new Thread() {
                            public void run() {
                                ChatClient2.pw.println(id + "&&[주제]&&22");
                                ChatClient2.pw.flush();
                            }
                        }.start();
                    }
                    allcount++;


                    Log.d("aabb", "그림시작");
                    count3 = 10;
                    if (allcount != 3) {
                        autoCountHandler.postDelayed(autoCountRunnable, 1000);
                    }  // 그림시작
                    else {

                            new Thread() {
                                public void run() {
                                    ChatClient2.pw.println(id + "&&[방초기화]&&");
                                    ChatClient2.pw.flush();
                                }
                            }.start();
                        }

                }
            }
        }
    };




}