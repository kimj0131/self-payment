////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_223000] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.base;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	public void createTable() {
		//
	}

	// 테이블을 삭제한다
	public void dropTable() {
		//
	}

	// 테이블 내용을 초기화한다
	public void truncateTable() {
		//
	}

	// -------------------------------------------------------------------------

	// select 쿼리 실행
	public void doSelectQuery(Iterator<T> iterator, String fieldSet, String where, String orderBy)
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

		System.out.printf("[ListManagerDb.doSelectQuery()] sql=\"%s\" \n", sql);

		this.reset();

		int idx = 0;
		while (rs.next()) {
			var item = this.onResultSetToItem(rs);
			if (iterator.onGetItem(item, idx++)) {
				this.add(item);
			}
		}

		rs.close();
		pstmt.close();
	}

	// insert 쿼리 수행
	public void doInsertQuery(T item) {
		//
	}

	// update 쿼리 수행
	public void doUpdateQuery(T item) {
		//
	}

	// delete 쿼리 수행
	public void performDeleteQuery(T item) {
		//
	}

	// -------------------------------------------------------------------------

	// ResultSet을 Item으로 변환한다
	protected T onResultSetToItem(ResultSet rs) {
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

	// -------------------------------------------------------------------------
}
