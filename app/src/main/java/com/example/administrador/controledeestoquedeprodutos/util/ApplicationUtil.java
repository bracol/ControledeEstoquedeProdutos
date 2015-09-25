package com.example.administrador.controledeestoquedeprodutos.util;

import android.content.Context;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ApplicationUtil {

    private static Context APPLICATION_CONTEXT;

    private ApplicationUtil() {
        super();
    }

    public static void setContext(Context context){
        APPLICATION_CONTEXT = context;
    }

    public static Context getContext(){
        return ApplicationUtil.APPLICATION_CONTEXT;
    }
}
