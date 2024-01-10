package com.adbansys.generadorBitacora.xlsx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bitacora_adbansys")

public class recordEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String id_del_proyecto;

	private String nombre_del_proyecto;
	
	private String nombre_del_cliente;
	
	private String nombre_del_consultor;
	
	@Column (unique = true)
	private LocalDate fecha;
	
	private int horas_regulares;
	
	private int horas_extra;
	
	@Transient
	private int horas_en_total;
	
	private String actividades;
	
	private String entregables;
	
	private boolean completado;
	
	
	public recordEmployee() {
	}
	
	
	public List<Object> getAttribute() {
		List<Object> attributes = new ArrayList<>();
		attributes.add(this.id);
		attributes.add(this.id_del_proyecto);
		attributes.add(this.nombre_del_proyecto);
		attributes.add(this.nombre_del_cliente);
		attributes.add(this.nombre_del_consultor);
		attributes.add(this.fecha);
		attributes.add(this.horas_regulares);
		attributes.add(this.horas_extra);
		attributes.add(this.horas_regulares + this.horas_extra);
		attributes.add(this.actividades);
		attributes.add(this.entregables);
		attributes.add(this.completado);
		
		return attributes;
	}
	
	
	public Long getId() {
		return id;
	}
	public String getIdProyecto() {
		return id_del_proyecto;
	}
	public String getNombreProyecto() {
		return nombre_del_proyecto;
	}
	public String getNombreCliente() {
		return nombre_del_cliente;
	}
	public String getNombreConsultor() {
		return nombre_del_consultor;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public int getHrsRegulares() {
		return horas_regulares;
	}
	public int getHrsExtra() {
		return horas_extra;
	}
	public int getHrsTotal() {
		return (this.horas_regulares + this.horas_extra);
	}
	public String getActividades() {
		return actividades;
	}
	public String getEntregables() {
		return entregables;
	}
	public boolean getCompletado() {
		return completado;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setIdProyecto(String idProyecto) {
		this.id_del_proyecto = idProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombre_del_proyecto = nombreProyecto;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombre_del_cliente = nombreCliente;
	}
	public void setNombreConsultor(String nombreConsultor) {
		this.nombre_del_consultor = nombreConsultor;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public void setHrsRegulares(int hrsRegulares) {
		this.horas_regulares = hrsRegulares;
	}
	public void setHrsExtra(int hrsExtra) {
		this.horas_extra = hrsExtra;
	}
	public void setHrsTotal(int hrsTotal) {
		this.horas_en_total = hrsTotal;
	}
	public void setActividades(String actividades) {
		this.actividades = actividades;
	}
	public void setEntregables(String entregables) {
		this.entregables = entregables;
	}
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	public void setRecordEmployee(String idProyecto, String nombreProyecto, String nombreCliente, String nombreConsultor, LocalDate fecha, int hrsRegulares, int hrsExtra, String actividades, String entregables, boolean completado) {
		this.id_del_proyecto = idProyecto;
		this.nombre_del_proyecto = nombreProyecto;
		this.nombre_del_cliente = nombreCliente;
		this.nombre_del_consultor = nombreConsultor;
		this.fecha = fecha;
		this.horas_regulares = hrsRegulares;
		this.horas_extra = hrsExtra;
		this.horas_en_total = hrsRegulares + hrsExtra;
		this.actividades = actividades;
		this.entregables = entregables;
		this.completado = completado;
	}
	
	public recordEmployee(
			Long id, 
			String idProyecto, 
			String nombreProyecto,  
			String nombreCliente, 
			String nombreConsultor, 
			LocalDate fecha, 
			int hrsRegulares, 
			int hrsExtra, 
			String actividades, 
			String entregables, 
			boolean completado
	) {
		super();
		this.id = id;
		this.id_del_proyecto = idProyecto;
		this.nombre_del_proyecto = nombreProyecto;
		this.nombre_del_cliente = nombreCliente;
		this.nombre_del_consultor = nombreConsultor;
		this.fecha = fecha;
		this.horas_regulares = hrsRegulares;
		this.horas_extra = hrsExtra;
		this.actividades = actividades;
		this.entregables = entregables;
		this.completado = completado;
	}
}
