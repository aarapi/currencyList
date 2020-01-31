package com.example.fragments.fragments;

import android.content.Intent;
import android.graphics.Point;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragments.R;
import com.example.fragments.activities.MainActivity;
import com.example.fragments.adapters.CurrencyListAdapter;
import com.example.fragments.basemodels.BaseActivity;
import com.example.fragments.basemodels.BaseFragment;
import com.example.fragments.data.CurrencyList;
import com.example.fragments.data.CurrencyModel;
import com.example.fragments.data.RatesEntity;
import com.example.fragments.request.RequestFunctions;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class FragmentCurrencyList extends BaseFragment {
    public static final int EXTRA_REVEAL_CENTER_PADDING = 40;
    private BaseActivity activity;

    private RecyclerView rv_currency_list;
    private List<CurrencyModel> currencyModels;

    private RecyclerView.LayoutManager layoutManager;

    public FragmentCurrencyList(BaseActivity activity) {
        super(R.layout.fragment_list_currency);
        this.activity = activity;
    }

    @Override
    public void initViews() {
        rv_currency_list = containerView.findViewById(R.id.rv_currency_list);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void bindEvents() {

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.test_menu, menu);
        setupSearchView(menu);
    }

    private void setupSearchView(Menu menu) {
    }

    public void onDataRecive(int action, String data) {
        Gson gson = new Gson();

        RatesEntity ratesEntity = gson.fromJson(data, RatesEntity.class);

        LinkedTreeMap ratesList = (LinkedTreeMap) ratesEntity.rates;
        currencyModels = new ArrayList<>();
        for (int i = 0; i < CurrencyList.currencyList.length; i++) {
            CurrencyModel currencyModel = new CurrencyModel(CurrencyList.currencyList[i],
                    (Double) ratesList.get(CurrencyList.currencyList[i]));
            currencyModels.add(currencyModel);
        }
        ((MainActivity) activity).setCurrencyModels(currencyModels);

        CurrencyListAdapter currencyListAdapter = new CurrencyListAdapter(currencyModels);

        layoutManager = new LinearLayoutManager(activity.getApplicationContext());
        rv_currency_list.setLayoutManager(layoutManager);

        rv_currency_list.setAdapter(currencyListAdapter);
    }

    @Override
    public void sendRequestMessage() {
        activity.sendRequest(RequestFunctions.getExchangeRates());
    }

    public List<CurrencyModel> getCurrencyModels() {
        return currencyModels;
    }
}


