package com.softwarelma.grej.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_datasource;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_select;

public class GrejDao {

	public void select() {
		try {
			DataSource dataSource = EpeDbFinalDb_datasource.retrieveOrCreateDataSource("jdbc:mysql://localhost:3306/grej?serverTimezone=UTC", "grej",
					"ncqgà1ePáB");
			String select = "select * from a";
			List<List<String>> listListStr = new ArrayList<>();
			EpeDbFinalDb_select.readQueryAsString(dataSource, select, "200", "java.sql.Clob", listListStr, true, false);
			// EpeDbFinalDb_select.retrieveListListStr(listExecResult, "postMessage", dataSource, "200", "java.sql.Clob", true, false);
			System.out.println(listListStr);
		} catch (EpeAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
