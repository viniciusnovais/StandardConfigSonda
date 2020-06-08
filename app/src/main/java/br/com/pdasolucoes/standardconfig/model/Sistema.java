package br.com.pdasolucoes.standardconfig.model;

/**
 * Created by PDA on 03/10/2017.
 */

public class Sistema {

    private int codigo;
    private String nome;
    private String sigla;
    private String packageName;
    private String imagePath;
    private String nameApk;
    private String namePaste;
    private String updateDescription;
    private String versions;

    public Sistema() {
    }

    public Sistema(int codigo, String nome, String sigla) {
        this.codigo = codigo;
        this.nome = nome;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getNameApk() {
        return nameApk;
    }

    public void setNameApk(String nameApk) {
        this.nameApk = nameApk;
    }

    public String getNamePaste() {
        return namePaste;
    }

    public void setNamePaste(String namePaste) {
        this.namePaste = namePaste;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }
}
