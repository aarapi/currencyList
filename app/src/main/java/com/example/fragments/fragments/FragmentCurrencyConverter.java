package com.example.fragments.fragments;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fragments.R;
import com.example.fragments.activities.MainActivity;
import com.example.fragments.adapters.CustomGridViewAdapter;
import com.example.fragments.basemodels.BaseActivity;
import com.example.fragments.basemodels.BaseFragment;
import com.example.fragments.data.CurrencyList;
import com.example.fragments.data.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FragmentCurrencyConverter extends BaseFragment implements View.OnClickListener, TextWatcher {
    private Spinner spinner, spinner2;
    private BaseActivity activity;
    private ImageView iv_reverse_currency;
    private EditText ed_amount_in, ed_amount_out;

    public FragmentCurrencyConverter(BaseActivity activity) {
        super(R.layout.fragment_currency_converter);
        this.activity = activity;
    }

    @Override
    public void initViews() {
        iv_reverse_currency = containerView.findViewById(R.id.iv_reverse_currency);
        spinner = containerView.findViewById(R.id.spinner);
        spinner2 = containerView.findViewById(R.id.spinner2);

        ed_amount_in = containerView.findViewById(R.id.ed_amount_in);
        ed_amount_out = containerView.findViewById(R.id.ed_amount_out);


    }

    @Override
    public void setViews() {
        MyAdapterSpinner adapter = new MyAdapterSpinner(activity.getApplicationContext(),
                R.layout.spinner_item, CurrencyList.currencyList, CurrencyList.countriesFlag);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(1);

    }

    @Override
    public void bindEvents() {

        iv_reverse_currency.setOnClickListener(this);

        ed_amount_in.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(200);
        rotate.setInterpolator(new LinearInterpolator());


        iv_reverse_currency.startAnimation(rotate);

        int position = spinner.getSelectedItemPosition();
        spinner.setSelection(spinner2.getSelectedItemPosition());
        spinner2.setSelection(position);

        String value = ed_amount_out.getText().toString();

        ed_amount_out.setText(ed_amount_in.getText().toString());
        ed_amount_in.setText(value);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        DecimalFormat df = new DecimalFormat("#.##");
        double coefficient = ((MainActivity)
                activity).getCurrencyModels().get(spinner.getSelectedItemPosition()).currencyValue / ((MainActivity)
                activity).getCurrencyModels().get(spinner2.getSelectedItemPosition()).currencyValue;

        if (ed_amount_in.getText().toString().equals("")) {
            ed_amount_in.setText("0.0");
        }
        double value = Double.parseDouble(ed_amount_in.getText().toString()) / coefficient;

        ed_amount_out.setText(df.format(value) + "");

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    protected class MyAdapterSpinner extends ArrayAdapter {

        int[] Image;
        String[] Text;

        public MyAdapterSpinner(Context context, int resource, String[] text, int[] image) {
            super(context, resource, text);
            Image = image;
            Text = text;
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.spinner_item, parent, false);

            //Set Custom View
            TextView tv = (TextView) view.findViewById(R.id.textView);
            ImageView img = (ImageView) view.findViewById(R.id.imageView);

            tv.setText(Text[position]);
            img.setImageResource(Image[position]);

            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
    }
}


