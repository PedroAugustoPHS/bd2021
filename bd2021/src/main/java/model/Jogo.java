package model;


public class Jogo {
    private Integer id;
    private String titulo;
    private String desenvolvedora;
    private String categoria;
    private String descricao;
    private String publicadora;
    private String ano_publicacao;
    private String cpu;
    private String gpu;
    private String memoria_ram;
    private String so;
    private String armazenamento;
    private String image;

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPublicadora() {
        return publicadora;
    }

    public String getAno_publicacao() {
        return ano_publicacao;
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public String getMemoria_ram() {
        return memoria_ram;
    }

    public String getSo() {
        return so;
    }

    public String getArmazenamento() {
        return armazenamento;
    }

    public String getImage() { return image; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPublicadora(String publicadora) {
        this.publicadora = publicadora;
    }

    public void setAno_publicacao(String ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setMemoria_ram(String memoria_ram) {
        this.memoria_ram = memoria_ram;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void setImage(String image) { this.image = image; }
}
