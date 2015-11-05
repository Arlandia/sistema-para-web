package br.com.gelateria.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class Mensagens {

	public void info(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Dados salvo no Banco com Sucesso!"));
  }
	
	public void erro(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro","Os dados não foram salvo no banco"));
	}
	
	public void infoExclusao(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Dados removidos com sucesso!"));
	}
	
	public void infoInsumo(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Existem insumos para serem Digitados  "));
	}
}
