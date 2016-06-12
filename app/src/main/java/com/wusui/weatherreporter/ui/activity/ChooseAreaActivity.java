package com.wusui.weatherreporter.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wusui.weatherreporter.R;
import com.wusui.weatherreporter.callback.HttpCallbackListener;
import com.wusui.weatherreporter.db.WeatherDB;
import com.wusui.weatherreporter.model.City;
import com.wusui.weatherreporter.ui.adapter.PlaceAdapter;
import com.wusui.weatherreporter.util.HttpUtil;

import java.util.List;

public class ChooseAreaActivity extends AppCompatActivity {

    private ProgressDialog ProgressDialog;
    private TextView titleText;
    private RecyclerView mRecyclerView;
    private PlaceAdapter mPlaceAdapter;
    private List<String>mDataLists;
    private List<City>mCityList;
    private WeatherDB mWeatherDB;
    private ProgressDialog mProgressDialog;
    private City mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_area);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recylcer_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mPlaceAdapter = new PlaceAdapter(ChooseAreaActivity.this,mDataLists));
        mWeatherDB = WeatherDB.getInstance(this);
        queryCities();
    }

    private void queryCities() {
        mCityList = mWeatherDB.loadCities();
        if (mCityList.size() > 0){
            mCityList.clear();
            for (City city : mCityList){
                mDataLists.add(city.getProvince());
                mDataLists.add(city.getCity());
            }
            mPlaceAdapter.notifyDataSetChanged();
        }else queryFromServer("city");
    }

    private void queryFromServer(final String city) {
        String address;
        address = " https://api.heweather.com/x3/citylist?search=allchina&key=7bac57fa89c64197a80560d165cdc00c";

        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                //result = Utility.handleCitiesResponse(mWeatherDB,response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgress();
                    }


                });
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void closeProgress() {
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }
}
