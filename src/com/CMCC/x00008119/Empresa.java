package com.CMCC.x00008119;

import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> planilla;

    public Empresa(String nombre) {
        this.nombre = nombre;
        planilla = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getPlanilla() {
        return planilla;
    }
    public void addEmpleado(Empleado empleado){

    }
    public void quitEmpleado(String empleado){

    }
}
