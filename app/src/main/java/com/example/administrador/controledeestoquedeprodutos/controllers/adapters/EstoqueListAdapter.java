package com.example.administrador.controledeestoquedeprodutos.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrador.controledeestoquedeprodutos.R;
import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;

import java.util.List;

/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueListAdapter extends BaseAdapter {
    Activity context;
    List<Estoque> estoques;

    public EstoqueListAdapter(Activity context, List<Estoque> estoques){
        this.context = context;
        this.estoques = estoques;
    }


    @Override
    public int getCount() {
        return estoques.size();
    }

    @Override
    public Estoque getItem(int position) {
        return estoques.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Estoque estoque = getItem(position);
        View estoqueList = context.getLayoutInflater().inflate(R.layout.list_item_product, parent, false);

        ImageView viewImg = (ImageView) estoqueList.findViewById(R.id.imgViewProduct);
        String cor = "#000000";
        viewImg.setBackgroundColor(android.graphics.Color.parseColor(cor));

        TextView textViewName = (TextView) estoqueList.findViewById(R.id.textViewNome);
        if(estoque.getNome() != null)
            textViewName.setText(estoque.getNome().toString());

        TextView textViewQtd = (TextView) estoqueList.findViewById(R.id.textViewQtd);
        if(estoque.getQuantidade() != null)
            textViewQtd.setText(estoque.getQuantidade().toString());

        TextView textViewValor = (TextView) estoqueList.findViewById(R.id.textViewValor);
        if(estoque.getValorUni() != null)
            textViewValor.setText(String.valueOf(estoque.getValorUni()));

        return estoqueList;
    }

    public void setItens(List<Estoque> itens){
        estoques.clear();
        estoques.addAll(itens);
    }
}
