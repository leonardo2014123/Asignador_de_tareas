
    package ventanas;

    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.InputStreamReader;
    import java.io.IOException;
    import javax.swing.JTextArea;

    /**
     * Clase que representa un panel para visualizar el historial de tareas.
     * Permite a los usuarios, ya sean administradores o normales, ver las tareas
     * asignadas según su tipo de usuario.
     */
    public class historial extends javax.swing.JPanel {

        private String tipoUsuario;
        private String nombreUsuario;

        /**
         * Constructor de la clase historial.
         * Inicializa el panel con el tipo y nombre del usuario.
         *
         * @param tipoUsuario El tipo de usuario ("ADMINISTRADOR" o "NORMAL").
         * @param nombreUsuario El nombre del usuario que accede al historial.
         */
        public historial(String tipoUsuario, String nombreUsuario) {
            initComponents();
            this.tipoUsuario = tipoUsuario;
            this.nombreUsuario = nombreUsuario;
        }

        /**
         * Muestra el historial de tareas en un área de texto.
         * Si el usuario es un administrador, se muestran todas las tareas.
         * Si el usuario es normal, solo se muestran sus propias tareas.
         * @throws IOException Si ocurre un error al leer el archivo de tareas.
         */
        public void mostrarHistorial() {
            areatexto.setText(""); // Limpia el contenido antes de cargar el nuevo
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tareas.txt"), "UTF-8"))) {
                String linea;
                StringBuilder contenido = new StringBuilder();
                boolean tareaUsuario = false; // Indica si la tarea pertenece al usuario actual

                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith("Usuario: ")) {
                        // Determina si la tarea pertenece al usuario actual
                        if ("ADMINISTRADOR".equalsIgnoreCase(tipoUsuario)) {
                            // Si es administrador, muestra todas las tareas
                            contenido.append(linea).append("\n");
                            tareaUsuario = true;
                        } else if (linea.contains(nombreUsuario)) {
                            // Si es usuario normal y coincide con el nombreUsuario, muestra la tarea
                            contenido.append(linea).append("\n");
                            tareaUsuario = true;
                        } else {
                            tareaUsuario = false; // Si no es la tarea del usuario actual, la ignora
                        }
                    } else if (tareaUsuario) {
                        // Si la tarea pertenece al usuario actual, muestra la información de la tarea
                        contenido.append(linea).append("\n");
                    }
                }

                areatexto.setText(contenido.toString());
            } catch (IOException e) {
                areatexto.setText("Error al leer el archivo de historial.");
                e.printStackTrace();
            }
        }


    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areatexto = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        areatexto.setColumns(20);
        areatexto.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        areatexto.setRows(5);
        jScrollPane2.setViewportView(areatexto);

        jPanel2.setBackground(new java.awt.Color(0, 150, 0));

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("HISTORIAL DE TAREAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(399, 399, 399))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areatexto;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
