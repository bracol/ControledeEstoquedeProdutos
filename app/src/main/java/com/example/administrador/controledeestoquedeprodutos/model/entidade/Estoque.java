package com.example.administrador.controledeestoquedeprodutos.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrador on 25/09/2015.
 */
public class Estoque implements Parcelable {

    @JsonIgnore
    private Integer _id;

    @JsonProperty("image")
    private String img;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("description")
    private String descricao;

    @JsonProperty("stock")
    private Long quantidade;

    @JsonProperty("minimunStock")
    private Long qtdMinimaEstoque;

    @JsonProperty("unitaryValue")
    private Double valorUni;

    @JsonProperty("id")
    private Long web_id;

    @JsonProperty("date")
    private Long date;

    @JsonIgnore
    public Integer get_id() {
        return _id;
    }

    @JsonIgnore
    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getQtdMinimaEstoque() {
        return qtdMinimaEstoque;
    }

    public void setQtdMinimaEstoque(Long qtdMinimaEstoque) {
        this.qtdMinimaEstoque = qtdMinimaEstoque;
    }

    public Double getValorUni() {
        return valorUni;
    }

    public void setValorUni(Double valorUni) {
        this.valorUni = valorUni;
    }

    public Long getWeb_id() {
        return web_id;
    }

    public void setWeb_id(Long web_id) {
        this.web_id = web_id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estoque estoque = (Estoque) o;

        if (_id != null ? !_id.equals(estoque._id) : estoque._id != null) return false;
        if (img != null ? !img.equals(estoque.img) : estoque.img != null) return false;
        if (nome != null ? !nome.equals(estoque.nome) : estoque.nome != null) return false;
        if (descricao != null ? !descricao.equals(estoque.descricao) : estoque.descricao != null)
            return false;
        if (quantidade != null ? !quantidade.equals(estoque.quantidade) : estoque.quantidade != null)
            return false;
        if (qtdMinimaEstoque != null ? !qtdMinimaEstoque.equals(estoque.qtdMinimaEstoque) : estoque.qtdMinimaEstoque != null)
            return false;
        if (valorUni != null ? !valorUni.equals(estoque.valorUni) : estoque.valorUni != null)
            return false;
        if (web_id != null ? !web_id.equals(estoque.web_id) : estoque.web_id != null) return false;
        return !(date != null ? !date.equals(estoque.date) : estoque.date != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (qtdMinimaEstoque != null ? qtdMinimaEstoque.hashCode() : 0);
        result = 31 * result + (valorUni != null ? valorUni.hashCode() : 0);
        result = 31 * result + (web_id != null ? web_id.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + _id +
                ", img='" + img + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", qtdMinimaEstoque=" + qtdMinimaEstoque +
                ", valorUni=" + valorUni +
                ", web_id=" + web_id +
                ", date=" + date +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.img);
        dest.writeString(this.nome);
        dest.writeString(this.descricao);
        dest.writeValue(this.quantidade);
        dest.writeValue(this.qtdMinimaEstoque);
        dest.writeValue(this.valorUni);
        dest.writeValue(this.web_id);
        dest.writeValue(this.date);
    }

    public Estoque() {
    }

    protected Estoque(Parcel in) {
        this._id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.img = in.readString();
        this.nome = in.readString();
        this.descricao = in.readString();
        this.quantidade = (Long) in.readValue(Integer.class.getClassLoader());
        this.qtdMinimaEstoque = (Long) in.readValue(Integer.class.getClassLoader());
        this.valorUni = (Double) in.readValue(Double.class.getClassLoader());
        this.web_id = (Long) in.readValue(Long.class.getClassLoader());
        this.date = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Estoque> CREATOR = new Creator<Estoque>() {
        public Estoque createFromParcel(Parcel source) {
            return new Estoque(source);
        }

        public Estoque[] newArray(int size) {
            return new Estoque[size];
        }
    };
}
