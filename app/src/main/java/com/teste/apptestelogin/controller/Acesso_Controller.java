package com.teste.apptestelogin.controller;

import android.content.Context;

import com.teste.apptestelogin.api.AppDataBase;

public class Acesso_Controller extends AppDataBase {

    public static String nomeDB;

    public Acesso_Controller(Context context) {
        super(context);
    }

    public static  String enviaNome(){

        return Acesso_Controller.nomeDB;
    }

}
