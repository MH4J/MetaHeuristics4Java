package de.mh4j.examples.qap.model;

import org.testng.annotations.Test;


import java.util.List;

import java.util.ArrayList;

public class LocationTest {

    @Test
    public void testCreate() {
         
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
    }
    
}