    package ventanas;

    import com.leo.asignador_de_tareas.Asignador_de_tareas.Tarea;
    import com.leo.asignador_de_tareas.Asignador_de_tareas.TareaManager;
    import com.leo.asignador_de_tareas.Asignador_de_tareas.User;
    import com.leo.asignador_de_tareas.Asignador_de_tareas.UserManager;
    import javax.swing.*;
    import javax.swing.event.DocumentEvent;
    import javax.swing.event.DocumentListener;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.stream.Collectors;

    /**
     * Clase que representa el panel para la creación de tareas.
     * Permite definir las características de una tarea, asignarla a un usuario,
     * y validar la información antes de guardarla.
     */
    public class crear_tarea extends JPanel {
        private final String nombreUsuario;
        private final String tipoUsuario;

        /**
         * Constructor que inicializa el panel de creación de tareas.
         *
         * @param nombreUsuario El nombre del usuario que accede al panel.
         * @param tipoUsuario El tipo de usuario ("ADMINISTRADOR" o "NORMAL").
         */
        public crear_tarea(String nombreUsuario, String tipoUsuario) {
            this.nombreUsuario = nombreUsuario;
            this.tipoUsuario = tipoUsuario;
            initComponents();
            configurarComponentes();
        }

        /**
         * Configura los componentes de la interfaz y los comportamientos de los campos.
         */
        private void configurarComponentes() {
            cargarUsuariosEnFiltro();
            btguardar.addActionListener(evt -> guardarTarea());

            limitarLongitudTexto(jTextFieldTarea, 50);
            limitarLongitudTexto(jTextFieldDescripcion, 100);
            limitarLongitudTexto(jTextFieldFechaInicio, 10);
            limitarLongitudTexto(jTextFieldHoraInicio, 5);
            limitarLongitudTexto(jTextFieldFechaFin, 10);
            limitarLongitudTexto(jTextFieldHoraFin, 5);

            agregarValidacionInicialLetras(jTextFieldTarea);
        }

        /**
         * Carga los usuarios en el combo box según el tipo de usuario.
         */
        private void cargarUsuariosEnFiltro() {
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            UserManager userManager = new UserManager();

            if ("ADMINISTRADOR".equalsIgnoreCase(tipoUsuario)) {
                modelo.addElement("Seleccionar usuario");
                try {
                    List<String> usuarios = userManager.obtenerTodosLosUsuarios()
                                           .stream()
                                           .map(User::getUsuario)
                                           .collect(Collectors.toList());

                    if (usuarios.isEmpty()) {
                        modelo.addElement("No hay usuarios disponibles");
                    } else {
                        usuarios.forEach(modelo::addElement);
                    }
                } catch (Exception e) {
                    modelo.addElement("Error al cargar usuarios");
                }
            } else {
                modelo.addElement(nombreUsuario);
                filtro_usuario.setEnabled(false);
            }

            filtro_usuario.setModel(modelo);
        }

        /**
         * Guarda la tarea creada en un archivo.
         * Realiza validaciones en los campos y muestra mensajes de error si es necesario.
         */
        private void guardarTarea() {
            String usuarioSeleccionado = (String) filtro_usuario.getSelectedItem();
            if ("Seleccionar usuario".equals(usuarioSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario válido.");
                return;
            }

            String nombreTarea = jTextFieldTarea.getText().trim();
            String descripcion = jTextFieldDescripcion.getText().trim();
            String prioridad = (String) comboBoxPrioridad.getSelectedItem();
            String estado = (String) comboBoxEstado.getSelectedItem();
            String fechaInicio = jTextFieldFechaInicio.getText().trim();
            String horaInicio = jTextFieldHoraInicio.getText().trim();
            String fechaFin = jTextFieldFechaFin.getText().trim();
            String horaFin = jTextFieldHoraFin.getText().trim();

            if (!validarCampos(nombreTarea, prioridad, estado, fechaInicio, horaInicio, fechaFin, horaFin)) {
                return;
            }

            Tarea nuevaTarea = new Tarea(usuarioSeleccionado, nombreTarea, descripcion, prioridad, estado, fechaInicio, horaInicio, fechaFin, horaFin);
            TareaManager tareaManager = new TareaManager();

            // Leer tareas existentes
            List<Tarea> tareasExistentes = tareaManager.leerTareasDesdeArchivo("tareas.txt");
            if (tareasExistentes == null) {
                tareasExistentes = new ArrayList<>();
            }

            // Añadir la nueva tarea a la lista existente
            tareasExistentes.add(nuevaTarea);

            // Guardar la lista actualizada en el archivo
            tareaManager.guardarTareasEnArchivo("tareas.txt", tareasExistentes, tipoUsuario, nombreUsuario);

            JOptionPane.showMessageDialog(this, "Tarea guardada exitosamente.");
        }

        /**
         * Valida que todos los campos obligatorios estén llenos y tengan un formato correcto.
         *
         * @param campos Los valores de los campos a validar.
         * @return true si todos los campos son válidos, de lo contrario false.
         */
        private boolean validarCampos(String... campos) {
            for (String campo : campos) {
                if (campo == null || campo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                    return false;
                }
            }
            return validarFormatoFechasYHoras(campos[3], campos[4]) &&
                   validarFormatoFechasYHoras(campos[5], campos[6]) &&
                   validarOrdenFechas(campos[3], campos[4], campos[5], campos[6]);
        }

        /**
         * Valida el formato de las fechas y horas según el patrón dd/MM/yyyy HH:mm.
         *
         * @param fecha La fecha a validar.
         * @param hora La hora a validar.
         * @return true si el formato es válido, de lo contrario false.
         */
        private boolean validarFormatoFechasYHoras(String fecha, String hora) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime.parse(fecha + " " + hora, formatter);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "El formato de fecha u hora no es válido. Ejemplo esperado: Fecha: 01/01/2023, Hora: 14:30");
                return false;
            }
        }

        /**
         * Valida que la fecha y hora de fin sean posteriores a las de inicio.
         *
         * @param fechaInicio La fecha de inicio.
         * @param horaInicio La hora de inicio.
         * @param fechaFin La fecha de fin.
         * @param horaFin La hora de fin.
         * @return true si la fecha de fin es posterior a la de inicio, de lo contrario false.
         */
        private boolean validarOrdenFechas(String fechaInicio, String horaInicio, String fechaFin, String horaFin) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime inicio = LocalDateTime.parse(fechaInicio + " " + horaInicio, formatter);
            LocalDateTime fin = LocalDateTime.parse(fechaFin + " " + horaFin, formatter);

            if (!fin.isAfter(inicio)) {
                JOptionPane.showMessageDialog(this, "La fecha y hora de fin deben ser posteriores a las de inicio. Ejemplo esperado: Inicio: 01/01/2023 14:30, Fin: 01/01/2023 15:30");
                return false;
            }
            return true;
        }

        /**
         * Limita la longitud del texto permitido en un campo de texto.
         *
         * @param textField El campo de texto a limitar.
         * @param longitud La longitud máxima permitida.
         */
        private void limitarLongitudTexto(JTextField textField, int longitud) {
            textField.setDocument(new LimitedDocument(longitud));
        }

        /**
         * Agrega una validación para que un campo de texto comience con letras.
         *
         * @param textField El campo de texto a validar.
         */
        private void agregarValidacionInicialLetras(JTextField textField) {
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) { validar(textField); }

                @Override
                public void removeUpdate(DocumentEvent e) { validar(textField); }

                @Override
                public void changedUpdate(DocumentEvent e) { validar(textField); }

                private void validar(JTextField campo) {
                    String texto = campo.getText();
                    if (!texto.matches("^[a-zA-Z].*")) {
                        JOptionPane.showMessageDialog(crear_tarea.this, "El campo debe comenzar con letras.");
                        campo.setText(texto.replaceAll("^[^a-zA-Z]*", ""));
                    }
                }
            });
        }

         /**
          * Clase auxiliar para limitar la longitud del texto en un campo de texto.
          * Extiende {@link javax.swing.text.PlainDocument} para controlar el número máximo
          * de caracteres que se pueden insertar en un campo.
          */
         private static class LimitedDocument extends javax.swing.text.PlainDocument {
             private final int maxLength;

             /**
              * Constructor de la clase LimitedDocument.
              *
              * @param maxLength La longitud máxima permitida para el texto.
              */
             public LimitedDocument(int maxLength) {
                 this.maxLength = maxLength;
             }

             /**
              * Sobrescribe el método insertString para controlar la longitud del texto.
              * Permite la inserción solo si el texto total no supera la longitud máxima.
              *
              * @param offset La posición donde se insertará el texto.
              * @param str El texto a insertar.
              * @param attr Los atributos del texto a insertar.
              * @throws javax.swing.text.BadLocationException Si la posición de inserción no es válida.
              */
             @Override
             public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                 if (str == null) {
                     return;
                 }
                 if ((getLength() + str.length()) <= maxLength) {
                     super.insertString(offset, str, attr);
                 }
             }
         }



     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTarea = new javax.swing.JTextField();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btguardar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        comboBoxPrioridad = new javax.swing.JComboBox<>();
        comboBoxEstado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldFechaInicio = new javax.swing.JTextField();
        jTextFieldHoraInicio = new javax.swing.JTextField();
        jTextFieldHoraFin = new javax.swing.JTextField();
        jTextFieldFechaFin = new javax.swing.JTextField();
        filtro_usuario = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("CREAR TAREA");

        jLabel3.setText("NOMBRE DE LA TAREA:");

        jLabel4.setText("DESCRIPCION:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PRIORIDAD");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ESTADO");

        btguardar.setText("GUARDAR");
        btguardar.setContentAreaFilled(false);
        btguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(0, 150, 0));

        jLabel7.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("COMIENZA !!");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        comboBoxPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baja", "Media", "Alta" }));
        comboBoxPrioridad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        comboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por hacer", "En progreso", "Completada" }));
        comboBoxEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HORA FIN");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("FECHA FIN ");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("FECHA INICIO ");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("HORA INICIO ");

        filtro_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        filtro_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(530, 530, 530))
            .addComponent(jSeparator2)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(filtro_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jTextFieldTarea)
                .addGap(83, 83, 83))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jTextFieldDescripcion))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(comboBoxPrioridad, 0, 97, Short.MAX_VALUE)
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(comboBoxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(179, 179, 179))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jTextFieldFechaInicio)))
                .addGap(31, 31, 31)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextFieldHoraInicio)
                .addGap(179, 179, 179))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextFieldFechaFin)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jTextFieldHoraFin))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(179, 179, 179))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(590, 590, 590)
                .addComponent(btguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(filtro_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(30, 30, 30)
                .addComponent(btguardar)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btguardar;
    private javax.swing.JComboBox<String> comboBoxEstado;
    private javax.swing.JComboBox<String> comboBoxPrioridad;
    private javax.swing.JComboBox<String> filtro_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldFechaFin;
    private javax.swing.JTextField jTextFieldFechaInicio;
    private javax.swing.JTextField jTextFieldHoraFin;
    private javax.swing.JTextField jTextFieldHoraInicio;
    private javax.swing.JTextField jTextFieldTarea;
    // End of variables declaration//GEN-END:variables

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
