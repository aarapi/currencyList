package com.example.fragments.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.fragments.basemodels.BaseActivity;
import com.example.fragments.basemodels.BaseFragment;
import com.example.fragments.data.CurrencyList;
import com.example.fragments.data.RatesEntity;
import com.example.fragments.utilities.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class SendRequest extends AsyncTask<String, Integer, String> {
    private String request;
    private Activity baseActivity;

    public SendRequest(Activity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... sendMessage) {
        String _rtn = "";
        try {
            this.request = sendMessage[0];
            _rtn = getResponse(this.request);


            Intent intent = new Intent(BaseFragment.ACTION_DATA_RECEIVER_BASE + 1);

            intent.putExtra("action", 1);
            intent.putExtra("data", _rtn);

            baseActivity.sendBroadcast(intent);


        }catch (Exception e){

        }
        return _rtn;
    }

    @Override
    protected void onPostExecute(String  result) {
        super.onPostExecute(result);
    }


    private String getResponse(String url) {

        String jsonResponse = HttpUtil.getResponse(url);


        return jsonResponse;
    }


}
