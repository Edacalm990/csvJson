/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csvJson;

import java.time.LocalDate;

/**
 *
 * @author eli
 */
public class Datos {
    private String nombreEmpleado;
    private String DNI_Pasaporte;
    private String puesto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String telefono;
    private boolean evaluador;
    private boolean coordinador;

    public Datos() {
    }

    
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getDNI_Pasaporte() {
        return DNI_Pasaporte;
    }

    public void setDNI_Pasaporte(String DNI_Pasaporte) {
        this.DNI_Pasaporte = DNI_Pasaporte;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEvaluador() {
        return evaluador;
    }

    public void setEvaluador(boolean evaluador) {
        this.evaluador = evaluador;
    }

    public boolean isCoordinador() {
        return coordinador;
    }

    public void setCoordinador(boolean coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public String toString() {
        return "Datos{" + "nombreEmpleado=" + nombreEmpleado + ", DNI_Pasaporte=" + DNI_Pasaporte + ", puesto=" + puesto + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", telefono=" + telefono + ", evaluador=" + evaluador + ", coordinador=" + coordinador + '}';
    }
    
    
}
