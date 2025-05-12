package main.java.com.granja.dao;

import main.java.com.granja.model.Empleado;
import main.java.com.granja.util.Logger;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private static final List<Empleado> empleados = new ArrayList<>();
    private static int nextId = 1;

    public void crear(Empleado empleado) {
        empleado.setId(nextId++);
        empleados.add(empleado);
        Logger.log("Empleado creado con ID: " + empleado.getId());
    }

    public Empleado obtenerPorId(int id) {
        return empleados.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    public void actualizar(Empleado empleado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == empleado.getId()) {
                empleados.set(i, empleado);
                Logger.log("Empleado actualizado con ID: " + empleado.getId());
                return;
            }
        }
    }

    public void eliminar(int id) {
        empleados.removeIf(e -> e.getId() == id);
        Logger.log("Empleado eliminado con ID: " + id);
    }
}
