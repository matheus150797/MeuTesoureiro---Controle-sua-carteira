package com.rightside.meutesoureiro_controlesuacarteira.model;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rightside.meutesoureiro_controlesuacarteira.config.ConfiguracaoFirebase;
import com.rightside.meutesoureiro_controlesuacarteira.helper.Base64Custom;
import com.rightside.meutesoureiro_controlesuacarteira.helper.DateCustom;

import java.util.EventListener;


public class Movimentacao {

    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private double valor;
    private String key;

    public Movimentacao() {
    }

    public void apagar() {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64( autenticacao.getCurrentUser().getEmail() );
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("movimentacao").child(idUsuario).removeValue();

        DatabaseReference apagarDespesa = ConfiguracaoFirebase.getFirebaseDatabase();
        apagarDespesa.child("usuarios")
                .child( idUsuario )
                .child("despesaTotal").removeValue();

        DatabaseReference apagarReceita = ConfiguracaoFirebase.getFirebaseDatabase();
        apagarReceita.child("usuarios")
                .child( idUsuario )
                .child("receitaTotal").removeValue();
    }

    public void salvar(String dataEscolhida){

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64( autenticacao.getCurrentUser().getEmail() );
        String mesAno = DateCustom.mesAnoDataEscolhida( dataEscolhida );

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("movimentacao")
                .child( idUsuario )
                .child( mesAno )
                .push()
                .setValue(this);

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
