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
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.gelateria.dao.InsumoDao;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.persistence.InsumoDaoJpa;

@ManagedBean
@RequestScoped
public class GraficoQtaInsumoBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;

   
	
	private LineChartModel model;
	private LineChartSeries series1;
	private LineChartSeries series2;
	private List<Insumo>  listaInsumo;
	private LineChartModel insumoModelSerie1;
	private LineChartModel insumoModelSerie2;
    	
	public GraficoQtaInsumoBean() {
		insumoModelSerie1 = new LineChartModel(); 
		    this.linha1();
		  
		    insumoModelSerie1.setTitle("compras");
		    insumoModelSerie1.setZoom(true);
		    insumoModelSerie1.getAxis(AxisType.Y).setLabel("Valores");
		    
   
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
	 


		public void linha1() {
	    	EntityManager manager = this.getManager();
			  InsumoDao iDao = new InsumoDaoJpa(manager);
			
				series1 = new LineChartSeries();
				series1.setLabel("Compras");
			  this.getListaInsumo();
			  
	       for (Insumo insumo : listaInsumo) {
	          if(insumo!=null){
	        	  series1.set(insumo.getTipoInsumo().getNome(), iDao.qtaDeUmTipoInsumo(insumo.getTipoInsumo().getCodigo()));
	        	 
	          }
		       
		   
		       
		}
	       this.insumoModelSerie1.addSeries(series1);
	       
	    }
	     
		public LineChartModel getModelSerie1() {
		       return insumoModelSerie1;
		}
		
	     
	  
	 
}
