
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Crear menú general de la aplicación
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Menú principal\n\n 1.Configurar el sistema\n "
                + "2.Carga\n 3.Aspiración\n 4.Aspiración y fregado\n 5.Estado general\n 6.Base de carga\n 7.Salir"));

        switch (opcion) {
            case 1:
                JOptionPane.showInputDialog(null, "Cofigurar el sistema");
                break;
            case 2:
                
                JOptionPane.showMessageDialog(null, "Carga");
                break;
            case 3:
                JOptionPane.showInputDialog(null, "Aspiración");
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
    }
    
}
