package search.actions;

import java.awt.Point;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.SupermercadoAgenteState;
import search.SupermercadoEnvironmentState;
import search.util.MapUnit;
import search.util.TipoEnum;

public class MoveRight extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	
	//private static SupermercadoAgenteState state;
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	
    	
    	SupermercadoAgenteState agState = (SupermercadoAgenteState) s;
        SupermercadoAgenteState nextState = agState.clone();
        
        //state = (SupermercadoAgenteState) s;
        
        Point ubicacionAgente = agState.getUbicacion();
        
        
        if(ubicacionAgente.x+1 < agState.getMapa().length) { //No esta en el borde
        	MapUnit unitRight = agState.getMapa()[ubicacionAgente.x+1][ubicacionAgente.y];
        	MapUnit thisUnit = agState.getMapa()[ubicacionAgente.x][ubicacionAgente.y];
        	
        	if(thisUnit.isRight()) {
        		TipoEnum tipo = unitRight.getTipo();
        		
        		if(tipo!=TipoEnum.CALLECORTADA && tipo!=TipoEnum.MANZANA && tipo!=TipoEnum.SUPERMERCADO && tipo!=TipoEnum.SUPERMERCADOCERRADO) {
        			
        			//Se realiza el movimiento y se devuelve el nuevo estado
        			nextState.setUbicacion(new Point(nextState.getUbicacion().x+1, nextState.getUbicacion().y));
        			nextState.setUbicacionAnterior(new Point(agState.getUbicacion().x, agState.getUbicacion().y));
        			//nextState.setCosto(getCost());
        			
        			return nextState;
        		}
        		
        	}
        	
        }
        
        return null;
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
          
        if(ubicacionAgente.x+1 < agState.getMapa().length) { //No esta en el borde
        	MapUnit unitRight = agState.getMapa()[ubicacionAgente.x+1][ubicacionAgente.y];
        	MapUnit thisUnit = agState.getMapa()[ubicacionAgente.x][ubicacionAgente.y];
        	
        	if(thisUnit.isRight()) {
        		TipoEnum tipo = unitRight.getTipo();
        		
        		if(tipo!=TipoEnum.CALLECORTADA && tipo!=TipoEnum.MANZANA && tipo!=TipoEnum.SUPERMERCADO && tipo!=TipoEnum.SUPERMERCADOCERRADO) {	
        			//Se realiza el movimiento y se devuelve el nuevo estado
        			
        			Point nuevaUbicacion = new Point(agState.getUbicacion().x+1, agState.getUbicacion().y);
        			Point ubicacionPrevia = new Point(agState.getUbicacion().x, agState.getUbicacion().y);

        			agState.setUbicacion(new Point(nuevaUbicacion.x, nuevaUbicacion.y));
        			agState.setUbicacionAnterior(new Point(ubicacionPrevia.x, ubicacionPrevia.y));
        			//agState.setCosto(getCost());
        			
        			environmentState.setUbicacionAgente(new Point(nuevaUbicacion.x, nuevaUbicacion.y));
        			return environmentState;
        			
        		}
        		
        	}
        	
        }
        

        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
    	double costo = 0.0;
    	
    	/*TipoEnum tipo = state.getMapa()[state.getUbicacion().x][state.getUbicacion().y].getTipo();
		
		costo +=  state.getMapa()[state.getUbicacion().x][state.getUbicacion().y].getCosto();
				
    		if (tipo == TipoEnum.BACHE) {
    			
    			costo += costo*(0.3);
    			
    		} 
    		else if (tipo == TipoEnum.CONGESTION) {
    			
    			costo += costo*(2);
    			
    		} 
    		else if (tipo == TipoEnum.EVENTO) {
    			
    			costo += costo*(0.8);
    			
    		}
    		*/
    		return costo;
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "MoveRight";
    }
}