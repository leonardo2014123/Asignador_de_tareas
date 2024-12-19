SISTEMA DE SEGUIMIENTO DE TAREAS
     Para desarrollar un proyecto en Java que permita asignar tareas, priorizarlas y hacer
     seguimiento, es importante definir una serie de requerimientos que cubran los aspectos
     funcionales, no funcionales, y técnicos del sistema. A continuación, detallo los posibles
     requerimientos divididos en distintas categorías:
-Paquetes
  -com.leo.asignador_de_tareas
              Clase                                          Descripción
	*Asignador_de_tareas	                 Clase principal de la aplicación Asignador de Tareas.
                 Modificador y tipo	                        Clase	                                                                          Descripción
                    static class 	                Asignador_de_tareas.Tarea	                            Clase que representa una tarea asignada a un usuario dentro del sistema.
                    static class 	             Asignador_de_tareas.TareaManager	                              Clase que gestiona las tareas dentro del sistema.
                    static class 	                Asignador_de_tareas.User	                              Clase que representa un usuario en el sistema.
                    static class 	             Asignador_de_tareas.UserManager	                              Clase para gestionar la lista de usuarios.
                 Modificador y tipo	                         Método	                                                                          Descripción
                    static void	                           main​(String[] args)	                          Método principal que lanza la aplicación y muestra la ventana de inicio de sesión.

                    Constructor	                              Descripción
                Asignador_de_tareas()                      Lanza la aplicación

        *Asignador_de_tareas.Tarea	         Clase que representa una tarea asignada a un usuario dentro del sistema.
                    Constructor	                                                                                    Descripción
             Tarea​(String usuario, String nombre, 
             String descripcion, String prioridad, String estado, 
             String fechaInicio, String horaInicio, String fechaFin, String horaFin)	              Constructor para inicializar una tarea con los detalles proporcionados.
                 Modificador y tipo                           Método 
                        String	                         getDescripcion()	 
                        String	                         getEstado()	 
                        String	                         getFechaFin()	 
                        String	                         getFechaInicio()	 
                        String	                         getHoraFin()	 
                        String                         	 getHoraInicio()	 
                        String	                         getNombre()	 
                        String	                         getPrioridad()	 
                        String	                         getUsuario()	 
                         void	                         setDescripcion​(String descripcion)	 
                         void	                         setEstado​(String estado)	 
                         void	                         setFechaFin​(String fechaFin)	 
                         void	                         setFechaInicio​(String fechaInicio)	 
                         void	                         setHoraFin​(String horaFin)	 
                         void	                         setHoraInicio​(String horaInicio)	 
                         void	                         setNombre​(String nombre)	 
                         void	                         setPrioridad​(String prioridad)	 
                         void	                         setUsuario​(String usuario)                                               
        *Asignador_de_tareas.TareaManager	 Clase que gestiona las tareas dentro del sistema.
                Modificador y tipo	                                            Método	                                             Descripción
                         void	                                   guardarTareasEnArchivo​(String archivo, 
                                                                   List<Asignador_de_tareas.Tarea> tareasActualizadas, 
                                                                   String tipoUsuario, String nombreUsuario)	                   Guarda las tareas en un archivo de texto.
             List<Asignador_de_tareas.Tarea>	                   leerTareasDesdeArchivo​(String archivo)	                   Lee las tareas desde un archivo de texto.
             List<Asignador_de_tareas.Tarea>	                   obtenerTareasPorUsuario​(String usuario)	                   Devuelve las tareas asignadas a un usuario específico.
             List<Asignador_de_tareas.Tarea>	                   obtenerTodasLasTareas()	                                   Devuelve todas las tareas almacenadas en el archivo.
             List<String>	                                   obtenerTodosLosUsuarios()	          Devuelve una lista con todos los usuarios que han sido asignados a alguna tarea.

                     Constructor                                  
                    TareaManager()	                 
        *Asignador_de_tareas.User	         Clase que representa un usuario en el sistema.
                    Constructor	                                                                   Descripción
          User​(String usuario, String correo, String tipoUsuario)	                     Constructor de la clase User.

              Modificador y tipo	                                            Método	                                             Descripción
          
                   String	                                                  getCorreo()	 
                   String	                                                getTipoUsuario()	 
                   String	                                                 getUsuario()	 
                    void	                                            setCorreo​(String correo)	 
                    void	                                       setTipoUsuario​(String tipoUsuario)	 
                    void	                                          setUsuario​(String usuario)	 
                   String	                                                   toString()	                         Devuelve una representación en forma de cadena del usuario.

        *Asignador_de_tareas.UserManager	 Clase para gestionar la lista de usuarios. 
                  Constructor                                    
                 UserManager()
             Modificador y tipo	                                            Método	                                             Descripción
           void	                                escribirUsuariosEnArchivo​(String archivo,
                                                List<Asignador_de_tareas.User> usuarios)	                  Escribe la lista de usuarios en un archivo de texto.
           boolean	                        existeUsuario​(List<Asignador_de_tareas.User> 
                                                usuarios, String usuario)	                                  Verifica si un usuario ya existe en la lista de usuarios.
           void	                                guardarUsuariosEnArchivo​(String archivo, 
                                                List<Asignador_de_tareas.User> usuariosActualizados, 
                                                String tipoUsuario)	                     Guarda la lista de usuarios en un archivo de texto, con reglas especiales para administradores.                                                    
           List<Asignador_de_tareas.User>       leerUsuariosDesdeArchivo​(String archivo)	                  Lee los usuarios desde un archivo de texto.
           List<Asignador_de_tareas.User>	obtenerTodosLosUsuarios()	                                  Obtiene todos los usuarios desde el archivo o la base de datos.
           Asignador_de_tareas.User	        obtenerUsuarioPorNombre​(String nombre)	                          Obtiene un usuario por nombre.


  -interfaz
             Clase                                           Descripción
        *editar_tarea	                         Clase que permite editar una tarea seleccionada en el sistema.
           Modificador y tipo	                               Clase	                                                      Descripción
               class 	                           editar_tarea.LimiteCaracteres	Clase interna que implementa un filtro para limitar el número de caracteres en un campo de texto.
           Nested classes/interfaces inherited from class javax.swing.JFrame
           JFrame.AccessibleJFrame
           Nested classes/interfaces inherited from class java.awt.Frame
           Frame.AccessibleAWTFrame
           Nested classes/interfaces inherited from class java.awt.Window
           Window.AccessibleAWTWindow, Window.Type
           Nested classes/interfaces inherited from class java.awt.Container
           Container.AccessibleAWTContainer
           Nested classes/interfaces inherited from class java.awt.Component
           Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

        *editar_user	                         Clase para editar un usuario existente.
            Modificador y tipo	                               Clase	                                                      Descripción

                  class 	                  editar_user.LimitarCaracteres	               Clase interna para limitar la cantidad de caracteres permitidos en un campo de texto.
            Nested classes/interfaces inherited from class javax.swing.JFrame
            JFrame.AccessibleJFrame
            Nested classes/interfaces inherited from class java.awt.Frame
            Frame.AccessibleAWTFrame
            Nested classes/interfaces inherited from class java.awt.Window
            Window.AccessibleAWTWindow, Window.Type
            Nested classes/interfaces inherited from class java.awt.Container
            Container.AccessibleAWTContainer
            Nested classes/interfaces inherited from class java.awt.Component
            Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

        *login	                                 Clase que representa la ventana de inicio de sesión de la aplicación.
              Modificador y tipo	                               Clase	                                                      Descripción

                     class 	                          login.LimitadorCaracteres	                   Clase auxiliar para limitar el número de caracteres en un JTextField.
              Nested classes/interfaces inherited from class javax.swing.JFrame
              JFrame.AccessibleJFrame
              Nested classes/interfaces inherited from class java.awt.Frame
              Frame.AccessibleAWTFrame
              Nested classes/interfaces inherited from class java.awt.Window
              Window.AccessibleAWTWindow, Window.Type
              Nested classes/interfaces inherited from class java.awt.Container
              Container.AccessibleAWTContainer
              Nested classes/interfaces inherited from class java.awt.Component
              Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

        *registro	                         Interfaz gráfica para el registro de usuarios.
             Modificador y tipo	                      Clase	                                                      Descripción

                    class 	           registro.LimitDocumentFilter	Clase auxiliar que implementa un filtro para limitar el número de caracteres permitidos en un campo de texto.
             Nested classes/interfaces inherited from class javax.swing.JFrame
             JFrame.AccessibleJFrame
             Nested classes/interfaces inherited from class java.awt.Frame
             Frame.AccessibleAWTFrame
             Nested classes/interfaces inherited from class java.awt.Window
             Window.AccessibleAWTWindow, Window.Type
             Nested classes/interfaces inherited from class java.awt.Container
             Container.AccessibleAWTContainer
             Nested classes/interfaces inherited from class java.awt.Component
             Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy
                   Constructor            	Descripcion
                   registro()	     Constructor de la clase registro.

        *VENTANA_PRINCIPAL	                 Clase principal de la aplicación que representa la ventana principal de la interfaz.
             Nested classes/interfaces inherited from class javax.swing.JFrame
             JFrame.AccessibleJFrame
             Nested classes/interfaces inherited from class java.awt.Frame
             Frame.AccessibleAWTFrame
             Nested classes/interfaces inherited from class java.awt.Window
             Window.AccessibleAWTWindow, Window.Type
             Nested classes/interfaces inherited from class java.awt.Container
             Container.AccessibleAWTContainer
             Nested classes/interfaces inherited from class java.awt.Component
             Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

                                      Constructor	                                                                  Descripción
            VENTANA_PRINCIPAL​(String nombreUsuario, String tipoUsuario)	                                   Constructor de la clase VENTANA_PRINCIPAL.

              Modificador y tipo	                      Clase	                                                      Descripción
                    void	                         cerrarVentanas()                                  Cierra la ventana principal de la aplicación.
                    void	                           crearTarea()	                                   Muestra el formulario para crear una nueva tarea en el panel principal.
 
  -ventanas
        *crear_tarea	                         Clase que representa el panel para la creación de tareas.
            Nested classes/interfaces inherited from class javax.swing.JPanel
            JPanel.AccessibleJPanel
            Nested classes/interfaces inherited from class javax.swing.JComponent
            JComponent.AccessibleJComponent
            Nested classes/interfaces inherited from class java.awt.Container
            Container.AccessibleAWTContainer
            Nested classes/interfaces inherited from class java.awt.Component
            Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

                             Constructor	                                                                  Descripción

           crear_tarea​(String nombreUsuario, String tipoUsuario)	              Constructor que inicializa el panel de creación de tareas.

        *historial	                         Clase que representa un panel para visualizar el historial de tareas.
           Nested classes/interfaces inherited from class javax.swing.JPanel
           JPanel.AccessibleJPanel
           Nested classes/interfaces inherited from class javax.swing.JComponent
           JComponent.AccessibleJComponent
           Nested classes/interfaces inherited from class java.awt.Container
           Container.AccessibleAWTContainer
           Nested classes/interfaces inherited from class java.awt.Component
           Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

            Modificador y tipo	                      Clase	                                                      Descripción

                   void	                       mostrarHistorial()	                          Muestra el historial de tareas en un área de texto.

                           Constructor	                                                                  Descripción
           historial​(String tipoUsuario, String nombreUsuario)	                              Constructor de la clase historial.

        *lista	                                 Panel para gestionar y mostrar una lista de tareas en una tabla.
            Nested classes/interfaces inherited from class javax.swing.JPanel
            JPanel.AccessibleJPanel
            Nested classes/interfaces inherited from class javax.swing.JComponent
            JComponent.AccessibleJComponent
            Nested classes/interfaces inherited from class java.awt.Container
            Container.AccessibleAWTContainer
            Nested classes/interfaces inherited from class java.awt.Component
            Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

                          Constructor	                                                                  Descripción
                lista​(String nombreUsuario, String tipoUsuario)	                                Constructor de la clase lista.

        *lista_user	                         Panel que gestiona y muestra una lista de usuarios en una tabla.
            Nested classes/interfaces inherited from class javax.swing.JPanel
            JPanel.AccessibleJPanel
            Nested classes/interfaces inherited from class javax.swing.JComponent
            JComponent.AccessibleJComponent
            Nested classes/interfaces inherited from class java.awt.Container
            Container.AccessibleAWTContainer
            Nested classes/interfaces inherited from class java.awt.Component
            Component.AccessibleAWTComponent, Component.BaselineResizeBehavior, Component.BltBufferStrategy, Component.FlipBufferStrategy

                                                   Constructor	                                                                  Descripción
                                    lista_user​(String nombreUsuario, String tipoUsuario)	                      Constructor de la clase lista_user.


