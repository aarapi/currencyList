package com.example.fragments.tasks;

import android.os.AsyncTask;

import com.example.fragments.utilities.HttpUtil;

import java.util.HashMap;

public class SendRequest extends AsyncTask<String, Integer, String> {
    private String request;
    private String requestURL;

    public SendRequest(String requestURL) {
        this.requestURL = requestURL;
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
        }catch (Exception e){

        }
        return _rtn;
    }

    @Override
    protected void onPostExecute(String  result) {
        super.onPostExecute(result);
    }


    private String getResponse(String apiKey) {

        String jsonResponse = HttpUtil.getResponse(this.requestURL);


        return jsonResponse;
    }


}
