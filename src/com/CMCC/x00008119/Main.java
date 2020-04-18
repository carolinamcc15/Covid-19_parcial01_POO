package com.CMCC.x00008119;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Empresa covid = new Empresa("Covid-19");
        byte option = 0, op = 0;
        char opcion = ' ';
        int extension=0, mContrato=0;
        String nombre, puesto, nombreDoc, numero, aux;
        double salario=0;

        do {
            System.out.print("\nIngrese una opción:\n" +
                    "1. Agregar empleado.\n" +
                    "2. Despedir empleado.\n" +
                    "3. Ver lista de empleados.\n" +
                    "4. Calcular sueldo.\n" +
                    "5. Mostrar totales.\n" +
                    "0. Salir\n" +
                    "Su elección: ");

            option = in.nextByte();
            in.nextLine();
            switch (option) {
                case 1:
                    System.out.print("\nTipo de empleado:\n1. Plaza Fija\n2. Servicio Profesional\nSu opción: ");
                    op = in.nextByte(); in.nextLine();

                    if(op==1) {
                        System.out.print("\nNombre: ");
                        nombre = in.nextLine();
                        System.out.print("Puesto: ");
                        puesto = in.nextLine();
                        System.out.print("Salario: $");
                        salario = in.nextDouble();in.nextLine();
                        System.out.print("Extensión: ");
                        extension = in.nextInt();in.nextLine();

                        PlazaFija plaza = new PlazaFija(nombre, puesto, salario, extension);
                        covid.addEmpleado(plaza);

                        ArrayList<Documento> doc = new ArrayList<>();
                        System.out.println("\nDocumento de identificación");
                        while(opcion!='n') {
                            System.out.print("Nombre del documento: ");
                            nombreDoc = in.nextLine();
                            System.out.print("Número de documento: ");
                            numero = in.nextLine();

                            Documento d = new Documento(nombreDoc, numero);
                            doc.add(d);

                            System.out.print("\n¿Desea agregar otro documento? (s/n): ");
                            opcion = in.next().charAt(0); in.nextLine();
                        }
                    }
                    else if(op==2){
                        System.out.print("\nNombre: ");
                        nombre = in.nextLine();
                        System.out.print("Puesto: ");
                        puesto = in.nextLine();
                        System.out.print("Salario: $");
                        salario = in.nextDouble();in.nextLine();
                        System.out.print("Meses de contrato: ");
                        mContrato = in.nextInt();in.nextLine();

                        ServicioProfesional servicio = new ServicioProfesional(nombre, puesto, salario, mContrato);
                        covid.addEmpleado(servicio);

                        ArrayList<Documento> doc = new ArrayList<>();
                        System.out.println("\nDocumento de identificación");
                        while(opcion!='n') {
                            System.out.print("Nombre del documento: ");
                            nombreDoc = in.nextLine();
                            System.out.print("Número de documento: ");
                            numero = in.nextLine();

                            Documento d = new Documento(nombreDoc, numero);
                            doc.add(d);

                            System.out.print("\n¿Desea agregar otro documento? (s/n): ");
                            opcion = in.next().charAt(0); in.nextLine();
                        }
                    }
                    else
                        System.out.println("La opción que ingresó es inválida");
                    break;

                case 2:
                    try {
                        String auxNombreEmpleado;
                        System.out.print("Ingrese el nombre del empleado a despedir: ");
                        auxNombreEmpleado = in.nextLine();
                        covid.quitEmpleado(auxNombreEmpleado);
                    } catch (NotFoundEmployedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    mostrarPlanilla(covid);
                    break;

                case 4:
                    System.out.print("Ingrese el nombre del empleado: ");
                    aux = in.nextLine();
                    salarioEmpleado(covid, aux);
                    break;

                case 5:
                    System.out.println("\nMostrando totales..." + CalculadoraImpuestos.mostrarTotales());
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida!");
                    break;

            }
        } while (option != 0);

    }
        public static void mostrarPlanilla (Empresa e){
            System.out.println("Mostrando lista de empleados de " + e.getNombre() + ":");
            ArrayList<Empleado> listaDeEmpleados = e.getPlanilla();
            for (Empleado empleado : listaDeEmpleados) {
                System.out.println("Nombre: " + empleado.getNombre()
                        + "\n\tPuesto: " + empleado.getPuesto()
                        + "\n\tSalario: $" + empleado.getSalario());
                if (empleado instanceof ServicioProfesional)
                    System.out.println("\tMeses de contrato: " + ((ServicioProfesional) empleado).getMeses());
                else
                    System.out.println("\tExtensión: " + ((PlazaFija) empleado).getExtension());
            }
        }

    //Busca empleado ingresado por el usuario y descuenta los impuestos
        public static void salarioEmpleado(Empresa e, String nombre){
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados = e.getPlanilla();
        for(Empleado a : empleados){
            if(a.getNombre().equalsIgnoreCase(nombre)) {
                double salary = 0;
                double conDescuento = 0;
                salary = a.getSalario();
                conDescuento = CalculadoraImpuestos.calcularPago(a);
                System.out.println("Sueldo con descuentos: $" + conDescuento);
            }
        }
    }
}


