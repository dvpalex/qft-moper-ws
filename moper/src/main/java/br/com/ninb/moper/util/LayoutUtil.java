package br.com.ninb.moper.util;

import java.util.ArrayList;
import java.util.List;

import br.com.ninb.moper.model.Layout;

public class LayoutUtil 
{
	public static void main(String args[]){
		
		List<Layout> list = new ArrayList<Layout>();
		
		Layout l1 = new Layout();
		l1.setColName("L1");
		l1.setBeginField(1);
		l1.setEndField(10);
		l1.setLenghtField(9);
		
		Layout l2 = new Layout();
		l2.setColName("L2");
		l2.setBeginField(11);
		l2.setEndField(20);
		l2.setLenghtField(9);
		
		Layout l3 = new Layout();
		l3.setColName("L3");
		l3.setBeginField(21);
		l3.setEndField(30);
		l3.setLenghtField(9);
		
		list.add(l1);
		list.add(l2);
		list.add(l3);
		
		//updateData(list, l1);
		
	}
	
	
	public List<Layout> updateData(List<Layout> layouts, Layout layoutDelete){
		
		try{	
				layouts.remove(layoutDelete);
			
				int begin = layoutDelete.getBeginField();
				
				for(Layout layout : layouts)
				{
					layout.setBeginField(begin);
					layout.setEndField(layout.getBeginField() + layout.getLenghtField());
					begin = layout.getEndField() + 1;
					
					System.out.println(layout.getColName()+" - "+layout.getBeginField() +" - "+layout.getEndField());
				}
			
				return layouts;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return layouts;
		}
	}
}