package model;

import java.sql.Date;

public class PrecoData {
    private Date data_registro;
    private Float preco;
    private Integer porcentagem_promo;
    private Integer jogo_id;
    private Integer loja_id;

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getPorcentagem_promo() {
        return porcentagem_promo;
    }

    public void setPorcentagem_promo(Integer porcentagem_promo) {
        this.porcentagem_promo = porcentagem_promo;
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
