package hf.yz.hfradiomanager_v2.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import hf.yz.hfradiomanager_v2.R;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHoder> {

    private List<SliderItem> list;
    private ViewPager2 viewPager2;
    private OnSliderViewClicked onSliderViewClicked;


    public SliderAdapter(List<SliderItem> list, ViewPager2 viewPager2, OnSliderViewClicked onSliderViewClicked) {
        this.list = list;
        this.viewPager2 = viewPager2;
        this.onSliderViewClicked = onSliderViewClicked;
    }

    @NonNull
    @Override
    public SliderViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_imv,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull SliderViewHoder holder, final int position) {
        holder.setImage(list.get(position));
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSliderViewClicked.onClicked();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class SliderViewHoder extends RecyclerView.ViewHolder{

        public ImageButton imageButton;

        public SliderViewHoder(@NonNull View itemView) {
            super(itemView);

            imageButton = itemView.findViewById(R.id.imv_slider);
        }

        void setImage(SliderItem item){

            imageButton.setImageResource(item.getImage());

        }
    }
}


