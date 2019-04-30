package com.example.silvia.recyclerviewadd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> names;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<String> names, int layout, OnItemClickListener listener) {
        this.names = names;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // evento cuando se crea el recyclerview ,  lo que se agrega despues  ya no  llama a este evento
        holder.bind(names.get(position), itemClickListener);


    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);


        }

        public void bind(final String  name,  final OnItemClickListener listener)
        {
            //getname
          this.textViewName.setText(name);

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  //nos da la position- este  click si hara este evento cada vez que lo solicitemos // de una sola celda// para que  al añadir  se haga bien los indices
                  listener.onItemClick(name, getAdapterPosition());

              }
          });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(String name, int position);
    }


}
