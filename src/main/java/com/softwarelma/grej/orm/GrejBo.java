package com.softwarelma.grej.orm;

public class GrejBo {

	private Integer id;
	private Integer idg;
	private Integer idp;
	private Integer idc;
	private Integer idu;
	private Integer id1;
	private Integer id2;
	private Integer id3;
	private String dt1;
	private String dt2;
	private String vc1;
	private String vc2;
	private String vc3;
	private String vc4;
	private String vc5;

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

	public Integer getIdu() {
		return idu;
	}

	public void setIdu(Integer idu) {
		this.idu = idu;
	}

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public Integer getId3() {
		return id3;
	}

	public void setId3(Integer id3) {
		this.id3 = id3;
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

	public String getVc1() {
		return vc1;
	}

	public void setVc1(String vc1) {
		this.vc1 = vc1;
	}

	public String getVc2() {
		return vc2;
	}

	public void setVc2(String vc2) {
		this.vc2 = vc2;
	}

	public String getVc3() {
		return vc3;
	}

	public void setVc3(String vc3) {
		this.vc3 = vc3;
	}

	public String getVc4() {
		return vc4;
	}

	public void setVc4(String vc4) {
		this.vc4 = vc4;
	}

	public String getVc5() {
		return vc5;
	}

	public void setVc5(String vc5) {
		this.vc5 = vc5;
	}

}