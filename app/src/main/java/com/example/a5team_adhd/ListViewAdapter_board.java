package com.example.a5team_adhd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter_board extends BaseAdapter {

 private ArrayList<ListVO> listVO = new ArrayList<ListVO>() ;
public ListViewAdapter_board() {

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
               convertView = inflater.inflate(R.layout.board_custom_listview, parent, false);
         }

 //  ImageView image = (ImageView) convertView.findViewById(R.id.img) ;
      TextView title = (TextView) convertView.findViewById(R.id.title) ;
   TextView Context = (TextView) convertView.findViewById(R.id.context) ;
    ImageView img = (ImageView) convertView.findViewById(R.id.imageView);




      ListVO listViewItem = listVO.get(position);

   // 아이템 내 각 위젯에 데이터 반영
   //   image.setImageDrawable(listViewItem.getImg());
    title.setText(listViewItem.getTitle());
     Context.setText(listViewItem.getContext());


    String encodedImage = listViewItem.getAuthor();
    byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    img.setImageBitmap(decodedByte);

        //리스트뷰 클릭 이벤트
      convertView.setOnClickListener(new View.OnClickListener() {
   @Override
  public void onClick(View view) {
                    Toast.makeText(context, (pos+1)+"번째 리스트가 클릭되었습니다.", Toast.LENGTH_SHORT).show();
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
   public void addVO( String title, String desc,String author) {
    ListVO item = new ListVO();

    //item.setImg(icon);
       item.setTitle(title);
   item.setContext(desc);
   item.setAuthor(author);

listVO.add(item);
}
}


