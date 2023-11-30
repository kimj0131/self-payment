////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_223000] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import ezen.project.first.team2.app.common.modules.database.DBConnector;

public class ListManagerDb<T extends ListItem> extends ListManager<T> {
	// -------------------------------------------------------------------------

	private String mTablename;

	// -------------------------------------------------------------------------

	public static interface Iterator<T> {
		// true를 리턴하면 해당 아이템을 읽거나 저장한다
		public boolean onGetItem(T item, int idx);
	}

	// -------------------------------------------------------------------------

	// 생성자
	public ListManagerDb(String tableName) {
		super();

		this.mTablename = tableName;
	}

	// -------------------------------------------------------------------------

	// 테이블을 생성한다
	public void createTable() throws Exception {
		// 테이블이 이미 존재한다면 예외 발생
		if (this.hasTable()) {
			String msg = String.format("[ListManagerDb.createTable()]" +
					" You already have table('%s')!", this.mTablename);
			throw new Exception(msg);
		}

		var dbConn = DBConnector.getInstance().getConnection();

		var sql = this.onMakeCreateTableQuery(this.mTablename);

		// System.out.printf("[ListManagerDb.createTable()] sql=\"%s\" \n", sql);

		Statement stmt = dbConn.createStatement();
		stmt.execute(sql);

		stmt.close();
	}

	// 테이블을 삭제한다
	public void dropTable() throws Exception {
		// 테이블이 이미 존재하지 않는다면 예외 발생
		if (!this.hasTable()) {
			String msg = String.format("[ListManagerDb.createTable()]" +
					" You don't have table('%s')!", this.mTablename);
			throw new Exception(msg);
		}

		var dbConn = DBConnector.getInstance().getConnection();

		var sql = String.format("drop table %s", this.mTablename);

		// System.out.printf("[ListManagerDb.dropTable()] sql=\"%s\" \n", sql);

		Statement stmt = dbConn.createStatement();
		stmt.execute(sql);

		stmt.close();
	}

	// 테이블 내용을 초기화한다
	public void truncateTable() throws Exception {
		// 테이블이 이미 존재하지 않는다면 예외 발생
		if (!this.hasTable()) {
			String msg = String.format("[ListManagerDb.createTable()]" +
					" You don't have table('%s')!", this.mTablename);
			throw new Exception(msg);
		}

		var dbConn = DBConnector.getInstance().getConnection();

		var sql = String.format("truncate table %s", this.mTablename);

		// System.out.printf("[ListManagerDb.truncateTable()] sql=\"%s\" \n", sql);

		Statement stmt = dbConn.createStatement();
		stmt.execute(sql);

		stmt.close();
	}

	// 테이블 존재여부 확인
	public boolean hasTable() throws Exception {
		var dbConn = DBConnector.getInstance().getConnection();

		DatabaseMetaData dbm = dbConn.getMetaData();
		// check if "employee" table is there
		ResultSet tables = dbm.getTables(null, null, this.mTablename, null);
		return tables.next();
	}

	// -------------------------------------------------------------------------

	// select 쿼리 실행
	public int doSelectQuery(Iterator<T> iterator, String fieldSet, String where, String orderBy)
			throws Exception {
		var dbConn = DBConnector.getInstance().getConnection();

		String _fieldset = "*";
		String _where = "";
		String _orderBy = "";

		if (fieldSet != null && !fieldSet.isEmpty()) {
			_fieldset = fieldSet;
		}

		if (where != null && !where.isEmpty()) {
			_where = " where " + where;
		}

		if (orderBy != null && !orderBy.isEmpty()) {
			_orderBy = " order by " + orderBy;
		}

		String sql = String.format("select %s from %s%s%s", _fieldset, this.mTablename, _where, _orderBy);
		PreparedStatement pstmt = dbConn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = pstmt.executeQuery();

		// System.out.printf("[ListManagerDb.doSelectQuery()] sql=\"%s\" \n", sql);

		// 개수 얻기
		int rCnt = 0;
		while (rs.next())
			rCnt++;
		rs.beforeFirst();

		this.reset();

		int idx = 0;
		while (rs.next()) {
			var item = this.onResultSetToItem(rs);

			if (iterator == null) {
				this.add(item);
			} else {
				if (iterator.onGetItem(item, idx++)) {
					this.add(item);
				}
			}
		}

		rs.close();
		pstmt.close();

		return rCnt;
	}

	public int doSelectQuery(Iterator<T> iterator) throws Exception {
		return this.doSelectQuery(iterator, null, null, null);
	}

	public int doSelectQuery() throws Exception {
		return this.doSelectQuery(null, null, null, null);
	}

	// insert 쿼리 수행
	public int doInsertQuery(T item) throws Exception {
		var dbConn = DBConnector.getInstance().getConnection();

		var strs = this.onMakeFieldsetAndValues(item);
		String sql = String.format("insert into %s(%s) values(%s)",
				this.mTablename, strs[0], strs[1]);

		// System.out.printf("[ListManagerDb.doInsertQuery()] sql=\"%s\" \n", sql);

		PreparedStatement pstmt = dbConn.prepareStatement(sql);
		int rows = pstmt.executeUpdate();

		pstmt.close();

		return rows;
	}

	// update 쿼리 수행
	public int doUpdateQuery(T item, String[] fieldset, String where) throws Exception {
		var dbConn = DBConnector.getInstance().getConnection();

		String _set = this.onMakeSet(item, fieldset);
		String _where = where != null && !where.isEmpty() ? " where " + where : "";
		String sql = String.format("update %s set %s%s",
				this.mTablename, _set, _where);

		// System.out.printf("[ListManagerDb.doUpdateQuery()] sql=\"%s\" \n", sql);

		PreparedStatement pstmt = dbConn.prepareStatement(sql);
		int rows = pstmt.executeUpdate();

		pstmt.close();

		return rows;
	}

	// delete 쿼리 수행
	public int doDeleteQuery(String where) throws Exception {
		var dbConn = DBConnector.getInstance().getConnection();

		String _where = where != null && !where.isEmpty() ? " where " + where : "";
		String sql = String.format("delete %s%s", this.mTablename, _where);

		// System.out.printf("[ListManagerDb.doDeleteQuery()] sql=\"%s\" \n", sql);

		PreparedStatement pstmt = dbConn.prepareStatement(sql);
		int rows = pstmt.executeUpdate();

		pstmt.close();

		return rows;
	}

	// getNextIdFromDb
	public int getNextIdFromDb(String field) throws Exception {
		this.reset();

		this.doSelectQuery((item, idx) -> true,
				String.format("max(%s) as %s", field, field), null, null);
		var item = this.getFirstItem();
		var id = item.getId() + 1;

		return id;
	}

	// -------------------------------------------------------------------------

	protected String onMakeCreateTableQuery(String tableName) {
		return null;
	}

	// -------------------------------------------------------------------------

	// ResultSet을 Item으로 변환한다 => doSelectQuery()
	protected T onResultSetToItem(ResultSet rs) {
		return null;
	}

	// 삽입할 필드셋과 값을 없는다 => doInsertQuery()
	protected String[] onMakeFieldsetAndValues(T item) {
		return null;
	}

	// 수정할 값들을 얻는다 => doUpdateQuery()
	protected String onMakeSet(T item, String[] fieldset) throws Exception {
		return null;
	}

	// -------------------------------------------------------------------------

	protected String getString(ResultSet rs, String name, String def) {
		try {
			return rs.getString(name);
		} catch (Exception e) {
			// e.printStackTrace();
			return def;
		}
	}

	protected int getInt(ResultSet rs, String name, int def) {
		try {
			return rs.getInt(name);
		} catch (Exception e) {
			// e.printStackTrace();
			return def;
		}
	}

	protected double getDouble(ResultSet rs, String name, double def) {
		try {
			return rs.getDouble(name);
		} catch (Exception e) {
			// e.printStackTrace();
			return def;
		}
	}

	protected Date getDate(ResultSet rs, String name, Date def) {
		try {
			return rs.getDate(name);
		} catch (Exception e) {
			// e.printStackTrace();
			return def;
		}
	}

	protected Timestamp getTimestamp(ResultSet rs, String name, Timestamp def) {
		try {
			return rs.getTimestamp(name);
		} catch (Exception e) {
			return def;
		}
	}

	//

	protected String getString(ResultSet rs, String name) {
		return this.getString(rs, name, "");
	}

	protected int getInt(ResultSet rs, String name) {
		return this.getInt(rs, name, 0);
	}

	protected double getDouble(ResultSet rs, String name) {
		return this.getDouble(rs, name, 0);
	}

	protected Date getDate(ResultSet rs, String name) {
		return this.getDate(rs, name, Date.valueOf("1970-01-01"));
	}

	protected Timestamp getTimestamp(ResultSet rs, String name) {
		return this.getTimestamp(rs, name, Timestamp.valueOf("1970-01-01 00:00:00"));
	}

	// -------------------------------------------------------------------------
}
