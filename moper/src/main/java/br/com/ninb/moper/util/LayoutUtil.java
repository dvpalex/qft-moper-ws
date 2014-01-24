package br.com.ninb.moper.util;

import java.util.List;
import br.com.ninb.moper.model.Layout;

public class LayoutUtil 
{
	public List<Layout> updateDataByDelete(List<Layout> layouts, Layout layoutDeleted)
	{		
		try{	
				int begin = layoutDeleted.getBeginField();
			
				/* Remover o layout a ser excluido da lista de layouts */
				for(int x=0 ; x < layouts.size() ; x++)
				{
					if(layouts.get(x).getIndexField() == layoutDeleted.getIndexField()){
						layouts.remove(x);
						break;
					}		
				}
						
				/* Organizar os intervalos de begin e end */
				for(Layout layout : layouts)
				{
					if(layout.getIndexField() > layoutDeleted.getIndexField())
					{					
						/* Altera o valor do BeginField */
						layout.setBeginField(begin);
						/* Altera o valor do EndField */
						layout.setEndField(layout.getBeginField() + layout.getLenghtField());
						/* Atualiza o valor do IndexField */
						layout.setIndexField(layout.getIndexField() - 1);
						begin = layout.getEndField() + 1;
					}
				}
			
				return layouts;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return layouts;
		}
	}
	
	public List<Layout> updateDataByEdit(List<Layout> layouts, Layout layoutEdit)
	{		
		try{
				/* Remover o layout a ser excluido da lista de layouts */
				for(int x=0 ; x < layouts.size() ; x++)
				{
					if(layouts.get(x).getIndexField() == layoutEdit.getIndexField())
					{				
						int end = layoutEdit.getEndField() + 1;
						
						for(int y=x+1 ; y < layouts.size() ; y++)
						{
							layouts.get(y).setBeginField(end);
							layouts.get(y).setEndField(end+layouts.get(y).getLenghtField());
							end = layouts.get(y).getEndField() + 1;
						}
						
						break;
					}		
				}
		
				return layouts;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return layouts;
		}
	}
}