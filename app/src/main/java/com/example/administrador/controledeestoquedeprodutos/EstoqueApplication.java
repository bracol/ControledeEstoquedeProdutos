package com.example.administrador.controledeestoquedeprodutos;

import android.app.Application;

import com.example.administrador.controledeestoquedeprodutos.util.ApplicationUtil;

/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueApplication extends Application {
    //application manipula ciclo de vida da aplicação

    public void onCreate(){
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }

}
