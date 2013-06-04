

package de.mh4j.examples.qap.model;

import java.util.ArrayList;
import java.util.List;


import de.mh4j.solver.Solution;


/** 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 * **/


public class Qap implements Solution<Qap>{
	
	
	public List<String> solution= new ArrayList<>();;	
	public int costs=0;
	
	public Qap (List<String> solution) {
        
		for (int i=0; i<solution.size(); i++){
			
			this.solution.add(solution.get(i));
			
			
		}
        
         calculateCosts(this.solution);
    }

	public Qap(Qap original) {
        
        this.solution = new ArrayList<>(original.solution);        
        this.costs = original.costs;
    }
	
	
	
	public void calculateCosts (List<String> solution){
		
		costs=0;
		
		int k=0;
		int m;
		
		for(int i=0; i<solution.size(); i++){
			
			for(int j=0; j<Facility.facilities.size(); j++){
				
				if (solution.get(i).equals(Facility.facilities.get(j).name)){
					
					m=0;
					
					for(int l=0; l<solution.size(); l++ ){
						
						for(int o=0; o<Facility.facilities.get(j).facilitiesNames.size(); o++){
							
							if(solution.get(l).equals(Facility.facilities.get(j).facilitiesNames.get(o))){
								
								
								k=Facility.facilities.get(j).facilitiesCosts.get(o);
								
								costs=costs+k*Location.locations.get(i).distances.get(m);
								m++;						
							}
						}	
					}
				}	
			}						
		}
	}
		
	
	
	public int getCosts() {
		
		return costs;
    }	
	
	@Override
    public boolean isBetterThan(Qap otherSolution) {
		
        return otherSolution.getCosts() < costs;
    }
	
	
	
}