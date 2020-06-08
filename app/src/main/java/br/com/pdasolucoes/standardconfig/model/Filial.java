package br.com.pdasolucoes.standardconfig.model;

import androidx.annotation.NonNull;

public class Filial {

    private int codigo;
    private String nome;

    @NonNull
    @Override
    public String toString() {
        return codigo + " - " + nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
