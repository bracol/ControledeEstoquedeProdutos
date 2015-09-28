package com.example.administrador.controledeestoquedeprodutos.model.async;

import android.os.AsyncTask;

import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.example.administrador.controledeestoquedeprodutos.model.http.EstoqueService;
import com.example.administrador.controledeestoquedeprodutos.model.servicos.EstoqueBusinessServices;

import java.util.List;

/**
 * Created by Administrador on 28/09/2015.
 */
public class FindWebProducts extends AsyncTask <Void, Void, List<Estoque>> {
    @Override
    protected List<Estoque> doInBackground(Void... params) {
        return EstoqueService.getEstoques();
    }
}
