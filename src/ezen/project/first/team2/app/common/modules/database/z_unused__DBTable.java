////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_144500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class z_unused__DBTable {
	// -------------------------------------------------------------------------

	public static interface ListIterator {
		public boolean onGetRecord(ResultSet rs, int idx, int count);
	}

	// -------------------------------------------------------------------------

	private String mTableName;

	// -------------------------------------------------------------------------

	// 생성자
	public z_unused__DBTable(String tableName) {
		this.mTableName = tableName;
	}

	// -------------------------------------------------------------------------

	public String getTableName() {
		return this.mTableName;
	}

	// -------------------------------------------------------------------------

	// 레코드셋 리스팅
	public void listRecordset(String[] fieldset, String where, String orderBy,
			ListIterator iterator) throws Exception {
		var dbConn = DBConnector.getInstance();
		if (!dbConn.isConnected()) {
			String msg = String.format("[DBTable.listRecordset()]" +
					" You must connect database!");
			throw new Exception(msg);
		}

		String _fieldset = "*";
		String _where = "";
		String _orderBy = "";

		if (fieldset != null && fieldset.length > 0) {
			for (var f : fieldset) {
				_fieldset += f + ",";
			}

			// 마지막 comma(,) 제거
			_fieldset = _fieldset.substring(0, _fieldset.length() - 2);
		}

		if (where != null && !where.isEmpty()) {
			_where = " where " + where;
		}

		if (orderBy != null && !orderBy.isEmpty()) {
			_orderBy = " order by " + orderBy;
		}

		String sql = String.format("select %s from %s %s%s",
				_fieldset, this.getTableName(), _where, _orderBy);
		// System.out.println(sql);

		try {
			PreparedStatement pstmt = dbConn.getConnection().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pstmt.executeQuery();

			// 레코드 개수 얻기
			int rCnt = 0;
			while (rs.next()) {
				rCnt++;
			}
			rs.beforeFirst();

			int idx = 0;
			while (rs.next()) {
				if (!iterator.onGetRecord(rs, idx++, rCnt)) {
					break;
				}
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 레코드 추가
	public void addRecord(String[] fieldset, Object[] values) throws Exception {
		var dbConn = DBConnector.getInstance();
		if (!dbConn.isConnected()) {
			String msg = String.format("[DBTable.listRecordset()]" +
					" You must connect database!");
			throw new Exception(msg);
		}

		String _fieldset = "";
		String _values = "";

		if (fieldset != null && fieldset.length > 0) {
			for (var f : fieldset) {
				_fieldset += f + ",";
			}

			// 마지막 comma(,) 제거
			_fieldset = _fieldset.substring(0, _fieldset.length() - 2);

			// ()로 묶기
			_fieldset = "(" + _fieldset + ")";
		}

		int idx = 0;
		for (var v : values) {
			if (v instanceof String) {
				String s = (String) v;
				if (s.contains("seq.nextval"))
					_values += String.format("%s,", v);
				else
					_values += String.format("'%s',", v);

			} else if (v instanceof Integer) {
				_values += String.format("%d,", (Integer) v);
			} else if (v instanceof Double) {
				_values += String.format("%f,", (Double) v);
			} else if (v instanceof LocalDate) {
				_values += String.format("'%s',", Date.valueOf((LocalDate) v).toString());
			} else if (v == null) {
				_values += "null,";
			} else {
				String msg = String.format("[DBTable.addRecord()]" +
						" Invalid type at %d", idx);
				throw new Exception(msg);
			}
		}

		// 마지막 comma(,) 제거
		_values = _values.substring(0, _values.length() - 2);

		String sql = String.format("insert into %s%s values(%s)",
				this.getTableName(), _fieldset, _values);
		System.out.println(sql);

		PreparedStatement pstmt = dbConn.getConnection().prepareStatement(sql);
		// int rows =
		pstmt.executeUpdate();

		pstmt.close();
	}

	// 레코드 수정

	// 레코드 삭제
}
