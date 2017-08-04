package com.yu.materialdesign;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    /**
     * Created by D22436 on 2017/8/4.
     */
    Fruit[] fruitList;
    Context context;

    public CardViewAdapter(Fruit[] fruitList, Context context) {
        this.fruitList = fruitList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.id_iv_item);
            tvName = (TextView) itemView.findViewById(R.id.id_tv_image_name);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = fruitList[position];
        holder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), fruit.getResId()));
        holder.tvName.setText(fruit.getName());
    }


    @Override
    public int getItemCount() {
        return fruitList.length;
    }
}
