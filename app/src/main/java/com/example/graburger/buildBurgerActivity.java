package com.example.graburger;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class buildBurgerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_xactivity);
        List<FoodItemModel> models;
        Adapter adapter;
        ViewPager viewPager;

        models = new ArrayList<>();
        models.add(new FoodItemModel(R.drawable.fries, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        models.add(new FoodItemModel(R.drawable.fries, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        models.add(new FoodItemModel(R.drawable.fries, "Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);
    }
}
