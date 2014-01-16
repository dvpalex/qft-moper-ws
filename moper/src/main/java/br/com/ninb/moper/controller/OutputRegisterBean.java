package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;
import br.com.ninb.moper.model.OutputRegister;
import br.com.ninb.moper.model.OutputRowRegister;
import br.com.ninb.moper.service.LayoutTypeService;
import br.com.ninb.moper.service.LayoutVersionService;
import br.com.ninb.moper.service.OutputRegisterService;
import br.com.ninb.moper.service.OutputRowRegisterService;

@ManagedBean
@SessionScoped
@Component
public class OutputRegisterBean 
{
	@Autowired
	protected OutputRegisterService service;	
	@Autowired
	protected OutputRowRegisterService rowService;	
	@Autowired
	protected LayoutTypeService layoutTypeService;
	@Autowired
	protected LayoutVersionService layoutVersionService;
	private List<OutputRegister> listOutputRegister;
	private List<OutputRowRegister> rows;
	private Map<String, Long> types = new HashMap<String, Long>(); 
	private Map<Long,Long> versions = new HashMap<Long,Long>();
	private Date firstDate, lastDate;
	
	@Autowired
	private OutputRegister outputRegister;
	
	private Long outputRegisterId;
	private Long layoutVersionId;
	private Long layoutTypeId;
	
	public String findByFileName()
	{
		push("/pages/private/outputregister/list");
		listOutputRegister = service.listByFileName(outputRegister.getFileName());
		return null;
	}
	
	public void list()
	{
		try{
				push("/pages/private/outputregister/list");
				listOutputRegister = service.listAll();		
				for(LayoutType type : layoutTypeService.list()){
					types.put(type.getDescr(),type.getLayoutTypeId());
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	public String listLayoutVersion()
	{
		try{
			//TODO terminar essa implementação
			Long layoutTypeId = 1l;
				push("/pages/private/outputregister/list");				
				for(LayoutVersion version : layoutVersionService.listByType(layoutTypeId)){
					versions.put(version.getLayoutVersionId(), version.getVersionLayout());
				}
				return null;
		
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public String listRows()
	{
		push("/pages/private/outputregister/list");
		rows = rowService.listByOutptRegister(outputRegister.getOutputRegisterId());		
		return null;
	}	
	
	public OutputRegister getOutputRegister() {
		return outputRegister;
	}

	public List<OutputRegister> getListOutputRegister() {
		return listOutputRegister;
	}

	public List<OutputRowRegister> getRows() {
		return rows;
	}

	public Long getOutputRegisterId() {
		return outputRegisterId;
	}

	public Long getLayoutTypeId() {
		return layoutTypeId;
	}

	public Long getLayoutVersionId() {
		return layoutVersionId;
	}

	public Map<String, Long> getTypes() {
		return types;
	}

	public Map<Long, Long> getVersions() {
		return versions;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public Date getLastDate() {
		return lastDate;
	}
}