package br.com.gelateria.controler.Graficos;

import java.io.Serializable;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@RequestScoped
public class GraficoCompraBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private LineChartModel model;
	private LineChartSeries series1;
	private LineChartSeries series2;
	
   public GraficoCompraBean() {
	model = new LineChartModel(); 
    this.linha1();
    this.linha2();
	model.setTitle("Compras");
	model.setLegendPosition("e");
	Axis yAxis = model.getAxis(AxisType.Y);
	yAxis.setMin(0);
	yAxis.setMax(10);
	}
   
	
	public void linha1(){
		 series1 = new LineChartSeries();
		series1.setLabel("Compras");
		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);
		this.model.addSeries(series1);
	}
 
   private  void linha2(){
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
	       return model;
	}
}
