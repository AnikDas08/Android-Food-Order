package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorder.adpatar.MainAdapter;
import com.example.foodorder.adpatar.OrderAdapter;
import com.example.foodorder.databinding.ActivityMainBinding;
import com.example.foodorder.model.MainModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainAdapter adapter;
    ArrayList<MainModel> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.setTitle("Food List for you");
        this.setTitleColor(R.color.blue);

        lists=new ArrayList<>();

        lists.add(new MainModel(R.drawable.image1,"Burger","200","Chickern Burger with extra cheese"));
        lists.add(new MainModel(R.drawable.image2,"Burger","100","Chickern Burger with extra cheese"));
        lists.add(new MainModel(R.drawable.image1,"Burger","300","Chickern Burger with extra cheese"));
        lists.add(new MainModel(R.drawable.image2,"Burger","400","Chickern Burger with extra cheese"));
        lists.add(new MainModel(R.drawable.image2,"Burger","200","Chickern Burger with extra cheese"));
        lists.add(new MainModel(R.drawable.image2,"Burger","100","Chickern Burger with extra cheese"));
        lists.add(new MainModel(R.drawable.image2,"Burger","300","Chickern Burger with extra cheese"));
        lists.add(new MainModel(R.drawable.image2,"Burger","400","Chickern Burger with extra cheese"));

        adapter=new MainAdapter(this,lists);
        binding.Rv.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.Rv.setLayoutManager(layoutManager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.OrderNow){
            Intent intent=new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}