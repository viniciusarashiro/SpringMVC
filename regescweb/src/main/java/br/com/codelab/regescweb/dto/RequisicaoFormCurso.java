package br.com.codelab.regescweb.dto;

import br.com.codelab.regescweb.models.Curso;
import br.com.codelab.regescweb.models.TipoCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
    public class RequisicaoFormCurso {
        @NotBlank
        @NotNull
        private String nome;
        @NotNull
        @NotBlank
        private String descricao;
        private TipoCurso tipoCurso;

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

        public Curso toCurso() {
            Curso curso = new Curso();
            curso.setNome(this.nome);
            curso.setDescricao(this.descricao);
            curso.setTipoCurso(this.tipoCurso);
            return curso;
        }

        public Curso toCurso(Curso curso) {
            curso.setNome(this.nome);
            curso.setDescricao(this.descricao);
            curso.setTipoCurso(this.tipoCurso);
            return curso;
        }

        public void fromCurso(Curso curso) {
            this.nome = curso.getNome();
            this.descricao = curso.getDescricao();
            this.tipoCurso = curso.getTipoCurso();
        }

        @Override
        public String toString() {
            return "RequisicaoFormCurso{" +
                    "nome='" + nome + '\'' +
                    ", descricao='" + descricao + '\'' +
                    ", tipoCurso=" + tipoCurso +
                    '}';
        }
    }


