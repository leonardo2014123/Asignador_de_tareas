    package interfaz;

    import com.leo.asignador_de_tareas.Asignador_de_tareas.User;
    import com.leo.asignador_de_tareas.Asignador_de_tareas.UserManager;
    import com.leo.asignador_de_tareas.Asignador_de_tareas.TareaManager;
    import java.awt.Window;
    import javax.swing.*;
    import java.util.List;
    import javax.swing.text.*;

    /**
     * Clase para editar un usuario existente.
     * Proporciona una interfaz gráfica para modificar el nombre de usuario y el correo.
     */
    public class editar_user extends JFrame {

        private User usuarioSeleccionado;
        private UserManager userManager;
        private TareaManager tareaManager;
        private String usuarioActual;

        /**
         * Constructor de la clase editar_user.
         *
         * @param usuarioSeleccionado El usuario que se desea editar.
         * @param usuarioActual El nombre de usuario actual del usuario que está editando.
         */
        public editar_user(User usuarioSeleccionado, String usuarioActual) {
            this.usuarioSeleccionado = usuarioSeleccionado;
            this.usuarioActual = usuarioActual;
            this.userManager = new UserManager();
            this.tareaManager = new TareaManager();

            initComponents(); // Método generado automáticamente por NetBeans
            setSize(750, 680);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            cargarDatosUsuario();
            ((AbstractDocument) jTextFieldUserNuevo.getDocument()).setDocumentFilter(new LimitarCaracteres(50));
            ((AbstractDocument) jTextFieldCorreoNuevo.getDocument()).setDocumentFilter(new LimitarCaracteres(50));
        }

        /**
         * Clase interna para limitar la cantidad de caracteres permitidos en un campo de texto.
         */
        public class LimitarCaracteres extends DocumentFilter {
            private int limite;

            /**
             * Constructor para inicializar el límite de caracteres.
             *
             * @param limite Número máximo de caracteres permitidos.
             */
            public LimitarCaracteres(int limite) {
                this.limite = limite;
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= limite) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    JOptionPane.showMessageDialog(null, "Máximo " + limite + " caracteres permitidos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() - length + text.length()) <= limite) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    JOptionPane.showMessageDialog(null, "Máximo " + limite + " caracteres permitidos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        /**
         * Carga los datos del usuario seleccionado en los campos de texto.
         *
         * Si el usuario no es encontrado, se muestra un mensaje de error y se cierra la ventana.
         */
        private void cargarDatosUsuario() {
            if (usuarioSeleccionado != null) {
                jTextFieldUser.setText(usuarioSeleccionado.getUsuario());
                jTextFieldCorreo.setText(usuarioSeleccionado.getCorreo());
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
                dispose();
            }
        }

        /**
         * Guarda los cambios realizados en el usuario.
         *
         * @throws IOException Si el nombre de usuario ya existe o los campos están vacíos.
         */
        public void guardarCambiosUsuario() {
            String usuarioNuevo = jTextFieldUserNuevo.getText().trim();
            String correoNuevo = jTextFieldCorreoNuevo.getText().trim();

            if (usuarioNuevo.isEmpty() || correoNuevo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.");
                return;
            }

            if (!usuarioNuevo.equals(usuarioSeleccionado.getUsuario())) {
                List<User> usuarios = userManager.obtenerTodosLosUsuarios();
                for (User usuario : usuarios) {
                    if (usuario.getUsuario().equals(usuarioNuevo)) {
                        JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe. Por favor, elija otro.");
                        return;
                    }
                }
            }

            String usuarioOriginal = usuarioSeleccionado.getUsuario();
            usuarioSeleccionado.setUsuario(usuarioNuevo);
            usuarioSeleccionado.setCorreo(correoNuevo);

            actualizarArchivos(usuarioOriginal);

            if (usuarioOriginal.equals(usuarioActual)) {
                JOptionPane.showMessageDialog(this, "Has actualizado tu propio usuario/correo. Por favor, inicia sesión nuevamente.");
                cerrarVentanasAbiertas();
                login log = new login();
                log.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
                dispose();
            }
        }

        /**
         * Actualiza los archivos de usuarios y tareas con los cambios realizados.
         *
         * @param usuarioAnterior El nombre de usuario anterior.
         * @throws IOException Si el usuario no es encontrado en la lista.
         */
        private void actualizarArchivos(String usuarioAnterior) {
            List<User> usuarios = userManager.obtenerTodosLosUsuarios();
            boolean usuarioActualizado = false;

            for (User usuario : usuarios) {
                if (usuario.getUsuario().equals(usuarioAnterior)) {
                    usuario.setUsuario(usuarioSeleccionado.getUsuario());
                    usuario.setCorreo(usuarioSeleccionado.getCorreo());
                    usuarioActualizado = true;
                    break;
                }
            }

            if (!usuarioActualizado) {
                JOptionPane.showMessageDialog(this, "No se encontró al usuario para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            userManager.guardarUsuariosEnArchivo("usuarios.txt", usuarios, "ADMINISTRADOR");

            List<com.leo.asignador_de_tareas.Asignador_de_tareas.Tarea> tareas = tareaManager.obtenerTodasLasTareas();
            for (com.leo.asignador_de_tareas.Asignador_de_tareas.Tarea tarea : tareas) {
                if (tarea.getUsuario().equals(usuarioAnterior)) {
                    tarea.setUsuario(usuarioSeleccionado.getUsuario());
                }
            }

            tareaManager.guardarTareasEnArchivo("tareas.txt", tareas, "ADMINISTRADOR", usuarioSeleccionado.getUsuario());
        }

        /**
         * Cierra todas las ventanas abiertas, excepto la actual.
         */
        private void cerrarVentanasAbiertas() {
            for (Window window : Window.getWindows()) {
                if (window != this) {
                    window.dispose();
                }
            }
        }




  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelCorreoNuevo = new javax.swing.JLabel();
        jLabelUserNuevo = new javax.swing.JLabel();
        btguardar = new javax.swing.JButton();
        btcancelar = new javax.swing.JButton();
        jLabelUser = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jTextFieldUserNuevo = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        jTextFieldCorreoNuevo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabelCorreoNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCorreoNuevo.setText("CORREO ELECTRONICO NUEVO :");

        jLabelUserNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUserNuevo.setText("USUARIO NUEVO  :");

        btguardar.setText("GUARDAR");
        btguardar.setContentAreaFilled(false);
        btguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btguardarActionPerformed(evt);
            }
        });

        btcancelar.setText("CANCELAR");
        btcancelar.setContentAreaFilled(false);
        btcancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcancelarActionPerformed(evt);
            }
        });

        jLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUser.setText("USUARIO ACTUAL  :");

        jLabelCorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCorreo.setText("CORREO ELECTRONICO ACTUAL:");

        jTextFieldUserNuevo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldCorreoNuevo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel2.setBackground(new java.awt.Color(0, 150, 0));

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar usuario");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addGap(129, 129, 129))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTextFieldUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabelCorreoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCorreoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelUserNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUserNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUserNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUserNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCorreoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCorreoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguardarActionPerformed
        guardarCambiosUsuario();
        
    }//GEN-LAST:event_btguardarActionPerformed

    private void btcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancelarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btcancelarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcancelar;
    private javax.swing.JButton btguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelCorreoNuevo;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUserNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldCorreoNuevo;
    private javax.swing.JTextField jTextFieldUser;
    private javax.swing.JTextField jTextFieldUserNuevo;
    // End of variables declaration//GEN-END:variables
}
