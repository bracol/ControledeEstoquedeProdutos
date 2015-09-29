package com.example.administrador.controledeestoquedeprodutos.controllers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrador.controledeestoquedeprodutos.R;
import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;

import java.util.List;

/**
 * Created by Administrador on 29/09/2015.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<Estoque> estoques;
    private int itemLayout;

    public RecycleAdapter(List<Estoque> items, int itemLayout) {
        this.estoques = items;
        this.itemLayout = itemLayout;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView imgView;
        TextView tvName;
        TextView tvQtd;
        TextView tvValor;


        public ViewHolder(View v) {
            super(v);
            imgView = (ImageView) v.findViewById(R.id.imgViewProduct);
            tvName = (TextView) v.findViewById(R.id.textViewNome);
            tvQtd = (TextView) v.findViewById(R.id.textViewQtd);
            tvValor = (TextView) v.findViewById(R.id.textViewValor);

        }

    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Estoque estoque = estoques.get(position);

        holder.tvName.setText(estoque.getNome());
        holder.tvQtd.setText(estoque.getQuantidade().toString());
        holder.tvValor.setText(estoque.getValorUni().toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return estoques.size();
    }
}
