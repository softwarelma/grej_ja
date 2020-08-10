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
			StringBuilder insert = new StringBuilder("insert into a (idg, idp, idc, idu, id1, id2, id3, vc1, vc2, vc3, vc4, vc5) values (");
			insert.append(bo.getIdg());
			insert.append(", ");
			insert.append(bo.getIdp());
			insert.append(", ");
			insert.append(bo.getIdc());
			insert.append(", ");
			insert.append(bo.getIdu());
			insert.append(", ");
			insert.append(bo.getId1());
			insert.append(", ");
			insert.append(bo.getId2());
			insert.append(", ");
			insert.append(bo.getId3());
			insert.append(", ");
			insert.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc1()));
			insert.append(", ");
			insert.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc2()));
			insert.append(", ");
			insert.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc3()));
			insert.append(", ");
			insert.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc4()));
			insert.append(", ");
			insert.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc5()));
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
			update.append(", idu = ");
			update.append(bo.getIdu());
			update.append(", id1 = ");
			update.append(bo.getId1());
			update.append(", id2 = ");
			update.append(bo.getId2());
			update.append(", id3 = ");
			update.append(bo.getId3());
			update.append(", dt2 = str_to_date('");
			update.append(bo.getDt2());
			update.append("', '%Y-%m-%d %T')");
			update.append(", vc1 = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc1()));
			update.append(", vc2 = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc2()));
			update.append(", vc3 = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc3()));
			update.append(", vc4 = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc4()));
			update.append(", vc5 = ");
			update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc5()));
			update.append(" where id = ");
			update.append(bo.getId());
			if (this.executeUpdate(update.toString()) == 1) {
				GrejResponse response2 = this.get(bo.getId());
				logger.log(Level.INFO, "putExisting - updated with id " + bo.getId());
				return response2;
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
			StringBuilder update = new StringBuilder("update a set dt2 = str_to_date('");
			update.append(bo.getDt2());
			update.append("', '%Y-%m-%d %T')");
			if (bo.getIdg() != null) {
				update.append(", idg = ");
				update.append(bo.getIdg());
			}
			if (bo.getIdp() != null) {
				update.append(", idp = ");
				update.append(bo.getIdp());
			}
			if (bo.getIdc() != null) {
				update.append(", idc = ");
				update.append(bo.getIdc());
			}
			if (bo.getIdu() != null) {
				update.append(", idu = ");
				update.append(bo.getIdu());
			}
			if (bo.getId1() != null) {
				update.append(", id1 = ");
				update.append(bo.getId1());
			}
			if (bo.getId2() != null) {
				update.append(", id2 = ");
				update.append(bo.getId2());
			}
			if (bo.getId3() != null) {
				update.append(", id3 = ");
				update.append(bo.getId3());
			}
			if (bo.getVc1() != null) {
				update.append(", vc1 = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc1()));
			}
			if (bo.getVc2() != null) {
				update.append(", vc2 = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc2()));
			}
			if (bo.getVc3() != null) {
				update.append(", vc3 = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc3()));
			}
			if (bo.getVc4() != null) {
				update.append(", vc4 = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc4()));
			}
			if (bo.getVc5() != null) {
				update.append(", vc5 = ");
				update.append(EpeDbEntity.getToStringAsVarcharOrNull(bo.getVc5()));
			}
			update.append(" where id = ");
			update.append(bo.getId());
			if (this.executeUpdate(update.toString()) == 1) {
				GrejResponse response2 = this.get(bo.getId());
				logger.log(Level.INFO, "patchExisting - updated with id " + bo.getId());
				return response2;
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
		bo.setIdu(entity.getInteger("idu"));
		bo.setId1(entity.getInteger("id1"));
		bo.setId2(entity.getInteger("id2"));
		bo.setId3(entity.getInteger("id3"));
		bo.setDt1(entity.getToString("dt1", format));
		bo.setDt2(entity.getToString("dt2", format));
		bo.setVc1(entity.getString("vc1"));
		bo.setVc2(entity.getString("vc2"));
		bo.setVc3(entity.getString("vc3"));
		bo.setVc4(entity.getString("vc4"));
		bo.setVc5(entity.getString("vc5"));
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
