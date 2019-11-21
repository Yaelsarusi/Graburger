package com.example.graburger;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class buildBurgerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_xactivity);


        // TODO : Make the next line work
        //BurgerItemModel curBurger =  (BurgerItemModel)getIntent().getExtras().getSerializable("BurgerItemModel");
        final BurgerItemModel curBurger = new BurgerItemModel();
        curBurger.updateBurger(BunType.WHOLE);

        List<FoodItemModel> upperBun = new ArrayList<>();
        List<FoodItemModel> bottomBun = new ArrayList<>();

        for (BunType c : BunType.all()) {
            upperBun.add(new FoodItemModel(c.getImage(true), c.getTitle()));
            bottomBun.add(new FoodItemModel(c.getImage(false), c.getTitle()));
        }

        setViewPager(upperBun, R.id.UpperBun, curBurger.getBun().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {}

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        ImageView bottomBunImage = findViewById(R.id.BottomBun);
                        final BurgerItemModel currentBurger = curBurger;
                        bottomBunImage.setImageResource(currentBurger.getBun().getImage(false));
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });

        List<FoodItemModel> cheese = new ArrayList<>();
        for (CheeseType c : CheeseType.all()) {
            cheese.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(cheese, R.id.Cheese, curBurger.getCheese().getPosition());

        List<FoodItemModel> patty = new ArrayList<>();
        for (PattyType c : PattyType.all()) {
            patty.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(patty, R.id.Patty, curBurger.getPatty().getPosition());

        List<FoodItemModel> tomato = new ArrayList<>();
        for (TomatoType c : TomatoType.all()) {
            tomato.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(tomato, R.id.Tomato, curBurger.getTomato().getPosition());

        List<FoodItemModel> lettuce = new ArrayList<>();
        for (LettuceType c : LettuceType.all()) {
            lettuce.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(lettuce, R.id.Lettuce, curBurger.getLettuce().getPosition());

        List<FoodItemModel> sauce = new ArrayList<>();
        for (SauceType c : SauceType.all()) {
            sauce.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(sauce, R.id.Sauce, curBurger.getSauce().getPosition());

        ImageView bottomBunImage = findViewById(R.id.BottomBun);
        bottomBunImage.setImageResource(curBurger.getBun().getImage(false));

        Button orderButton = findViewById(R.id.Order);
        orderButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final BurgerItemModel currentBurger = curBurger;
                // TODO
            }
        });

    }

    private void setViewPager(List<FoodItemModel> model, int id, int position) {
        Adapter adapter;
        ViewPager viewPager;

        adapter = new Adapter(model, this);
        viewPager = findViewById(id);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    private void setViewPager(List<FoodItemModel> model, int id, int position, ViewPager.OnPageChangeListener listener ) {
        Adapter adapter;
        ViewPager viewPager;

        adapter = new Adapter(model, this);
        viewPager = findViewById(id);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(listener);

    }
}
