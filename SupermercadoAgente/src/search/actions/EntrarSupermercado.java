package search.actions;

import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.SupermercadoAgenteState;
import search.SupermercadoEnvironmentState;
import search.util.Comercio;
import search.util.MapUnit;
import search.util.Producto;
import search.util.ProductoComercio;
import search.util.TipoEnum;

public class EntrarSupermercado extends SearchAction {

	//private static SupermercadoAgenteState state;
	
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	
    	SupermercadoAgenteState agState = (SupermercadoAgenteState) s;
        SupermercadoAgenteState nextState = agState.clone();
        
        //state = (SupermercadoAgenteState) s;
        
        Point ubicacionAgente = agState.getUbicacion();
        Point ubicacionSupermercado = this.getNearbySupermarket(nextState);

        
        if(ubicacionSupermercado != null) {
        	
        	if(verSiTieneParaComprar(ubicacionSupermercado,nextState)) {
        	nextState.setUbicacion(new Point(ubicacionSupermercado.x,ubicacionSupermercado.y));
        	nextState.setUbicacionAnterior(new Point(ubicacionAgente.x,ubicacionAgente.y));
        	//nextState.setCosto(getCost());
        	
        	
        	return nextState;
        	}
        }
        
        
        return null;
    }

	private boolean verSiTieneParaComprar(Point ubicacionSupermercado, SupermercadoAgenteState state) {
		
		
		ArrayList<Producto> productosEnSuper=new ArrayList();
		//obtengo que productos tiene el supermercado
		for(ProductoComercio pc : state.getMatrizProductoComercio()) {
			
			if(pc.getComercio().getUbicacion().equals(ubicacionSupermercado)) {
				productosEnSuper.add(pc.getProducto());
			}
			
		}
		
		for(Producto pS : productosEnSuper) {
			
			/*if(state.getListaProductos().containsKey(p) && !state.getListaProductos().get(p)) {
				return true;
			}*/
			for(Producto pA : state.getListaProductos().keySet()) {
				
				if(pS.equals(pA)) {
					if(!state.getListaProductos().get(pA)) {
						return true;
					}
				}
				
			}
			
		}
		
		
		return false;
	}

	/**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        SupermercadoEnvironmentState environmentState = (SupermercadoEnvironmentState) est;
        SupermercadoAgenteState agState = ((SupermercadoAgenteState) ast);

        //state = (SupermercadoAgenteState) ast;
        
        Point ubicacionAgente = agState.getUbicacion();
        Point ubicacionSupermercado = this.getNearbySupermarket(agState);
        
        if(ubicacionSupermercado != null ) {
        	
        	if(verSiTieneParaComprar(ubicacionSupermercado,agState)) {
        	agState.setUbicacion(new Point(ubicacionSupermercado.x,ubicacionSupermercado.y));
        	agState.setUbicacionAnterior(new Point(ubicacionAgente.x,ubicacionAgente.y));
        	//agState.setCosto(getCost());
        	
    		environmentState.setUbicacionAgente(new Point(ubicacionSupermercado.x,ubicacionSupermercado.y));
            
            return environmentState;
        	}
        }
        
        return null;
                   
        	
        
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost(SearchBasedAgentState s) {
    	SupermercadoAgenteState state = (SupermercadoAgenteState)s;
    	
    	double costo = 0.0;
    	costo +=  state.getMapa()[state.getUbicacion().x][state.getUbicacion().y].calcularCosto();
        return costo;
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "EntrarSupermercado";
    }
    
	private Point getNearbySupermarket(SupermercadoAgenteState s) { //Se asume que los supermercados estan espaciados
			
	    	ArrayList<ProductoComercio> MatrizProductoComercio = s.getMatrizProductoComercio();
	    	for(ProductoComercio pc : MatrizProductoComercio) {
	    		
	    		Comercio c = pc.getComercio();
	    		MapUnit mu = s.getMapa()[c.getUbicacion().x][c.getUbicacion().y];
	    		
	    		if((new Double(c.getUbicacion().distanceSq(s.getUbicacion()))).equals(new Double(1.0))){
	    			if(mu.getTipo().equals(TipoEnum.SUPERMERCADO)) {
	    				return new Point(c.getUbicacion().x,c.getUbicacion().y);
	    			}
	    		}
	    		
	    	}
	    	
			return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}
}
