package de.mh4j.examples.qap.solver;






import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;

import de.mh4j.examples.qap.model.Facility;
import de.mh4j.examples.qap.model.Location;

import de.mh4j.examples.qap.solver.SimulatedAnnealingQapSolver;




public class SimulatedAnnealingQapSolverTest {

	@Test
	public void testSolveSimpleInstance() {
		

		String facilityName1="1";
    	String facilityName2="2";
    	String facilityName3="3";
    	String facilityName4="4";
    	
    	List<Integer> cost_list1=new ArrayList<Integer>(); 
    	List<Integer> cost_list2=new ArrayList<Integer>();
    	List<Integer> cost_list3=new ArrayList<Integer>();
    	List<Integer> cost_list4=new ArrayList<Integer>();
    	
    	cost_list1.add(8);
    	cost_list1.add(1);
    	cost_list1.add(5);
    	
    	cost_list2.add(8);
    	cost_list2.add(1);
    	cost_list2.add(1);
    	
    	cost_list3.add(1);
    	cost_list3.add(1);
    	cost_list3.add(2);
    	
    	cost_list4.add(5);
    	cost_list4.add(1);
    	cost_list4.add(2);
    	
    	List<String> names1=new ArrayList<String>(); 
    	List<String> names2=new ArrayList<String>();
    	List<String> names3=new ArrayList<String>();
    	List<String> names4=new ArrayList<String>();
    	
    	names1.add("2");
    	names1.add("3");
    	names1.add("4");
    	
    	names2.add("1");
    	names2.add("3");
    	names2.add("4");
    	
    	names3.add("1");
    	names3.add("2");
    	names3.add("4");
    	
    	names4.add("1");
    	names4.add("2");
    	names4.add("3");
    	



        new Facility(facilityName1, cost_list1, names1);
        new Facility(facilityName2, cost_list2, names2);
        new Facility(facilityName3, cost_list3, names3);
        new Facility(facilityName4, cost_list4, names4);
        
        
        String locationName1="A";
    	String locationName2="B";
    	String locationName3="C";
    	String locationName4="D";
    	
    	List<Integer> distances1=new ArrayList<Integer>(); 
    	List<Integer> distances2=new ArrayList<Integer>();
    	List<Integer> distances3=new ArrayList<Integer>();
    	List<Integer> distances4=new ArrayList<Integer>();
    	
    	distances1.add(20);
    	distances1.add(56);
    	distances1.add(11);
    	
    	distances2.add(20);
    	distances2.add(40);
    	distances2.add(25);
    	
    	distances3.add(56);
    	distances3.add(40);
    	distances3.add(54);
    	
    	distances4.add(11);
    	distances4.add(25);
    	distances4.add(54);
    	
    	List<String> distancesNames1=new ArrayList<String>(); 
    	List<String> distancesNames2=new ArrayList<String>();
    	List<String> distancesNames3=new ArrayList<String>();
    	List<String> distancesNames4=new ArrayList<String>();
    	
    	distancesNames1.add("B");
    	distancesNames1.add("C");
    	distancesNames1.add("D");
    	
    	distancesNames2.add("A");
    	distancesNames2.add("C");
    	distancesNames2.add("D");
    	
    	distancesNames3.add("A");
    	distancesNames3.add("B");
    	distancesNames3.add("D");
    	
    	distancesNames4.add("A");
    	distancesNames4.add("B");
    	distancesNames4.add("C");
    	



        new Location(locationName1, distances1, distancesNames1);
        new Location(locationName2, distances2, distancesNames2);
        new Location(locationName3, distances3, distancesNames3);
        new Location(locationName4, distances4, distancesNames4); 
        
        
        
        
    
    
		
		
		SimulatedAnnealingQapSolver solver=new SimulatedAnnealingQapSolver();
		
		
		
		
		SimulatedAnnealingQapSolver.locs.add("1");
		SimulatedAnnealingQapSolver.locs.add("2");
		SimulatedAnnealingQapSolver.locs.add("4");
		SimulatedAnnealingQapSolver.locs.add("3");
		
		solver.createInitialSolution();
		
		
		solver.setLogLevel(Level.TRACE);
		solver.run();
		
	
	}
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
