package com.example.accuweather.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accuweather.R;
import com.example.accuweather.Utils;
import com.example.accuweather.data.model.openweathermap.ForecastItem;

import java.util.List;

public class HourPrognosisAdapter extends RecyclerView.Adapter<HourPrognosisAdapter.HourViewHolder> {

    private List<ForecastItem> list;

    public void setList(List<ForecastItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HourPrognosisAdapter.HourViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hour_prognosis, viewGroup, false);
        return new HourPrognosisAdapter.HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder hourViewHolder, int i) {
        ForecastItem item = list.get(i);
        hourViewHolder.textviewHours.setText(Utils.getStrHour(item.getDt()));
        hourViewHolder.textViewTemperature.setText((int) item.getMain().getTempMax() + "°");
        hourViewHolder.imageViewIcon.setImageResource(Utils.getIconName(item.getWeatherArray().get(0).getIcon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HourViewHolder extends RecyclerView.ViewHolder {
        private TextView textviewHours;
        private TextView textViewTemperature;
        private ImageView imageViewIcon;

        public HourViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewHours = itemView.findViewById(R.id.textviewHours);
            textViewTemperature = itemView.findViewById(R.id.textViewTemperature);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
