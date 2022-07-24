package me.ads.lembretemedicamentos.medicamentos;

public class Medicamentos {

    private int id;
    private String nome;
    private String dosagem;
    private String tipo;
    private String posologia;
    private String horario;
    private String tempotratamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public String getTempotratamento() {
        return tempotratamento;
    }

    public void setTempotratamento(String tempotratamento) {
        this.tempotratamento = tempotratamento;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
