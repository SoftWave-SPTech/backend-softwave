package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

@Entity
public class AnaliseProcesso {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        private Processo processo;

        @Lob
        @Column(columnDefinition = "TEXT")
        private String resumoIA;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Processo getProcesso() {
                return processo;
        }

        public void setProcesso(Processo processo) {
                this.processo = processo;
        }

        public String getResumoIA() {
                return resumoIA;
        }

        public void setResumoIA(String resumoIA) {
                this.resumoIA = resumoIA;
        }
}


