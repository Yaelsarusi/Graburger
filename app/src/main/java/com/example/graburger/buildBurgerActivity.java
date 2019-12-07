package com.example.graburger;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;

public class buildBurgerActivity extends AppCompatActivity {

    static int CREATE_NEW_ACTIVITY_CODE = 0;
    static int EDIT_ACTIVITY_CODE = 1;

    public static int HALF_LOOPS = 500;

    BurgerItemModel curBurger;
    private ViewPager upperBunPager;
    private ViewPager bottomBunPager;

//    mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mProducts);
//    mViewPager.setAdapter(mAdapter);
//    mViewPager.setCurrentItem(mViewPager.getChildCount() * Adapter.LOOPS_COUNT / 2, false); // set current item in the adapter to middle

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
                curBurger.getBun().getPosition() + (HALF_LOOPS * BunType.size()),
                new ViewPager.OnPageChangeListener() {
                    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

                    @Override
                    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                            return;
                        }
                        buildBurgerActivity.this.bottomBunPager.scrollTo(buildBurgerActivity.this.upperBunPager.getScrollX(),
                                buildBurgerActivity.this.upperBunPager.getScrollY());
                        curBurger.updateBurger(BunType.get(position % BunType.size()));
                        updateCurrentBurgerDesc();
                    }

                    @Override
                    public void onPageSelected(final int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(final int state) {
                        mScrollState = state;
                        if (state == ViewPager.SCROLL_STATE_IDLE) {
                            buildBurgerActivity.this.bottomBunPager.setCurrentItem(buildBurgerActivity.this.upperBunPager.getCurrentItem() * HALF_LOOPS, false);
                        }
                    }
                });

        setViewPager(BunType.getAsListArray(false),
                R.id.BottomBun,
                curBurger.getBun().getPosition() + (HALF_LOOPS * BunType.size()),
                new ViewPager.OnPageChangeListener() {
                    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

                    @Override
                    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                            return;
                        }
                        buildBurgerActivity.this.upperBunPager.scrollTo(buildBurgerActivity.this.bottomBunPager.getScrollX(),
                                buildBurgerActivity.this.bottomBunPager.getScrollY());
                        curBurger.updateBurger(BunType.get(position % BunType.size()));
                        updateCurrentBurgerDesc();

                    }

                    @Override
                    public void onPageSelected(final int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(final int state) {
                        mScrollState = state;
                        if (state == ViewPager.SCROLL_STATE_IDLE) {
                            buildBurgerActivity.this.upperBunPager.setCurrentItem(buildBurgerActivity.this.bottomBunPager.getCurrentItem() * HALF_LOOPS, false);
                        }
                    }
                });

        setViewPager(CheeseType.getAsListArray(),
                R.id.Cheese,
                curBurger.getCheese().getPosition() + (HALF_LOOPS * CheeseType.size()),
                new ViewPager.OnPageChangeListener() {
            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(CheeseType.get(position % CheeseType.size()));
                updateCurrentBurgerDesc();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    updateCurrentBurgerDesc();
                }
            }
        });

        setViewPager(PattyType.getAsListArray(),
                R.id.Patty,
                curBurger.getPatty().getPosition() + (HALF_LOOPS * PattyType.size()),
                new ViewPager.OnPageChangeListener() {
            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(PattyType.get(position % PattyType.size()));
                updateCurrentBurgerDesc();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    updateCurrentBurgerDesc();
                }
            }
        });

        setViewPager(TomatoType.getAsListArray(),
                R.id.Tomato,
                curBurger.getTomato().getPosition() + (HALF_LOOPS * TomatoType.size()),
                new ViewPager.OnPageChangeListener() {

            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;
            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(TomatoType.get(position % TomatoType.size()));
                updateCurrentBurgerDesc();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    updateCurrentBurgerDesc();
                }
            }
        });

        setViewPager(LettuceType.getAsListArray(),
                R.id.Lettuce,
                curBurger.getLettuce().getPosition() + (HALF_LOOPS * LettuceType.size()),
                new ViewPager.OnPageChangeListener() {
            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;
            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(LettuceType.get(position % LettuceType.size()));
                updateCurrentBurgerDesc();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    updateCurrentBurgerDesc();
                }
            }
        });

        setViewPager(SauceType.getAsListArray(),
                R.id.Sauce,
                curBurger.getSauce().getPosition() + (HALF_LOOPS * SauceType.size()),
                new ViewPager.OnPageChangeListener() {
            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curBurger.updateBurger(SauceType.get(position % SauceType.size()));
                updateCurrentBurgerDesc();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    updateCurrentBurgerDesc();
                }
            }
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

        updateCurrentBurgerDesc();
    }

    private void updateCurrentBurgerDesc() {
        TextView curOrderText = findViewById(R.id.CurOrder);
        curOrderText.setText(Html.fromHtml(String.format(getString(R.string.CurOrder), curBurger.getDesc())));
    }

    private void setViewPager(List<FoodItemModel> model, int id, int position, ViewPager.OnPageChangeListener listener ) {
        Adapter adapter;
        ViewPager viewPager;

        adapter = new Adapter(model, this);
        viewPager = findViewById(id);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position, false);
        viewPager.addOnPageChangeListener(listener);
    }
}
