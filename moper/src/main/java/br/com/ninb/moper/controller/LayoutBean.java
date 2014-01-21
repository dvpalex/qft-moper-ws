package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.RowType;
import br.com.ninb.moper.model.TypeColEnum;
import br.com.ninb.moper.service.LayoutService;
import br.com.ninb.moper.service.LayoutTypeService;
import br.com.ninb.moper.service.RowTypeService;

@ManagedBean(name="layoutBean")
@SessionScoped
@Component
public class LayoutBean 
{
	private Layout layout;
	private List<Layout> layouts;
	@Autowired
	private LayoutService service;
	@Autowired
	private LayoutTypeService serviceType;
	@Autowired
	private RowTypeService serviceRowType;
	private List<SelectItem> types;
	private List<SelectItem> rowTypes;
	private TypeColEnum[] columTypes;
	
	private List<Layout> listLayoutLogicalAdd = new ArrayList<Layout>();
	private List<Layout> listLayoutLogicalEdit = new ArrayList<Layout>();
	private List<Layout> listLayoutLogicalDel = new ArrayList<Layout>();
	
	public LayoutBean() {
		super();
	}
	
	public List<SelectItem> getRowTypes()
	{
		rowTypes = new ArrayList<SelectItem>();
		
		for(RowType type : serviceRowType.listAll())
		{
			rowTypes.add( new SelectItem(type.getRowTypeId(), type.getDescr()));		
		}
		
		return rowTypes;
	}
	
	public List<SelectItem> getTypes()
	{
		types = new ArrayList<SelectItem>();
		
		for(LayoutType type : serviceType.listAll())
		{
			types.add( new SelectItem(type.getLayoutTypeId(), type.getName()));		
		}
		
		return types;
	}
		
	public void create()
	{	
		layout.setLayoutType(serviceType.selectById(layout.getLayoutType().getLayoutTypeId()));
		layout.setRowType(new RowType());
		columTypes = TypeColEnum.values();		
		push("/pages/private/layout/new");
	}
	
	public void add()
	{	
		for(SelectItem item : types){
			if(item.getValue() == layout.getLayoutType().getLayoutTypeId()){
				layout.getLayoutType().setName(item.getLabel());
				break;
			}
		}
		
		for(SelectItem item : rowTypes){
			if(item.getValue() == layout.getRowType().getRowTypeId()){
				layout.getRowType().setDescr(item.getLabel());
				break;
			}
		}
		
		LayoutType type = layout.getLayoutType();				
		listLayoutLogicalAdd.add(layout);
		layout = new Layout();
		layout.setLayoutType(type);
		layout.setRowType(new RowType());
		push("/pages/private/layout/new");
	}
	
	public void select()
	{
		push("/pages/private/layout/new1");
	}
	
	public void save()
	{	
		service.save(layout);	
		layout = new Layout();
		layout.setLayoutType(new LayoutType());
		layout.setRowType(new RowType());	
		list();
	}
	
	public void saveLogical()
	{			
		for(SelectItem item : types){
			if(item.getValue() == layout.getLayoutType().getLayoutTypeId()){
				layout.getLayoutType().setName(item.getLabel());
				break;
			}
		}
		
		for(SelectItem item : rowTypes){
			if(item.getValue() == layout.getRowType().getRowTypeId()){
				layout.getRowType().setDescr(item.getLabel());
				break;
			}
		}
		
		listLayoutLogicalAdd.add(layout);
		list();
		layouts.addAll(0,listLayoutLogicalAdd);
	}
	
	public void delete()
	{
		service.delete(layout);
		list();
	}
	
	public void deleteLogical()
	{		
		layouts.remove(layout);
	}
	
	public void search()
	{
		if(layout.getLayoutType().getLayoutTypeId() == 0 && layout.getColName() == "" && layout.getRowType().getRowTypeId() == 0){
			list();
		}else{		
				layouts = service.listByLayout(layout);
				push("/pages/private/layout/list");
		}	
	}
	
	public void list()
	{
		layouts = service.listAll();
		layout = new Layout();
		layout.setLayoutType(new LayoutType());
		layout.setRowType(new RowType());	
		push("/pages/private/layout/list");
	}

	public void edit() 
	{ 
		push("/pages/private/layout/edit");
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	public LayoutService getService() {
		return service;
	}

	public void setService(LayoutService service) {
		this.service = service;
	}

	public LayoutTypeService getServiceType() {
		return serviceType;
	}

	public void setServiceType(LayoutTypeService serviceType) {
		this.serviceType = serviceType;
	}

	public TypeColEnum[] getColumTypes() {
		return columTypes;
	}

	public void setColumTypes(TypeColEnum[] columTypes) {
		this.columTypes = columTypes;
	}

	public RowTypeService getServiceRowType() {
		return serviceRowType;
	}

	public void setServiceRowType(RowTypeService serviceRowType) {
		this.serviceRowType = serviceRowType;
	}

	public List<Layout> getListLayoutLogicalAdd() {
		return listLayoutLogicalAdd;
	}

	public void setListLayoutLogicalAdd(List<Layout> listLayoutLogicalAdd) {
		this.listLayoutLogicalAdd = listLayoutLogicalAdd;
	}

	public List<Layout> getListLayoutLogicalEdit() {
		return listLayoutLogicalEdit;
	}

	public void setListLayoutLogicalEdit(List<Layout> listLayoutLogicalEdit) {
		this.listLayoutLogicalEdit = listLayoutLogicalEdit;
	}

	public List<Layout> getListLayoutLogicalDel() {
		return listLayoutLogicalDel;
	}

	public void setListLayoutLogicalDel(List<Layout> listLayoutLogicalDel) {
		this.listLayoutLogicalDel = listLayoutLogicalDel;
	}
}