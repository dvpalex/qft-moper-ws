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
	protected static Layout layout;
	protected static LayoutVersion layoutVersion;
	protected List<LayoutVersion> versions;
	
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
	
	public void resetLayout(LayoutVersion layoutVersion)
	{
		layout = new Layout();
		layout.setLayoutVersion(layoutVersion);
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
	public List<Layout> getLayouts() {
		return layouts;
	}
	public LayoutType getLayoutType() {
		return layoutType;
	}
	public LayoutService getLayoutService() {
		return layoutService;
	}
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}
	public LayoutTypeService getLayoutTypeService() {
		return layoutTypeService;
	}
	public void setLayoutTypeService(LayoutTypeService layoutTypeService) {
		this.layoutTypeService = layoutTypeService;
	}
	public LayoutVersionService getLayoutVersionService() {
		return layoutVersionService;
	}
	public void setLayoutVersionService(LayoutVersionService layoutVersionService) {
		this.layoutVersionService = layoutVersionService;
	}
	public RowTypeService getRowTypeService() {
		return rowTypeService;
	}
	public void setRowTypeService(RowTypeService rowTypeService) {
		this.rowTypeService = rowTypeService;
	}
	@SuppressWarnings("static-access")
	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}
	@SuppressWarnings("static-access")
	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}
	@SuppressWarnings("static-access")
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	@SuppressWarnings("static-access")
	public void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}
	public List<LayoutVersion> getVersions() {
		return versions;
	}
	public void setVersions(List<LayoutVersion> versions) {
		this.versions = versions;
	}
}