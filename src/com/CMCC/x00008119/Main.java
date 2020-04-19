package com.CMCC.x00008119;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Empresa covid = new Empresa("Covid-19");
        byte option, op = 0;
        do {
            System.out.print("\nIngrese una opción:\n" +
                    "1. Agregar empleado.\n" +
                    "2. Despedir empleado.\n" +
                    "3. Ver lista de empleados.\n" +
                    "4. Calcular sueldo.\n" +
                    "5. Mostrar totales.\n" +
                    "0. Salir\n" +
                    "Su elección: ");
            option = in.nextByte(); in.nextLine();

            switch (option) {
                case 1:
                    try{
                    System.out.print("\nTipo de empleado:\n1. Plaza Fija\n2. Servicio Profesional\nSu opción: ");
                    op = in.nextByte(); in.nextLine();
                    switch(op){
                        case 1:
                            try {
                                covid.addEmpleado(insertEmployed(1));
                            }
                            catch (InvalidSalaryException i){
                                System.out.println(i.getMessage());
                        }
                            //Aún así el programa finaliza
                            catch (InputMismatchException i){
                                System.out.println("Hubo un error al ingresar los datos");
                            }
                            break;
                        case 2:
                            try {
                                covid.addEmpleado(insertEmployed(2));
                            }
                            catch (InvalidSalaryException i){
                                System.out.println(i.getMessage());
                            }
                            break;
                        default:
                            System.out.println("La opción que ingresó es inválida");
                            break;
                    }
                    } catch (AlreadyExistDocumentException e){
                        System.out.println(e.getMessage() + "\nEl ingreso de usuario ha sido abortado.");
                    }
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
                    calculateSalary(covid);
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
        public static void mostrarPlanilla(Empresa e){
            System.out.println("\nMostrando lista de empleados de " + e.getNombre() + ":");
            for (Empleado empleado : e.getPlanilla()) {
                System.out.println("Nombre: " + empleado.getNombre()
                        + "\n\tPuesto: " + empleado.getPuesto()
                        + "\n\tSalario: $" + empleado.getSalario());
                if (empleado instanceof ServicioProfesional)
                    System.out.println("\tMeses de contrato: " + ((ServicioProfesional) empleado).getMeses());
                else
                    System.out.println("\tExtensión: " + ((PlazaFija) empleado).getExtension());
                System.out.println("\tDocumentos: ");
                for (Documento documento : empleado.getDocumentos()){
                    System.out.println("\t\t"+documento.toString());
                }
            }
        }

    //Busca empleado ingresado por el usuario y descuenta los impuestos
        public static void calculateSalary(Empresa empresa){
            System.out.print("Ingrese el nombre del empleado: ");
            String nombre = in.nextLine();
            for(Empleado empleado : empresa.getPlanilla()){
                if(empleado.getNombre().equalsIgnoreCase(nombre)) {
                    DecimalFormat dosDecimales = new DecimalFormat("#.##");
                    System.out.print("Sueldo con descuentos: $");
                    System.out.print(dosDecimales.format(CalculadoraImpuestos.calcularPago(empleado))+"\n");
                }
                else
                    System.out.println("El empleado no se encuentra en la planilla.");
            }
        }
        public static PlazaFija requestPlazaFija(){
            String nombre,puesto;
            double salario;
            int extension;
            System.out.print("\nNombre: ");
            nombre = in.nextLine();
            System.out.print("Puesto: ");
            puesto = in.nextLine();
            System.out.print("Salario: $");
            salario = in.nextDouble();in.nextLine();

            System.out.print("Extensión: ");
            extension = in.nextInt();in.nextLine();
            return new PlazaFija(nombre,puesto,salario,extension);
        }
        public static ServicioProfesional requestServicioProfesional(){
            String nombre, puesto;
            double salario;
            int mesesDeContrato;
            System.out.print("\nNombre: ");
            nombre = in.nextLine();
            System.out.print("Puesto: ");
            puesto = in.nextLine();
                System.out.print("Salario: $");
                salario = in.nextDouble();
                in.nextLine();
            System.out.print("Meses de contrato: ");
            mesesDeContrato = in.nextInt();in.nextLine();
        return new ServicioProfesional(nombre,puesto,salario,mesesDeContrato);
        }

        public static Empleado insertEmployed(int option) throws AlreadyExistDocumentException {
            Empleado employed;
            String nombreDocumento="", numeroDocumento="";
            byte cont=0;
            if(option == 1){
                employed = requestPlazaFija();
            }
            else
                employed = requestServicioProfesional();
            do{
                System.out.print("\nIngrese nombre de documento (Enter para abortar solicitud): ");
                nombreDocumento= in.nextLine();
                if (!nombreDocumento.isEmpty()){
                    cont++;
                    System.out.print("Ingrese el número del documento: ");
                    numeroDocumento = in.nextLine();
                    employed.addDocumento(new Documento(nombreDocumento,numeroDocumento));
                }
                else if(cont==0)
                    System.out.println("Debe ingresar al menos un documento");
            }while((!nombreDocumento.equals(""))||(cont==0));
            return employed;
        }
}


