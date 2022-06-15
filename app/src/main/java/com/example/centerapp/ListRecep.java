package com.example.centerapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ListRecep extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ListRecepModel> listRecep;
    ListRecepAdapter adapter;
    ImageButton imageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recep);
        imageButton1 = findViewById(R.id.imageButton1);
        //imageButton1.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View v) {
            //    Intent i = new Intent(ListRecep.this, .class);
            //    startActivity(i);
             //   finish();
          //  }
        //});

        initData();
        initRecyclerView();



    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListRecepAdapter(listRecep);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }

    private void initData() {
        listRecep = new ArrayList<>();
        listRecep.add(new ListRecepModel("bouchra"
        ,"allalou","cinovac","1200"));
        listRecep.add(new ListRecepModel("bouchra"
                ,"allalou","cinovac","1200"));
        listRecep.add(new ListRecepModel("bouchra"
                ,"allalou","cinovac","1200"));
        listRecep.add(new ListRecepModel("bouchra"
                ,"allalou","cinovac","1200"));
        listRecep.add(new ListRecepModel("bouchra"
                ,"allalou","cinovac","1200"));
        listRecep.add(new ListRecepModel("bouchra"
                ,"allalou","cinovac","1200"));










    }
}