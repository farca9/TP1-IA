package search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import search.util.*;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class SupermercadoAgenteState extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private Point Ubicacion;
    private Point UbicacionAnterior;
    private	HashMap<Producto,Boolean> ListaProductos;
    private ArrayList<ProductoComercio> MatrizProductoComercio;
    private MapUnit[][] Mapa;
	

    public SupermercadoAgenteState() {
    
        this.initState();
    }
    
    public SupermercadoAgenteState(Point UbicacionInicial, HashMap ListaProductos, ArrayList MatrizProductoComercio, MapUnit[][] Mapa) {
        
    	this.Ubicacion = UbicacionInicial;
    	this.UbicacionAnterior = null;
    	this.ListaProductos = ListaProductos;
    	this.MatrizProductoComercio = MatrizProductoComercio;
    	this.Mapa = Mapa;
    	
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
    	SupermercadoAgenteState clonedState = new SupermercadoAgenteState();
    	
    	HashMap<Producto,Boolean> clonHM = new HashMap();
    	for(Map.Entry<Producto,Boolean> entry : ListaProductos.entrySet()) {
    		clonHM.put(entry.getKey().clone() , entry.getValue());
    	}
    	
    	ArrayList<ProductoComercio> clonAL = new ArrayList();
    	for(ProductoComercio pc : MatrizProductoComercio) {
    		clonAL.add(pc.clone());
    	}
    	
    	MapUnit[][] clonMapa = new MapUnit[Mapa.length][Mapa[0].length];  //Se asume que el mapa es cuadrado
    	for(int i = 0; i<Mapa.length-1; i++) {
    		for(int j = 0; j<Mapa[i].length-1; j++) {
    			clonMapa[i][j]=Mapa[i][j].clone();
    		}
    	}
    	
    	clonedState.setUbicacion(new Point(this.getUbicacion().x,this.getUbicacion().y));
    	clonedState.setUbicacionAnterior(new Point(this.getUbicacionAnterior().x,this.getUbicacionAnterior().y));
    	clonedState.setListaProductos(clonHM);
    	clonedState.setMatrizProductoComercio(clonAL);
    	clonedState.setMapa(clonMapa);
    	
    	return clonedState;
		
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
        //TODO: Complete Method
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
	//TODO: Complete Method

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "\n";

        str+="- Ubicacion Actual: ("+ Ubicacion.x+","+Ubicacion.y+")\n";
        str+="- Ubicacion Anterior: ("+UbicacionAnterior.x+","+UbicacionAnterior.y+")\n";
        str+="- Lista Productos: "+ListaProductos.toString()+"\n";
        str+="- MatrizProductoComercio:\n";
        str+="  "+MatrizProductoComercio.toString();
        
        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
   
    	if (!(obj instanceof SupermercadoAgenteState)) {
            return false;
        } else {
        	
        	SupermercadoAgenteState state = (SupermercadoAgenteState) obj;
        	
        	if(Ubicacion.x != state.getUbicacion().x || Ubicacion.y != state.getUbicacion().y) return false;
        	
        	if(UbicacionAnterior.x != state.getUbicacionAnterior().x || UbicacionAnterior.y != state.getUbicacionAnterior().y) return false;
        
        	Set<Entry<Producto, Boolean>> receivedSet = state.ListaProductos.entrySet();
        	Set<Entry<Producto, Boolean>> thisSet = this.ListaProductos.entrySet();
        	if(receivedSet.size()!=thisSet.size()) {
        		return false;
        	} else if (!receivedSet.equals(thisSet)) {
        		return false;
        	}
        	
        	if(!state.getMatrizProductoComercio().equals(this.MatrizProductoComercio)) {
        		return false;
        	}
        	
        	MapUnit[][] receivedMapa = state.getMapa();
        	
        	if(receivedMapa.length != Mapa.length || receivedMapa[0].length != Mapa[0].length) {
        		return false;
        	}
        	
        	for(int i = 0; i<Mapa.length-1; i++) {
        		for(int j = 0; j<Mapa[i].length-1; j++) {
        			if(!receivedMapa[i][j].equals(Mapa[i][j])) {
        				return false;
        			}
        		}
        	}
        	
        	return true;
        }
    }

	public Point getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(Point ubicacion) {
		Ubicacion = ubicacion;
	}

	public Point getUbicacionAnterior() {
		return UbicacionAnterior;
	}

	public void setUbicacionAnterior(Point ubicacionAnterior) {
		UbicacionAnterior = ubicacionAnterior;
	}

	public HashMap<Producto, Boolean> getListaProductos() {
		return ListaProductos;
	}

	public void setListaProductos(HashMap<Producto, Boolean> listaProductos) {
		ListaProductos = listaProductos;
	}

	public ArrayList<ProductoComercio> getMatrizProductoComercio() {
		return MatrizProductoComercio;
	}

	public void setMatrizProductoComercio(ArrayList<ProductoComercio> matrizProductoComercio) {
		MatrizProductoComercio = matrizProductoComercio;
	}

	public MapUnit[][] getMapa() {
		return Mapa;
	}

	public void setMapa(MapUnit[][] mapa) {
		Mapa = mapa;
	}

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     
}
