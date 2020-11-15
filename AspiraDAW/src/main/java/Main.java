
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author pablo
 */
public class Main {
public static Casa casa;
public static Aspirador aspirador;
public static final double CONSUMOFREGAR = 0.75;
public static final double CONSUMOASPIRAR = 1.5;
public static final String USUARIO = "admin";
public static final String CLAVE = "admin";
    public static void main(String[] args) {
        aspirador = new Aspirador();

        boolean accesoValido;
        do{
            String u = JOptionPane.showInputDialog(null, "Introduce el usuario");
            String c = JOptionPane.showInputDialog(null, "Introdzca contraseña");
            if (u.equals (USUARIO) && c.equals(CLAVE)){
                accesoValido = true;
                configurarSistema();

                int opcion;
                do{
                    //Crear menú general de la aplicación
                    opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Menú principal\n\n 1.Configurar el sistema\n "
                    + "2.Carga\n 3.Aspiración\n 4.Aspiración y fregado\n 5.Estado general\n 6.Base de carga\n 7.Salir"));

                    switch (opcion) {
                        case 1: configurarSistema();
                            break;
                        case 2: carga();
                            break;
                        case 3: modoAspirar();
                            break;
                        case 4: modoAspirarFregar();
                            break;
                        case 5: estadoGeneral ();
                            break;
                        case 6: baseCarga ();
                            break;
                        default:
                            JOptionPane.showInputDialog(null, "Saliendo...");
                            break;
                    }
                }while(opcion!=7);
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
                accesoValido = false;
            }
        }while (!accesoValido);
        
    }
    
    //Metodo para configurar la casa
    public static void configurarSistema(){
        int opcion = Integer.parseInt (JOptionPane.showInputDialog(null, "¿Qué desea hacer?\n 1. Configurar casa por defecto \n 2.Crear casa personalizada"));
        
        switch (opcion){
            case 1: casaPorDefecto ();
                break;
            case 2: casaPersonalizada ();
                break;
            default :
                break;
        }
    }
    
    //Metodo para configurar la casa por defecto del ejercicio
    public static void casaPorDefecto (){
        boolean valorValido = false;
        casa = new Casa();
        Dependencia[] dependencias = new Dependencia [5];
        
        Dependencia cocina = new Dependencia ();
        cocina.nombre = "Cocina";
        while (!valorValido){
            cocina.metros = Integer.parseInt (JOptionPane.showInputDialog(null, "¿Cuántos metros cuadrados tiene la cocina?"));
            if (comprobarMetros(cocina.metros)){
                valorValido = true;
                dependencias[0] = cocina;
            }else{
                JOptionPane.showMessageDialog(null, "Los metros deben ser entre 1 y 100.");
            }
        }
        valorValido = false;
       
        Dependencia salon = new Dependencia ();
        salon.nombre = "Salón";
        while (!valorValido){
            salon.metros = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos metros cuadrados tiene el salón?"));
            if (comprobarMetros(salon.metros)){
                valorValido = true;
                dependencias[1] = salon;
            }else{
                JOptionPane.showMessageDialog(null, "Los metros deben ser entre 1 y 100.");
            }
        }
        valorValido = false;
        
        Dependencia cuartoBaño = new Dependencia ();
        cuartoBaño.nombre = "Cuarto de baño";
        while (!valorValido){
            cuartoBaño.metros = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos metros cuadrados tiene el cuarto de baño"));
            if (comprobarMetros(cuartoBaño.metros)){
                valorValido = true;
                dependencias[2] = cuartoBaño;
            }else{
                JOptionPane.showMessageDialog(null, "Los metros deben ser entre 1 y 100.");
            }
        }
        valorValido = false;
        
        Dependencia dormitorio1 = new Dependencia ();
        dormitorio1.nombre = "Dormitorio 1";
        while (!valorValido){
            dormitorio1.metros = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos metros cuadrados tiene el dormitorio 1?"));
            if (comprobarMetros(dormitorio1.metros)){
                valorValido = true;
                dependencias[3] = dormitorio1;
            }else{
                JOptionPane.showMessageDialog(null, "Los metros deben ser entre 1 y 100");
            }
        }
        valorValido = false;
        
        Dependencia dormitorio2 = new Dependencia ();
        dormitorio2.nombre = "Dormitorio 2";
        while (!valorValido){
            dormitorio2.metros = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos metros cuadrados tiene el dormitorio 2?"));
            if (comprobarMetros(dormitorio2.metros)){
                valorValido = true;
                dependencias[4] = dormitorio2;
            }else{
                JOptionPane.showMessageDialog(null, "Los metros deben ser entre 1 y 100.");
            }
        }
        casa.dependencias = dependencias;   
    }
    
    //Metodo para configurar la casa como mejora opcional
    public static void casaPersonalizada (){
        casa = new Casa();
        int numeroDependencias = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas dependencias tiene la casa?"));
        Dependencia[] dependencias = new Dependencia[numeroDependencias];
        for (int i = 1; i<=numeroDependencias; i++){
            Dependencia dependencia = new Dependencia ();
            dependencia.nombre = JOptionPane.showInputDialog(null, "¿Cual es el nombre de la dependencia "+i+"?");
            dependencia.metros = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuantos metros tiene la dependencia "+dependencia.nombre+"?"));
            dependencias[i-1] = dependencia;     
        }
        casa.dependencias = dependencias; 
    }
   
    //Metodo para comprobar que el valor introducidos de los metros es valido
    public static boolean comprobarMetros (int metros){
        boolean valorCorrecto;  
        if (metros<1 || metros>100){
            valorCorrecto = false;  
        }else{
            valorCorrecto = true;
        }
        return valorCorrecto;
    }
    
    //Metodo para añadir el valor de la bateria entre los valores validos
    public static void carga (){
        boolean valorValido = false;
        while (!valorValido){
            aspirador.bateria = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuanta batería tiene el aspirador?"));
            if (comprobarBateria(aspirador.bateria)){
                valorValido = true;
            }else{
                JOptionPane.showMessageDialog(null, "La batería debe estar entre 0 y 100");
            }
        }    
    }

    //Metodo para comprobar que el valor introducido en la bateria es valido
    public static boolean comprobarBateria (double bateria){
        boolean valorCorrecto;  
        if (bateria<0 || bateria>100){
            valorCorrecto = false;  
        }else{
            valorCorrecto = true;
        }
        return valorCorrecto;
    }
    
    //Metodo para seleccionar que tipo de aspiración desea ejecutar el usuario
    public static void modoAspirar (){
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué tipo de aspiración desea?\n 1. Modo Completo \n 2.Modo dependencias"));
        switch (opcion){
            case 1: modoCompleto ("aspirar");
                break;
            case 2: modoDependencias ("aspirar");
                break;
            default:
                break;
        }
    }
    
    //Metodo para seleccionar que tipo de aspiracion y fregado desea ejecutar el usuario
    public static void modoAspirarFregar (){
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué tipo de aspiración y fregado desea?\n 1. Modo completo\n 2. Modo dependencias"));
        switch (opcion){
            case 1: modoCompleto ("AspirarFregar");
                break;
            case 2: modoDependencias ("AspirarFregar");
                break;
        }
    }
    
    //Metodo para configurar el modo de limpieza completo
    public static void modoCompleto (String modo){
        int numeroDependencias = casa.dependencias.length;
        int i = 0;
        while (i<numeroDependencias && bateriaSuficiente (modo, casa.dependencias[i])){
            if (modo.equals ("aspirar")){
                aspirar (casa.dependencias[i]);
            }else if (modo.equals ("AspirarFregar")){
                aspirar (casa.dependencias[i]);
                fregar (casa.dependencias[i]);
            }
            i++;
        }
        if (i+1 == numeroDependencias){
            JOptionPane.showMessageDialog(null, "Limpieza finalizada");            
        }   
    }
    
    //Metodo para configurar el modo de limpieza por dependencias
    public static void modoDependencias (String modo){
        String mensaje = "¿Qué dependencia desea limpiar?";
        int numeroDependencias = casa.dependencias.length;  
        for (int i = 1; i<=numeroDependencias; i++){
            mensaje = mensaje + "\n "+i+". "+casa.dependencias[i-1].nombre;
        }
        
        int dependencia = Integer.parseInt(JOptionPane.showInputDialog(null, mensaje));
        dependencia --;
        if (bateriaSuficiente (modo, casa.dependencias[dependencia])){
            if (modo.equals ("aspirar")){
                aspirar (casa.dependencias[dependencia]);
            }else if (modo.equals ("AspirarFregar")){
                aspirar (casa.dependencias[dependencia]);
                fregar (casa.dependencias[dependencia]);
            }
        }
    }
    
    //Metodo para comprobar que el aspirador tiene bateria para limpiar la dependencia y volver a la zona de carga
    public static boolean bateriaSuficiente (String modo, Dependencia dependencia){
        boolean suficiente;
        double bateriaNecesaria = 0.0; 
        if (modo.equals ("aspirar")){
            bateriaNecesaria = dependencia.metros *CONSUMOASPIRAR; 
        }else if (modo.equals ("AspirarFregar")){
            bateriaNecesaria = dependencia.metros * (CONSUMOASPIRAR + CONSUMOFREGAR);   
        }
        bateriaNecesaria = bateriaNecesaria +3;
        if (aspirador.bateria >= bateriaNecesaria ){
            suficiente = true;
        }else{
            JOptionPane.showMessageDialog(null, "Batería agotada.");
            suficiente = false;
        }
        return suficiente;
    }
    
    //Metodo para configurar el modo de aspiracion
    public static void aspirar (Dependencia dependencia){
        JOptionPane.showMessageDialog(null, "Iniciando aspiración de "+dependencia.nombre);
        aspirador.ubicacion = dependencia;
        aspirador.bateria = aspirador.bateria - (dependencia.metros*CONSUMOASPIRAR);
        JOptionPane.showMessageDialog(null, "Finalizada aspiracion de "+dependencia.nombre);
    }
    
    //Metodo para configurar el modo de fregado
    public static void fregar (Dependencia dependencia){
        JOptionPane.showMessageDialog(null, "Iniciando fregado de "+dependencia.nombre);
        aspirador.ubicacion = dependencia;
        aspirador.bateria = aspirador.bateria - (dependencia.metros*CONSUMOFREGAR);
        JOptionPane.showMessageDialog(null, "Finalizado el fregado de "+dependencia.nombre);
    }
    
    //Metodo para configurar el estado general del aspirador
    public static void estadoGeneral (){
        SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
        Date fecha = new Date ();
        JOptionPane.showMessageDialog(null, formato.format(fecha));
        JOptionPane.showMessageDialog(null, "Batería actual: "+aspirador.bateria+"%");
        if (aspirador.ubicacion != null){
            JOptionPane.showMessageDialog(null, "El aspirador se encuentra en "+aspirador.ubicacion.nombre);
        }
        String mensaje = "Casa: ";
        int numeroDependencias = casa.dependencias.length;
        int metros = 0;
        for(int i = 0; i<numeroDependencias; i++){
            mensaje = mensaje +"\n "+casa.dependencias[i].nombre+" "+casa.dependencias[i].metros+" metros cuadrados";
            metros += casa.dependencias[i].metros;
        }
        mensaje = mensaje + "\n Total: "+metros+" metros cuadrados.";
        JOptionPane.showMessageDialog(null, mensaje);   
    }
    
    //Metodo para configurar que el aspirador se cargue al 100% cuando vuelva a su base de carga
    public static void baseCarga (){
        JOptionPane.showMessageDialog(null, "Volviendo a base de carga");
        JOptionPane.showMessageDialog(null, "Cargando batería");
        aspirador.bateria = 100;
        JOptionPane.showMessageDialog(null, "Batería cargada.");
    }
}


