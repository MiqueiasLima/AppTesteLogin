package com.teste.apptestelogin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.teste.apptestelogin.R;
import com.teste.apptestelogin.api.ClienteAdapter;
import com.teste.apptestelogin.controller.Cadastro_Controller;
import com.teste.apptestelogin.model.Cliente;

import java.util.List;

public class ConsultarClientesActivity extends AppCompatActivity {

    List<Cliente> clientes;

    ClienteAdapter adapter;

    Cliente obj;
    Cadastro_Controller controller;

    RecyclerView rvClientesVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clientes);

        rvClientesVip = findViewById(R.id.rvClientesVip);

        controller = new Cadastro_Controller(getApplicationContext());

        clientes = controller.listar();

        adapter = new ClienteAdapter(clientes, getApplicationContext());

        rvClientesVip.setAdapter(adapter);

        rvClientesVip.setLayoutManager(new LinearLayoutManager(this));

    }
}