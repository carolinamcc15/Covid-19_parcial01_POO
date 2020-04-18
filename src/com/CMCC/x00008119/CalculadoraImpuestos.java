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
            totalRenta += 0.1*empleado.getSalario();
            return 0.9*empleado.getSalario();
        }
        else if(empleado instanceof PlazaFija){
            totalAFP += 0.0625*empleado.getSalario();
            totalISSS += 0.03*empleado.getSalario();
            restante = empleado.getSalario()*0.9075;
            if((restante>=0.01)&&(restante<=472.00)){
                pago = restante;
            }
            else if((restante>=472.01)&&(restante<=895.24)){
                pago = restante - 0.1*(restante-472)+17.67;
            }
            else if((restante>=895.25)&&(restante<=2038.10)){
                pago = restante - 0.2*(restante-895.24)+60;
            }
            else if((restante>=2038.11)){
                pago = restante - 0.3*(restante-2038.10)+288.57;
            }
            totalRenta += restante - pago;
        }
        return pago;
    }
    public static String mostrarTotales(){
        return    "\nTotal de renta: $" + totalRenta
                + "\nTotal ISSS:     $" + totalISSS
                + "\nTotal de AFP:   $" + totalAFP + "\n";
    }
}
