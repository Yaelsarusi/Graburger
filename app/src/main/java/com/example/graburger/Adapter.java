package com.example.graburger;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class Adapter extends PagerAdapter {
    private List<FoodItemModel> models;
    private Context context;

    public Adapter(List<FoodItemModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    // This is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        int index = models.indexOf(object);
        if (index == -1) {
            return POSITION_NONE;
        }
        return index;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_item, container, false);

        ImageView imageView;
        TextView desc;

        imageView = view.findViewById(R.id.image);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(models.get(position).getImage());
        desc.setText(models.get(position).getDesc());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
