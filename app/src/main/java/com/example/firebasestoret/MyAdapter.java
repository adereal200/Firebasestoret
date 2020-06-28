package com.example.firebasestoret;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<SliderItem> sliderItems;

    public MyAdapter(Context context, List<SliderItem> sliderItems) {
        this.sliderItems = sliderItems;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_pager_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    //   SliderItem sliderItem = sliderItems.get(position);

      //  holder.textViewName.setText(upload.getName());

        Glide.with(context).load(sliderItems.get(position).getImageurl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

           // textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageView = (ImageView) itemView.findViewById(R.id.imageone);
        }
    }
}
