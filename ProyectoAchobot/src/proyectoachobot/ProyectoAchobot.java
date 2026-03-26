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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProyectoAchobot {

    // Variable de estado de la luz
    static boolean luzEncendida = false;

    public static void main(String[] args) {
        // Crear la ventana
        JFrame ventana = new JFrame("Achobot APP");
        ventana.setSize(600, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setResizable(false);     // esto evita que se estire
        ventana.setLocationRelativeTo(null);
        
        
        
        ////// IMAGENES //////
        ///
        // Rutas base de imágenes y scripts
        String rutaBase = "C:\\Users\\alumno\\Documents\\NetBeansProjects\\ProyectoAchobot\\src\\proyectoachobot\\";

        // Cargar imágenes
        ImageIcon icon = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/iconoAchobot.png"));
        ventana.setIconImage(icon.getImage());
        ImageIcon imagenNormal = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/achobot.png"));
        ImageIcon hoverOjos = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/hoverOjos.png"));
        ImageIcon hoverNariz = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/hoverNariz.png"));
        ImageIcon hoverBoca = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/hoverBoca.png"));
        ImageIcon hoverPelo = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/hoverPelo.png"));
        ImageIcon hoverLogo = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/hoverLogo.png"));
        ImageIcon hoverTierrablanca = new ImageIcon(ProyectoAchobot.class.getResource("/proyectoachobot/hoverTierrablanca.png"));
        
        // Redimensionar imágenes
        Image imgNormal = imagenNormal.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        Image imgHoverOjos = hoverOjos.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        Image imgHoverNariz = hoverNariz.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        Image imgHoverBoca = hoverBoca.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        Image imgHoverPelo = hoverPelo.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        Image imgHoverLogo = hoverLogo.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        Image imgHoverTierrablanca = hoverTierrablanca.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        
        
        // JLabel que mostrará la imagen
        JLabel fondo = new JLabel(new ImageIcon(imgNormal));
        fondo.setBounds(0, 0, 600, 600);
        ventana.add(fondo);
        

        ////// Luces - OJO1 //////
        ///
        // Creación de JButton botonOjo1
        JButton botonOjo1 = new JButton();
        botonOjo1.setBounds(170, 168, 60, 60);
        
        botonOjo1.setOpaque(false);
        botonOjo1.setContentAreaFilled(false);
        botonOjo1.setBorderPainted(false);
        
        fondo.add(botonOjo1);
        
        // Scripts de las Luces
        String scriptEncenderLuces = "scripts/encenderLuces.ps1";
        String scriptApagarLuces  = "scripts/apagarLuces.ps1";
        
        // Acción para el botón1 de la luz
        botonOjo1.addActionListener(e -> new Thread(() -> {
            if (!luzEncendida) {
                luzEncendida = true;
                ejecutarPowerShell(scriptEncenderLuces);
            } else {
                luzEncendida = false;
                ejecutarPowerShell(scriptApagarLuces);
            }
        }).start());
        
        // Hover para el Ojo1
        botonOjo1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgHoverOjos)); // cambiar imagen
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgNormal)); // volver a normal
            }
        });
        
        
        ////// Luces - OJO2 //////
        ///
        // Creación de JButton botonOjo2
        JButton botonOjo2 = new JButton();
        botonOjo2.setBounds(365, 168, 60, 60);
        
        botonOjo2.setOpaque(false);
        botonOjo2.setContentAreaFilled(false);
        botonOjo2.setBorderPainted(false);
        
        fondo.add(botonOjo2);
        
        // (No añadimos las variables de los scripts porque estan en ojo1)
        
        // Acción para el botón2 de la luz
        botonOjo2.addActionListener(e -> new Thread(() -> {
            if (!luzEncendida) {
                luzEncendida = true;
                ejecutarPowerShell(scriptEncenderLuces);
            } else {
                luzEncendida = false;
                ejecutarPowerShell(scriptApagarLuces);
            }
        }).start());
        
        // Hover para el Ojo2
        botonOjo2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgHoverOjos)); // cambiar imagen
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgNormal)); // volver a normal
            }
        });

        ////// Micro IA - Nariz //////
        ///
        // Creación de JButton botonMicro
        JButton botonMicro = new JButton();
        botonMicro.setBounds(260, 205, 71, 50);
        
        botonMicro.setOpaque(false);
        botonMicro.setContentAreaFilled(false);
        botonMicro.setBorderPainted(false);
        
        fondo.add(botonMicro);
        
        // Script del micro
        String scriptMicroIA = "scripts/vozIA.ps1";
        
        // Accion para el botón micro IA
        botonMicro.addActionListener(e -> new Thread(() -> {
            ejecutarPowerShell(scriptMicroIA);
        }).start());
        
        // Hover para la Nariz
        botonMicro.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgHoverNariz)); // cambiar imagen
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgNormal)); // volver a normal
            }
        });
        
        ////// ChatIA - Boca //////
        ///
        // Creación de JButton botonBoca
        JButton botonBoca = new JButton();
        botonBoca.setBounds(145, 275, 300, 130);
        
        botonBoca.setOpaque(false);
        botonBoca.setContentAreaFilled(false);
        botonBoca.setBorderPainted(false);
        
        fondo.add(botonBoca);
        
        // Accion para el botón Boca
        botonBoca.addActionListener(e -> abrirVentanaTexto());
        
        
        // Hover para la Boca
        botonBoca.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgHoverBoca)); // cambiar imagen
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgNormal)); // volver a normal
            }
        });
        
        ////// Información - Pelo //////
        ///
        // Creación de JButton botonPelo
        JButton botonPelo = new JButton();
        botonPelo.setBounds(145, 60, 305, 80);
        
        botonPelo.setOpaque(false);
        botonPelo.setContentAreaFilled(false);
        botonPelo.setBorderPainted(false);
        
        fondo.add(botonPelo);
        
        // Accion para el botón Pelo
        botonPelo.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                ventana,
                "🌐 ACHOBOT APP – Windows Edition (Beta)\n\n" +
                "Director de diseño\n" +
                "• Carlos Murillo Almodóvar\n\n" +
                "Programador \n" +
                "• Diego Morillas Criado\n\n" +
                "Soporte técnico\n" +
                "• Manuel Darío López Rodríguez\n\n" +
                "Contacto\n" +
                "• diegomorillascriado08@gmail.com\n" +
                "• cmurilloa03@educarex.es\n\n" +
                "Instrucciones\n" +
                "💇 Pelo → información y ayuda\n" +
                "👁️ Ojos → encender/apagar luces\n" +
                "👄 Boca → escribir texto y hablar con Achobot\n" +
                "👃 Nariz → hablar con Achobot\n\n" +
                "Pulsa sobre Achobot y descubre todas sus funciones\n\n" +
                "Versión 1.0 – IES Tierrablanca",
                "Acerca de Achobot APP",
            JOptionPane.INFORMATION_MESSAGE
            );
        });
        
        // Hover para el Pelo
        botonPelo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgHoverPelo)); // cambiar imagen
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgNormal)); // volver a normal
            }
        });
       
        ////// Página Achobot - Logo //////
        ///
        // Creación de JButton botonLogo
        JButton botonLogo = new JButton();
        botonLogo.setBounds(275, 438, 230, 90);
        
        botonLogo.setOpaque(false);
        botonLogo.setContentAreaFilled(false);
        botonLogo.setBorderPainted(false);
        
        fondo.add(botonLogo);
        
        // Accion para el botón Logo
        botonLogo.addActionListener(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://achobot.my.canva.site/"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        // Hover para el Logo
        botonLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgHoverLogo)); // cambiar imagen
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgNormal)); // volver a normal
            }
        });
        
        
        ////// Página Achobot - Logo //////
        ///
        // Creación de JButton botonTierrablanca
        JButton botonTierrablanca = new JButton();
        botonTierrablanca.setBounds(90, 438, 180, 90);
        
        botonTierrablanca.setOpaque(false);
        botonTierrablanca.setContentAreaFilled(false);
        botonTierrablanca.setBorderPainted(false);
        
        fondo.add(botonTierrablanca);
        
        // Accion para el botón Tierrablanca
        botonTierrablanca.addActionListener(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://iestierrablanca.educarex.es/"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        // Hover para Tierrablanca
        botonTierrablanca.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgHoverTierrablanca)); // cambiar imagen
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                fondo.setIcon(new ImageIcon(imgNormal)); // volver a normal
            }
        });
        
        
        
        
        
        
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
    
public static void abrirVentanaTexto() {
    JFrame ventanaTexto = new JFrame("Hablar con Achobot");
    ventanaTexto.setSize(500, 300); // ventana más grande
    ventanaTexto.setLayout(null);
    ventanaTexto.setLocationRelativeTo(null);

    JLabel label = new JLabel("¡Pregúntale a Achobot! (Se paciente):");
    label.setBounds(20, 20, 300, 25);
    ventanaTexto.add(label);

    // JTextArea para poder escribir varias líneas
    JTextArea areaTexto = new JTextArea();
    areaTexto.setLineWrap(true);
    areaTexto.setWrapStyleWord(true);

    // Scroll pane para el JTextArea
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(areaTexto);
    scrollPane.setBounds(20, 50, 440, 120); // más ancho y alto
    ventanaTexto.add(scrollPane);

    JButton botonEnviar = new JButton("Hablar");
    botonEnviar.setBounds(20, 190, 100, 30);
    ventanaTexto.add(botonEnviar);

    String rutaScript = "scripts/textoIA.ps1";

    botonEnviar.addActionListener(e -> {
        String texto = areaTexto.getText();

        if (!texto.isEmpty()) {
            new Thread(() -> ejecutarPowerShellConTexto(rutaScript, texto)).start();
        }
    });

    ventanaTexto.setVisible(true);
}
}
