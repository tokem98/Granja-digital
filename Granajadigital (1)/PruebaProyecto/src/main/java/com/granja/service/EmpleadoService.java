package main.java.com.granja.service;

import main.java.com.granja.dao.EmpleadoDAO;
import main.java.com.granja.model.Empleado;
import main.java.com.granja.util.Logger;
import java.util.List;

public class EmpleadoService {
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public void registrarEmpleado(Empleado empleado) {
        try {
            empleadoDAO.crear(empleado);
            Logger.log("Empleado registrado: " + empleado.getNombre());
        } catch (Exception e) {
            Logger.error("Error al registrar empleado", e);
            throw new RuntimeException("Error al registrar empleado: " + e.getMessage(), e);
        }
    }

    public List<Empleado> listarEmpleados() {
        try {
            return empleadoDAO.obtenerTodos();
        } catch (Exception e) {
            Logger.error("Error al listar empleados", e);
            throw new RuntimeException("Error al listar empleados: " + e.getMessage(), e);
        }
    }

    public void actualizarEmpleado(Empleado empleado) {
        try {
            empleadoDAO.actualizar(empleado);
            Logger.log("Empleado actualizado: " + empleado.getNombre());
        } catch (Exception e) {
            Logger.error("Error al actualizar empleado", e);
            throw new RuntimeException("Error al actualizar empleado: " + e.getMessage(), e);
        }
    }

    public void eliminarEmpleado(int id) {
        try {
            empleadoDAO.eliminar(id);
            Logger.log("Empleado eliminado: ID " + id);
        } catch (Exception e) {
            Logger.error("Error al eliminar empleado", e);
            throw new RuntimeException("Error al eliminar empleado: " + e.getMessage(), e);
        }
    }

    public Empleado buscarPorId(int id) {
        try {
            return empleadoDAO.obtenerPorId(id);
        } catch (Exception e) {
            Logger.error("Error al buscar empleado por ID: " + id, e);
            throw new RuntimeException("Error al buscar empleado: " + e.getMessage(), e);
        }
    }
}

