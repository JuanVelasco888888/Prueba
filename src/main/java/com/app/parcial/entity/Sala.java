package com.app.parcial.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "salas")
public class Sala {
    @Id
    private String id;
    private String numero;
    private String Tipo_Pantalla;
    private List<Integer> selectedSitio;
    private String selectedSitioStr;
    private List<Integer> asientosOcupados;
    
    public Sala() {
        this.selectedSitio = new ArrayList<>();
        this.asientosOcupados = new ArrayList<>();
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo_Pantalla() {
		return Tipo_Pantalla;
	}
	public void setTipo_Pantalla(String tipo_Pantalla) {
		Tipo_Pantalla = tipo_Pantalla;
	}
	public List<Integer> getSelectedSitio() {
		return selectedSitio;
	}
	public void setSelectedSitio(List<Integer> selectedSitio) {
		this.selectedSitio = selectedSitio;
	}
	public String getSelectedSitioStr() {
		return selectedSitioStr;
	}
	public void setSelectedSitioStr(String selectedSitioStr) {
	    this.selectedSitioStr = selectedSitioStr;
	}
	public List<Integer> getAsientosOcupados() {
		return asientosOcupados;
	}
	public void setAsientosOcupados(List<Integer> asientosOcupados) {
		this.asientosOcupados = asientosOcupados;
	}
    
    
}