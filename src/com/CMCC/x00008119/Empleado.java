package com.CMCC.x00008119;

import java.util.ArrayList;

public abstract class Empleado {
    protected String nombre;
    protected String puesto;
    protected ArrayList<Documento> documentos;
    protected double salario;

    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        documentos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }
    public void addDocumento(Documento documento){

    }
    public void removeDocumento(String documento){

    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
