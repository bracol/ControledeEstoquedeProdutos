package com.example.administrador.controledeestoquedeprodutos.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 25/09/2015.
 */
public class Estoque implements Parcelable {

    private Integer id;
    private Integer img;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Integer qtdMinimaEstoque;
    private Double valorUni;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQtdMinimaEstoque() {
        return qtdMinimaEstoque;
    }

    public void setQtdMinimaEstoque(Integer qtdMinimaEstoque) {
        this.qtdMinimaEstoque = qtdMinimaEstoque;
    }

    public Double getValorUni() {
        return valorUni;
    }

    public void setValorUni(Double valorUni) {
        this.valorUni = valorUni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estoque estoque = (Estoque) o;

        if (Double.compare(estoque.valorUni, valorUni) != 0) return false;
        if (id != null ? !id.equals(estoque.id) : estoque.id != null) return false;
        if (img != null ? !img.equals(estoque.img) : estoque.img != null) return false;
        if (nome != null ? !nome.equals(estoque.nome) : estoque.nome != null) return false;
        if (descricao != null ? !descricao.equals(estoque.descricao) : estoque.descricao != null)
            return false;
        if (quantidade != null ? !quantidade.equals(estoque.quantidade) : estoque.quantidade != null)
            return false;
        return !(qtdMinimaEstoque != null ? !qtdMinimaEstoque.equals(estoque.qtdMinimaEstoque) : estoque.qtdMinimaEstoque != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (qtdMinimaEstoque != null ? qtdMinimaEstoque.hashCode() : 0);
        temp = Double.doubleToLongBits(valorUni);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.img);
        dest.writeString(this.nome);
        dest.writeString(this.descricao);
        dest.writeValue(this.quantidade);
        dest.writeValue(this.qtdMinimaEstoque);
        dest.writeDouble(this.valorUni);
    }

    public Estoque() {
    }

    protected Estoque(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.img = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nome = in.readString();
        this.descricao = in.readString();
        this.quantidade = (Integer) in.readValue(Integer.class.getClassLoader());
        this.qtdMinimaEstoque = (Integer) in.readValue(Integer.class.getClassLoader());
        this.valorUni = in.readDouble();
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
