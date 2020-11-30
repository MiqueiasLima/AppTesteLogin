package com.teste.apptestelogin.dbframeworkapp;

public class ClienteDataModel {

    public static final String TABELA = "cliente";
    public static final String ID = "id";
    public static final String PRIMEIRO_NOME = "primeiroNome";
    public static final String SEGUNDO_NOME = "segundoNome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";


    public static String gerarTabela(){

        String query = "create table "+TABELA+ " ( ";

        query += ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += PRIMEIRO_NOME + " TEXT, ";
        query += SEGUNDO_NOME + " TEXT, ";
        query += EMAIL + " TEXT, ";
        query += SENHA+ " TEXT ";

        query += " ) ";

        return query;
    }


}
