package com.tolet.sajib.retrofit_jan_18_12_18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tolet.sajib.retrofit_jan_18_12_18.model.ModelTwo;

import java.util.ArrayList;
import java.util.List;

public class KitchenlistAdapter extends RecyclerView.Adapter<KitchenlistAdapter.ViewHolder> {
    private ArrayList<ModelTwo> listItem;
    private Context context;
    public static int kid;
    public static String kName;

    public KitchenlistAdapter(ArrayList<ModelTwo> mArrayList, Context context) {
        this.listItem = mArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public KitchenlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenlistAdapter.ViewHolder holder, int position) {

//holder.textView.setText(datamodel.get(position).getName());
        holder.textViewHead.setText(listItem.get(position).getKitchenName());
        holder.textViewDesc.setText(listItem.get(position).getCuisine());
        holder.textFoodRating.setText(String.format("%.2f",listItem.get(position).getRating()));
        String status = String.valueOf(listItem.get(position).getKitchenStatus());
        String mImage = "http://www.fnstatic.co.uk/images/content/recipe/quiz-can-you-guess-which-fast-food-item-has-more-calories-.jpeg";
        if(!listItem.get(position).getKitchenImage().isEmpty()){
            mImage = listItem.get(position).getKitchenImage();
        }
        Glide.with(context)
                .load(mImage)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .crossFade()
                .into(holder.imageView1);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView1;
        public TextView textFoodRating;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead =  itemView.findViewById(R.id.textFoodName);
            textViewDesc =itemView.findViewById(R.id.textFoodCat);
            imageView1=itemView.findViewById(R.id.imageView);
            textFoodRating=itemView.findViewById(R.id.textFoodRating);

        }
    }
}
