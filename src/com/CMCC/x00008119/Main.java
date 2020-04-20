package com.CMCC.x00008119;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        in.useLocale(Locale.US); //Permite el uso de '.' como separador decimal
        Empresa covid = new Empresa("Covid-19");
        byte option, op;
        do {
            try{
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
                }
            catch (InputMismatchException e) {
                    System.out.println("\nDebe ingresar un número.");
                    in.nextLine();
                    option=7;
                }

            switch (option) {
                case 1:
                    try {
                        System.out.print("\nTipo de empleado:\n1. Plaza Fija\n2. Servicio Profesional\nSu opción: ");
                        op = in.nextByte();
                        in.nextLine();
                        switch (op) {
                            case 1:
                                covid.addEmpleado(insertEmployed(1));
                                break;
                            case 2:
                                covid.addEmpleado(insertEmployed(2));
                                break;
                            default:
                                System.out.println("La opción que ingresó es inválida. \nEl ingreso de empleado ha sido abortado.");
                                break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\nFormatos de datos numéricos inválido." + "\nEl ingreso de empleado ha sido abortado.");
                        in.nextLine();// Limpia el Scanner para evitar generar otra excepcion
                    }
                      catch (InvalidNumberException e) {
                            System.out.println("\nError. Algunos valores numéricos son inválidos \nIngreso de empleado abortado.");
                        }
                    catch (AlreadyExistDocumentException |AlreadyExistEmployeeException| EmptyFieldException e){
                    System.out.println(e.getMessage() + "\nEl ingreso de empleado ha sido abortado.");
                }
                break;
                case 2:
                    try {
                        String auxNombreEmpleado;
                        System.out.print("Ingrese el nombre del empleado a despedir: ");
                        auxNombreEmpleado = in.nextLine();
                        covid.quitEmpleado(auxNombreEmpleado);
                        System.out.println("El empleado " + auxNombreEmpleado + " ha sido despedido.");
                    } catch (NotFoundEmployedException | EmptyFieldException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    mostrarPlanilla(covid);
                    break;
                case 4:
                    try {
                        calculateSalary(covid);
                    } catch (EmptyFieldException i) {
                        System.out.println(i.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("\nMostrando totales..." + CalculadoraImpuestos.mostrarTotales());
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida! Intente de nuevo.");
                    break;
            }
        }while (option != 0);
    }
        public static void mostrarPlanilla(Empresa empresa){
            System.out.println("\nMostrando lista de empleados de " + empresa.getNombre() + ":");
            for (Empleado empleado : empresa.getPlanilla()) {
                System.out.println("Nombre: " + empleado.getNombre()
                        + "\n\tPuesto: " + empleado.getPuesto()
                        + "\n\tSalario: $" + String.format("%.2f",empleado.getSalario()));
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
        public static void calculateSalary(Empresa empresa) throws EmptyFieldException{
            byte cont = 0;
            System.out.print("\nIngrese el nombre del empleado: ");
            String nombre = in.nextLine();
            if (nombre.isEmpty())
                throw new EmptyFieldException("Debe ingresar un nombre.");
            else{
            for(Empleado empleado : empresa.getPlanilla()) {
                if (empleado.getNombre().equalsIgnoreCase(nombre)) {
                    cont++;
                    System.out.print("Sueldo con descuentos: $");
                    System.out.print(String.format("%.2f", CalculadoraImpuestos.calcularPago(empleado)) + "\n");
                }
            }
            if(cont==0)
                System.out.println("El empleado no se encuentra en la planilla.");
            }
        }
        public static PlazaFija requestPlazaFija() throws EmptyFieldException, InvalidNumberException{
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
            if (nombre.isEmpty()|| puesto.isEmpty())
               throw new EmptyFieldException("Se han detectado campos vacíos.");
            else if((salario<=0)||(extension<=0))
                throw new InvalidNumberException("\nError. Algunos datos numéricos ingresados son inválidos.");
            else return new PlazaFija(nombre,puesto,salario,extension);
        }
        public static ServicioProfesional requestServicioProfesional() throws EmptyFieldException, InvalidNumberException {
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
            if (nombre.isEmpty()|| puesto.isEmpty())
                throw new EmptyFieldException("\nError. Se han detectado campos vacíos.");
            else if((salario<=0)||(mesesDeContrato<=0))
                throw new InvalidNumberException("\nError. Algunos datos numéricos ingresados son inválidos.");
            else return new ServicioProfesional(nombre,puesto,salario,mesesDeContrato);
        }

        public static Empleado insertEmployed(int option) throws AlreadyExistDocumentException, EmptyFieldException, InvalidNumberException {
            Empleado employed;
            String nombreDocumento, numeroDocumento;
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
                    System.out.print("Ingrese el número del documento: ");
                    numeroDocumento = in.nextLine();
                    if(!numeroDocumento.isEmpty()) {
                        employed.addDocumento(new Documento(nombreDocumento, numeroDocumento));
                        cont++;
                    }
                    else
                    System.out.println("El documento no pudo ser guardado debido a campos incompletos.");
                }
                else if(cont==0)
                    System.out.println("Debe ingresar al menos un documento.");
            }while((!nombreDocumento.equals(""))||(cont==0));
            return employed;
        }
}


