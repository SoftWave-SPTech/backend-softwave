package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import jakarta.persistence.*;

@Entity
public class AnaliseProcesso {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        private UltimasMovimentacoes movimentacoes;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String resumoIA;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public UltimasMovimentacoes getMovimentacoes() {
                return movimentacoes;
        }

        public void setMovimentacoes(UltimasMovimentacoes movimentacoes) {
                this.movimentacoes = movimentacoes;
        }

        public String getResumoIA() {
                return resumoIA;
        }

        public void setResumoIA(String resumoIA) {
                this.resumoIA = resumoIA;
        }
}


