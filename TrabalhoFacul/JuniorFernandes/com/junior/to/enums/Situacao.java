package com.junior.to.enums;

/*
 * @author Junior
 */
public enum Situacao {

    /**
     * Situaçao para pendencias
     */
    ABERTA(1, "pendente"),
    /**
     * Situaçao para finalizados
     */
    FINALIZADA(2, "Finalizada"),
    /**
     * Situaçao cancelados 
     */
    CANCELADA(3, "Cancelada");

    private final int ID;
    private final String DESCRICAO;

    private Situacao(int id, String descricao) {
        this.ID = id;
        this.DESCRICAO = descricao;
    }

    /*
     * @return int id
     */
    public int getId() {
        return ID;
    }

    @Override
    public String toString() {
        return this.DESCRICAO;
    }
}
