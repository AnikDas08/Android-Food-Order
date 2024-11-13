package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodorder.adpatar.OrderAdapter;
import com.example.foodorder.databinding.ActivityOrderBinding;
import com.example.foodorder.model.OrderModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;
    OrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DbHelper helper=new DbHelper(this);
        ArrayList<OrderModel> list=helper.getOrder();


        adapter=new OrderAdapter(this,list);
        binding.Rv.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.Rv.setLayoutManager(layoutManager);

    }
}