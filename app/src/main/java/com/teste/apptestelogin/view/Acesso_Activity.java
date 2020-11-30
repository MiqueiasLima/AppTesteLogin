package com.teste.apptestelogin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.teste.apptestelogin.R;
import com.teste.apptestelogin.controller.Acesso_Controller;
import com.teste.apptestelogin.controller.Cadastro_Controller;

public class Acesso_Activity extends AppCompatActivity {

    TextView txtBoasVindas;
    String nome;
    Cadastro_Controller cadastroController;
    public static String nomeCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acesso_);
        initVariaveis();
        nomeCliente = Acesso_Activity.recebeNome();
        txtBoasVindas.setText("Bem Vindo(a) " + Acesso_Activity.nomeCliente);

    }

    public static String recebeNome() {
        return Acesso_Controller.enviaNome();
    }

    public void initVariaveis() {

        txtBoasVindas = findViewById(R.id.txtBoasVindas);

        cadastroController = new Cadastro_Controller(getApplicationContext());
    }

    public void meusDados(View view) {

        Intent intent = new Intent(Acesso_Activity.this, MeusDados.class);
        startActivity(intent);


    }

    public void atualizarDados(View view) {

        Intent intent = new Intent(Acesso_Activity.this, AtualizarDados.class);
        startActivity(intent);

    }

    public void excluirConta(View view) {

        new FancyAlertDialog.Builder(this)
                .setTitle("EXCLUIR SUA CONTA")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Tem certeza que deseja excluir sua conta?")
                .setNegativeBtnText("RETORNAR")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        if (cadastroController.deletar()) {
                            Toast.makeText(getApplicationContext(), "Conta Excluída com Sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Acesso_Activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), "Continue Usando nosso Aplicativo", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

    }

    public void sairAplicativo(View view) {

        new FancyAlertDialog.Builder(this)
                .setTitle("SAIR DO APLICATIVO")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Tem certeza que deseja sair?")
                .setNegativeBtnText("RETORNAR")
                .setPositiveBtnBackground(Color.parseColor("#21EA17"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#ED0317"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                            Toast.makeText(getApplicationContext(), "Você Saiu", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Acesso_Activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), "Continue Usando nosso Aplicativo", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();


    }

    public void listarClientes(View view) {

        Intent intent = new Intent(Acesso_Activity.this,ConsultarClientesActivity.class);
        startActivity(intent);

    }
}