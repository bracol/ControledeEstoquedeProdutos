package com.example.administrador.controledeestoquedeprodutos.controllers.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.example.administrador.controledeestoquedeprodutos.model.servicos.EstoqueBusinessServices;

import java.util.List;

/**
 * Created by Administrador on 25/09/2015.
 */
public class SaveAsync  extends AsyncTask<Estoque, ProgressDialog, Void> {
    @Override
    protected Void doInBackground(Estoque... params) {

        EstoqueBusinessServices.save(params[0]);
        return null;
    }
}
