package com.example.fragments.request;

import com.example.fragments.data.RatesEntity;
import com.example.fragments.data.RequestModel;
import com.example.fragments.utilities.CheckSetup;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class RequestFunctions {


    public static RequestModel getExchangeRates() {

        RequestModel requestModel = new RequestModel(CheckSetup.RequestUrl.EXCHANGE_RATES_URL,
                CheckSetup.LocalActions.ANNOYING_PROJECTS_GET_RATES_LIST);

        return requestModel;
    }

}
