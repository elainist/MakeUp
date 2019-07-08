package com.myapplicationdev.android.makeup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapplicationdev.android.makeup.Interface.ItemClickListener;
import com.myapplicationdev.android.makeup.Model.Item;
import com.myapplicationdev.android.makeup.ViewHolder.ItemViewHolder;
import com.squareup.picasso.Picasso;

public class ItemList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    FirebaseDatabase database;
    DatabaseReference itemList;

    String categoryId = "";

    FirebaseRecyclerAdapter<Item, ItemViewHolder>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        database = FirebaseDatabase.getInstance();
        itemList =database.getReference("Foods");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_item);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null)
        {
            loadListItem(categoryId);
        }
    }

    private void loadListItem(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(Item.class,R.layout.item_item,ItemViewHolder.class,itemList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, Item model, int position) {
                viewHolder.item_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.item_image);
                final Item local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent itemDetail = new Intent(ItemList.this,ItemDetail.class);
                        itemDetail.putExtra("ItemId",adapter.getRef(position).getKey());// send item id to new activity
                        startActivity(itemDetail);
                    }
                });

            }
        };

        //set adapter

        recyclerView.setAdapter(adapter);
    }
}
