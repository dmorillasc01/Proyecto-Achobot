/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoachobot;

/**
 *
 * @author alumno
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;

public class ProyectoAchobot {
    public static void main(String[] args) {
        // Crear la ventana
        JFrame ventana = new JFrame("Achobot");
        ventana.setSize(600, 600); // tamaño específico
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cargar la imagen
        ImageIcon imagenOriginal = new ImageIcon("C:\\Users\\alumno\\Documents\\NetBeansProjects\\ProyectoAchobot\\achobot.png");

        // Redimensionar la imagen al tamaño de la ventana
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(
            ventana.getWidth(), ventana.getHeight(), Image.SCALE_SMOOTH
        );

        // Crear JLabel con la imagen redimensionada
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenRedimensionada));
        ventana.add(etiquetaImagen);

        // Mostrar ventana
        ventana.setVisible(true);
    }
}
