package com.teste.apptestelogin.controller;

import android.content.ContentValues;
import android.content.Context;

import com.teste.apptestelogin.api.AppDataBase;
import com.teste.apptestelogin.dbframeworkapp.ClienteDataModel;
import com.teste.apptestelogin.model.Cliente;

import java.util.List;

public class Cadastro_Controller extends AppDataBase {

    private static final String TABELA = ClienteDataModel.TABELA;
    private ContentValues values;

    public Cadastro_Controller(Context context) {
        super(context);
    }

    public boolean incluir(Cliente cliente){

        values = new ContentValues();

        values.put(ClienteDataModel.PRIMEIRO_NOME,cliente.getPrimeiroNome());
        values.put(ClienteDataModel.SEGUNDO_NOME,cliente.getSegundoNome());
        values.put(ClienteDataModel.EMAIL,cliente.getEmail());
        values.put(ClienteDataModel.SENHA,cliente.getSenha());

        return insert(TABELA,values);
    }

    public boolean alterar(Cliente cliente){

        values = new ContentValues();
        values.put(ClienteDataModel.PRIMEIRO_NOME,cliente.getPrimeiroNome());
        values.put(ClienteDataModel.SEGUNDO_NOME,cliente.getSegundoNome());
        values.put(ClienteDataModel.EMAIL,cliente.getEmail());
        values.put(ClienteDataModel.SENHA,cliente.getSenha());

        return update(TABELA,values);
    }

    public boolean deletar(){

        return delete(TABELA);
    }

    public List<Cliente> listar(){

        return listClientes(TABELA);
    }

    public boolean validarEmail(String email) {

        return buscaEmail(TABELA,email);
    }
}
