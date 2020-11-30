package com.teste.apptestelogin.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.teste.apptestelogin.controller.Acesso_Controller;
import com.teste.apptestelogin.dbframeworkapp.ClienteDataModel;
import com.teste.apptestelogin.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper {

    static int clienteID;
    Acesso_Controller acessoController;
    public static final String LOG_APP = "CLIENTE_LOG";
    private static final String DB_NAME = "clienteDB.sqlite";
    private static final int DB_VERSION = 1;
    Cursor cursor;
    SQLiteDatabase db;

    public AppDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase(); //Habilitar Leitura e Escrita no Banco

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(ClienteDataModel.gerarTabela());
            Log.i(LOG_APP, "TABELA: " + ClienteDataModel.gerarTabela());
        } catch (SQLException e) {
            Log.i(LOG_APP, "Erro TB Cliente: " + e.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String tabela, ContentValues values) {

        boolean sucesso = true;

        try {

            sucesso = db.insert(tabela, null, values) > 0;
            Log.i(LOG_APP, "Inserção realizada com Sucesso");

        } catch (SQLException e) {
            Log.e(LOG_APP, "Erro ao inserir na Tabela: " + tabela + " Erro: " + e.getMessage());
            sucesso = false;
        }

        return sucesso;

    }

    public boolean update(String tabela, ContentValues values) {

        boolean sucesso = true;

        try {

            sucesso = db.update(tabela, values, "id=?", new String[]{Integer.toString(clienteID)}) > 0;
            Log.i(LOG_APP, "Update Realizado com Sucesso");


        } catch (SQLException e) {
            Log.e(LOG_APP, "Falhou ao executar o update: " + e.getMessage());
        }

        return sucesso;
    }


    public boolean delete(String tabela) {

        boolean sucesso = true;

        try {

            Log.i(LOG_APP, "Usuário deletado com Sucesso");
            sucesso = db.delete(tabela, "id=?", new String[]{Integer.toString(clienteID)}) > 0;

        } catch (SQLException e) {

            Log.e(LOG_APP, "Erro ao Deletar: " + e.getMessage());

        }

        return sucesso;
    }

    public boolean buscaEmail(String tabela, String email) {
        boolean retorno = true;

        String query = "SELECT * FROM " + tabela;

        try {

            String emailBD;
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    emailBD = cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL));

                    if (email.equals(emailBD)) {
                        retorno = false;
                        return retorno;
                    }

                } while (cursor.moveToNext());
            }


        } catch (SQLException e) {
            Log.i(LOG_APP, "Erro ao realizar Busca: " + e.getMessage());
        }


        return retorno;
    }


    public boolean autenticaCliente(String tabela, String email, String senha) {

        boolean retorno = true;

        String query = "SELECT * FROM " + tabela;

        try {

            String emailBD = null;
            String senhaBD = null;
            String nomeDB = null;
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                do {

                    emailBD = cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL));
                    senhaBD = cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA));
                    nomeDB = cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME));


                    if (email.equals(emailBD) && senha.equals(senhaBD)) {
                        Acesso_Controller.nomeDB = nomeDB;
                        clienteID = cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID));
                        retorno = true;
                        return true;
                    }

                } while (cursor.moveToNext());

            }

            retorno = false;
            return retorno;

        } catch (SQLException e) {
            Log.e(LOG_APP, "Erro ao realizar busca: " + e.getMessage());
            retorno = false;
            return retorno;
        }

    }

    public Cliente getClienteByID(String tabela) {

        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM " + tabela + " WHERE id = " + clienteID;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor.moveToNext()) {

                cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
                cliente.setSegundoNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SEGUNDO_NOME)));
                cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));

            }

        } catch (SQLException e) {
            Log.e(LOG_APP, "Erro: getClienteById: " + e.getMessage());
        }

        return cliente;

    }

    public List<Cliente> listClientes(String tabela) {

        List<Cliente> list = new ArrayList<>();

        Cliente cliente;
        // Select no banco de dados
        // SELECT * FROM tabela

        String sql = "SELECT * FROM " + tabela;

        try {

            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    cliente = new Cliente();

                    cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                    cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
                    cliente.setSegundoNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SEGUNDO_NOME)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));

                    list.add(cliente);

                } while (cursor.moveToNext());

                Log.i(LOG_APP, tabela + " lista gerada com sucesso.");

            }

        } catch (SQLException e) {

            Log.e(LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(LOG_APP, "Erro: " + e.getMessage());
        }

        return list;

    }
}
