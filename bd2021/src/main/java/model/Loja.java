package model;

public class Loja {
    private Integer id;
    private String nome;
    private String loja_link;
    private String loja_img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLoja_link() {
        return loja_link;
    }

    public void setLoja_link(String loja_link) {
        this.loja_link = loja_link;
    }

    public String getLoja_img() {
        return loja_img;
    }

    public void setLoja_img(String loja_img) {
        this.loja_img = loja_img;
    }
}
