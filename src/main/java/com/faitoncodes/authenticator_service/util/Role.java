package com.faitoncodes.authenticator_service.util;

public enum Role {
    PROFESSOR(1, "Professor"),
    ALUNO(2, "Aluno");

    private int valor;
    private String descricao;

    Role(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Role fromValor(int valor) {
        for (Role tipo : Role.values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}
