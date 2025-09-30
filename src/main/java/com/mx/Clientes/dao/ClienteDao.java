package com.mx.Clientes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Clientes.entidad.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, String>{
	List<Cliente> findByIdBanco (int idBanco);

}
