
    package interfaz;

    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import javax.swing.JFrame;
    import javax.swing.JOptionPane;
    import javax.swing.text.AbstractDocument;
    import javax.swing.text.AttributeSet;
    import javax.swing.text.BadLocationException;
    import javax.swing.text.DocumentFilter;

    /**
     * Clase que representa la ventana de inicio de sesión de la aplicación.
     * Permite a los usuarios ingresar sus credenciales y acceder al sistema
     * según su tipo de usuario.
     */
    public class login extends javax.swing.JFrame {

        /**
         * Tipo de usuario del usuario que inicia sesión.
         */
        private String tipoUsuario;

        /**
         * Nombre del usuario que inicia sesión.
         */
        private String nombreUsuario;

        /**
         * Constructor de la clase login.
         * Configura la ventana y la hace visible.
         */
        public login() {
            initComponents();
            setSize(800, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            ((AbstractDocument) jTextFieldUser.getDocument()).setDocumentFilter(new LimitadorCaracteres(50));
            ((AbstractDocument) jTextFieldCorreo.getDocument()).setDocumentFilter(new LimitadorCaracteres(50));
        }
        /**
        * Clase auxiliar para limitar el número de caracteres en un JTextField.
        */
       public class LimitadorCaracteres extends DocumentFilter {
        private int maxChars;
        /**
         * Constructor de la clase LimitadorCaracteres.
         * 
         * @param maxChars el número máximo de caracteres permitido.
         */
        public LimitadorCaracteres(int maxChars) {
            this.maxChars = maxChars;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= maxChars) {
                super.insertString(fb, offset, string, attr);
            } else {
                mostrarAdvertencia();
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if ((fb.getDocument().getLength() - length + text.length()) <= maxChars) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                mostrarAdvertencia();
            }
        }
        /**
         * Muestra una advertencia al usuario indicando que el límite de caracteres
         * ha sido excedido.
         */
        private void mostrarAdvertencia() {
            JOptionPane.showMessageDialog(null, "Solo se permiten " + maxChars + " caracteres.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        btiniciar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btregistro = new javax.swing.JButton();
        btsalir = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("INICIAR SESION");

        jLabelUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelUser.setText("Usuario");

        jLabelCorreo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCorreo.setText("Correo Electronico");

        jTextFieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserActionPerformed(evt);
            }
        });

        jTextFieldCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreoActionPerformed(evt);
            }
        });

        btiniciar.setText("INICIAR");
        btiniciar.setContentAreaFilled(false);
        btiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btiniciarActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo virdi.jpeg"))); // NOI18N

        btregistro.setText("REGISTRARSE");
        btregistro.setContentAreaFilled(false);
        btregistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btregistroActionPerformed(evt);
            }
        });

        btsalir.setText("SALIR");
        btsalir.setContentAreaFilled(false);
        btsalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsalirActionPerformed(evt);
            }
        });

        jLabelLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jLabelLogo.setText("ProgSis");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(202, 202, 202))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(131, 131, 131))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(243, 243, 243))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldUser)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(219, 219, 219))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldCorreo)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btiniciar)
                        .addGap(50, 50, 50)
                        .addComponent(btregistro)
                        .addGap(53, 53, 53)
                        .addComponent(btsalir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(76, 76, 76)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabelCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btiniciar)
                    .addComponent(btregistro)
                    .addComponent(btsalir))
                .addGap(27, 27, 27))
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 562, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 736, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserActionPerformed
        
    }//GEN-LAST:event_jTextFieldUserActionPerformed

    private void btregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btregistroActionPerformed
     registro registrar = new registro(); // Crea una instancia de la ventana de registro
   
    }//GEN-LAST:event_btregistroActionPerformed

    private void btsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsalirActionPerformed
       System.exit(0);       
    }//GEN-LAST:event_btsalirActionPerformed

    private void btiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btiniciarActionPerformed
      // Llama al método para verificar las credenciales
    if (verificarCredenciales()) { 
        VENTANA_PRINCIPAL principal = new VENTANA_PRINCIPAL(nombreUsuario, tipoUsuario);
        this.dispose(); 
    }//GEN-LAST:event_btiniciarActionPerformed
}
    private void jTextFieldCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreoActionPerformed
       
    }//GEN-LAST:event_jTextFieldCorreoActionPerformed


    /**
     * Método auxiliar para verificar las credenciales ingresadas.
     * Busca el usuario en usuarios.txt y valida que coincidan el usuario y el correo.
     * 
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws IOException Si ocurre un error al leer usuario.txt.
     */
    private boolean verificarCredenciales() {
        String usuario = jTextFieldUser.getText();  
        String correo = jTextFieldCorreo.getText(); 

        // Validar que ambos campos no estén vacíos
        if (usuario.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Leer el archivo de usuarios y buscar las credenciales
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            boolean credencialesValidas = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Usuario: " + usuario) && line.contains("Correo: " + correo)) {
                    credencialesValidas = true;

                    // Extraer el tipo de usuario de la línea
                    int tipoIndex = line.indexOf("Tipo: ") + 6; // Índice de inicio del tipo de usuario
                    tipoUsuario = line.substring(tipoIndex).trim();
                    nombreUsuario = usuario;
                    break;
                }
            }

            if (credencialesValidas) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al acceder al archivo de usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false; 
    }

   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btiniciar;
    private javax.swing.JButton btregistro;
    private javax.swing.JButton btsalir;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
