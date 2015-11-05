package br.com.gelateria.controler.Graficos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.model.Compra;
import br.com.gelateria.persistence.CompraDaoJpa;

@ManagedBean
@RequestScoped
public class GraficoCompraMesBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private LineChartModel model;
	private LineChartSeries series1;
	private LineChartSeries series2;
	private List<Compra> listaCompra;
	private LineChartModel dateModel;
	
   public GraficoCompraMesBean() {   
	   dateModel = new LineChartModel(); 
    this.linha1();
  
    dateModel.setTitle("compras");
    dateModel.setZoom(true);
    dateModel.getAxis(AxisType.Y).setLabel("Valores");
    DateAxis axis = new DateAxis("Datas");
    axis.setTickAngle(-50);
    /*axis.setMax("2015-12-31");*/
    axis.setTickFormat("%b %#d, %y");
    dateModel.getAxes().put(AxisType.X, axis);
    }
	
   
	 public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }
	
   public List<Compra> getListaCompra(){
   	if (this.listaCompra == null) {	
   		  EntityManager manager = this.getManager();
		     CompraDao cDao = new CompraDaoJpa(manager);
	    this.listaCompra=  cDao.CompraOrdenada();
   	}
  
   	return listaCompra;
   }

   
	public void linha1(){
		series1 = new LineChartSeries();
		series1.setLabel("Compras");
		this.getListaCompra();
		
		
		for(Compra a : listaCompra){
			SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");  
			  
			  String  result = null;
			try {
				 result = in.format(in.parse(a.getDataCupom().toString()));
				 System.err.println(result);
			} catch (ParseException e) {
				
				e.printStackTrace();
			} 
			series1.set(result,a.getItens());
		}

		this.dateModel.addSeries(series1);
	}
 
   private void linha2(){
	   series2 = new LineChartSeries();
		series2.setLabel("Vendas");
		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);
		this.model.addSeries(series2);
   }
	
	public LineChartModel getModel() {
	       return dateModel;
	}
	
	
	
}
