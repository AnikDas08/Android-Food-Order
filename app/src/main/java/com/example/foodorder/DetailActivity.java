package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.IntArrayEvaluator;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodorder.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    int a=1;
    String b;
    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        if(getIntent().getIntExtra("type",0)==1) {
            final int image = getIntent().getIntExtra("image", 0);
            final String foodName = getIntent().getStringExtra("name");
            int price=Integer.parseInt(getIntent().getStringExtra("price"));
            final String description = getIntent().getStringExtra("description");

            binding.DetailImage.setImageResource(image);
            binding.DetailName.setText(foodName);
            binding.DetailPrice.setText(String.format("%d", price));
            binding.Descriptions.setText(description);




            helper = new DbHelper(this);
            binding.Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInsert = helper.insertOrder(binding.NameEdit.getText().toString().trim(), binding.NumberEdit.getText().toString().trim(), Integer.parseInt(binding.DetailPrice.getText().toString()), image, Integer.parseInt(binding.Quantites.getText().toString()),foodName, description);

                    if (isInsert) {
                        Toast.makeText(DetailActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailActivity.this, OrderActivity.class);
                        intent.putExtra("quant",Integer.parseInt(binding.Quantites.getText().toString()));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(DetailActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            binding.Decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int c = Integer.parseInt(binding.Quantites.getText().toString());
                    if (c > 1) {
                        a = c - 1;
                        binding.DetailPrice.setText(String.format("%d", price * a));
                        b = String.valueOf(a);
                        binding.Quantites.setText(b);
                    } else {
                        a = 1;
                        b = String.valueOf(a);
                        binding.Quantites.setText(b);
                    }
                }
            });
            binding.Increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int c = Integer.parseInt(binding.Quantites.getText().toString());
                    a = c + 1;
                    binding.DetailPrice.setText(String.format("%d", price * a));
                    b = String.valueOf(a);
                    binding.Quantites.setText(b);
                }

            });

        }

        else {
            int id=getIntent().getIntExtra("id",0);
            DbHelper helper1=new DbHelper(this);
            Cursor cursor=helper1.getOrderbyId(id);
            binding.DetailImage.setImageResource(cursor.getInt(4));
            binding.DetailName.setText(cursor.getString(6));
            binding.DetailPrice.setText(String.format("%d", cursor.getInt(3)));
            binding.Descriptions.setText(cursor.getString(7));
            binding.NameEdit.setText(cursor.getString(1));
            binding.NumberEdit.setText(cursor.getString(2));
            binding.Quantites.setText(String.format("%d", cursor.getInt(5)));
            binding.Button.setText("Update Now");

            binding.Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdate=helper1.updateOrder(
                            binding.NameEdit.getText().toString(),
                            binding.NumberEdit.getText().toString(),
                            Integer.parseInt(binding.DetailPrice.getText().toString()),
                            cursor.getInt(4),
                            Integer.parseInt(binding.Quantites.getText().toString()),
                            binding.DetailName.getText().toString(),
                            binding.Descriptions.getText().toString(),
                            id


                    );
                    if(isUpdate){
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DetailActivity.this,OrderActivity.class));
                    }
                }
            });
        }

    }
}