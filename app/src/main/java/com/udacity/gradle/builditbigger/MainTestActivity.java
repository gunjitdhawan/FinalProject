package com.udacity.gradle.builditbigger;

import android.util.Log;

/**
 * Created by gunjit on 08/11/16.
 */

public class MainTestActivity extends MainActivity {

    private MainTestCallback mCallback;

    public void setResponseCallback(MainTestCallback loginCallback){
        mCallback = loginCallback;
    }

    public interface MainTestCallback{
        void onReceivedResponse(String response);
    }

    @Override
    public void handleResponse(String loginResponse) {
        Log.e("-----", "77");
        mCallback.onReceivedResponse(loginResponse);
    }
}
