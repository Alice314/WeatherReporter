package com.wusui.weatherreporter.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wusui.weatherreporter.model.City;

import java.util.List;

/**
 * Created by fg on 2016/6/10.
 */

public class PlaceAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String>mCityList;
    private City selectedCity;

    public PlaceAdapter(Context context,List<String>list){
        mContext = context;
        mCityList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
