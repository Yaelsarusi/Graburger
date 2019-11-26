package com.example.graburger;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter orderListAdapter;
    BurgerItemModel curBurger;
    ArrayList<FoodItemModel> orderList;
    int orderPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderList = new ArrayList<>();

        CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.carousel);

        List<CarouselPicker.PickerItem> imageItems = new ArrayList<>();
        CarouselPicker.DrawableItem fries = new CarouselPicker.DrawableItem(R.drawable.fries);
        CarouselPicker.DrawableItem burger = new CarouselPicker.DrawableItem(R.drawable.burger);
        CarouselPicker.DrawableItem soda = new CarouselPicker.DrawableItem(R.drawable.soda);

        imageItems.add(fries);
        imageItems.add(burger);
        imageItems.add(soda);

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
                    startActivityForResult(intent, buildBurgerActivity.CREATE_NEW_ACTIVITY_CODE);
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
        // Make sure the request was successful
        if (resultCode == RESULT_OK) {
            // Check which request we're responding to
            BurgerItemModel orderedBurger = (BurgerItemModel) data.getExtras().getSerializable("order");
            orderedBurger.updateBurger();
            if (requestCode == buildBurgerActivity.CREATE_NEW_ACTIVITY_CODE) {
                orderList.add(orderedBurger);
            } else if (requestCode == buildBurgerActivity.EDIT_ACTIVITY_CODE) {
                MainActivity.this.orderList.set(MainActivity.this.orderPosition, orderedBurger);
            }
            orderListAdapter.notifyDataSetChanged();
            manageOrderCarouselButtons(false);
        }
    }

    private void createOrderCarousel() {
        orderListAdapter = new Adapter(orderList, this);
        viewPager = findViewById(R.id.orderListPager);
        viewPager.setAdapter(orderListAdapter);
        viewPager.setPadding(130, 0, 130, 0);
        orderPosition = 0;
        manageOrderCarouselButtons(true);

        Button deleteButton = findViewById(R.id.deleteItemButton);
        Button editButton = findViewById(R.id.editItemButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Edit button is shown only if the item is a burger, therfore we can assume at this point the item in the orderPosition is a burger.
                BurgerItemModel curBurger = (BurgerItemModel)MainActivity.this.orderList.get(MainActivity.this.orderPosition);
                Intent intent = new Intent(MainActivity.this, buildBurgerActivity.class);
                intent.putExtra("curBurger", curBurger);
                startActivityForResult(intent, buildBurgerActivity.EDIT_ACTIVITY_CODE);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.orderList.remove(MainActivity.this.orderPosition);
                MainActivity.this.orderListAdapter.notifyDataSetChanged();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!MainActivity.this.orderList.isEmpty()){
                    manageOrderCarouselButtons(false);
                }
            }

            @Override
            public void onPageSelected(int position) {
                //position of the selected item
                MainActivity.this.orderPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO : check if we need to implement
            }

        });
    }

    private void manageOrderCarouselButtons(boolean hide) {
        Button deleteButton = findViewById(R.id.deleteItemButton);
        Button editButton = findViewById(R.id.editItemButton);
        if (hide){
            deleteButton.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
        }
        else {
            deleteButton.setVisibility(View.VISIBLE);
            // Todo - Show edit button only if current item in the carousel is burger
            editButton.setVisibility(View.VISIBLE);
        }
    }
}
