package com.newmantech.appcliente.utils;

import android.text.format.Formatter;
import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class Utilitario {

    public static String IP_LOCAL = "http://192.168.1.183";

    //public static String baseUrl = IP_LOCAL+":8075/";

    //public static String baseUrlServio = IP_LOCAL+":8080/ServiciosWebSaas/";

    public static String baseUrl = IP_LOCAL+":8075/";

    public static String baseUrlPis= IP_LOCAL+":8077/";

    public static String baseUrlServio = IP_LOCAL+":8080/ServiciosWebSaas/";

    public static String baseUrlSWeb = IP_LOCAL+":8080/WebSaas/";

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i("IP", "***** IP_LOCAL="+ ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("IP", ex.toString());
        }
        return null;
    }

    public static Integer idCliente = 3339;

    public static String getIP(){
        List<InetAddress> addrs;
        String address = "";
        try{
            /*List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces){
                addrs = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addrs){
                    if(!addr.isLoopbackAddress() && addr instanceof Inet4Address){
                        address = addr.getHostAddress().toUpperCase(new Locale("es", "MX"));
                        Log.w("IP", "Ex getting IP value " + address);
                    }
                }
            }*/

            InetAddress address2 = InetAddress.getLocalHost();
            //System.out.println("IP Local :"+address2.getHostAddress());
            Log.w("IP", "Ex getting IP local " + address2);
            address = address2.getHostAddress();
        }catch (Exception e){
            Log.w("IP", "Ex getting IP value " + e.getMessage());
        }
        return address;
    }

    public static boolean isInteger(String pNumber)
    {
        try {
            double vDecimal = Double.parseDouble(pNumber);
            if(vDecimal == (int) vDecimal){
                return true;
            }else{
                return false;
            }
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isNumeric(String pNumber)
    {
        try {
            double number = Double.parseDouble(pNumber);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isEmail(String pEmail) {
        //^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(pEmail);
        return matcher.find();
    }

    public static double roundDecimals(double d, int decimals)
    {
        String pattern = "#0.";
        for(int i=0; i<decimals; i++){
            pattern = pattern + "#";
        }
        DecimalFormat twoDForm = new DecimalFormat(pattern);
        return Double.valueOf(twoDForm.format(d));
    }


    public  enum ESTADO_FLUJO_TRABAJO{
        CREACION_FLUJO (100,"CREACION FLUJO","Se está procesando su pedido"),
        APROBACION_FLUJO (101,"APROBACION FLUJO","Se está procesando su pedido"),
        ABASTECIMIENTO_FLUJO (102,"ABASTECIMIENTO FLUJO","En preparación"),

        PRODUCCION_FLUJO (103,"PRODUCCION FLUJO","En preparación"),
        CONTROL_CALIDAD_FLUJO (104,"CONTROL CALIDAD FLUJO","En preparación"),
        PRE_DISTRIBUCION_FLUJO (105,"PRE DISTRIBUCION FLUJO","Pedido programado para su distribución"),

        INCIO_ENTREGA_REPROGRAMAOD (106,"INICIO ENTREGA REPROGRAMADO","Pedido re-programado para su distribución"),

        INCIO_ENTREGA_FLUJO (107,"INICIO ENTREGA FLUJO","Pedido enviado"),
        INCIDENCIA_FLUJO (108,"INCIDENCIA FLUJO","Se ha registrado una incidencia en la entrega"),

        FIN_ENTREGA_FLUJO (109,"FIN ENTREGA FLUJO","Pedido entregado");

        private final int codigo;
        private final String  texto;
        private final String keyMsg;

        private ESTADO_FLUJO_TRABAJO(int codigo, String texto, String keyMsg){
            this.codigo = codigo;
            this.texto = texto;
            this.keyMsg= keyMsg;
        }
        public int getCodigo() {
            return codigo;
        }
        public String getTexto() {
            return texto;
        }
        public String getKeyMsg() {
            return keyMsg;
        }

        public static ESTADO_FLUJO_TRABAJO getEstadoWorkFlowByID(Integer id){
            ESTADO_FLUJO_TRABAJO[] valores = ESTADO_FLUJO_TRABAJO.values();
            for(int i=0; i<valores.length; i++){
                if(valores[i].getCodigo() == id){
                    return  valores[i];
                }
            }
            return null;
        }

        public static String getEstadoWorkFlowKeyMsg(Integer estado){
            String descripcion =  null;
            for(Utilitario.ESTADO_FLUJO_TRABAJO item : Utilitario.ESTADO_FLUJO_TRABAJO.values() ){
                if(item.getCodigo() ==estado){
                    descripcion = item.getKeyMsg();
                    break;
                }

            }
            return descripcion;
        }
    }

}
