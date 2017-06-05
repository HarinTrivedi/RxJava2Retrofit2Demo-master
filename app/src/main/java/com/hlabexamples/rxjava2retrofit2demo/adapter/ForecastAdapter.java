package com.hlabexamples.rxjava2retrofit2demo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hlabexamples.rxjava2retrofit2demo.R;
import com.hlabexamples.rxjava2retrofit2demo.databinding.RowForecastItemBinding;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.Forecast;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.Temp;
import com.hlabexamples.rxjava2retrofit2demo.utils.Utils;

import java.util.List;

/**
 * Created by Harry's Lab on 27/05/17.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Forecast> items;

    public ForecastAdapter(Context context, List<Forecast> items) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder((RowForecastItemBinding) DataBindingUtil.inflate(inflater, R.layout.row_forecast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Forecast forecast = items.get(position);
        holder.bind(forecast);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RowForecastItemBinding binding;

        Holder(RowForecastItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Forecast forecast) {
            if (forecast.getWeather() != null)
                binding.tvDesc.setText(forecast.getWeather().get(0).getDescription());
            Temp temp = forecast.getTemp();
            if (temp != null) {
                binding.tvTemp.setText(String.format("%s %s", String.format("%.1f", temp.getMax()), context.getString(R.string.str_celsius)));
                binding.tvMin.setText(String.format("%s %s", String.format("%.1f", temp.getMin()), context.getString(R.string.str_celsius)));
            }
            binding.tvHumidity.setText(String.format("%s %s", context.getString(R.string.str_humidity), String.valueOf(forecast.getHumidity())));
            binding.tvDate.setText(Utils.parseDate(forecast.getDt() * 1000));
            binding.executePendingBindings();
        }
    }
}
