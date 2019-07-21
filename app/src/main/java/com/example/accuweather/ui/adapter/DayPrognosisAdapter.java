package com.example.accuweather.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accuweather.R;
import com.example.accuweather.Utils;
import com.example.accuweather.data.model.accuweather.forecast.DailyForecast;

import java.util.List;

public class DayPrognosisAdapter extends RecyclerView.Adapter<DayPrognosisAdapter.DayViewHolder> {

    private List<DailyForecast> list;
    private OnDayClickListener onDayClickListener;

    private int itemSelected = 0;

    private Context context;

    public void setItemSelected(int itemSelected) {
        this.itemSelected = itemSelected;
    }

    public int getItemSelected() {
        return itemSelected;
    }

    public interface OnDayClickListener {
        void onDayClick(int position);
    }

    public void setList(List<DailyForecast> list) {
        this.list = list;
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        this.onDayClickListener = onDayClickListener;
    }


    public DayPrognosisAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_day_prognosis, viewGroup, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int i) {
        DailyForecast item = list.get(i);
        dayViewHolder.textViewDate.setText(Utils.getJustStrDay(item.getEpochDate()));
        dayViewHolder.textViewTempMinMax.setText(Utils.getTemperatureStr(item.getTemperature().getMaximum().getValue(), item.getTemperature().getMinimum().getValue()));
        dayViewHolder.imageViewIcon.setImageResource(Utils.getIconIdToDrawableRes(item.getDay().getIcon()));

        //Set color depends of selected position
        if (itemSelected == i) {
            dayViewHolder.textViewDate.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            dayViewHolder.textViewTempMinMax.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            dayViewHolder.imageViewIcon.setColorFilter(context.getResources().getColor(R.color.colorPrimaryDark));
            dayViewHolder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
        } else {
            dayViewHolder.textViewDate.setTextColor(context.getResources().getColor(R.color.colorBlack));
            dayViewHolder.textViewTempMinMax.setTextColor(context.getResources().getColor(R.color.colorBlack));
            dayViewHolder.imageViewIcon.setColorFilter(context.getResources().getColor(R.color.colorBlack));
            dayViewHolder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DayViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate;
        private TextView textViewTempMinMax;
        private ImageView imageViewIcon;
        private ConstraintLayout constraintLayout;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTempMinMax = itemView.findViewById(R.id.textViewTempMinMax);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutMain);

            //OnClick position interface
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDayClickListener != null) {
                        onDayClickListener.onDayClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
