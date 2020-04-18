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
    planilla.add(empleado);
    }

    public void quitEmpleado(String empleado) throws NotFoundEmployedException {
        boolean empleadoEncontrado = false;
        for (Empleado e : planilla){
            if (e.getNombre().equalsIgnoreCase(empleado))
                empleadoEncontrado=true;
        }
        if (empleadoEncontrado)
        planilla.removeIf(e -> (e.getNombre().equalsIgnoreCase(empleado)));
        else throw new NotFoundEmployedException("El empleado no ha sido encontrado.\n");
    }
}
