////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231121TUE_115500] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.manager;

public class ProductCode {
	// -------------------------------------------------------------------------

	public enum Type {
		Snack, // 과자
		Ramen, // 라면
		Drink, // 주류
		Fruit, // 과일
		Vegetable, // 채소
	}

	// -------------------------------------------------------------------------

	private Type mType;
	private int mSn;

	// -------------------------------------------------------------------------

	// 생성자 - 타입, 시리얼 입력
	public ProductCode(Type type, int sn) {
		this.mType = type;
		this.mSn = sn;
	}

	// 생성자 - 제품 코드 문자열 입력
	public ProductCode(String prodCode) {
		String typeStr = prodCode.substring(0, 1);
		String snStr = prodCode.substring(1);

		this.mType = this.strToType(typeStr);
		this.mSn = Integer.parseInt(snStr);
	}

	// -------------------------------------------------------------------------

	// 타입 얻기
	public Type getType() {
		return this.mType;
	}

	// 타입 문자열(한 문자) 얻기
	public String getTypeStr() {
		return this.typeToStr(this.getType());
	}

	// 시리얼 얻기
	public int getSn() {
		return this.mSn;
	}

	// Type으로 문자열(한 문자) 얻기
	public String typeToStr(Type type) {
		switch (type) {
			// 과자
			case Snack:
				return "S";

			// 라면
			case Ramen:
				return "R";

			// 주류
			case Drink:
				return "D";

			// 과일
			case Fruit:
				return "F";

			// 채소
			case Vegetable:
				return "V";
		}

		return "";
	}

	// 문자열로 Type 얻기
	public Type strToType(String str) {
		// 과자
		if (str.equals("S"))
			return Type.Snack;

		// 라면
		else if (str.equals("R"))
			return Type.Ramen;

		// 주류
		else if (str.equals("D"))
			return Type.Drink;

		// 과일
		else if (str.equals("F"))
			return Type.Fruit;

		// 채소
		else if (str.equals("V"))
			return Type.Vegetable;

		return null;
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		return String.format("%s%03d", this.getTypeStr(), this.getSn());
	}

	@Override
	public boolean equals(Object arg0) {
		var prodCode = (ProductCode) arg0;
		return this.getType() == prodCode.getType() && this.getSn() == prodCode.getSn();
	}
}
