package com.mx.Clientes.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mx.Clientes.dao.ClienteDao;
import com.mx.Clientes.dto.CuentaModel;
import com.mx.Clientes.dto.Respuesta;
import com.mx.Clientes.entidad.Cliente;

@Service
public class ClienteService {

	ClienteDao dao;
	public ClienteService(ClienteDao clienteDao) {
		dao = clienteDao;
		restTemplate = rest;
	}
	public Respuesta guardar(Cliente cliente) {
	    Respuesta rs = new Respuesta();
	    try {
	        if (dao.existsById(cliente.getCurp())) {
	            rs.setMensaje("El cliente no se registró porque ya existe");
	            rs.setSuccess(false);
	            rs.setObj(cliente);
	            return rs;
	        }
	        
	        dao.save(cliente);
	        rs.setMensaje("El cliente ha sido agregado exitosamente");
	        rs.setSuccess(true);
	        rs.setObj(cliente);
	        return rs;
	        
	    } catch (Exception e) {
	        rs.setMensaje("Error al guardar el cliente");
	        rs.setSuccess(false);
	        rs.setObj(e.getStackTrace());
	        return rs;
	    }
	}
	public Respuesta eliminar(Cliente cliente) {
	    Respuesta rs = new Respuesta();
	    try {
	        if(dao.existsById(cliente.getCurp())) {
	            List<CuentaModel> cuentas = restTemplate.getForObject("http://localhost:9001/cuentas/buscarPorCurp/"+cliente.getCurp(),List.class);
	            if(cuentas != null) {
	                rs.setMensaje("El cliente no se puede eliminar por que tiene cuentas");
	                rs.setSuccess(false);
	                rs.setObj(cuentas);
	                return rs;
	            }
	            cliente = dao.findById(cliente.getCurp()).orElse(null);
	            rs.setObj(new Cliente(cliente.getCurp(),
	                cliente.getNombre(),
	                cliente.getApellido(),
	                cliente.getFechaNacimiento(),
	                cliente.getGenero(),
	                cliente.getCiudad(),
	                cliente.getEstadocivil(),
	                cliente.getIdBanco()));
	            dao.delete(cliente);
	            rs.setMensaje("El cliente ha sido eliminado");
	            rs.setSuccess(true);
	            return rs;
	        }
	        rs.setMensaje("El cliente que tratas de eliminar no existe");
	        rs.setSuccess(false);
	        rs.setObj(cliente.getCurp());
	        return rs;
	    } catch (Exception e) {
	        rs.setMensaje("Error al eliminar");
	        rs.setSuccess(false);
	        rs.setObj(e.getStackTrace());
	        return rs;
	    }
	}
	public ResponseEntity<Cliente> buscar(String curp){
	    Cliente cliente = dao.findById(curp).orElse(null);
	    if(cliente == null){
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}

	public ResponseEntity<List<Cliente>> buscarPorIdBanco(int idBanco){
	    List<Cliente> clientes = dao.findByIdBanco(idBanco);
	    if(clientes.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
}
