package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
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
@ViewScoped 
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
	private Map<String,Long> versions = new HashMap<String,Long>();
	private Date firstDate, lastDate;
	@Autowired
	private OutputRegister outputRegister;	
	private StringBuilder messageRows;
	
	private void validarRows()
	{
		if(listOutputRegister.size() == 1){
			rows = new ArrayList<OutputRowRegister>();
			rows = rowService.listByOutptRegister(listOutputRegister.get(0).getOutputRegisterId());
		}else if(listOutputRegister.size() == 0 || listOutputRegister.size() > 1){
			rows = new ArrayList<OutputRowRegister>();
		}
		
		outputRegister = new OutputRegister();
		outputRegister.setLayoutType(new LayoutType());
		outputRegister.setLayoutVersion(new LayoutVersion());
	}
	
	public void findByFileName()
	{
		try{
				push("/pages/private/outputregister/list");		
				if(outputRegister.getFileName() == ""){
					listOutputRegister = service.listAll();
				}else{			
						listOutputRegister = service.listByFileName(outputRegister.getFileName());
				}			
				
				validarRows();
						
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}                                                                
	
	public void find()
	{
		try{
				push("/pages/private/outputregister/list");
				listOutputRegister = service.find(outputRegister, firstDate, lastDate);	
				outputRegister.setLayoutType(new LayoutType());
				outputRegister.setLayoutVersion(new LayoutVersion());
				firstDate = null;
				lastDate = null;  
				validarRows();

		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	public void list()
	{
		try{
				push("/pages/private/outputregister/list");
				listOutputRegister = service.listAll();		
				for(LayoutType type : layoutTypeService.listAll()){
					types.put(type.getDescr(),type.getLayoutTypeId());
				}
				
				versions = new HashMap<String,Long>();
				rows = new ArrayList<OutputRowRegister>();
				outputRegister.setLayoutType(new LayoutType());
				outputRegister.setLayoutVersion(new LayoutVersion());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	public void listLayoutVersion()
	{
		try{
				if(outputRegister.getLayoutType().getLayoutTypeId() == 0){
					versions = new HashMap<String, Long>();
				}else{			
						push("/pages/private/outputregister/list");				
						for(LayoutVersion version : layoutVersionService.listByType(outputRegister.getLayoutType().getLayoutTypeId())){
							versions.put(version.getDescr(),version.getLayoutVersionId());
						}
				}

				outputRegister.setLayoutType(new LayoutType());
				outputRegister.setLayoutVersion(new LayoutVersion());
				
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void listRows()
	{
		rows = new ArrayList<OutputRowRegister>();
		rows = rowService.listByOutptRegister(outputRegister.getOutputRegisterId());
		outputRegister = new OutputRegister();
		outputRegister.setLayoutType(new LayoutType());
		push("/pages/private/outputregister/list");
	}	
	
	
	public void showMessage() 
	{  
		messageRows = new StringBuilder();
		rows = new ArrayList<OutputRowRegister>();
		rows = rowService.listByOutptRegister(outputRegister.getOutputRegisterId());	
		
		for(OutputRowRegister row : rows){
			messageRows.append(row.getRowIndex()+row.getContent()+row.getRowType().getDescr());
		}
		
		outputRegister = new OutputRegister();
		outputRegister.setLayoutType(new LayoutType());			
		RequestContext.getCurrentInstance().openDialog("/pages/private/outputregister/rows"); 
    }

	public List<OutputRegister> getListOutputRegister() {
		return listOutputRegister;
	}

	public List<OutputRowRegister> getRows() {
		return rows;
	}

	public Map<String, Long> getTypes() {
		return types;
	}

	public Map<String, Long> getVersions() {
		return versions;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public OutputRegister getOutputRegister() {
		return outputRegister;
	}
	
	public void setOutputRegister(OutputRegister outputRegister) {
		this.outputRegister = outputRegister;
	}

	public StringBuilder getMessageRows() {
		return messageRows;
	}
}