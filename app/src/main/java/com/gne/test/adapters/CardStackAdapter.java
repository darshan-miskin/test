package com.gne.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gne.test.R;
import com.gne.test.databinding.RecyclerItemBinding;
import com.gne.test.vo.User;

import java.util.ArrayList;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.CardViewHolder>{

    public interface OnCardActionClickListener{
        void onLikeClicked();
        void onDislikeClicked();
        void onMessageClicked();
    }

    private OnCardActionClickListener onCardActionClickListener;
    private ArrayList<User> arrayList;

    public CardStackAdapter(ArrayList<User> arrayList, OnCardActionClickListener onCardActionClickListener) {
        this.arrayList = arrayList;
        this.onCardActionClickListener=onCardActionClickListener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RecyclerItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.recycler_item,parent,false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.binding.setUser(arrayList.get(position));
        if(arrayList.get(position).isLiked()){
            holder.binding.imgLike.setVisibility(View.GONE);
            holder.binding.txtLike.setVisibility(View.VISIBLE);
        }
        else {
            holder.binding.imgLike.setVisibility(View.VISIBLE);
            holder.binding.txtLike.setVisibility(View.GONE);
        }
        if(arrayList.get(position).isDisliked()){
            holder.binding.imgDislike.setVisibility(View.GONE);
            holder.binding.txtDislike.setVisibility(View.VISIBLE);
        }
        else {
            holder.binding.imgDislike.setVisibility(View.VISIBLE);
            holder.binding.txtDislike.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder{

        RecyclerItemBinding binding;
        public CardViewHolder(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());

            this.binding=binding;

            binding.cardLike.setOnClickListener(v->{
                onCardActionClickListener.onLikeClicked();
            });

            binding.cardDislike.setOnClickListener(v->{
                onCardActionClickListener.onDislikeClicked();
            });

            binding.fabMessage.setOnClickListener(v->{
                onCardActionClickListener.onMessageClicked();
            });
        }
    }
}
