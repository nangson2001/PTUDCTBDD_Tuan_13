package com.example.jsonkill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<ItemModel> itemModels;
    ItemClickListener itemClickListener;

    public ItemAdapter(List<ItemModel> itemModels, ItemClickListener itemClickListener) {
        this.itemModels = itemModels;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ItemViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel itemModel = itemModels.get(position);
        holder.txvUsername.setText(itemModel.getUserName());
        holder.txvEmail.setText(itemModel.getEmail());
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(itemModel.getAvatar());
            Picasso.get().load("https://lebavui.github.io" + jsonObject.getString("thumbnail")).into(holder.imageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txvUsername;
        TextView txvEmail;
        ItemClickListener _itemClickListener;

        public ItemViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_avatar);
            txvEmail = itemView.findViewById(R.id.text_email);
            txvUsername = itemView.findViewById(R.id.text_username);
            _itemClickListener = itemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_itemClickListener != null) _itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
