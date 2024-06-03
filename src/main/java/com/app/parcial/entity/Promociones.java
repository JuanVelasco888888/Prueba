package com.app.parcial.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "promociones")
public class Promociones {
    private int Precios;
    private int Descuentos;
    private String Ofertas;
    private String Palomitas;
    private String Adicionales;
    private String Tamaño;
    private String Bebidas;
    
	public int getPrecios() {
		return Precios;
	}
	public void setPrecios(int precios) {
		Precios = precios;
	}
	public int getDescuentos() {
		return Descuentos;
	}
	public void setDescuentos(int descuentos) {
		Descuentos = descuentos;
	}
	public String getOfertas() {
		return Ofertas;
	}
	public void setOfertas(String ofertas) {
		Ofertas = ofertas;
	}
	public String getPalomitas() {
		return Palomitas;
	}
	public void setPalomitas(String palomitas) {
		Palomitas = palomitas;
	}
	public String getAdicionales() {
		return Adicionales;
	}
	public void setAdicionales(String adicionales) {
		Adicionales = adicionales;
	}
	public String getTamaño() {
		return Tamaño;
	}
	public void setTamaño(String tamaño) {
		Tamaño = tamaño;
	}
	public String getBebidas() {
		return Bebidas;
	}
	public void setBebidas(String bebidas) {
		Bebidas = bebidas;
	}
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
}
