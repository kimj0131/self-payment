package ezen.project.first.team2.app.common.utils;

import java.awt.Dimension;
import java.text.NumberFormat;

public class UnitUtils {
	//
	public static String dimToStr(Dimension dim) {
		return String.format("%dx%d", dim.width, dim.height);
	}

	// 숫자를 화폐(콤마로 구분되는) 문자열로 변환
	public static String numToCurrencyStr(int num) {
		return NumberFormat.getInstance().format(num);
	}
}
