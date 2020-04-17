package com.CMCC.x00008119;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	Empresa covid = new Empresa("Covid-19");
	byte option = 0;
	do {
        System.out.println("Ingrese una opción:\n" +
                "1. Agregar empleado.\n" +
                "2. Despedir empleado.\n" +
                "3. Ver lista de empleados.\n" +
                "4. Calcular sueldo.\n" +
                "5. Mostrar totales.\n" +
                "0. Salir");
	    switch (option){
            case 1:
                //covid.addEmpleado();
                break;
            case 2:
                //covid.quitEmpleado();
                break;
            case 3:
                mostrarPlanilla(covid);
                break;
            case 4:
                break;
            case 5:
                CalculadoraImpuestos.mostrarTotales();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("opción inválida!");
                break;

        }
    }while(option!=0);
    }
    public static void mostrarPlanilla(Empresa e){
        System.out.println("Mostrando lista de empleados de " + e.getNombre() + ":");
        ArrayList<Empleado> listaDeEmpleados = e.getPlanilla();
        for (Empleado empleado : listaDeEmpleados){
            System.out.println("Nombre: "+ empleado.getNombre()
            + "\n\tPuesto: " + empleado.getPuesto()
            + "\n\tSalario: "+ empleado.getSalario());
            if (empleado instanceof ServicioProfesional)
                System.out.println("\tMeses de contrato: " + ((ServicioProfesional) empleado).getMeses());
            else
                System.out.println("\tExtensión: " + ((PlazaFija) empleado).getExtension());
        }
    }
}
