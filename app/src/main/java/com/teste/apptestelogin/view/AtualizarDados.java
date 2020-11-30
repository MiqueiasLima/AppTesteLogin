package com.teste.apptestelogin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teste.apptestelogin.R;
import com.teste.apptestelogin.api.ApiUtil;
import com.teste.apptestelogin.controller.Cadastro_Controller;
import com.teste.apptestelogin.controller.Login_Controller;
import com.teste.apptestelogin.model.Cliente;

public class AtualizarDados extends AppCompatActivity {

    EditText txtHintPrimeiroNome;
    EditText txtHintSegundoNome;
    EditText txtHintEmailCadastro;
    EditText txtHintSenhaCadastro;
    EditText txtHintRepitaSenhaAlterada;
    Button btnSalvarEdicao;
    Button btnEditar;

    Login_Controller loginController;
    Cadastro_Controller cadastro_controller;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_dados);
        initFormulario();

        cliente = loginController.getClienteById();
        txtHintPrimeiroNome.setText(cliente.getPrimeiroNome());
        txtHintSegundoNome.setText(cliente.getSegundoNome());
        txtHintEmailCadastro.setText(cliente.getEmail());
        //txtHintSenhaCadastro.setText(cliente.getSenha());



    }

    private void initFormulario() {

        txtHintPrimeiroNome = findViewById(R.id.txtHintPrimeiroNome);
        txtHintSegundoNome = findViewById(R.id.txtHintSegundoNome);
        txtHintEmailCadastro = findViewById(R.id.txtHintEmailCadastro);
        txtHintSenhaCadastro = findViewById(R.id.txtHintSenhaCadastro);
        txtHintRepitaSenhaAlterada = findViewById(R.id.txtHintRepitaSenhaAlterada);
        btnEditar = findViewById(R.id.btnEditar);
        btnSalvarEdicao = findViewById(R.id.btnSalvarEdicao);
        loginController = new Login_Controller(getApplicationContext());
        cadastro_controller = new Cadastro_Controller(getApplicationContext());
    }

    public void voltarMeusDados(View view) {

        Intent intent = new Intent(AtualizarDados.this,Acesso_Activity.class);
        startActivity(intent);

    }

    public void editarCliente(View view){
        //Ativar o botão Salvar
        //Desabilitar botão Editar
        btnEditar.setEnabled(false);
        btnSalvarEdicao.setEnabled(true);

        txtHintPrimeiroNome.setEnabled(true);
        txtHintSegundoNome.setEnabled(true);
        txtHintSenhaCadastro.setEnabled(true);
        txtHintRepitaSenhaAlterada.setEnabled(true);

    }

    public void salvarEdicaoCliente(View view) {

        if(verificaCampos()){

            Cliente cliente = new Cliente();
            cliente.setPrimeiroNome(txtHintPrimeiroNome.getText().toString());
            cliente.setSegundoNome(txtHintSegundoNome.getText().toString());
            cliente.setEmail(txtHintEmailCadastro.getText().toString());
            cliente.setSenha(ApiUtil.gerarMD5Hash(txtHintSenhaCadastro.getText().toString()));

            if(cadastro_controller.alterar(cliente)){
                Toast.makeText(getApplicationContext(),"Alteração Realizada",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AtualizarDados.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {

                Toast.makeText(getApplicationContext(),"Erro ao Realizar Alteração",Toast.LENGTH_LONG).show();
            }

        }

    }

    private boolean verificaCampos() {

        boolean retorno = true;
        String senha = txtHintSenhaCadastro.getText().toString();
        String repitaSenha = txtHintRepitaSenhaAlterada.getText().toString();

        if (TextUtils.isEmpty(txtHintPrimeiroNome.getText().toString())) {
            txtHintPrimeiroNome.setError("Campo Inválido");
            txtHintPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(txtHintSegundoNome.getText().toString())) {
            txtHintSegundoNome.setError("Campo Inválido");
            txtHintSegundoNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(txtHintRepitaSenhaAlterada.getText().toString())) {
            txtHintRepitaSenhaAlterada.setError("Campo Inválido");
            txtHintRepitaSenhaAlterada.requestFocus();
            retorno = false;
        }

        if(TextUtils.isEmpty(txtHintSenhaCadastro.getText().toString())){
            txtHintRepitaSenhaAlterada.setError("Campo Inválido");
            txtHintRepitaSenhaAlterada.requestFocus();
            retorno = false;
        }

        if (!senha.equals(repitaSenha)) {
            txtHintRepitaSenhaAlterada.setError("Senhas Incompatíveis");
            txtHintRepitaSenhaAlterada.requestFocus();
            retorno = false;
        }


        return retorno;
    }

}