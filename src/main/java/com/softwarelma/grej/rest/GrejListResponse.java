package com.softwarelma.grej.rest;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.grej.orm.GrejBo;

public class GrejListResponse {

	private List<GrejBo> listBo = new ArrayList<>();
	private String error;

	public List<GrejBo> getListBo() {
		return listBo;
	}

	public void setListBo(List<GrejBo> listBo) {
		this.listBo = listBo;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
