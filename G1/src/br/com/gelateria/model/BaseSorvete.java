package br.com.gelateria.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity

public class BaseSorvete  implements Serializable{
	
	
	@Id
	@GeneratedValue
	public int codigo;
	public double ligaNeutra;
	public double sabor;
	public double emulsao;
	public double leiteCondesado;
	public double acucar;
	public double leite;
	public double  Chocolate;
	public double volumeUnitario;
	
	
	
	



	public BaseSorvete(){
		
	}
	
	
	
	public int getCodigo() {
		return codigo;
	}
 
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getLigaNeutra() {
		return ligaNeutra;
	}
	public void setLigaNeutra(double ligaNeutra) {
		this.ligaNeutra = ligaNeutra;
	}
	public double getSabor() {
		return sabor;
	}
	public void setSabor(double sabor) {
		this.sabor = sabor;
	}
	public double getEmulsao() {
		return emulsao;
	}
	public void setEmulsao(double emulsao) {
		this.emulsao = emulsao;
	}
	public double getLeiteCondesado() {
		return leiteCondesado;
	}
	public void setLeiteCondesado(double leiteCondesado) {
		this.leiteCondesado = leiteCondesado;
	}
	public double getAcucar() {
		return acucar;
	}
	public void setAcucar(double acucar) {
		this.acucar = acucar;
	}
	public double getLeite() {
		return leite;
	}
	public void setLeite(double leite) {
		this.leite = leite;
	}
	public double getChocolate() {
		return Chocolate;
	}
	public void setChocolate(double chocolate) {
		Chocolate = chocolate;
	}



	
	public double getVolumeUnitario() {
		return volumeUnitario;
	}



	public void setVolumeUnitario(double volumeUnitario) {
		this.volumeUnitario = volumeUnitario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseSorvete other = (BaseSorvete) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
	

}
