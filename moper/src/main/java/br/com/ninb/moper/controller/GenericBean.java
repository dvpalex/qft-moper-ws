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
import br.com.ninb.moper.service.LayoutStatusService;
import br.com.ninb.moper.service.LayoutTypeService;
import br.com.ninb.moper.service.LayoutVersionService;
import br.com.ninb.moper.service.RowTypeService;

public class GenericBean
{
	protected static LayoutType layoutType;
	protected static List<Layout> layouts;
	
	protected Layout layout;
	private List<SelectItem> layoutTypes;
	private List<SelectItem> rowTypes;
	private List<SelectItem> layoutVersions;
	@Autowired
	protected LayoutService layoutService;
	@Autowired
	protected LayoutTypeService layoutTypeService;
	@Autowired
	protected LayoutVersionService layoutVersionService;
	@Autowired
	protected RowTypeService rowTypeService;
	@Autowired
	protected LayoutStatusService layoutStatusService;
	
	private LayoutVersion layoutVersion;
	
	public List<SelectItem> getRowTypes()
	{
		rowTypes = new ArrayList<SelectItem>();
		
		for(RowType type : rowTypeService.listAll()){
			rowTypes.add( new SelectItem(type.getRowTypeId(), type.getDescr()));		
		}
		
		return rowTypes;
	}
	
	public List<SelectItem> getLayoutTypes()
	{
		layoutTypes = new ArrayList<SelectItem>();
		
		for(LayoutType type : layoutTypeService.listAll()){
			layoutTypes.add( new SelectItem(type.getLayoutTypeId(), type.getName()));		
		}
		
		return layoutTypes;
	}
	
	public List<SelectItem> getLayoutVersions()
	{
		layoutVersions = new ArrayList<SelectItem>();
		
		for(LayoutVersion version : layoutVersionService.listByLayoutTypeId(layout.getLayoutVersion().getLayoutType().getLayoutTypeId())){
			layoutVersions.add( new SelectItem(version.getLayoutVersionId(), version.getDescr()));		
		}
		
		return layoutVersions;
	}
	
	public void listLayoutVersions()
	{
		layoutVersions = new ArrayList<SelectItem>();
		
		for(LayoutVersion version : layoutVersionService.listByLayoutTypeId(layout.getLayoutVersion().getLayoutType().getLayoutTypeId())){
			layoutVersions.add( new SelectItem(version.getLayoutVersionId(), version.getDescr()));		
		}
		
		push("/pages/private/layout/list");
	}
	
	public void resetLayout(LayoutType layoutType)
	{
		layout = new Layout();
		layout.setLayoutVersion(new LayoutVersion());
		layout.getLayoutVersion().setLayoutType(layoutType);
		layout.setRowType(new RowType());
	}

	public TypeColEnum[] getColumTypes() {
		return TypeColEnum.values();
	}
	public LayoutService getService() {
		return layoutService;
	}
	public void setService(LayoutService service) {
		this.layoutService = service;
	}
	public LayoutTypeService getServiceType() {
		return layoutTypeService;
	}
	public void setServiceType(LayoutTypeService serviceType) {
		this.layoutTypeService = serviceType;
	}
	public LayoutVersionService getServiceVersion() {
		return layoutVersionService;
	}
	public void setServiceVersion(LayoutVersionService serviceVersion) {
		this.layoutVersionService = serviceVersion;
	}
	public RowTypeService getServiceRowType() {
		return rowTypeService;
	}
	public void setServiceRowType(RowTypeService serviceRowType) {
		this.rowTypeService = serviceRowType;
	}
	public LayoutStatusService getLayoutStatusService() {
		return layoutStatusService;
	}
	public void setLayoutStatusService(LayoutStatusService layoutStatusService) {
		this.layoutStatusService = layoutStatusService;
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
	public LayoutType getLayoutType() {
		return layoutType;
	}
	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}
}