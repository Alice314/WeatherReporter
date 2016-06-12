package com.wusui.weatherreporter.callback;

/**
 * Created by fg on 2016/6/10.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
