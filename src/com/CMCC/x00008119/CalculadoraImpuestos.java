package com.CMCC.x00008119;
public final class CalculadoraImpuestos {
    private static double totalRenta=0;
    private static double totalISSS=0;
    private static double totalAFP=0;

    private CalculadoraImpuestos(){}

    public static double calcularPago(Empleado empleado){
        double pago = 0, restante=0;
        if(empleado instanceof ServicioProfesional){
            totalRenta += 0.1*empleado.getSalario();
            return 0.9*empleado.getSalario();
        }
        else if(empleado instanceof PlazaFija){
            double renta = 0;
            totalAFP += 0.0625*empleado.getSalario();
            totalISSS += 0.03*empleado.getSalario();
            restante = empleado.getSalario()*0.9075;

            if((restante>=0.01)&&(restante<=472.00)){
                renta = 0;
            }
            else if((restante>=472.01)&&(restante<=895.24)){
                renta = 0.1*(restante-472)+17.67;
            }
            else if((restante>=895.25)&&(restante<=2038.10)){
                renta = 0.2*(restante-895.24)+60;
            }
            else if((restante>=2038.11)){
                renta = 0.3*(restante-2038.10)+288.57;
            }
            totalRenta += renta;
            pago = restante - renta;
        }
        return pago;
    }
    public static String mostrarTotales(){
        return    "\nTotal de renta: $" + String.format("%.2f", totalRenta)
                + "\nTotal ISSS:     $" + String.format("%.2f", totalISSS)
                + "\nTotal de AFP:   $" + String.format("%.2f", totalAFP);
    }
}
