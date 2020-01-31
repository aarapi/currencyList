package com.example.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fragments.R;
import com.example.fragments.data.CurrencyList;
import com.example.fragments.data.CurrencyModel;

import java.util.List;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.MyViewHolder> {

    private List<CurrencyModel> currencyModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_currency_name, tv_currency_value;
        private ImageView iv_country_flag;


        public MyViewHolder(View view) {
            super(view);
            tv_currency_name = view.findViewById(R.id.tv_currency_name);
            tv_currency_value = view.findViewById(R.id.tv_currency_value);

            iv_country_flag = view.findViewById(R.id.iv_country_flag);

        }
    }


    public CurrencyListAdapter(List<CurrencyModel> list) {
        this.currencyModelList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_recyclerview_item, parent, false);


        return new MyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CurrencyModel item = currencyModelList.get(position);

        holder.tv_currency_name.setText(item.currencyName);
        holder.tv_currency_value.setText(item.currencyValue + "");
        holder.iv_country_flag.setImageResource(CurrencyList.countriesFlag[position]);


    }

    @Override
    public int getItemCount() {
        return currencyModelList.size();
    }


}