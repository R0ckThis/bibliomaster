/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author ramon
 */
public class Valor {

    private double diaria;
    private double multa;

    public Valor() {
        this.diaria = 0.0;
        this.multa = 0.0;
    }

    /**
     * @return the diaria
     */
    public double getDiaria() {
        return diaria;
    }

    /**
     * @param diaria the diaria to set
     */
    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    /**
     * @return the multa
     */
    public double getMulta() {
        return multa;
    }

    /**
     * @param multa the multa to set
     */
    public void setMulta(double multa) {
        this.multa = multa;
    }
}
