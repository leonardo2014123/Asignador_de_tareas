
 
    package com.leo.asignador_de_tareas;

    import interfaz.login;
    import java.util.List;
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.File;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.stream.Collectors;

    /**
     * Clase principal de la aplicación Asignador de Tareas.
     * Contiene la lógica principal y las clases para gestionar usuarios y tareas.
     */
    public class Asignador_de_tareas {

    /**
     * Método principal que lanza la aplicación y muestra la ventana de inicio de sesión.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new login(); // Inicia la ventana del login
        });
    }

    /**
     * Clase que representa un usuario del sistema.
     */
    /**
 * Clase que representa un usuario en el sistema.
 */
    public static class User {
        private String usuario;
        private String correo;
        private String tipoUsuario;

        /**
         * Constructor de la clase User.
         * 
         * @param usuario El nombre de usuario.
         * @param correo El correo electrónico del usuario.
         * @param tipoUsuario El tipo de usuario (por ejemplo, ADMINISTRADOR, USUARIO).
         */
        public User(String usuario, String correo, String tipoUsuario) {
            this.usuario = usuario;
            this.correo = correo;
            this.tipoUsuario = tipoUsuario;
        }


        public String getUsuario() {return usuario; }
        public String getCorreo() {return correo; }
        public String getTipoUsuario() {return tipoUsuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }
        public void setCorreo(String correo) { this.correo = correo; }
        public void setTipoUsuario(String tipoUsuario) {this.tipoUsuario = tipoUsuario; }

        /**
         * Devuelve una representación en forma de cadena del usuario.
         * 
         * @return Una cadena con el nombre de usuario, correo y tipo de usuario.
         */
        @Override
        public String toString() {
            return "Usuario: " + usuario + ", Correo: " + correo + ", Tipo: " + tipoUsuario;
        }
    }

    /**
     * Clase para gestionar la lista de usuarios.
     */
    public static class UserManager {

        /**
         * Lee los usuarios desde un archivo de texto.
         * 
         * @param archivo El nombre del archivo que contiene los usuarios.
         * @return Lista de usuarios cargados desde el archivo.
         */
        public List<User> leerUsuariosDesdeArchivo(String archivo) {
            List<User> usuarios = new ArrayList<>();
            File file = new File(archivo);
            if (!file.exists()) {
               return usuarios; // Si el archivo no existe, retorna una lista vacía
            }
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(", ");
                    if (partes.length == 3) {
                        String usuario = partes[0].substring(9).trim();
                        String correo = partes[1].substring(8).trim();
                        String tipo = partes[2].substring(6).trim();
                        usuarios.add(new User(usuario, correo, tipo));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return usuarios;
        }

        /**
         * Escribe la lista de usuarios en un archivo de texto.
         * 
         * @param archivo El nombre del archivo donde se guardarán los usuarios.
         * @param usuarios Lista de usuarios a guardar.
         */
        public void escribirUsuariosEnArchivo(String archivo, List<User> usuarios) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                for (User usuario : usuarios) {
                    writer.write("Usuario: " + usuario.getUsuario() + ", Correo: " + usuario.getCorreo() + ", Tipo: " + usuario.getTipoUsuario());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Verifica si un usuario ya existe en la lista de usuarios.
         * 
         * @param usuarios Lista de usuarios existentes.
         * @param usuario El nombre del usuario a verificar.
         * @return true si el usuario ya existe, false en caso contrario.
         */
        public boolean existeUsuario(List<User> usuarios, String usuario) {
            for (User u : usuarios) {
                if (u.getUsuario().equals(usuario)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Obtiene todos los usuarios desde el archivo o la base de datos.
         * 
         * @return Lista de todos los usuarios.
         */
        public List<User> obtenerTodosLosUsuarios() {
            return leerUsuariosDesdeArchivo("usuarios.txt");
        }

        /**
         * Obtiene un usuario por nombre.
         * 
         * @param nombre El nombre del usuario a buscar.
         * @return El usuario correspondiente, o null si no se encuentra.
         */
        public User obtenerUsuarioPorNombre(String nombre) {
            List<User> usuarios = leerUsuariosDesdeArchivo("usuarios.txt");
            for (User usuario : usuarios) {
                if (usuario.getUsuario().equals(nombre)) {
                    return usuario;
                }
            }
            return null; // Si no se encuentra el usuario
        }

        /**
         * Guarda la lista de usuarios en un archivo de texto, con reglas especiales para administradores.
         * 
         * @param archivo El nombre del archivo donde se guardarán los usuarios.
         * @param usuariosActualizados Lista de usuarios que se desean añadir o actualizar.
         * @param tipoUsuario El tipo de usuario que está realizando la operación (puede ser ADMINISTRADOR u otro tipo).
         */
        public void guardarUsuariosEnArchivo(String archivo, List<User> usuariosActualizados, String tipoUsuario) {
            List<User> usuariosExistentes = leerUsuariosDesdeArchivo(archivo);

            if (!"ADMINISTRADOR".equalsIgnoreCase(tipoUsuario)) {
                // Filtrar usuarios duplicados (por nombre de usuario) y conservar los usuarios actuales
                Set<String> nombresUsuariosActualizados = usuariosActualizados.stream()
                        .map(User::getUsuario)
                        .collect(Collectors.toSet());

                usuariosExistentes = usuariosExistentes.stream()
                        .filter(u -> !nombresUsuariosActualizados.contains(u.getUsuario()))
                        .collect(Collectors.toList());

                // Añadir usuarios actualizados (sin duplicar)
                usuariosExistentes.addAll(usuariosActualizados);
            } else {
                // Para administradores, sobrescribir con todos los usuarios proporcionados
                usuariosExistentes = usuariosActualizados;
            }

            // Escribir los usuarios en el archivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (User usuario : usuariosExistentes) {
                    bw.write("Usuario: " + usuario.getUsuario() 
                             + ", Correo: " + usuario.getCorreo() 
                             + ", Tipo: " + usuario.getTipoUsuario());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

       /**
     * Clase que representa una tarea asignada a un usuario dentro del sistema.
     * Esta clase contiene toda la información necesaria para gestionar una tarea,
     * incluyendo detalles como el nombre, descripción, prioridades y fechas de inicio y fin.
     */
    public static class Tarea {
        private String usuario;
        private String nombre;
        private String descripcion;
        private String prioridad;
        private String estado;
        private String fechaInicio;
        private String horaInicio;
        private String fechaFin;
        private String horaFin;

        /**
         * Constructor para inicializar una tarea con los detalles proporcionados.
         * 
         * @param usuario El usuario asignado a la tarea.
         * @param nombre El nombre de la tarea.
         * @param descripcion Una descripción breve de la tarea.
         * @param prioridad La prioridad de la tarea (por ejemplo: Alta, Media, Baja).
         * @param estado El estado actual de la tarea (por ejemplo: Pendiente, En progreso, Completada).
         * @param fechaInicio La fecha de inicio de la tarea.
         * @param horaInicio La hora exacta de inicio de la tarea.
         * @param fechaFin La fecha en la que se espera que finalice la tarea.
         * @param horaFin La hora exacta en la que la tarea debería finalizar.
         */
        public Tarea(String usuario, String nombre, String descripcion, String prioridad, String estado,
                     String fechaInicio, String horaInicio, String fechaFin, String horaFin) {
            this.usuario = usuario;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.prioridad = prioridad;
            this.estado = estado;
            this.fechaInicio = fechaInicio;
            this.horaInicio = horaInicio;
            this.fechaFin = fechaFin;
            this.horaFin = horaFin;
        }

        // Métodos getters y setters
        public String getUsuario() { return usuario; }
        public String getNombre() { return nombre; }
        public String getDescripcion() { return descripcion; }
        public String getPrioridad() { return prioridad; }
        public String getEstado() { return estado; }
        public String getFechaInicio() { return fechaInicio; }
        public String getHoraInicio() { return horaInicio; }
        public String getFechaFin() { return fechaFin; }
        public String getHoraFin() { return horaFin; }

        public void setUsuario(String usuario) { this.usuario = usuario; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
        public void setEstado(String estado) { this.estado = estado; }
        public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
        public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
        public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
        public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
    }

    /**
     * Clase que gestiona las tareas dentro del sistema.
     * Permite realizar operaciones como cargar tareas desde un archivo, 
     * guardarlas de nuevo, y obtener tareas filtradas según ciertos criterios.
     */
    public static class TareaManager {

        /**
         * Lee las tareas desde un archivo de texto.
         * Cada tarea debe estar formateada de forma específica en el archivo.
         * 
         * @param archivo El nombre del archivo que contiene las tareas.
         * @return Una lista de objetos Tarea cargados desde el archivo.
         */
        public List<Tarea> leerTareasDesdeArchivo(String archivo) {
            List<Tarea> tareas = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                String usuario = null, nombre = null, descripcion = null, prioridad = null, estado = null;
                String fechaInicio = null, horaInicio = null, fechaFin = null, horaFin = null;

                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith("Usuario:")) {
                        usuario = linea.substring(8).trim();
                    } else if (linea.startsWith("Nombre de la tarea:")) {
                        nombre = linea.substring(19).trim();
                    } else if (linea.startsWith("Descripción:")) {
                        descripcion = linea.substring(12).trim();
                    } else if (linea.startsWith("Prioridad:")) {
                        prioridad = linea.substring(10).trim();
                    } else if (linea.startsWith("Estado:")) {
                        estado = linea.substring(7).trim();
                    } else if (linea.startsWith("Fecha de inicio:")) {
                        fechaInicio = linea.substring(16).trim();
                    } else if (linea.startsWith("Hora de inicio:")) {
                        horaInicio = linea.substring(15).trim();
                    } else if (linea.startsWith("Fecha de fin:")) {
                        fechaFin = linea.substring(13).trim();
                    } else if (linea.startsWith("Hora de fin:")) {
                        horaFin = linea.substring(12).trim();
                    } else if (linea.startsWith("---------------------------")) {
                        tareas.add(new Tarea(usuario, nombre, descripcion, prioridad, estado, fechaInicio, horaInicio, fechaFin, horaFin));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tareas;
        }

        /**
         * Guarda las tareas en un archivo de texto. Si el usuario no es un administrador, 
         * solo sus propias tareas serán guardadas. Para los administradores, 
         * se sobrescribe todo el archivo con las tareas proporcionadas.
         * 
         * @param archivo El nombre del archivo donde se guardarán las tareas.
         * @param tareas Lista de tareas a guardar.
         * @param tipoUsuario Tipo de usuario que está realizando la operación (ADMINISTRADOR o usuario normal).
         * @param nombreUsuario El nombre del usuario que está guardando las tareas.
         */
        public void guardarTareasEnArchivo(String archivo, List<Tarea> tareasActualizadas, String tipoUsuario, String nombreUsuario) {
            List<Tarea> tareasExistentes = leerTareasDesdeArchivo(archivo);

            if (!"ADMINISTRADOR".equalsIgnoreCase(tipoUsuario)) {
                // Filtrar las tareas que no pertenecen al usuario actual
                List<Tarea> tareasNoDelUsuario = tareasExistentes.stream()
                    .filter(t -> !t.getUsuario().equals(nombreUsuario))
                    .collect(Collectors.toList());

                // Añadir las tareas actualizadas del usuario actual
                tareasNoDelUsuario.addAll(tareasActualizadas);

                // Sobrescribir el archivo con todas las tareas
                tareasExistentes = tareasNoDelUsuario;
            } else {
                // Para administradores, sobrescribir con todas las tareas proporcionadas
                tareasExistentes = tareasActualizadas;
            }

            // Escribir las tareas en el archivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (Tarea tarea : tareasExistentes) {
                    bw.write("Usuario: " + tarea.getUsuario());
                    bw.newLine();
                    bw.write("Nombre de la tarea: " + tarea.getNombre());
                    bw.newLine();
                    bw.write("Descripción: " + tarea.getDescripcion());
                    bw.newLine();
                    bw.write("Prioridad: " + tarea.getPrioridad());
                    bw.newLine();
                    bw.write("Estado: " + tarea.getEstado());
                    bw.newLine();
                    bw.write("Fecha de inicio: " + tarea.getFechaInicio());
                    bw.newLine();
                    bw.write("Hora de inicio: " + tarea.getHoraInicio());
                    bw.newLine();
                    bw.write("Fecha de fin: " + tarea.getFechaFin());
                    bw.newLine();
                    bw.write("Hora de fin: " + tarea.getHoraFin());
                    bw.newLine();
                    bw.write("---------------------------");
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Devuelve todas las tareas almacenadas en el archivo.
         * 
         * @return Una lista de todas las tareas.
         */
        public List<Tarea> obtenerTodasLasTareas() {
            return leerTareasDesdeArchivo("tareas.txt");
        }

        /**
         * Devuelve las tareas asignadas a un usuario específico.
         * 
         * @param usuario El nombre del usuario para filtrar las tareas.
         * @return Una lista de tareas asignadas al usuario.
         */
        public List<Tarea> obtenerTareasPorUsuario(String usuario) {
            List<Tarea> todasLasTareas = leerTareasDesdeArchivo("tareas.txt");
            List<Tarea> tareasPorUsuario = new ArrayList<>();

            for (Tarea tarea : todasLasTareas) {
                if (tarea.getUsuario().equals(usuario)) {
                    tareasPorUsuario.add(tarea);
                }
            }
            return tareasPorUsuario;
        }

        /**
         * Devuelve una lista con todos los usuarios que han sido asignados a alguna tarea.
         * 
         * @return Una lista de nombres de usuarios únicos.
         */
        public List<String> obtenerTodosLosUsuarios() {
            List<Tarea> todasLasTareas = leerTareasDesdeArchivo("tareas.txt");
            Set<String> usuarios = new HashSet<>();

            for (Tarea tarea : todasLasTareas) {
                usuarios.add(tarea.getUsuario());
            }
            return new ArrayList<>(usuarios);
        }
      }

    }


    
    
    
    
    
    
    
    


