package com.softwarelma.grej.rest;

import com.softwarelma.grej.orm.GrejBo;

public class GrejResponse {

	private GrejBo bo;
	private String error;

	public GrejBo getBo() {
		return bo;
	}

	public void setBo(GrejBo bo) {
		this.bo = bo;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
