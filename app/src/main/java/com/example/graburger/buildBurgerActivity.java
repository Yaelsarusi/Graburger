package com.example.graburger;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class buildBurgerActivity extends AppCompatActivity {

    static int CREATE_NEW_ACTIVITY_CODE = 0;
    static int EDIT_ACTIVITY_CODE = 1;
    BurgerItemModel curBurger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_xactivity);

        Intent intent = getIntent();
        curBurger = (BurgerItemModel) intent.getExtras().getSerializable("curBurger");

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
                        curBurger.updateBurger(BunType.get(position));
                        ImageView bottomBunImage = findViewById(R.id.BottomBun);
                        bottomBunImage.setImageResource(curBurger.getBun().getImage(false));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });

        List<FoodItemModel> cheese = new ArrayList<>();
        for (CheeseType c : CheeseType.all()) {
            cheese.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(cheese, R.id.Cheese, curBurger.getCheese().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {}

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                        curBurger.updateBurger(CheeseType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });

        List<FoodItemModel> patty = new ArrayList<>();
        for (PattyType c : PattyType.all()) {
            patty.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(patty, R.id.Patty, curBurger.getPatty().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {}

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                        curBurger.updateBurger(PattyType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });

        List<FoodItemModel> tomato = new ArrayList<>();
        for (TomatoType c : TomatoType.all()) {
            tomato.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(tomato, R.id.Tomato, curBurger.getTomato().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {}

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                        curBurger.updateBurger(TomatoType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });

        List<FoodItemModel> lettuce = new ArrayList<>();
        for (LettuceType c : LettuceType.all()) {
            lettuce.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(lettuce, R.id.Lettuce, curBurger.getLettuce().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {}

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                        curBurger.updateBurger(LettuceType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });

        List<FoodItemModel> sauce = new ArrayList<>();
        for (SauceType c : SauceType.all()) {
            sauce.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }

        setViewPager(sauce, R.id.Sauce, curBurger.getSauce().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {}

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                        curBurger.updateBurger(SauceType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });

        ImageView bottomBunImage = findViewById(R.id.BottomBun);
        bottomBunImage.setImageResource(curBurger.getBun().getImage(false));

        Button orderButton = findViewById(R.id.Order);
        orderButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent resultIntent = new Intent(buildBurgerActivity.this, MainActivity.class);
                resultIntent.putExtra("order", curBurger);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        updateCurrentBurgerDesc(curBurger);
    }

    private void updateCurrentBurgerDesc(BurgerItemModel curBurger) {
        TextView curOrderText = findViewById(R.id.CurOrder);
        curOrderText.setText(getString(R.string.CurOrder) + curBurger.getDesc()); //Todo find out what comment wants
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
