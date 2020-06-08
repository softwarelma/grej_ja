package com.softwarelma.grej.orm;

public class GrejBo {

	private Integer id;
	private Integer idg;
	private Integer idp;
	private Integer idc;
	private String usr;
	private String dt1;
	private String dt2;
	private String vs1;
	private String vs2;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrejBo other = (GrejBo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdg() {
		return idg;
	}

	public void setIdg(Integer idg) {
		this.idg = idg;
	}

	public Integer getIdp() {
		return idp;
	}

	public void setIdp(Integer idp) {
		this.idp = idp;
	}

	public Integer getIdc() {
		return idc;
	}

	public void setIdc(Integer idc) {
		this.idc = idc;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getDt1() {
		return dt1;
	}

	public void setDt1(String dt1) {
		this.dt1 = dt1;
	}

	public String getDt2() {
		return dt2;
	}

	public void setDt2(String dt2) {
		this.dt2 = dt2;
	}

	public String getVs1() {
		return vs1;
	}

	public void setVs1(String vs1) {
		this.vs1 = vs1;
	}

	public String getVs2() {
		return vs2;
	}

	public void setVs2(String vs2) {
		this.vs2 = vs2;
	}

}