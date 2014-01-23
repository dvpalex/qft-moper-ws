package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;
import br.com.ninb.moper.model.RowType;
import br.com.ninb.moper.model.TypeColEnum;
import br.com.ninb.moper.service.LayoutService;
import br.com.ninb.moper.service.LayoutTypeService;
import br.com.ninb.moper.service.LayoutVersionService;
import br.com.ninb.moper.service.RowTypeService;

public class LayoutGeneric
{
	protected Layout layout;
	protected List<Layout> layouts;
	private List<SelectItem> layoutTypes;
	private List<SelectItem> rowTypes;
	private List<SelectItem> layoutVersions;
	@Autowired
	private LayoutService service;
	@Autowired
	private LayoutTypeService serviceType;
	@Autowired
	private LayoutVersionService serviceVersion;
	@Autowired
	private RowTypeService serviceRowType;
	
	private LayoutVersion layoutVersion;
	
	public List<SelectItem> getRowTypes()
	{
		rowTypes = new ArrayList<SelectItem>();
		
		for(RowType type : serviceRowType.listAll()){
			rowTypes.add( new SelectItem(type.getRowTypeId(), type.getDescr()));		
		}
		
		return rowTypes;
	}
	
	public List<SelectItem> getLayoutTypes()
	{
		layoutTypes = new ArrayList<SelectItem>();
		
		for(LayoutType type : serviceType.listAll()){
			layoutTypes.add( new SelectItem(type.getLayoutTypeId(), type.getName()));		
		}
		
		return layoutTypes;
	}
	
	public List<SelectItem> getLayoutVersions()
	{
		layoutVersions = new ArrayList<SelectItem>();
		
		for(LayoutVersion version : serviceVersion.listByLayoutTypeId(layout.getLayoutVersion().getLayoutType().getLayoutTypeId())){
			layoutVersions.add( new SelectItem(version.getLayoutVersionId(), version.getDescr()));		
		}
		
		return layoutVersions;
	}
	
	public void listLayoutVersions()
	{
		layoutVersions = new ArrayList<SelectItem>();
		
		for(LayoutVersion version : serviceVersion.listByLayoutTypeId(layout.getLayoutVersion().getLayoutType().getLayoutTypeId())){
			layoutVersions.add( new SelectItem(version.getLayoutVersionId(), version.getDescr()));		
		}
		
		push("/pages/private/layout/list");
	}

	public TypeColEnum[] getColumTypes() {
		return TypeColEnum.values();
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

	public LayoutVersionService getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(LayoutVersionService serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public RowTypeService getServiceRowType() {
		return serviceRowType;
	}

	public void setServiceRowType(RowTypeService serviceRowType) {
		this.serviceRowType = serviceRowType;
	}

	public LayoutVersion getLayoutVersion() {
		return layoutVersion;
	}

	public void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	public void setLayoutTypes(List<SelectItem> layoutTypes) {
		this.layoutTypes = layoutTypes;
	}

	public void setRowTypes(List<SelectItem> rowTypes) {
		this.rowTypes = rowTypes;
	}

	public void setLayoutVersions(List<SelectItem> layoutVersions) {
		this.layoutVersions = layoutVersions;
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
}