package br.com.ninb.moper.model;

public enum TypeColEnum {
	N("Numérico"),
	D("Data"),
	C("Caracter");
	
	private String value;
	
	TypeColEnum(String value) 
	{
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
