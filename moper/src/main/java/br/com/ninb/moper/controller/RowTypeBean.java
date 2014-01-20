package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.ninb.moper.model.RowType;
import br.com.ninb.moper.service.RowTypeService;

@ManagedBean(name="rowTypeBean")
@SessionScoped
@Component
public class RowTypeBean 
{
	private RowType rowType;
	private List<RowType> types;
	@Autowired
	private RowTypeService service;
	
	public RowTypeBean() {
		super();
	}

	public void add()
	{	
		rowType = new RowType();
		push("/pages/private/rowtype/new");
	}
	
	public void save()
	{	
		service.save(rowType);	
		rowType = new RowType();
		list();
	}
	
	public void delete()
	{
		service.delete(rowType);
		list();
	}
	
	public void search()
	{
		types = service.listByDescription(rowType.getDescr());
		push("/pages/private/rowtype/list");
	}
	
	public void list()
	{
		types = service.listAll();
		rowType = new RowType();
		push("/pages/private/rowtype/list");
	}

	public void edit() 
	{ 
		push("/pages/private/rowtype/edit");
	}
	
	public List<RowType> getTypes() {
		return types;
	}

	public void setTypes(List<RowType> types) {
		this.types = types;
	}

	public RowTypeService getService() {
		return service;
	}

	public void setService(RowTypeService service) {
		this.service = service;
	}

	public RowType getRowType() {
		return rowType;
	}

	public void setRowType(RowType rowType) {
		this.rowType = rowType;
	}
}