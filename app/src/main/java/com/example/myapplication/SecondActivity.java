package com.example.myapplication;

import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONException;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    String url = "https://dummyjson.com/products/",imgURL;
    TextView title,price,rate,reviews,des,cont;
    ArrayList<SlideModel> imgSlide;
    ImageButton btn,add,remove;
    int counter;
    Button cart;
    ImageButton fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String id = getIntent().getStringExtra("id");

        ImageSlider imageSlider;

        cont = findViewById(R.id.cont);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        btn = findViewById(R.id.back);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        rate = findViewById(R.id.rate);
        reviews = findViewById(R.id.reviews);
        des = findViewById(R.id.des);
        fav = findViewById(R.id.fav);

        fav.setOnClickListener(v -> {
            Toast.makeText(this, "Saved at favourite", Toast.LENGTH_SHORT).show();
        });


        counter = Integer.parseInt(cont.getText().toString());

        cart = findViewById(R.id.button);

        cart.setOnClickListener(v -> {
            Toast.makeText(this, "Item add to cart", Toast.LENGTH_SHORT).show();
        });

        add.setOnClickListener(v -> {
            ++counter;

            cont.setText(counter+"");

        });

        remove.setOnClickListener(v -> {
            --counter;
            if (counter<1){
                counter = 1;
            }else {
                cont.setText(counter + "");
            }
        });

        btn.setOnClickListener(v -> {
            finish();
        });

        imageSlider = findViewById(R.id.image_slider);

        imgSlide = new ArrayList<SlideModel>();


        JsonObjectRequest request = new JsonObjectRequest(
        Request.Method.GET,url+id,null, response -> {

            try {
            title.setText(response.getString("title"));
            reviews.setText( response.getString("stock")+" reviews");
            rate.setText( response.getString("rating"));
            price.setText( "$"+response.getString("price"));
            des.setText( response.getString("description"));

                for (int i=0; i <= response.getJSONArray("images").length();i++) {
                    imgURL = response.getJSONArray("images").getString(i);
                    imgSlide.add(new SlideModel(imgURL,ScaleTypes.CENTER_INSIDE));
                    imageSlider.setImageList(imgSlide,ScaleTypes.FIT);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {
            System.out.println(error);
        });
        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(request);

    }

    }
