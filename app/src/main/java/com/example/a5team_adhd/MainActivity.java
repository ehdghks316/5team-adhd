package com.example.a5team_adhd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView drawing = (ImageView)findViewById(R.id.drawing2);
        //iv.setImageResource(R.drawable.img);
        //Glide.with(this).load(R.drawable.img).into(iv);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(drawing);
        Glide.with(this).load(R.drawable.drawing).into(imageViewTarget);


    }
}
