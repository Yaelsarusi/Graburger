package com.example.graburger;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import in.goodiebag.carouselpicker.CarouselPicker;

public class MainActivity extends AppCompatActivity {

    int orderCarouselPosition;
    ViewPager orderCarouselViewPager;
    CardAdapter orderCarouselCardAdapter;
    BurgerItemModel curBurger;
    CarouselPicker carouselPicker;
    ArrayList<FoodItemModel> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNewItemCarousel();
        createOrderCarousel();
    }

    private void createNewItemCarousel() {
        carouselPicker = (CarouselPicker) findViewById(R.id.carouselFoodItems);

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

        final GestureDetector gestureDetector = new GestureDetector(this, new TapGestureDetector());

        carouselPicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }

        });
    }

    private void createOrderCarousel() {
        this.orderList = new ArrayList<>();
        orderCarouselCardAdapter = new CardAdapter(orderList, this);
        orderCarouselViewPager = findViewById(R.id.viewPagerOrderList);
        orderCarouselViewPager.setAdapter(orderCarouselCardAdapter);
        orderCarouselViewPager.setPadding(130, 0, 130, 0);
        orderCarouselPosition = 0;
        manageOrderCarouselView(false);

        // TODO: need to add the burger description to the carousel

        Button deleteButton = findViewById(R.id.buttonDeleteItem);
        Button editButton = findViewById(R.id.buttonEditItem);

        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Edit button is shown only if the item is a burger, therefore we can assume at this point the item in the orderCarouselPosition is a burger.
                BurgerItemModel curBurger = (BurgerItemModel) MainActivity.this.orderList.get(MainActivity.this.orderCarouselPosition);
                Intent intent = new Intent(MainActivity.this, BuildBurgerActivity.class);
                intent.putExtra("curBurger", curBurger);
                startActivityForResult(intent, BuildBurgerActivity.EDIT_ACTIVITY_CODE);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.orderList.remove(MainActivity.this.orderCarouselPosition);
                MainActivity.this.orderCarouselPosition--;
                manageOrderCarouselView(true);
            }
        });

        orderCarouselViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //position of the selected item
                MainActivity.this.orderCarouselPosition = position;
                manageOrderCarouselView(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO : check if we need to implement
            }

        });
    }

    public void launchCheckoutDialog(View view) {
    }

    private void manageOrderCarouselView(boolean dataChanged) {
        // TODO: Carousel doesn't update the images after update.
        if (MainActivity.this.orderCarouselPosition < 0) {
            MainActivity.this.orderCarouselPosition = 0;
        }
        Button deleteButton = findViewById(R.id.buttonDeleteItem);
        Button editButton = findViewById(R.id.buttonEditItem);
        if (MainActivity.this.orderList.isEmpty()) {
            deleteButton.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
        } else {
            deleteButton.setVisibility(View.VISIBLE);
            if (this.orderList.get(this.orderCarouselPosition) instanceof BurgerItemModel) {
                editButton.setVisibility(View.VISIBLE);
            }
            else {
                editButton.setVisibility(View.GONE);
            }
        }

        if (dataChanged) {
            MainActivity.this.orderCarouselCardAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Make sure the request was successful
        if (resultCode == RESULT_OK) {
            // Check which request we're responding to
            BurgerItemModel orderedBurger = (BurgerItemModel) Objects.requireNonNull(data.getExtras()).getSerializable("order");
            assert orderedBurger != null;
            orderedBurger.updateBurger();
            if (requestCode == BuildBurgerActivity.CREATE_NEW_ACTIVITY_CODE) {
                orderList.add(0, orderedBurger);
            } else if (requestCode == BuildBurgerActivity.EDIT_ACTIVITY_CODE) {
                MainActivity.this.orderList.set(MainActivity.this.orderCarouselPosition, orderedBurger);
            }
            manageOrderCarouselView(true);
        }
    }

    /**
     * Adds onClick functionality for the main app carousel
     */
    class TapGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            switch (carouselPicker.getCurrentItem()) {
                case 0:
                    orderList.add(0, new FoodItemModel(R.drawable.fries, "fries"));
                    orderCarouselCardAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    curBurger = new BurgerItemModel();
                    Intent intent = new Intent(MainActivity.this, BuildBurgerActivity.class);
                    intent.putExtra("curBurger", curBurger);
                    startActivityForResult(intent, BuildBurgerActivity.CREATE_NEW_ACTIVITY_CODE);
                    break;
                case 2:
                    orderList.add(0, new FoodItemModel(R.drawable.soda, "soda"));
                    orderCarouselCardAdapter.notifyDataSetChanged();
                    break;
            }
            manageOrderCarouselView(true);

            return false;
        }
    }

    /** Called when the user taps the checkout button */
    public void checkout(View view) {
        Intent intent = new Intent(MainActivity.this, ThankYouActivity.class);
        startActivity(intent);
    }

}
