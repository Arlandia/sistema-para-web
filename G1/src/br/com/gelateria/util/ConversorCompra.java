package br.com.gelateria.util;



import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.CompraDaoJpa;


@FacesConverter(value="converterCompra" , forClass=Insumo.class)
public class ConversorCompra implements Converter {
	
	 
	
	 public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }

	 public Object getAsObject(FacesContext context,
	            UIComponent component, String value) {
		 System.err.println("context"+"componente"+component+"value"+value);
	        Compra retorno = null;
	        EntityManager manager = this.getManager();
	       
	            if (value != null) {
	               CompraDao aDao = new CompraDaoJpa(manager);
	               
	               Integer id = Integer.parseInt(value); 
	             retorno =   aDao.pegarIdValido(id);
	             
	             System.err.println("valor retorno"+retorno.getCodigo()+"valor do value="+value);
	             return retorno;
	            }
	        
	            else{
	           System.err.println( "Erro="+retorno.getCodigo()+",valor do value="+value);
	        
            return null;
	            }
	    }
	 

	    @Override
	    public String getAsString(FacesContext context,UIComponent component, Object value) {
	    	System.err.println("metodo= getAsString, context"+" componente="+component+"value="+value);
	    	if (value != null) {
	        	try{
	        	Compra compra = (Compra)value;
	            return compra.getCodigo().toString();
	        	}catch(Exception e){
	        	System.err.println(e.getMessage()+"Deu erro no getAsStrign");
	        	
	        }
	        	  
	        }
	        return null;
	    }
}


