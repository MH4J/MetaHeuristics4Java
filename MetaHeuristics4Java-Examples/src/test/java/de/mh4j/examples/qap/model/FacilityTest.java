package de.mh4j.examples.qap.model;

import org.testng.annotations.Test;


import java.util.List;

import java.util.ArrayList;

public class FacilityTest {

    @Test
    public void testCreate() {
         
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
    }
    
}