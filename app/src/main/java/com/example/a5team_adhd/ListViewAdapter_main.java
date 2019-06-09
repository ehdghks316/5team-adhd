package com.example.a5team_adhd;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter_main extends BaseAdapter {
    static String id;
    public static String allsubject;
    private ArrayList<ListVO_main> listVO = new ArrayList<ListVO_main>() ;
    public ListViewAdapter_main() {

    }

    @Override
    public int getCount() {
        return listVO.size() ;
    }

    // ** 이 부분에서 리스트뷰에 데이터를 넣어줌 **
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //postion = ListView의 위치      /   첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_custom_listview_main, parent, false);
        }

        //  ImageView image = (ImageView) convertView.findViewById(R.id.img) ;
        final TextView title = (TextView) convertView.findViewById(R.id.title_main) ;
        final TextView Context = (TextView) convertView.findViewById(R.id.context_main) ;


        final ListVO_main listViewItem = listVO.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        //   image.setImageDrawable(listViewItem.getImg());
        title.setText(listViewItem.getTitle());
        Context.setText(listViewItem.getContext());


        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, (pos+1)+"번째 리스트가 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                Log.d("aabb", title.getText()+"");
                Log.d("aabb", Context.getText()+"");
                Log.d("aabb", listViewItem.getAuthor());
                allsubject = Context.getText()+"";

             //   String[] words = id.split("&&");    // hong&&0

                new Thread() {
                    public void run() {
                        ChatClient2.pw.println(testroomlist.id+"&&[방입장]&&"+ listViewItem.getAuthor());
                        ChatClient2.pw.flush();
                    }
                }.start();


             /*   Intent intent=new Intent(context,MainChat.class);
                context.startActivity(intent);
                intent.putExtra("roomid",words[0]+Context.getText());*/


            }
        });

        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position ;
    }


    @Override
    public Object getItem(int position) {
        return listVO.get(position) ;
    }

    // 데이터값 넣어줌
    public void addVO( String title, String desc ,String num) {
        ListVO_main item = new ListVO_main();

        //item.setImg(icon);
        item.setTitle(title);
        item.setContext(desc);
        item.setAuthor(num); // 방제목

        listVO.add(item);
    }
}


