package com.example.foodorder.adpatar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.DetailActivity;
import com.example.foodorder.R;
import com.example.foodorder.model.MainModel;

import java.util.ArrayList;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.FoodViewHolder>{

    Context context;
    ArrayList<MainModel> lists;

    public MainAdapter(Context context, ArrayList<MainModel> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_food,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int i) {
        final MainModel list=lists.get(i);
        holder.imageView.setImageResource(list.getImage());
        holder.name.setText(list.getName());
        holder.price.setText(list.getPrice());
        holder.description.setText(list.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("image",list.getImage());
                intent.putExtra("name",list.getName());
                intent.putExtra("price",list.getPrice());
                intent.putExtra("description",list.getDescription());
                intent.putExtra("type",1);
                intent.putExtra("priceBd",list.getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,price,description;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.Image);
            name=itemView.findViewById(R.id.Name);
            price=itemView.findViewById(R.id.Price);
            description=itemView.findViewById(R.id.Description);
        }
    }
}
