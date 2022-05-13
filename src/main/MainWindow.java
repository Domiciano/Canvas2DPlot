package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainWindow implements Initializable{

    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    
    private ArrayList<Double> ejeX;
    private ArrayList<Double> ejeY;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ejeX = new ArrayList<>();
		ejeY = new ArrayList<>();
		
		for(int i=0 ; i< 10 ; i++) {
			ejeX.add(50.0+10*i);
			ejeY.add(50*Math.random());
		}
		
		double[] resX = getMinMax(ejeX);
		double minX = resX[0];
		double maxX = resX[1];
		
		double[] resY = getMinMax(ejeY);
		double minY = resY[0];
		double maxY = resY[1];
		
		System.out.println(minX+", "+maxX+", "+minY+", "+maxY);
		
		double deltaPX = canvas.getWidth();
		double deltaDias = maxX-minX;
		double pendienteX = deltaPX/deltaDias;
		double interceptoX = pendienteX*minX*(-1);
		
		double deltaPY = -canvas.getHeight();
		double deltaAccidente = maxY-minY;
		double pendienteY = deltaPY/deltaAccidente;
		double interceptoY = pendienteY*maxY*(-1);
		
		
		
		
		
		gc = canvas.getGraphicsContext2D();


		gc.setFill(Color.rgb(240,240,240));
		gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
		
		gc.setFill(Color.BLUE);
		for(int i=0 ; i<ejeX.size() ; i++) {
			gc.fillOval( 
					conversion(pendienteX, interceptoX, ejeX.get(i))-3,
					conversion(pendienteY, interceptoY, ejeY.get(i))-3, 6,6);
		}
		
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(2);
		gc.moveTo( 
				conversion(pendienteX, interceptoX, ejeX.get(0)),
				conversion(pendienteY, interceptoY, ejeY.get(0))
		);
		gc.lineTo(
				conversion(pendienteX, interceptoX, ejeX.get(1)),
				conversion(pendienteY, interceptoY, ejeY.get(1))
				);
		gc.stroke();
		
	}
	
	private double conversion(double m, double b, double input) {
		return m*input+b;
	}

	private double[] getMinMax(ArrayList<Double> arr) {
		ArrayList<Double> aux = new ArrayList<>();
		aux.addAll(arr);
		Collections.sort(aux);
		double min = aux.get(0);
		double max = aux.get(aux.size()-1);
		return new double[] {min, max};
	}

	

}
