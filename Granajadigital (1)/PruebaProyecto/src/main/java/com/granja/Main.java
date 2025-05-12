package main.java.com.granja;

import main.java.com.granja.util.Logger;
import main.java.com.granja.model.Animal;
import main.java.com.granja.service.AnimalService;
import main.java.com.granja.model.Animal.EstadoSalud;
import main.java.com.granja.model.Empleado;
import main.java.com.granja.model.Empleado.Rol;
import main.java.com.granja.service.EmpleadoService;
import main.java.com.granja.service.ActividadService;
import main.java.com.granja.model.Actividad;
import main.java.com.granja.model.Actividad.TipoActividad;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AnimalService animalService = new AnimalService();
    private static final EmpleadoService empleadoService = new EmpleadoService();
    private static final ActividadService actividadService = new ActividadService();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        try {
            Logger.log("Iniciando aplicación");
            while (true) {
                mostrarMenu();
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1 -> gestionAnimales();
                    case 2 -> gestionEmpleados();
                    case 3 -> gestionActividades();
                    case 4 -> {
                        Logger.log("Aplicación finalizada");
                        return;
                    }
                    default -> System.out.println("Opción no válida");
                }
            }
        } catch (Exception e) {
            Logger.error("Error general en la aplicación", e);
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            Logger.log("Cerrando aplicación");
            scanner.close();
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE GRANJA ===");
        System.out.println("1. Gestión de Animales");
        System.out.println("2. Gestión de Empleados");
        System.out.println("3. Gestión de Actividades");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void gestionAnimales() {
        while (true) {
            try {
                System.out.println("\n=== GESTIÓN DE ANIMALES ===");
                System.out.println("1. Registrar nuevo animal");
                System.out.println("2. Editar animal");
                System.out.println("3. Eliminar animal");
                System.out.println("4. Listar animales");
                System.out.println("5. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1 -> registrarAnimal();
                    case 2 -> editarAnimal();
                    case 3 -> eliminarAnimal();
                    case 4 -> listarAnimales();
                    case 5 -> { return; }
                    default -> System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido");
                Logger.error("Error en ingreso de opción en gestión de animales", e);
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                Logger.error("Error inesperado en gestión de animales", e);
            }
        }
    }

    private static void gestionEmpleados() {
        while (true) {
            try {
                System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
                System.out.println("1. Registrar nuevo empleado");
                System.out.println("2. Editar empleado");
                System.out.println("3. Eliminar empleado");
                System.out.println("4. Listar empleados");
                System.out.println("5. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1 -> registrarEmpleado();
                    case 2 -> editarEmpleado();
                    case 3 -> eliminarEmpleado();
                    case 4 -> listarEmpleados();
                    case 5 -> { return; }
                    default -> System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido");
                Logger.error("Error en ingreso de opción en gestión de empleados", e);
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                Logger.error("Error inesperado en gestión de empleados", e);
            }
        }
    }

    private static void gestionActividades() {
        while (true) {
            try {
                System.out.println("\n=== GESTIÓN DE ACTIVIDADES ===");
                System.out.println("1. Registrar nueva actividad");
                System.out.println("2. Editar actividad");
                System.out.println("3. Eliminar actividad");
                System.out.println("4. Listar actividades");
                System.out.println("5. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1 -> registrarActividad();
                    case 2 -> editarActividad();
                    case 3 -> eliminarActividad();
                    case 4 -> listarActividades();
                    case 5 -> { return; }
                    default -> System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido");
                Logger.error("Error en ingreso de opción en gestión de actividades", e);
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                Logger.error("Error inesperado en gestión de actividades", e);
            }
        }
    }

    private static void registrarAnimal() {
        try {
            System.out.println("\n=== REGISTRAR NUEVO ANIMAL ===");
            
            System.out.print("Identificador (ej. BOV-001): ");
            String identificador = scanner.nextLine();
            
            System.out.print("Especie: ");
            String especie = scanner.nextLine();
            
            System.out.print("Raza: ");
            String raza = scanner.nextLine();
            
            System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
            LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
            
            System.out.println("Estado de salud (1-EXCELENTE, 2-BUENO, 3-REGULAR, 4-MALO, 5-CRITICO): ");
            EstadoSalud estadoSalud = EstadoSalud.values()[Integer.parseInt(scanner.nextLine()) - 1];
            
            System.out.print("Ubicación: ");
            String ubicacion = scanner.nextLine();
            
            Animal animal = new Animal(identificador, especie, raza, fechaNacimiento, estadoSalud, ubicacion);
            animalService.registrarAnimal(animal);
            
            System.out.println("Animal registrado con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al registrar animal: " + e.getMessage());
            Logger.error("Error en registro de animal", e);
        }
    }

    private static void listarAnimales() {
        try {
            List<Animal> animales = animalService.listarAnimales();
            if (animales.isEmpty()) {
                System.out.println("No hay animales registrados");
                return;
            }
            
            System.out.println("\n=== LISTA DE ANIMALES ===");
            System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s%n", 
                "ID", "IDENTIFICADOR", "ESPECIE", "RAZA", "ESTADO SALUD", "UBICACIÓN");
            
            for (Animal animal : animales) {
                System.out.printf("%-10d %-15s %-15s %-15s %-15s %-15s%n",
                    animal.getId(),
                    animal.getIdentificador(),
                    animal.getEspecie(),
                    animal.getRaza(),
                    animal.getEstadoSalud(),
                    animal.getUbicacion());
            }
        } catch (Exception e) {
            System.out.println("Error al listar animales: " + e.getMessage());
            Logger.error("Error en listado de animales", e);
        }
    }

    private static void editarAnimal() {
        try {
            listarAnimales();
            System.out.print("\nIngrese el ID del animal a editar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Animal animal = animalService.buscarPorId(id);
            if (animal == null) {
                System.out.println("Animal no encontrado");
                return;
            }

            System.out.println("Ingrese los nuevos datos (Enter para mantener el valor actual):");
            
            System.out.print("Ubicación [" + animal.getUbicacion() + "]: ");
            String ubicacion = scanner.nextLine();
            if (!ubicacion.isEmpty()) {
                animal.setUbicacion(ubicacion);
            }
            
            System.out.println("Estado de salud actual [" + animal.getEstadoSalud() + "]");
            System.out.println("Nueva salud (1-EXCELENTE, 2-BUENO, 3-REGULAR, 4-MALO, 5-CRITICO, Enter para mantener): ");
            String saludStr = scanner.nextLine();
            if (!saludStr.isEmpty()) {
                animal.setEstadoSalud(EstadoSalud.values()[Integer.parseInt(saludStr) - 1]);
            }
            
            animalService.actualizarAnimal(animal);
            System.out.println("Animal actualizado con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al editar animal: " + e.getMessage());
            Logger.error("Error en edición de animal", e);
        }
    }

    private static void eliminarAnimal() {
        try {
            listarAnimales();
            System.out.print("\nIngrese el ID del animal a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("¿Está seguro de eliminar el animal? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                animalService.eliminarAnimal(id);
                System.out.println("Animal eliminado con éxito!");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar animal: " + e.getMessage());
            Logger.error("Error en eliminación de animal", e);
        }
    }

    private static void registrarEmpleado() {
        try {
            System.out.println("\n=== REGISTRAR NUEVO EMPLEADO ===");
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.println("Rol (1-VETERINARIO, 2-PEON, 3-ENCARGADO): ");
            Rol rol = Rol.values()[Integer.parseInt(scanner.nextLine()) - 1];
            
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            
            System.out.print("Fecha de contratación (dd/MM/yyyy): ");
            LocalDate fechaContratacion = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
            
            Empleado empleado = new Empleado(nombre, rol, telefono, fechaContratacion);
            empleadoService.registrarEmpleado(empleado);
            
            System.out.println("Empleado registrado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al registrar empleado: " + e.getMessage());
            Logger.error("Error en registro de empleado", e);
        }
    }

    private static void listarEmpleados() {
        try {
            List<Empleado> empleados = empleadoService.listarEmpleados();
            if (empleados.isEmpty()) {
                System.out.println("No hay empleados registrados");
                return;
            }
            
            System.out.println("\n=== LISTA DE EMPLEADOS ===");
            System.out.printf("%-5s %-20s %-15s %-15s %-15s%n", 
                "ID", "NOMBRE", "ROL", "TELÉFONO", "CONTRATACIÓN");
            
            for (Empleado empleado : empleados) {
                System.out.printf("%-5d %-20s %-15s %-15s %-15s%n",
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getRol(),
                    empleado.getTelefono(),
                    empleado.getFechaContratacion().format(DATE_FORMATTER));
            }
        } catch (Exception e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
            Logger.error("Error en listado de empleados", e);
        }
    }

    private static void editarEmpleado() {
        try {
            listarEmpleados();
            System.out.print("\nIngrese el ID del empleado a editar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Empleado empleado = empleadoService.buscarPorId(id);
            if (empleado == null) {
                System.out.println("Empleado no encontrado");
                return;
            }

            System.out.println("Ingrese los nuevos datos (Enter para mantener el valor actual):");
            
            System.out.print("Nombre [" + empleado.getNombre() + "]: ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                empleado.setNombre(nombre);
            }
            
            System.out.print("Teléfono [" + empleado.getTelefono() + "]: ");
            String telefono = scanner.nextLine();
            if (!telefono.isEmpty()) {
                empleado.setTelefono(telefono);
            }
            
            System.out.println("Rol actual [" + empleado.getRol() + "]");
            System.out.println("Nuevo rol (1-VETERINARIO, 2-PEON, 3-ENCARGADO, Enter para mantener): ");
            String rolStr = scanner.nextLine();
            if (!rolStr.isEmpty()) {
                empleado.setRol(Rol.values()[Integer.parseInt(rolStr) - 1]);
            }
            
            empleadoService.actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al editar empleado: " + e.getMessage());
            Logger.error("Error en edición de empleado", e);
        }
    }

    private static void eliminarEmpleado() {
        try {
            listarEmpleados();
            System.out.print("\nIngrese el ID del empleado a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("¿Está seguro de eliminar el empleado? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                empleadoService.eliminarEmpleado(id);
                System.out.println("Empleado eliminado con éxito!");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            Logger.error("Error en eliminación de empleado", e);
        }
    }

    private static void registrarActividad() {
        try {
            System.out.println("\n=== REGISTRAR NUEVA ACTIVIDAD ===");
            
            System.out.println("Tipo de actividad:");
            System.out.println("1-ORDEÑE");
            System.out.println("2-ALIMENTACIÓN");
            System.out.println("3-VACUNACIÓN");
            System.out.println("4-LIMPIEZA");
            TipoActividad tipoActividad = TipoActividad.values()[Integer.parseInt(scanner.nextLine()) - 1];
            
            System.out.print("Fecha (dd/MM/yyyy): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
            
            System.out.print("Hora (HH:mm): ");
            LocalTime hora = LocalTime.parse(scanner.nextLine());
            
            listarEmpleados();
            System.out.print("ID del empleado responsable: ");
            int idEmpleado = Integer.parseInt(scanner.nextLine());
            Empleado empleado = empleadoService.buscarPorId(idEmpleado);
            if (empleado == null) {
                throw new RuntimeException("Empleado no encontrado");
            }
            
            Actividad actividad = new Actividad(tipoActividad, fecha, hora, empleado);
            
            System.out.print("¿Involucra animales? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                listarAnimales();
                System.out.print("IDs de los animales (separados por comas): ");
                String[] ids = scanner.nextLine().split(",");
                List<Animal> animales = new ArrayList<>();
                for (String id : ids) {
                    Animal animal = animalService.buscarPorId(Integer.parseInt(id.trim()));
                    if (animal != null) {
                        animales.add(animal);
                    }
                }
                actividad.setAnimalesInvolucrados(animales);
            }
            
            actividadService.registrarActividad(actividad);
            System.out.println("Actividad registrada con éxito!");
            
        } catch (Exception e) {
            System.out.println("Error al registrar actividad: " + e.getMessage());
            Logger.error("Error en registro de actividad", e);
        }
    }

    private static void editarActividad() {
        try {
            listarActividades();
            System.out.print("\nIngrese el ID de la actividad a editar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Actividad actividad = actividadService.buscarPorId(id);
            if (actividad == null) {
                System.out.println("Actividad no encontrada");
                return;
            }

            System.out.println("Ingrese los nuevos datos (Enter para mantener el valor actual):");
            
            System.out.print("Fecha [" + actividad.getFecha() + "] (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine();
            if (!fechaStr.isEmpty()) {
                actividad.setFecha(LocalDate.parse(fechaStr, DATE_FORMATTER));
            }
            
            System.out.print("Hora [" + actividad.getHora() + "] (HH:mm): ");
            String horaStr = scanner.nextLine();
            if (!horaStr.isEmpty()) {
                actividad.setHora(LocalTime.parse(horaStr));
            }
            
            listarEmpleados();
            System.out.print("ID del empleado responsable [" + actividad.getEmpleadoResponsable().getId() + "]: ");
            String idEmpleadoStr = scanner.nextLine();
            if (!idEmpleadoStr.isEmpty()) {
                Empleado empleado = empleadoService.buscarPorId(Integer.parseInt(idEmpleadoStr));
                if (empleado != null) {
                    actividad.setEmpleadoResponsable(empleado);
                }
            }
            
            List<Animal> animales = new ArrayList<>();
            System.out.print("¿Actualizar animales involucrados? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                listarAnimales();
                System.out.print("IDs de los animales (separados por comas): ");
                String[] ids = scanner.nextLine().split(",");
                for (String idAnimal : ids) {
                    Animal animal = animalService.buscarPorId(Integer.parseInt(idAnimal.trim()));
                    if (animal != null) {
                        animales.add(animal);
                    }
                }
                actividad.setAnimalesInvolucrados(animales);
            }
            
            actividadService.actualizarActividad(actividad);
            System.out.println("Actividad actualizada con éxito!");
        } catch (Exception e) {
            System.out.println("Error al editar actividad: " + e.getMessage());
            Logger.error("Error en edición de actividad", e);
        }
    }

    private static void eliminarActividad() {
        try {
            listarActividades();
            System.out.print("\nIngrese el ID de la actividad a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("¿Está seguro de eliminar la actividad? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                actividadService.eliminarActividad(id);
                System.out.println("Actividad eliminada con éxito!");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar actividad: " + e.getMessage());
            Logger.error("Error en eliminación de actividad", e);
        }
    }

    private static void listarActividades() {
        try {
            List<Actividad> actividades = actividadService.listarActividades();
            if (actividades.isEmpty()) {
                System.out.println("No hay actividades registradas");
                return;
            }
            
            System.out.println("\n=== LISTA DE ACTIVIDADES ===");
            System.out.printf("%-5s %-15s %-12s %-8s %-20s %-30s%n", 
                "ID", "TIPO", "FECHA", "HORA", "RESPONSABLE", "ANIMALES");
            
            for (Actividad actividad : actividades) {
                String animalesStr = actividad.getAnimalesInvolucrados() == null ? "N/A" : 
                    actividad.getAnimalesInvolucrados().stream()
                        .map(a -> a.getIdentificador())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                
                System.out.printf("%-5d %-15s %-12s %-8s %-20s %-30s%n",
                    actividad.getId(),
                    actividad.getTipoActividad(),
                    actividad.getFecha().format(DATE_FORMATTER),
                    actividad.getHora(),
                    actividad.getEmpleadoResponsable().getNombre(),
                    animalesStr);
            }
        } catch (Exception e) {
            System.out.println("Error al listar actividades: " + e.getMessage());
            Logger.error("Error en listado de actividades", e);
        }
    }
}