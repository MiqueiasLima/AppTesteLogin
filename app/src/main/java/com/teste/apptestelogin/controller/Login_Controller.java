package com.teste.apptestelogin.controller;

import android.content.Context;

import com.teste.apptestelogin.api.AppDataBase;
import com.teste.apptestelogin.dbframeworkapp.ClienteDataModel;
import com.teste.apptestelogin.model.Cliente;

public class Login_Controller extends AppDataBase {


    private static final String TABELA = ClienteDataModel.TABELA;

    public Login_Controller(Context context) {
        super(context);
    }

    public boolean autenticacao(String email, String senha){

        return autenticaCliente(TABELA,email,senha);
    }

    public Cliente getClienteById(){

        return getClienteByID(ClienteDataModel.TABELA);

    }

}
