package com.CMCC.x00008119;

public final class CalculadoraImpuestos {
    private static double totalRenta=0;
    private static double totalISSS=0;
    private static double totalAFP=0;
    private CalculadoraImpuestos(){ }
    public static double calcularPago(Empleado empleado){
        double pago = 0;
        return pago;
    }
    public static String mostrarTotales(){
        return "\nTotal de renta: $" + totalRenta
                + "\nTotal ISSS: $" + totalISSS
                + "\nTotal de AFP: $" + totalAFP;
    }
}
