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

    public void addEmpleado(Empleado empleado) throws AlreadyExistEmployeeException {
        for (Empleado auxEmpleado : planilla){
            if (auxEmpleado.nombre.equalsIgnoreCase(empleado.nombre))
                throw new AlreadyExistEmployeeException("El empleado " + empleado.nombre + " ya ha sido registrado en planilla.");
        }
        planilla.add(empleado);
    }

    public void quitEmpleado(String empleado) throws NotFoundEmployedException, EmptyFieldException {
        boolean empleadoEncontrado = false;
        if (empleado.isEmpty())
            throw new EmptyFieldException("Debe ingresar un nombre.");
        for (Empleado e : planilla){
            if (e.getNombre().equalsIgnoreCase(empleado))
                empleadoEncontrado=true;
        }
        if (empleadoEncontrado)
        planilla.removeIf(e -> (e.getNombre().equalsIgnoreCase(empleado)));
        else throw new NotFoundEmployedException("El empleado no se encuentra en la planilla.");
    }
}
