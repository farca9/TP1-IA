package search;

import java.awt.Point;

import frsf.cidisi.faia.state.EnvironmentState;
import search.util.MapUnit;

public class SupermercadoEnvironmentState extends EnvironmentState {
	
	//TODO: Setup Variables - Definir bien en funcion de percepciones
    private MapUnit[][] Mapa;
    private Point UbicacionAgente;
	
    public SupermercadoEnvironmentState() {
        
        this.initState();
    }
    
    public SupermercadoEnvironmentState(MapUnit[][] mapa, Point ubicacionAgente) {
    	this.Mapa = mapa;
    	this.UbicacionAgente = ubicacionAgente;
    	this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        //TODO: Complete Method
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
    
	

}

