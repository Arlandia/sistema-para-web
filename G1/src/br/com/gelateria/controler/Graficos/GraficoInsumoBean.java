package br.com.gelateria.controler.Graficos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.gelateria.dao.InsumoDao;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.persistence.InsumoDaoJpa;

@ManagedBean
@RequestScoped
public class GraficoInsumoBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private List<Insumo>  listaInsumo;
    	
	public GraficoInsumoBean() {
   
	}

	public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }
	 
	 
	 public List<Insumo> getListaInsumo(){
		  EntityManager manager = this.getManager();
		  InsumoDao iDao = new InsumoDaoJpa(manager);
		  this.listaInsumo = iDao.getAll(Insumo.class);
		  return listaInsumo;
	 }
	 

	 
	 
	 
	 private BarChartModel barModel;
	   
	 
	    @PostConstruct
	    public void init() {
	        createBarModels();
	    }
	 
	    public BarChartModel getBarModel() {
	        return barModel;
	    }
	     
	 
	 
	    private BarChartModel initBarModel() {
	    	EntityManager manager = this.getManager();
			  InsumoDao iDao = new InsumoDaoJpa(manager);
			
	        BarChartModel model = new BarChartModel();
	 
	        ChartSeries insumoChar = new ChartSeries();
			 insumoChar.setLabel("Insumo");
			  this.getListaInsumo();
			  
	       for (Insumo insumo : listaInsumo) {
	          if(insumo!=null){
	        	  insumoChar.set(insumo.getTipoInsumo().getNome(), iDao.qtaDeUmTipoInsumo(insumo.getTipoInsumo().getCodigo()));
	        	 
	          }
		       
		   
		       
		}
	        model.addSeries(insumoChar);
	        return model;
	    }
	     
	    private void createBarModels() {
	        createBarModel();
	        
	    }
	     
	    private void createBarModel() {
	        barModel = initBarModel();
	         
	        barModel.setTitle("Quantidade de Insumo Comprada vs Quantidade Utilizada");
	        barModel.setLegendPosition("ne");
	         
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Insumo");
	         
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Quantidade Comprada");
	        yAxis.setMin(0);
	      
	    }
	 
}
