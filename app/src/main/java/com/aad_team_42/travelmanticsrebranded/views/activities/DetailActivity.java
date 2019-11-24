package com.aad_team_42.travelmanticsrebranded.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aad_team_42.travelmanticsrebranded.R;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
  TextView destination, about,price;
  ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        destination = findViewById(R.id.destination);
        about = findViewById(R.id.tour_detail);
        price = findViewById(R.id.price);
        image = findViewById(R.id.image_tour);

        receiveDetail();
    }

    public void receiveDetail(){
        String extras = getIntent().getStringExtra("image");
        destination.setText(getIntent().getStringExtra("destination"));
        about.setText(getIntent().getStringExtra("about"));
        price.setText(getIntent().getStringExtra("price"));
        if (extras != null) {
            Glide.with(this).load(extras).into(image);
        }

    }
}
