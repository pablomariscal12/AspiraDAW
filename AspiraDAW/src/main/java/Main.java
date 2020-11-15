
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pablo
 */
public class Main {
public static Casa casa;
public static Aspirador aspirador;
public static final double consumoFregar = 0.75;
public static final double consumoAspirar = 1.5;
    public static void main(String[] args) {
        aspirador = new Aspirador();
        int opcion = 0;
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
    }
    
    public static void configurarSistema(){
        int opcion = Integer.parseInt (JOptionPane.showInputDialog(null, "¿Qué desea hacer?\n 1. Configurar casa por defecto \n 2.Crear casa personalizada"));
        
        switch (opcion){
            case 1: casaPorDefecto ();
                break;
            case 2:
                break;
            default :
                break;
        }
    }
    
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
   
    public static boolean comprobarMetros (int metros){
     boolean valorCorrecto;  
     if (metros<1 || metros>100){
       valorCorrecto = false;  
     }else{
         valorCorrecto = true;
     }
     return valorCorrecto;
             }
    
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

    public static boolean comprobarBateria (double bateria){
     boolean valorCorrecto;  
     if (bateria<0 || bateria>100){
       valorCorrecto = false;  
     }else{
         valorCorrecto = true;
     }
     return valorCorrecto;
             }
    
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
    
    public static void modoAspirarFregar (){
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué tipo de aspiración y fregado desea?\n 1. Modo completo\n 2. Modo dependencias"));
        
        switch (opcion){
            case 1: modoCompleto ("AspirarFregar");
                break;
            case 2: modoDependencias ("AspirarFregar");
                break;
        }
    }
    
    public static void modoCompleto (String modo){
        int numeroDependencias = casa.dependencias.length;
        int i = 0;
        while (i<numeroDependencias && bateriaSuficiente (modo, casa.dependencias[i])){
                if (modo == "aspirar"){
                    aspirar (casa.dependencias[i]);
                }else if (modo == "AspirarFregar"){
                    aspirar (casa.dependencias[i]);
                    fregar (casa.dependencias[i]);
                }
                i++;
        }
        if (i+1 == numeroDependencias){
            JOptionPane.showMessageDialog(null, "Limpieza finalizada");            
        }
        
    }
    
    public static void modoDependencias (String modo){
        String mensaje = "¿Qué dependencia desea limpiar?";
                int numeroDependencias = casa.dependencias.length;
                
                for (int i = 1; i<=numeroDependencias; i++){
                    mensaje = mensaje + "\n "+i+". "+casa.dependencias[i-1].nombre;
                }
        
        int dependencia = Integer.parseInt(JOptionPane.showInputDialog(null, mensaje));
        dependencia --;
        if (bateriaSuficiente (modo, casa.dependencias[dependencia])){
              if (modo == "aspirar"){
                    aspirar (casa.dependencias[dependencia]);
                }else if (modo == "AspirarFregar"){
                    aspirar (casa.dependencias[dependencia]);
                    fregar (casa.dependencias[dependencia]);
                }
        }
        }
    
    public static boolean bateriaSuficiente (String modo, Dependencia dependencia){
        boolean suficiente = true;
        double bateriaNecesaria = 0.0;
        
        if (modo == "aspirar"){
            bateriaNecesaria = dependencia.metros *consumoAspirar;
            
        }else if (modo == "AspirarFregar"){
            bateriaNecesaria = dependencia.metros * (consumoAspirar + consumoFregar);
            
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
    
    public static void aspirar (Dependencia dependencia){
        JOptionPane.showMessageDialog(null, "Iniciando aspiración de "+dependencia.nombre);
        aspirador.ubicacion = dependencia;
        aspirador.bateria = aspirador.bateria - (dependencia.metros*consumoAspirar);
        JOptionPane.showMessageDialog(null, "Finalizada aspiracion de "+dependencia.nombre);
    }
    
    public static void fregar (Dependencia dependencia){
        JOptionPane.showMessageDialog(null, "Iniciando fregado de "+dependencia.nombre);
        aspirador.ubicacion = dependencia;
        aspirador.bateria = aspirador.bateria - (dependencia.metros*consumoFregar);
        JOptionPane.showMessageDialog(null, "Finalizado el fregado de "+dependencia.nombre);
    }
    
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
    
    public static void baseCarga (){
        JOptionPane.showMessageDialog(null, "Volviendo a base de carga");
        JOptionPane.showMessageDialog(null, "Cargando batería");
            aspirador.bateria = 100;
        JOptionPane.showMessageDialog(null, "Batería cargada.");
    }
    
    
    }


