package br.com.pdasolucoes.standardconfig.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Reposicao implements KvmSerializable {

    private int Produto;
    private String Griffe;
    private String Linha;
    private String Codigo;
    private String Descricao;
    private int Quantidade;
    private String Tamanho;
    private String Cor;
    private String Foto;
    private String Status;
    private String DataHora;

    public void setProduto(int produto) {
        Produto = produto;
    }

    public void setGriffe(String griffe) {
        Griffe = griffe;
    }

    public void setLinha(String linha) {
        Linha = linha;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public void setTamanho(String tamanho) {
        Tamanho = tamanho;
    }

    public void setCor(String cor) {
        Cor = cor;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setDataHora(String dataHora) {
        DataHora = dataHora;
    }

    public Reposicao() {
        this.Produto = -1;
        this.Griffe = "";
        this.Linha = "";
        this.Codigo = "";
        this.Descricao = "";
        this.Quantidade = 0;
        this.Tamanho = "";
        this.Cor = "";
        this.Foto = "";
        this.Status = "";
        this.DataHora = "";
    }

    @Override
    public Object getProperty(int i) {

        switch (i) {
            case 0:
                return Produto;
            case 1:
                return Codigo;
            case 2:
                return Status;
            case 3:
                return DataHora;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int i, Object o) {

        switch (i) {
            case 0:
                Produto = Integer.parseInt(o.toString());
                break;
            case 1:
                Codigo = o.toString();
                break;
            case 2:
                Status = o.toString();
                break;
            case 3:
                DataHora = o.toString();
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "Produto";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Codigo";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Status";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DataHora";
                break;
        }
    }
}
