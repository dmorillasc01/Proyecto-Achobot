/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoachobot;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.io.IOException;

public class ProyectoAchobot {

    // Variable de estado de la luz
    static boolean luzEncendida = false;

    public static void main(String[] args) {
        // Crear la ventana
        JFrame ventana = new JFrame("Achobot");
        ventana.setSize(1000, 800);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);

        // Rutas base de imágenes y scripts
        String rutaBase = "C:\\Users\\alumno\\Documents\\NetBeansProjects\\ProyectoAchobot\\src\\proyectoachobot\\";
        String rutaImagenNormal = rutaBase + "achobot.png";
        String rutaImagenEncendida = rutaBase + "achobotLuz.png";

        // Cargar imágenes
        ImageIcon imagenNormal = new ImageIcon(rutaImagenNormal);
        ImageIcon imagenEncendida = new ImageIcon(rutaImagenEncendida);

        // Redimensionar imágenes
        Image imgNormal = imagenNormal.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        Image imgEncendida = imagenEncendida.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);

        // JLabel que mostrará la imagen
        JLabel fondo = new JLabel(new ImageIcon(imgNormal));
        fondo.setBounds(0, 0, 600, 600);
        ventana.add(fondo);

        // Botones invisibles
        JButton botonOjo1 = new JButton();
        botonOjo1.setBounds(145, 160, 40, 35);
        JButton botonOjo2 = new JButton();
        botonOjo2.setBounds(405, 160, 40, 35);
        JButton botonBoca = new JButton();
        botonBoca.setBounds(115, 275, 360, 150);
        JButton botonMicro = new JButton();
        botonMicro.setBounds(255, 190, 80, 50);

        botonOjo1.setOpaque(false);
        botonOjo1.setContentAreaFilled(false);
        botonOjo1.setBorderPainted(false);
        botonOjo2.setOpaque(false);
        botonOjo2.setContentAreaFilled(false);
        botonOjo2.setBorderPainted(false);
        botonMicro.setOpaque(false);
        botonMicro.setContentAreaFilled(false);
        botonMicro.setBorderPainted(false);

        // Acción para el botón1 de la luz
        botonOjo1.addActionListener(e -> new Thread(() -> {
            String script;
            if (!luzEncendida) {
                script = rutaBase + "encenderLuces.ps1";
                luzEncendida = true;
                fondo.setIcon(new ImageIcon(imgEncendida));
            } else {
                script = rutaBase + "apagarLuces.ps1";
                luzEncendida = false;
                fondo.setIcon(new ImageIcon(imgNormal));
            }
            ejecutarPowerShell(script);
        }).start());

        // Acción para el botón2 de la luz
        botonOjo2.addActionListener(e -> new Thread(() -> {
            String script;
            if (!luzEncendida) {
                script = rutaBase + "encenderLuces.ps1";
                luzEncendida = true;
                fondo.setIcon(new ImageIcon(imgEncendida));
            } else {
                script = rutaBase + "apagarLuces.ps1";
                luzEncendida = false;
                fondo.setIcon(new ImageIcon(imgNormal));
            }
            ejecutarPowerShell(script);
        }).start());

        // Accion para el botón micro IA
        botonMicro.addActionListener(e -> new Thread(() -> {
            String script;
            script = rutaBase + "vozIA.ps1";
            ejecutarPowerShell(script);
        }).start());
        
        
        fondo.add(botonOjo1);
        fondo.add(botonOjo2);
        fondo.add(botonBoca);
        fondo.add(botonMicro);

        ventana.setVisible(true);
        
        // Listener para apagar luz al cerrar la ventana
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (luzEncendida) {
                    ejecutarPowerShell(rutaBase + "apagarLuces.ps1");
                    luzEncendida = false;
                }
                // La ventana se cerrará automáticamente después
            }
        });

        ventana.setVisible(true);
    }

    // Método para ejecutar un script de PowerShell
    public static void ejecutarPowerShell(String rutaScript) {
        try {
            String comando = "powershell.exe -ExecutionPolicy Bypass -File \"" + rutaScript + "\"";
            Process proceso = Runtime.getRuntime().exec(comando);
            proceso.waitFor(); // espera a que termine
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void ejecutarPowerShellConTexto(String rutaScript, String texto) {
    try {
        // Escapamos comillas dobles dentro del texto
        texto = texto.replace("\"", "`\"");

        String comando = "powershell.exe -ExecutionPolicy Bypass -File \"" 
                         + rutaScript + "\" -texto \"" + texto + "\"";
        Process proceso = Runtime.getRuntime().exec(comando);
        proceso.waitFor();
    } catch (IOException | InterruptedException ex) {
        ex.printStackTrace();
    }
}
    
}