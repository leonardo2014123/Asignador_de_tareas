    package interfaz;

    import com.leo.asignador_de_tareas.Asignador_de_tareas.User;
    import com.leo.asignador_de_tareas.Asignador_de_tareas.UserManager;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.List;
    import javax.swing.JFrame;
    import javax.swing.JOptionPane;
    import javax.swing.text.AbstractDocument;
    import javax.swing.text.AttributeSet;
    import javax.swing.text.BadLocationException;
    import javax.swing.text.DocumentFilter;


    /**
     * Interfaz gráfica para el registro de usuarios. 
     * Permite registrar nuevos usuarios verificando que los datos sean válidos y no estén duplicados.
     */
    public class registro extends javax.swing.JFrame {

        private UserManager userManager = new UserManager();
        private List<User> usuarios = userManager.leerUsuariosDesdeArchivo("usuarios.txt");

        /**
         * Constructor de la clase registro.
         * Configura la ventana de registro y la hace visible.
         */
        public registro() {
            initComponents();
            setSize(800, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo esta ventana
            // Aplicar filtro de límite de caracteres
            ((AbstractDocument) jTextFieldUser.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
            ((AbstractDocument) jTextFieldCorreo.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        }
        
        
        /**
        * Clase auxiliar que implementa un filtro para limitar el número de caracteres
        * permitidos en un campo de texto.
        */
        public class LimitDocumentFilter extends DocumentFilter {
            private final int maxLength;
            
        /**
        * Constructor de la clase LimitDocumentFilter.
        * 
        * @param maxLength la longitud máxima permitida para el texto.
        */
            public LimitDocumentFilter(int maxLength) {
                this.maxLength = maxLength;
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    showWarning();
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    showWarning();
                }
            }
        /**
         * Muestra una advertencia al usuario indicando que no se pueden insertar
         * más caracteres de los permitidos.
         */
            private void showWarning() {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    javax.swing.JOptionPane.showMessageDialog(null, "No se permiten más de " + maxLength + " caracteres.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
                });
            }
        }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelFondo = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        btcancelar = new javax.swing.JButton();
        btguardar = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        jComboBoxTipoUser = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabelFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo virdi.jpeg"))); // NOI18N

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("REGISTRARSE");

        jLabelUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelUsuario.setText("Usuario");

        jLabelCorreo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCorreo.setText("Correo Electronico");

        btcancelar.setText("CANCELAR");
        btcancelar.setContentAreaFilled(false);
        btcancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcancelarActionPerformed(evt);
            }
        });

        btguardar.setText("GUARDAR");
        btguardar.setContentAreaFilled(false);
        btguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btguardarActionPerformed(evt);
            }
        });

        jLabelLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jLabelLogo.setText("ProgSis");

        jComboBoxTipoUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBoxTipoUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Administrador" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(320, 320, 320))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(289, 289, 289))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(335, 335, 335))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldUser)
                        .addGap(200, 200, 200))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(347, 347, 347))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCorreo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(btguardar)
                                .addGap(54, 54, 54)
                                .addComponent(btcancelar)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(200, 200, 200)))
                .addComponent(jLabelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabelCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btguardar)
                    .addComponent(btcancelar))
                .addGap(33, 94, Short.MAX_VALUE))
            .addComponent(jLabelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 765, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Valida los datos ingresados en los campos y registra al usuario si no existe previamente.
     * 
     * @param evt Evento generado al pulsar el botón "Guardar".
     */
    private void btguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguardarActionPerformed
        String usuario = jTextFieldUser.getText().trim();
        String correo = jTextFieldCorreo.getText().trim();
        String tipoUsuario = jComboBoxTipoUser.getSelectedItem().toString().trim();

        // Verificar que todos los campos estén completos
        if (usuario.isEmpty() || correo.isEmpty() || tipoUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos. Por favor, completa el usuario, correo y tipo de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el tipo de usuario esté seleccionado
        if (tipoUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar que el nombre de usuario no esté duplicado
        if (userManager.existeUsuario(usuarios, usuario)) {
            JOptionPane.showMessageDialog(this, "El usuario ya existe. Elige otro nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Crear y agregar el nuevo usuario
            User nuevoUsuario = new User(usuario, correo, tipoUsuario);
            usuarios.add(nuevoUsuario);
            userManager.escribirUsuariosEnArchivo("usuarios.txt", usuarios);
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            this.dispose();
        }

    }//GEN-LAST:event_btguardarActionPerformed

    private void btcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancelarActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_btcancelarActionPerformed
    
        /**
         * Limpia los campos de entrada para permitir un nuevo registro.
         */
            private void limpiarCampos() {
                jTextFieldUser.setText("");
                jTextFieldCorreo.setText("");
                jComboBoxTipoUser.setSelectedIndex(0);
            }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcancelar;
    private javax.swing.JButton btguardar;
    private javax.swing.JComboBox<String> jComboBoxTipoUser;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables

   
    
}
