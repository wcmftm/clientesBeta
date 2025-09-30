package com.mx.Clientes.dto;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaModel {

	private long numero;
	private String tipo;
	private double saldo;
	private double adeudo;
	private String status;
	private String curpCliente;
	private int idBanco;

}


