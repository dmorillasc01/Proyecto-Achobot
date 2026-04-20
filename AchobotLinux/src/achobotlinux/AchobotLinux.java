package achobotlinux;

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

public class AchobotLinux {

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
        // Cargar imágenes
        ImageIcon icon = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/iconoAchobot.png"));
        ventana.setIconImage(icon.getImage());
        ImageIcon imagenNormal = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/achobot.png"));
        ImageIcon hoverOjos = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/hoverOjos.png"));
        ImageIcon hoverNariz = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/hoverNariz.png"));
        ImageIcon hoverBoca = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/hoverBoca.png"));
        ImageIcon hoverPelo = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/hoverPelo.png"));
        ImageIcon hoverLogo = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/hoverLogo.png"));
        ImageIcon hoverTierrablanca = new ImageIcon(AchobotLinux.class.getResource("/achobotlinux/img/hoverTierrablanca.png"));
        
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
        String scriptEncenderLuces = "./scripts/encenderLuces.sh";
        String scriptApagarLuces  = "./scripts/apagarLuces.sh";
        
        // Acción para el botón1 de la luz
        botonOjo1.addActionListener(e -> new Thread(() -> {
            if (!luzEncendida) {
                luzEncendida = true;
                ejecutarBash(scriptEncenderLuces);
            } else {
                luzEncendida = false;
                ejecutarBash(scriptApagarLuces);
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
                ejecutarBash(scriptEncenderLuces);
            } else {
                luzEncendida = false;
                ejecutarBash(scriptApagarLuces);
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
        String scriptMicroIA = "./scripts/vozIA.sh";
        
        // Accion para el botón micro IA
        botonMicro.addActionListener(e -> new Thread(() -> {
            ejecutarBash(scriptMicroIA);
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
                "🌐 ACHOBOT APP – Linux Edition (Beta)\n\n" +
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
        

        ventana.setVisible(true);
    }

    // Método para ejecutar un script de Bash
    public static void ejecutarBash(String rutaScript) {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", rutaScript);
            pb.inheritIO(); // muestra salida en consola

            Process proceso = pb.start();
            proceso.waitFor();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void ejecutarBashConTexto(String rutaScript, String texto) {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", rutaScript, texto);
            pb.inheritIO(); // opcional: ver salida en consola

            Process proceso = pb.start();
            proceso.waitFor();
        }catch (IOException | InterruptedException ex) {
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

    String rutaScript = "./scripts/textoIA.sh";

    botonEnviar.addActionListener(e -> {
        String texto = areaTexto.getText();

        if (!texto.isEmpty()) {
            new Thread(() -> ejecutarBashConTexto(rutaScript, texto)).start();
        }
    });

    ventanaTexto.setVisible(true);
}
}
