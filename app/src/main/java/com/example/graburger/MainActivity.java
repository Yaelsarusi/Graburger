package com.example.graburger;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<FoodItemModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.carousel);

        List<CarouselPicker.PickerItem> imageItems = new ArrayList<>();
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.fries));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.burger));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.soda));
        //Create an adapter
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this, imageItems, 0);
        //Set the adapter
        carouselPicker.setAdapter(imageAdapter);

        carouselPicker.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            // This is a comment

            @Override
            public void onPageSelected(int position) {
                //position of the selected item
                if (position == 1){
                    Intent intent = new Intent(MainActivity.this, buildBurgerActivity.class);
                    // TODO : Make the next line work
                    //intent.putExtra("BurgerItemModel", new BurgerItemModel());
                    startActivity(intent);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        models = new ArrayList<>();
        models.add(new FoodItemModel(R.drawable.burger, "Brochure")); //, "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"
        models.add(new FoodItemModel(R.drawable.soda, "Sticker")); //, "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"
        models.add(new FoodItemModel(R.drawable.fries, "Poster")); //, "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."
        models.add(new FoodItemModel(R.drawable.fries, "Namecard")); //, "Business cards are cards bearing business information about a company or individual."

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);
    }


    public void launchCheckoutDialog(View view) {
    }
}
