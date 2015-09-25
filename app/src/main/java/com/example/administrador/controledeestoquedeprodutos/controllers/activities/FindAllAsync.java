package com.example.administrador.controledeestoquedeprodutos.controllers.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.example.administrador.controledeestoquedeprodutos.model.servicos.EstoqueBusinessServices;

import java.util.List;

/**
 * Created by Administrador on 25/09/2015.
 */
public class FindAllAsync extends AsyncTask<Void, Void, List<Estoque>> {
    @Override
    protected List<Estoque> doInBackground(Void... params) {
        return EstoqueBusinessServices.findAll();
    }
}
