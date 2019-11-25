package com.example.graburger;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter orderListAdapter;
    BurgerItemModel curBurger;
    ArrayList<FoodItemModel> orderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderList = new ArrayList<>();

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

            @Override
            public void onPageSelected(int position) {
                //position of the selected item
                if (position == 1){
                    curBurger = new BurgerItemModel();
                    Intent intent = new Intent(MainActivity.this, buildBurgerActivity.class);
                    intent.putExtra("curBurger", curBurger);
                    startActivityForResult(intent, buildBurgerActivity.ACTIVITY_CODE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO : check if we need to implement
            }
        });

        createOrderCarousel();

    }


    public void launchCheckoutDialog(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == buildBurgerActivity.ACTIVITY_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                BurgerItemModel orderedBurger = (BurgerItemModel) data.getExtras().getSerializable("order");
                orderedBurger.updateBurger();
                orderList.add(orderedBurger);
                orderListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void createOrderCarousel() {
        orderListAdapter = new Adapter(orderList, this);
        viewPager = findViewById(R.id.orderListPager);
        viewPager.setAdapter(orderListAdapter);
        viewPager.setPadding(130, 0, 130, 0);
    }

}
