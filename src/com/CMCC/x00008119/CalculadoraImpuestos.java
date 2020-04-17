package com.CMCC.x00008119;

public final class CalculadoraImpuestos {
    private static double totalRenta=0;
    private static double totalISSS=0;
    private static double totalAFP=0;

    private CalculadoraImpuestos(){}

    public static double calcularPago(Empleado empleado){
        double pago = 0;
        double restante=0;

        if(empleado instanceof ServicioProfesional){
            double salarioSP=0;
            salarioSP = empleado.getSalario();

            totalRenta = 0.1*salarioSP;

            pago = salarioSP - totalRenta;
        }

        else if(empleado instanceof PlazaFija){
            double salarioPF=0;
            salarioPF = empleado.getSalario();

            totalAFP = 0.0625*salarioPF;
            totalISSS = 0.03*salarioPF;

            restante = salarioPF - totalAFP - totalISSS;

            if((restante>=0.01)&&(restante<=472.00)){
                totalRenta = 0;
            }
            else if((restante>=472.01)&&(restante<=895.24)){
                totalRenta = 0.1*(restante-472)+17.67;
            }
            else if((restante>=895.25)&&(restante<=2038.10)){
                totalRenta = 0.2*(restante-895.24)+60;
            }
            else if((restante>=2038.11)){
                totalRenta = 0.3*(restante-2038.10)+288.57;
            }
            pago = restante - totalRenta;
        }
        return pago;
    }

    public static String mostrarTotales(){
        return    "\nTotal de renta: $" + totalRenta
                + "\nTotal ISSS:     $" + totalISSS
                + "\nTotal de AFP:   $" + totalAFP + "\n";
    }
}
