package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    ImageView img;
    String id,Title,Des,Price,Img;
    int Fav;
    ArrayList<DataItem> Data;
    Adaptor adapter;
    RecyclerView recyclerView;
    String url = "https://dummyjson.com/products/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volleyGetJsonObjectRequest();

        img = findViewById(R.id.img);
        Data = new ArrayList<>();
        recyclerView = findViewById(R.id.Recycler);
        adapter = new Adaptor(Data,this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL));

    }

    private void volleyGetJsonObjectRequest() {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                        try {

                    for (int i=0; i <= response.getJSONArray("products").length();i++) {

                        id = response.getJSONArray("products").getJSONObject(i).getString("id");
                        Title = response.getJSONArray("products").getJSONObject(i).getString("title");
                        Des = response.getJSONArray("products").getJSONObject(i).getString("description");
                        Price = "$"+response.getJSONArray("products").getJSONObject(i).getString("price");
                        Img = response.getJSONArray("products").getJSONObject(i).getString("thumbnail");
                        Fav = R.drawable.heart;
                        Data.add(new DataItem(id,Title,Des,Price,Img,Fav));
                        recyclerView.setAdapter(adapter);

                    }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                }, error -> {


                });

        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(jsonObjectRequest);

    }

    @Override
    public void onClickListener(int Position) {

        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("id",Data.get(Position).getId());
        startActivity(intent);


    }
}