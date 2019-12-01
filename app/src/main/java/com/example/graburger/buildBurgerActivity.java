package com.example.graburger;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;

public class buildBurgerActivity extends AppCompatActivity {

    static int CREATE_NEW_ACTIVITY_CODE = 0;
    static int EDIT_ACTIVITY_CODE = 1;
    BurgerItemModel curBurger;
    private ViewPager upperBunPager;
    private ViewPager bottomBunPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_xactivity);

        Intent intent = getIntent();
        curBurger = (BurgerItemModel) intent.getExtras().getSerializable("curBurger");

        upperBunPager = findViewById(R.id.UpperBun);
        bottomBunPager = findViewById(R.id.BottomBun);

        setViewPager(BunType.getAsListArray(true),
                R.id.UpperBun,
                curBurger.getBun().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

                    @Override
                    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                            return;
                        }
                        buildBurgerActivity.this.bottomBunPager.scrollTo(buildBurgerActivity.this.upperBunPager.getScrollX(),
                                buildBurgerActivity.this.upperBunPager.getScrollY());
                        curBurger.updateBurger(BunType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageSelected(final int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(final int state) {
                        mScrollState = state;
                        if (state == ViewPager.SCROLL_STATE_IDLE) {
                            buildBurgerActivity.this.bottomBunPager.setCurrentItem(buildBurgerActivity.this.upperBunPager.getCurrentItem(), false);
                        }
                    }
                });

        setViewPager(BunType.getAsListArray(false),
                R.id.BottomBun,
                curBurger.getBun().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

                    @Override
                    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                            return;
                        }
                        buildBurgerActivity.this.upperBunPager.scrollTo(buildBurgerActivity.this.bottomBunPager.getScrollX(),
                                buildBurgerActivity.this.bottomBunPager.getScrollY());
                        curBurger.updateBurger(BunType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageSelected(final int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(final int state) {
                        mScrollState = state;
                        if (state == ViewPager.SCROLL_STATE_IDLE) {
                            buildBurgerActivity.this.upperBunPager.setCurrentItem(buildBurgerActivity.this.bottomBunPager.getCurrentItem(), false);
                        }
                    }
                });

        setViewPager(CheeseType.getAsListArray(),
                R.id.Cheese,
                curBurger.getCheese().getPosition(),
                new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(CheeseType.get(position));
                updateCurrentBurgerDesc(curBurger);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        setViewPager(PattyType.getAsListArray(),
                R.id.Patty,
                curBurger.getPatty().getPosition(),
                new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(PattyType.get(position));
                updateCurrentBurgerDesc(curBurger);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        setViewPager(TomatoType.getAsListArray(),
                R.id.Tomato,
                curBurger.getTomato().getPosition(),
                new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(TomatoType.get(position));
                updateCurrentBurgerDesc(curBurger);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        setViewPager(LettuceType.getAsListArray(),
                R.id.Lettuce,
                curBurger.getLettuce().getPosition(),
                new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(LettuceType.get(position));
                updateCurrentBurgerDesc(curBurger);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        setViewPager(SauceType.getAsListArray(),
                R.id.Sauce,
                curBurger.getSauce().getPosition(),
                new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(SauceType.get(position));
                updateCurrentBurgerDesc(curBurger);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        ImageButton orderButton = findViewById(R.id.add);

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
