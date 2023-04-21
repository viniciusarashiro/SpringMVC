package br.com.codelab.regescweb.models;

import jakarta.persistence.*;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoCurso tipoCurso;

    public Curso() {

    }

    public Curso(String nome, String descricao, TipoCurso tipoCurso) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipoCurso = tipoCurso;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipoCurso=" + tipoCurso +
                '}';
    }
}
