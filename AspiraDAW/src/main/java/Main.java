
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    /**
     * @param args the command line arguments
     */
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
            case 3: modo();
                break;
            case 4:
                JOptionPane.showInputDialog(null, "Aspiración y fregado");
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Estado general");
                break;
            case 6:
                JOptionPane.showInputDialog(null, "Base de carga");
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
        cocina.nombre = "cocina";
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
        salon.nombre = "salon";
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
    
    public static void modo (){
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué tipo de aspiración desea?\n 1. Modo Completo \n 2.Modo dependencias"));
        
        switch (opcion){
            case 1: modoCompleto ();
                break;
            case 2:
                break;
            default:
                break;
        }
    }
    
    public static void modoCompleto (){
        int numeroDependencias = casa.dependencias.length;
        for (int i = 0; i<numeroDependencias; i++){
            if (bateriaSuficiente ("aspirar", casa.dependencias[i])){
                aspirar (casa.dependencias[i]);
            }
        }
        JOptionPane.showMessageDialog(null, "Aspiración finalizada");
    }
    
    public static boolean bateriaSuficiente (String modo, Dependencia dependencia){
        boolean suficiente = true;
        if (modo == "aspirar"){
            double bateriaNecesaria = dependencia.metros *1.5;
            bateriaNecesaria = bateriaNecesaria +3;
            if (aspirador.bateria >= bateriaNecesaria ){
                suficiente = true;
            }else{
                suficiente = false;
            }
        }
        return suficiente;
    }
    
    public static void aspirar (Dependencia dependencia){
        JOptionPane.showMessageDialog(null, "Iniciando aspiración de "+dependencia.nombre);
        aspirador.bateria = aspirador.bateria - (dependencia.metros*1.5);
        JOptionPane.showMessageDialog(null, "Finalizada aspiracion de "+dependencia.nombre);
    }
}
