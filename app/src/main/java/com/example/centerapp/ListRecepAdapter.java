package com.example.centerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListRecepAdapter extends RecyclerView.Adapter<ListRecepAdapter.ViewHolder>{

    private List<ListRecepModel> listRecep;

    public ListRecepAdapter(List<ListRecepModel>listRecep){
        this.listRecep=listRecep;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recep_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String att1 = listRecep.get(position). getLastName();
        String att2 = listRecep.get(position).getName();
        String att3 = listRecep.get(position).getVaccine();
        String att4 = listRecep.get(position).getTime();

        holder.setData(att1, att2, att3, att4);

    }

    @Override
    public int getItemCount() { return listRecep.size();}


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;

        public ViewHolder(View view) {
            super(view);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
        }

        public void setData(String att1, String att2, String att3, String att4) {
            textView1.setText(att1);
            textView2.setText(att2);
            textView3.setText(att3);
            textView4.setText(att4);
        }
    }
}
