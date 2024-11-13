package com.example.foodorder.adpatar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.DbHelper;
import com.example.foodorder.DetailActivity;
import com.example.foodorder.R;
import com.example.foodorder.model.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolderWord> {
    Context context;
    ArrayList<OrderModel> lists;

    public OrderAdapter(Context context, ArrayList<OrderModel> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public OrderViewHolderWord onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderViewHolderWord(LayoutInflater.from(context).inflate(R.layout.order_samples,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolderWord holder, @SuppressLint("RecyclerView") int i) {
        holder.orderImage.setImageResource(lists.get(i).getOrderImage());
        holder.orderName.setText(lists.get(i).getOrderName());
        holder.orderPrice.setText(lists.get(i).getOrderPrice());
        holder.orderNumber.setText(lists.get(i).getOrderNumber());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(lists.get(i).getOrderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper helper = new DbHelper(context);
                String orderId = lists.get(i).getOrderNumber();
                if(helper.deleteOrders(orderId) > 0){
                    // Remove the item from the data list
                    lists.remove(i);
                    // Notify the adapter about the removed item
                    notifyItemRemoved(i);
                    notifyItemRangeChanged(i, lists.size());

                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Not deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class OrderViewHolderWord extends RecyclerView.ViewHolder {
        ImageView orderImage,delete;
        TextView orderName,orderPrice,orderNumber;
        LinearLayout layout;
        public OrderViewHolderWord(@NonNull View itemView) {
            super(itemView);
            orderImage=itemView.findViewById(R.id.OderImage);
            orderName=itemView.findViewById(R.id.OderName);
            orderPrice=itemView.findViewById(R.id.OderPrice);
            orderNumber=itemView.findViewById(R.id.OrderNumber);
            delete=itemView.findViewById(R.id.Delete);
            layout=itemView.findViewById(R.id.LinearLayout);
        }
    }
}
