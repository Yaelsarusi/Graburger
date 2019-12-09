package com.example.graburger;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class BuildBurgerActivity extends AppCompatActivity {

    static int CREATE_NEW_ACTIVITY_CODE = 0;
    static int EDIT_ACTIVITY_CODE = 1;
    BurgerItemModel curBurger;
    private ViewPager upperBunPager;
    private ViewPager bottomBunPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_burger);

        Intent intent = getIntent();
        curBurger = (BurgerItemModel) Objects.requireNonNull(intent.getExtras()).getSerializable("curBurger");

        upperBunPager = findViewById(R.id.viewUpperBun);
        bottomBunPager = findViewById(R.id.viewBottomBun);

        setViewPager(BunType.getAsListArray(true),
                R.id.viewUpperBun,
                curBurger.getBun().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

                    @Override
                    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                            return;
                        }
                        BuildBurgerActivity.this.bottomBunPager.scrollTo(BuildBurgerActivity.this.upperBunPager.getScrollX(),
                                BuildBurgerActivity.this.upperBunPager.getScrollY());
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
                            BuildBurgerActivity.this.bottomBunPager.setCurrentItem(BuildBurgerActivity.this.upperBunPager.getCurrentItem(), false);
                        }
                    }
                });

        setViewPager(BunType.getAsListArray(false),
                R.id.viewBottomBun,
                curBurger.getBun().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

                    @Override
                    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                            return;
                        }
                        BuildBurgerActivity.this.upperBunPager.scrollTo(BuildBurgerActivity.this.bottomBunPager.getScrollX(),
                                BuildBurgerActivity.this.bottomBunPager.getScrollY());
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
                            BuildBurgerActivity.this.upperBunPager.setCurrentItem(BuildBurgerActivity.this.bottomBunPager.getCurrentItem(), false);
                        }
                    }
                });

        setViewPager(CheeseType.getAsListArray(),
                R.id.viewCheese,
                curBurger.getCheese().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        curBurger.updateBurger(CheeseType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

        setViewPager(PattyType.getAsListArray(),
                R.id.viewPatty,
                curBurger.getPatty().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        curBurger.updateBurger(PattyType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

        setViewPager(TomatoType.getAsListArray(),
                R.id.viewTomato,
                curBurger.getTomato().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        curBurger.updateBurger(TomatoType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

        setViewPager(LettuceType.getAsListArray(),
                R.id.viewLettuce,
                curBurger.getLettuce().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        curBurger.updateBurger(LettuceType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

        setViewPager(SauceType.getAsListArray(),
                R.id.viewSauce,
                curBurger.getSauce().getPosition(),
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        curBurger.updateBurger(SauceType.get(position));
                        updateCurrentBurgerDesc(curBurger);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

        Button orderButton = findViewById(R.id.buttonOrder);

        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent(BuildBurgerActivity.this, MainActivity.class);
                resultIntent.putExtra("order", curBurger);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        updateCurrentBurgerDesc(curBurger);
    }

    private void updateCurrentBurgerDesc(BurgerItemModel curBurger) {
        TextView curOrderText = findViewById(R.id.textCurOder);
        curOrderText.setText(String.format("%s%s", getString(R.string.curOrder), curBurger.getDesc()));
    }

    private void setViewPager(List<FoodItemModel> model, int id, int position, ViewPager.OnPageChangeListener listener) {
        CardAdapter cardAdapter;
        ViewPager viewPager;

        cardAdapter = new CardAdapter(model, this);
        viewPager = findViewById(id);
        viewPager.setAdapter(cardAdapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(listener);
    }
}
