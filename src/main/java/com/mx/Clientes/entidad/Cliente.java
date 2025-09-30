package com.mx.Clientes.entidad;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTES2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	private String curp;
	private String nombre;
	private String apellido;
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	private String genero;
	private String ciudad;
	@Column(name = "ESTADOCIVIL")
	private String estadocivil;
	@Column(name = "ID_BANCO")
	private int idBanco;

}
