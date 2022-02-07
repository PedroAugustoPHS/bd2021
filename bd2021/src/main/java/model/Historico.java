package model;

import java.sql.Date;

public class Historico {
    private Date data_menor_preco;
    private Integer menor_preco;
    private Integer media_preco;
    private Integer maior_promo;
    private Integer jogo_id;
    private Integer loja_id;

    public Date getData_menor_preco() {
        return data_menor_preco;
    }

    public void setData_menor_preco(Date data_menor_preco) {
        this.data_menor_preco = data_menor_preco;
    }

    public Integer getMenor_preco() {
        return menor_preco;
    }

    public void setMenor_preco(Integer menor_preco) {
        this.menor_preco = menor_preco;
    }

    public Integer getMedia_preco() {
        return media_preco;
    }

    public void setMedia_preco(Integer media_preco) {
        this.media_preco = media_preco;
    }

    public Integer getMaior_promo() {
        return maior_promo;
    }

    public void setMaior_promo(Integer maior_promo) {
        this.maior_promo = maior_promo;
    }

    public Integer getJogo_id() {
        return jogo_id;
    }

    public void setJogo_id(Integer jogo_id) {
        this.jogo_id = jogo_id;
    }

    public Integer getLoja_id() {
        return loja_id;
    }

    public void setLoja_id(Integer loja_id) {
        this.loja_id = loja_id;
    }
}
