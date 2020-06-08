package com.softwarelma.grej.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_datasource;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_select;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_update;
import com.softwarelma.grej.rest.GrejListResponse;
import com.softwarelma.grej.rest.GrejResponse;

public class GrejDao {

	private static final Logger logger = Logger.getLogger(GrejDao.class.getName());
	private static final String format = "yyyy-MM-dd HH:mm:ss";
	private DataSource dataSource;

	public GrejDao() {
		try {
			this.dataSource = EpeDbFinalDb_datasource.retrieveOrCreateDataSource("jdbc:mysql://localhost:3306/grej?serverTimezone=UTC", "grej", "ncqgà1ePáB");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	// PUBLIC METHODS

	public GrejListResponse getAll() {
		GrejListResponse response = new GrejListResponse();
		try {
			logger.log(Level.INFO, "getAll - begin");
			String select = "select * from a";
			response.getListBo().addAll(this.readQueryAsBo(select));
			logger.log(Level.INFO, "getAll - " + response.getListBo().size() + " bo found");
			logger.log(Level.INFO, "getAll - end");
			return response;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setError(e.getMessage());
			return response;
		}
	}

	public GrejResponse get(Integer id) {
		GrejResponse response = new GrejResponse();
		try {
			logger.log(Level.INFO, "get - begin");
			String select = "select * from a where id = " + id;
			GrejBo bo = this.readQueryAsBoOne(select);
			response.setBo(bo);
			logger.log(Level.INFO, "get - found with id " + id);
			logger.log(Level.INFO, "get - end");
			return response;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setError(e.getMessage());
			return response;
		}
	}

	public GrejResponse postNew(GrejBo bo) {
		GrejResponse response = new GrejResponse();
		try {
			logger.log(Level.INFO, "postNew - begin");
			StringBuilder insert = new StringBuilder("insert into a (idg, idp, idc, usr, vs1, vs2) values (");
			insert.append(bo.getIdg());
			insert.append(bo.getIdp());
			insert.append(bo.getIdc());
			insert.append(bo.getUsr());
			insert.append(bo.getVs1());
			insert.append(bo.getVs2());
			insert.append(")");
			this.executeUpdate(insert.toString());
			String select = "select * from a order by id desc";
			bo = this.readQueryAsBoOne(select);
			response.setBo(bo);
			logger.log(Level.INFO, "postNew - inserted with id " + bo.getId());
			logger.log(Level.INFO, "postNew - end");
			return response;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setError(e.getMessage());
			return response;
		}
	}

	public GrejResponse putExisting(GrejBo bo) {
		GrejResponse response = new GrejResponse();
		try {
			logger.log(Level.INFO, "putExisting - begin");
			bo.setDt2(EpeAppUtils.retrieveTimestamp(format));
			StringBuilder update = new StringBuilder("update a set idg = ");
			update.append(bo.getIdg());
			update.append(", idp = ");
			update.append(bo.getIdp());
			update.append(", idc = ");
			update.append(bo.getIdc());
			update.append(", usr = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getUsr()));
			update.append(", vs1 = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVs1()));
			update.append(", vs2 = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVs2()));
			update.append(" where id = ");
			update.append(bo.getId());
			if (this.executeUpdate(update.toString()) == 1) {
				response.setBo(bo);
				logger.log(Level.INFO, "putExisting - updated with id " + bo.getId());
			} else {
				response.setError("Not updated with id " + bo.getId());
				logger.log(Level.INFO, "putExisting - not updated with id " + bo.getId());
			}
			logger.log(Level.INFO, "putExisting - end");
			return response;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setError(e.getMessage());
			return response;
		}
	}

	public GrejResponse patchExisting(GrejBo bo) {
		GrejResponse response = new GrejResponse();
		try {
			logger.log(Level.INFO, "patchExisting - begin");
			bo.setDt2(EpeAppUtils.retrieveTimestamp(format));
			String sep = "";
			StringBuilder update = new StringBuilder("update a set ");
			if (bo.getIdg() != null) {
				update.append("idg = ");
				update.append(bo.getIdg());
				sep = ", ";
			}
			if (bo.getIdp() != null) {
				update.append(sep);
				sep = ", ";
				update.append("idp = ");
				update.append(bo.getIdp());
			}
			if (bo.getIdc() != null) {
				update.append(sep);
				sep = ", ";
				update.append("idc = ");
				update.append(bo.getIdc());
			}
			if (bo.getUsr() != null) {
				update.append(sep);
				sep = ", ";
				update.append("usr = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getUsr()));
			}
			if (bo.getVs1() != null) {
				update.append(sep);
				sep = ", ";
				update.append("vs1 = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVs1()));
			}
			if (bo.getVs2() != null) {
				update.append(sep);
				sep = ", ";
				update.append("vs2 = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVs2()));
			}
			update.append(" where id = ");
			update.append(bo.getId());
			if (this.executeUpdate(update.toString()) == 1) {
				response.setBo(bo);
				logger.log(Level.INFO, "patchExisting - updated with id " + bo.getId());
			} else {
				response.setError("Not updated with id " + bo.getId());
				logger.log(Level.INFO, "patchExisting - not updated with id " + bo.getId());
			}
			logger.log(Level.INFO, "patchExisting - end");
			return response;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setError(e.getMessage());
			return response;
		}
	}

	// PRIVATE METHODS

	private GrejBo getBo(EpeDbEntity entity) throws EpeAppException {
		GrejBo bo = new GrejBo();
		bo.setId(entity.getInteger("id"));
		bo.setIdg(entity.getInteger("idg"));
		bo.setIdp(entity.getInteger("idp"));
		bo.setIdc(entity.getInteger("idc"));
		bo.setUsr(entity.getString("usr"));
		bo.setDt1(entity.getToString("dt1", format));
		bo.setDt2(entity.getToString("dt2", format));
		bo.setVs1(entity.getString("vs1"));
		bo.setVs2(entity.getString("vs2"));
		return bo;
	}

	private int executeUpdate(String update) throws EpeAppException {
		return EpeDbFinalDb_update.executeUpdate(this.dataSource, update);
	}

	private List<GrejBo> readQueryAsBo(String select) throws EpeAppException {
		List<EpeDbEntity> listEntity = this.readQueryAsEntity(select);
		List<GrejBo> listBo = new ArrayList<>();
		for (EpeDbEntity entity : listEntity)
			listBo.add(this.getBo(entity));
		return listBo;
	}

	private GrejBo readQueryAsBoOne(String select) throws EpeAppException {
		EpeDbEntity entity = this.readQueryAsEntityOne(select);
		return this.getBo(entity);
	}

	private List<EpeDbEntity> readQueryAsEntity(String select) throws EpeAppException {
		List<EpeDbEntity> listEntity = new ArrayList<>();
		EpeDbFinalDb_select.readQueryAsEntity(this.dataSource, select, "a", "200", listEntity);
		return listEntity;
	}

	private EpeDbEntity readQueryAsEntityOne(String select) throws EpeAppException {
		List<EpeDbEntity> listEntity = new ArrayList<>();
		EpeDbFinalDb_select.readQueryAsEntity(this.dataSource, select, "a", "1", listEntity);
		return listEntity.get(0);
	}

}
