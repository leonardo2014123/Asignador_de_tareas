    package interfaz;

    import com.leo.asignador_de_tareas.Asignador_de_tareas.Tarea;
    import com.leo.asignador_de_tareas.Asignador_de_tareas;
    import javax.swing.*;
    import javax.swing.text.*;
    import java.util.List;

    /**
     * Clase que permite editar una tarea seleccionada en el sistema.
     * Proporciona una interfaz gráfica para modificar los datos de una tarea.
     */
    public class editar_tarea extends JFrame {

        private Asignador_de_tareas.Tarea tareaOriginal;
        private Asignador_de_tareas.Tarea tareaSeleccionada;
        private Asignador_de_tareas.TareaManager tareaManager;

        /**
         * Constructor de la clase editar_tarea.
         *
         * @param tareaSeleccionada La tarea seleccionada que se desea editar.
         */
        public editar_tarea(Asignador_de_tareas.Tarea tareaSeleccionada) {
            this.tareaSeleccionada = tareaSeleccionada;

            // Inicializamos tareaOriginal copiando los valores de tareaSeleccionada
            this.tareaOriginal = new Asignador_de_tareas.Tarea(
                tareaSeleccionada.getUsuario(),
                tareaSeleccionada.getNombre(),
                tareaSeleccionada.getDescripcion(),
                tareaSeleccionada.getPrioridad(),
                tareaSeleccionada.getEstado(),
                tareaSeleccionada.getFechaInicio(),
                tareaSeleccionada.getHoraInicio(),
                tareaSeleccionada.getFechaFin(),
                tareaSeleccionada.getHoraFin()
            );

            this.tareaManager = new Asignador_de_tareas.TareaManager();
            initComponents(); // Método generado automáticamente por NetBeans
            setSize(750, 680);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cargarDatosTarea();

            setVisible(true);
            ((AbstractDocument) cambiarNombre.getDocument()).setDocumentFilter(new LimiteCaracteres(50));
            ((AbstractDocument) descripcion.getDocument()).setDocumentFilter(new LimiteCaracteres(100));
        }

        /**
         * Clase interna que implementa un filtro para limitar el número de caracteres en un campo de texto.
         */
        public class LimiteCaracteres extends DocumentFilter {
            private int limite;

            /**
             * Constructor de la clase LimiteCaracteres.
             *
             * @param limite Límite máximo de caracteres permitidos.
             */
            public LimiteCaracteres(int limite) {
                this.limite = limite;
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length()) <= limite) {
                    super.insertString(fb, offset, text, attr);
                } else {
                    JOptionPane.showMessageDialog(null, "Solo puedes ingresar hasta " + limite + " caracteres.",
                            "Límite de caracteres", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() - length + text.length()) <= limite) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    JOptionPane.showMessageDialog(null, "Solo puedes ingresar hasta " + limite + " caracteres.",
                            "Límite de caracteres", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        /**
         * Carga los datos de la tarea seleccionada en los campos correspondientes de la interfaz.
         */
        private void cargarDatosTarea() {
            nombre.setText(tareaSeleccionada.getNombre());
            cambiarNombre.setText(tareaSeleccionada.getNombre());
            comboBoxPrioridad.setSelectedItem(tareaSeleccionada.getPrioridad());
            comboBoxEstado.setSelectedItem(tareaSeleccionada.getEstado());
            fechaInicio.setText(tareaSeleccionada.getFechaInicio());
            horaInicio.setText(tareaSeleccionada.getHoraInicio());
            fechaFin.setText(tareaSeleccionada.getFechaFin());
            horaFin.setText(tareaSeleccionada.getHoraFin());
            descripcion.setText(tareaSeleccionada.getDescripcion());
        }

        /**
         * Guarda los cambios realizados en la tarea seleccionada.
         * Valida que los campos obligatorios no estén vacíos antes de actualizar la tarea.
         * 
         * @throws IOException Si ocurre un error al leer o guardar las tareas en el archivo.
         */
        private void guardarCambios() {
            if (nombre.getText().isEmpty() || fechaInicio.getText().isEmpty() || horaInicio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos obligatorios.");
                return;
            }

            tareaSeleccionada.setNombre(cambiarNombre.getText());
            tareaSeleccionada.setPrioridad(comboBoxPrioridad.getSelectedItem().toString());
            tareaSeleccionada.setEstado(comboBoxEstado.getSelectedItem().toString());
            tareaSeleccionada.setFechaInicio(fechaInicio.getText());
            tareaSeleccionada.setHoraInicio(horaInicio.getText());
            tareaSeleccionada.setFechaFin(fechaFin.getText());
            tareaSeleccionada.setHoraFin(horaFin.getText());
            tareaSeleccionada.setDescripcion(descripcion.getText());

            try {
                List<Asignador_de_tareas.Tarea> todasLasTareas = tareaManager.leerTareasDesdeArchivo("tareas.txt");

                boolean tareaActualizada = false;
                for (int i = 0; i < todasLasTareas.size(); i++) {
                    Asignador_de_tareas.Tarea tarea = todasLasTareas.get(i);

                    if (tarea.getNombre().equals(tareaOriginal.getNombre()) &&
                        tarea.getDescripcion().equals(tareaOriginal.getDescripcion()) &&
                        tarea.getFechaInicio().equals(tareaOriginal.getFechaInicio()) &&
                        tarea.getHoraInicio().equals(tareaOriginal.getHoraInicio())) {

                        todasLasTareas.set(i, tareaSeleccionada);
                        tareaActualizada = true;
                        break;
                    }
                }

                if (!tareaActualizada) {
                    JOptionPane.showMessageDialog(this, "No se encontró la tarea seleccionada para actualizar.");
                    return;
                }

                tareaManager.guardarTareasEnArchivo("tareas.txt", todasLasTareas, "ADMINISTRADOR", "");
                JOptionPane.showMessageDialog(this, "Los cambios se han guardado correctamente.");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar la tarea: " + e.getMessage());
            }
        }





    



   




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabelPrioridad = new javax.swing.JLabel();
        cambiarNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jLabelNombreNuevo = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btcancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        comboBoxPrioridad = new javax.swing.JComboBox<>();
        comboBoxEstado = new javax.swing.JComboBox<>();
        jLabelFechaInicio = new javax.swing.JLabel();
        fechaInicio = new javax.swing.JTextField();
        jLabelHoraInicio = new javax.swing.JLabel();
        horaInicio = new javax.swing.JTextField();
        fechaFin = new javax.swing.JTextField();
        horaFin = new javax.swing.JTextField();
        jLabelHoraFin = new javax.swing.JLabel();
        jLabelFechaFin = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabelPrioridad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPrioridad.setText("PRIORIDAD:");

        cambiarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarNombreActionPerformed(evt);
            }
        });

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane2.setViewportView(descripcion);

        jLabelNombreNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreNuevo.setText("CAMBIAR NOMBRE :");

        jLabelEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEstado.setText("ESTADO:");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DESCRIPCION :");

        btnGuardar.setText("GUARDAR");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        jPanel5.setBackground(new java.awt.Color(0, 150, 0));

        jLabel12.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("   Edita !!");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombre.setText("NOMBRE ACTUAL :");

        comboBoxPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baja", "Media", "Alta" }));
        comboBoxPrioridad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        comboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por hacer", "En progreso", "Completada" }));
        comboBoxEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelFechaInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaInicio.setText("FECHA INICIO ");

        jLabelHoraInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHoraInicio.setText("HORA INICIO ");

        fechaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaFinActionPerformed(evt);
            }
        });

        jLabelHoraFin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHoraFin.setText("HORA FIN");

        jLabelFechaFin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFechaFin.setText("FECHA FIN ");

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabelPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(comboBoxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(90, 90, 90)
                        .addComponent(jLabelHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(horaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabelFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabelHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(horaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabelNombreNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cambiarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cambiarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombreNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(horaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(horaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fechaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaFinActionPerformed

    }//GEN-LAST:event_fechaFinActionPerformed

    private void btcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancelarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btcancelarActionPerformed

    private void cambiarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarNombreActionPerformed
        
    }//GEN-LAST:event_cambiarNombreActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
    
    }//GEN-LAST:event_nombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       guardarCambios();
    }//GEN-LAST:event_btnGuardarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField cambiarNombre;
    private javax.swing.JComboBox<String> comboBoxEstado;
    private javax.swing.JComboBox<String> comboBoxPrioridad;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JTextField fechaFin;
    private javax.swing.JTextField fechaInicio;
    private javax.swing.JTextField horaFin;
    private javax.swing.JTextField horaInicio;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFechaFin;
    private javax.swing.JLabel jLabelFechaInicio;
    private javax.swing.JLabel jLabelHoraFin;
    private javax.swing.JLabel jLabelHoraInicio;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreNuevo;
    private javax.swing.JLabel jLabelPrioridad;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
