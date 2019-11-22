package com.example.graburger;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class buildBurgerActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_xactivity);


        List<FoodItemModel> UpperBun = new ArrayList<>();
        for (CheeseType c: CheeseType.all()) {
            UpperBun.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(UpperBun, R.id.UpperBun);

    }

    private void setViewPager(List<FoodItemModel> model, int id){
        Adapter adapter;
        ViewPager viewPager;

        adapter = new Adapter(model, this);
        viewPager = findViewById(id);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);
    }



}
