
    package interfaz;

    import java.awt.BorderLayout;
    import javax.swing.JFrame;
    import ventanas.crear_tarea;
    import ventanas.historial;
    import ventanas.lista;
    import ventanas.lista_user;

    /**
     * Clase principal de la aplicación que representa la ventana principal de la interfaz.
     * Permite a los usuarios acceder a diferentes secciones, como lista de tareas, historial,
     * creación de tareas y administración de usuarios. 
     */
    public class VENTANA_PRINCIPAL extends javax.swing.JFrame {

        private String tipoUsuario; // El tipo de usuario ("ADMINISTRADOR" o "NORMAL")
        private String nombreUsuario; // El nombre del usuario que accede a la ventana principal

        /**
         * Constructor de la clase VENTANA_PRINCIPAL.
         * Inicializa la ventana principal y configura su diseño y contenido inicial.
         *
         * @param nombreUsuario El nombre del usuario que accede a la ventana.
         * @param tipoUsuario El tipo de usuario que accede ("ADMINISTRADOR" o "NORMAL").
         */
        public VENTANA_PRINCIPAL(String nombreUsuario, String tipoUsuario) {
            initComponents();
            this.nombreUsuario = nombreUsuario;
            this.tipoUsuario = tipoUsuario;
            setSize(1200, 900);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            contenido.setLayout(new BorderLayout());
            mostrarLista();
        }

        /**
         * Muestra la lista de tareas en el panel principal.
         * Este método inicializa y carga un panel con las tareas del usuario actual.
         */
        private void mostrarLista() {
            lista list = new lista(nombreUsuario, tipoUsuario);

            // Limpiar el JPanel "contenido" y añadir el nuevo JPanel
            contenido.removeAll();
            contenido.add(list, BorderLayout.CENTER);

            // Actualizar la interfaz
            contenido.revalidate();
            contenido.repaint();
        }

        /**
         * Muestra el formulario para crear una nueva tarea en el panel principal.
         * Este método inicializa y carga un panel para ingresar los datos de una nueva tarea.
         */
        public void crearTarea() {
            crear_tarea crear = new crear_tarea(nombreUsuario, tipoUsuario);

            // Limpiar el JPanel "contenido" y añadir el nuevo JPanel
            contenido.removeAll();
            contenido.add(crear, BorderLayout.CENTER);

            // Actualizar la interfaz
            contenido.revalidate();
            contenido.repaint();
        }

        /**
         * Muestra el historial de tareas en el panel principal.
         * Este método inicializa y carga un panel que muestra las tareas
         * dependiendo del tipo de usuario (todas las tareas para administradores,
         * solo las tareas del usuario actual para usuarios normales).
         */
        private void mostrarHistorial() {
            historial history = new historial(tipoUsuario, nombreUsuario);

            // Cargar el contenido del archivo en el JTextArea
            history.mostrarHistorial();

            // Limpiar el JPanel "contenido" y añadir el nuevo JPanel
            contenido.removeAll();
            contenido.add(history, BorderLayout.CENTER);

            // Actualizar la interfaz
            contenido.revalidate();
            contenido.repaint();
        }

        /**
         * Muestra la lista de usuarios en el panel principal.
         * Este método inicializa y carga un panel para visualizar y administrar
         * usuarios, disponible para los administradores.
         */
        private void mostrarUser() {
            lista_user user = new lista_user(nombreUsuario, tipoUsuario);

            // Limpiar el JPanel "contenido" y añadir el nuevo JPanel
            contenido.removeAll();
            contenido.add(user, BorderLayout.CENTER);

            // Actualizar la interfaz
            contenido.revalidate();
            contenido.repaint();
        }

        /**
         * Cierra la ventana principal de la aplicación.
         * Este método libera los recursos utilizados por la ventana y la cierra.
         */
        public void cerrarVentanas() {
            dispose();
        }


           

   
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        FONDO = new javax.swing.JPanel();
        contenido = new javax.swing.JPanel();
        MENU = new javax.swing.JPanel();
        btcreartarea = new javax.swing.JButton();
        btlista = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btcerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btuser = new javax.swing.JButton();
        bthistorial = new javax.swing.JButton();

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VERDE.jpg"))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("BUSCAR TAREA");
        jPanel1.add(jButton3);

        jButton5.setText("VER TAREAS ASIGNADAS");
        jPanel1.add(jButton5);

        jButton4.setText("HISTORIAL");
        jPanel1.add(jButton4);

        jButton2.setText("CREAR TAREA");
        jPanel1.add(jButton2);

        jButton1.setText("SALIR");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FONDO.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenidoLayout = new javax.swing.GroupLayout(contenido);
        contenido.setLayout(contenidoLayout);
        contenidoLayout.setHorizontalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );
        contenidoLayout.setVerticalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        MENU.setBackground(new java.awt.Color(0, 130, 50));

        btcreartarea.setBackground(new java.awt.Color(0, 153, 0));
        btcreartarea.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btcreartarea.setForeground(new java.awt.Color(255, 255, 255));
        btcreartarea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crear tarea.png"))); // NOI18N
        btcreartarea.setText("Crear Tarea");
        btcreartarea.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btcreartarea.setBorderPainted(false);
        btcreartarea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btcreartarea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btcreartarea.setIconTextGap(5);
        btcreartarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcreartareaActionPerformed(evt);
            }
        });

        btlista.setBackground(new java.awt.Color(0, 153, 0));
        btlista.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btlista.setForeground(new java.awt.Color(255, 255, 255));
        btlista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inicio.png"))); // NOI18N
        btlista.setText("Inicio");
        btlista.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btlista.setBorderPainted(false);
        btlista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btlista.setIconTextGap(5);
        btlista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlistaActionPerformed(evt);
            }
        });

        btcerrar.setBackground(new java.awt.Color(0, 153, 0));
        btcerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btcerrar.setForeground(new java.awt.Color(255, 255, 255));
        btcerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cerrar sesio.png"))); // NOI18N
        btcerrar.setText("Cerrar Sesion");
        btcerrar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btcerrar.setBorderPainted(false);
        btcerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btcerrar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btcerrar.setIconTextGap(5);
        btcerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcerrarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MENU");

        btuser.setBackground(new java.awt.Color(0, 153, 0));
        btuser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btuser.setForeground(new java.awt.Color(255, 255, 255));
        btuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios.png"))); // NOI18N
        btuser.setText("Usuarios");
        btuser.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btuser.setBorderPainted(false);
        btuser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btuser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btuser.setIconTextGap(5);
        btuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btuserActionPerformed(evt);
            }
        });

        bthistorial.setBackground(new java.awt.Color(0, 153, 0));
        bthistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bthistorial.setForeground(new java.awt.Color(255, 255, 255));
        bthistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/historial azul.png"))); // NOI18N
        bthistorial.setText("Historial");
        bthistorial.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        bthistorial.setBorderPainted(false);
        bthistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bthistorial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bthistorial.setIconTextGap(5);
        bthistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthistorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MENULayout = new javax.swing.GroupLayout(MENU);
        MENU.setLayout(MENULayout);
        MENULayout.setHorizontalGroup(
            MENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENULayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(MENULayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btcreartarea, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btuser, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(bthistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btlista, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btcerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        MENULayout.setVerticalGroup(
            MENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENULayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(MENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MENULayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btcreartarea, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MENULayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btuser, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MENULayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(bthistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btlista, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addComponent(btcerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout FONDOLayout = new javax.swing.GroupLayout(FONDO);
        FONDO.setLayout(FONDOLayout);
        FONDOLayout.setHorizontalGroup(
            FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FONDOLayout.createSequentialGroup()
                .addComponent(MENU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FONDOLayout.setVerticalGroup(
            FONDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FONDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FONDO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btcreartareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcreartareaActionPerformed
       crearTarea();
    }//GEN-LAST:event_btcreartareaActionPerformed

    private void btlistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlistaActionPerformed
        mostrarLista();
    }//GEN-LAST:event_btlistaActionPerformed

    private void btcerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcerrarActionPerformed
     login log = new login();
     this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btcerrarActionPerformed

    private void btuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btuserActionPerformed
      mostrarUser();  // TODO add your handling code here:
    }//GEN-LAST:event_btuserActionPerformed

    private void bthistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthistorialActionPerformed
      mostrarHistorial();  // TODO add your handling code here:
    }//GEN-LAST:event_bthistorialActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FONDO;
    private javax.swing.JPanel MENU;
    private javax.swing.JButton btcerrar;
    private javax.swing.JButton btcreartarea;
    private javax.swing.JButton bthistorial;
    private javax.swing.JButton btlista;
    private javax.swing.JButton btuser;
    public javax.swing.JPanel contenido;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
