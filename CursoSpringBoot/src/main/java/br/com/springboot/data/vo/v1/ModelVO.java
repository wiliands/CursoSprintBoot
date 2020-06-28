package br.com.springboot.data.vo.v1;

import java.io.Serializable;

public interface ModelVO<ID> extends Serializable {
	
	public ID getKey();
	
	public void setKey(ID key);

}
