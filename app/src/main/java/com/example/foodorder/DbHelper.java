package com.example.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorder.model.OrderModel;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    final static String DBNAME="orderpl.db";
    final static int DBVERSION=4;
    public DbHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders"+
                        "(id integer primary key autoincrement,"+
                        "name text,"+
                        "phone text,"+
                        "price int,"+
                        "image int,"+
                        "quantity int,"+
                        "foodName text,"+
                        "description text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }
    public boolean insertOrder(String name, String phone,int price,int image,int quantity,String foodName,String description){
        SQLiteDatabase database=getReadableDatabase();
        ContentValues value=new ContentValues();
        value.put("name",name);
        value.put("phone",phone);
        value.put("price",price);
        value.put("image",image);
        value.put("quantity",quantity);
        value.put("foodName",foodName);
        value.put("description",description);

        long id=database.insert("orders",null,value);
        if(id<=0){
            return false;
        }
        else{
            return true;
        }
    }
    public ArrayList<OrderModel> getOrder(){
        ArrayList<OrderModel> orders=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();

        Cursor cursor=database.rawQuery("select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                OrderModel model=new OrderModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setOrderName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setOrderPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        return orders;
    }
    public Cursor getOrderbyId(int id){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from orders where id="+id,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public boolean updateOrder(String name, String phone,int price,int image,int quantity,String foodName,String description,int id){
        SQLiteDatabase database=getReadableDatabase();
        ContentValues value=new ContentValues();
        value.put("name",name);
        value.put("phone",phone);
        value.put("price",price);
        value.put("image",image);
        value.put("quantity",quantity);
        value.put("foodName",foodName);
        value.put("description",description);

        long row=database.update("orders",value,"id="+id,null);
        if(row<=0){
            return false;
        }
        else{
            return true;
        }
    }
    public int deleteOrders(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders", "id="+id,null );
    }
}
