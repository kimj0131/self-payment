////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class ProductItem extends ListItem {
	// -------------------------------------------------------------------------

	// 상품 ID.
	// private int mProdId = 0;
	// 상품 코드. 한번 설정 후 변경하면 안 된다.
	private ProductCode mProdCode = null;
	// 등록일
	private LocalDate mRegDate = LocalDate.now();
	// 이름
	private String mName = "";
	// 가격
	private int mPrice = 0;
	// 설명
	private String mDesc = "";

	// -------------------------------------------------------------------------

	// 생성자
	public ProductItem() {
	}

	// 생성자
	public ProductItem(int id, ProductCode prodCode, LocalDate regDate, String name,
			int price, String desc) {
		this.setValues(id, prodCode, regDate, name, price, desc);
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 값 설정
	public void setValues(int id, ProductCode prodCode, LocalDate regDate, String name,
			int price, String desc) {
		this.mId = id;

		this.mProdCode = prodCode;
		this.mRegDate = regDate;
		this.mName = name;
		this.mPrice = price;
		this.mDesc = desc;
	}

	// -------------------------------------------------------------------------

	// 상품 코드 설정
	public void setProdCode(ProductCode prodCode) {
		this.mProdCode = prodCode;
	}

	// 등록일 설정
	public void setRegDate(LocalDate regDate) {
		this.mRegDate = regDate;
	}

	// 이름 설정
	public void setName(String name) {
		this.mName = name;
	}

	// 가격 설정
	public void setPrice(int price) {
		this.mPrice = price;
	}

	// 설명 설정
	public void setDesc(String desc) {
		this.mDesc = desc;
	}

	// -------------------------------------------------------------------------

	// 상품 코드 얻기
	public ProductCode getProdCode() {
		return this.mProdCode;
	}

	// 상품 코드 문자열 얻기
	public String getProdCodeStr() {
		return this.mProdCode.toString();
	}

	// 등록일 얻기
	public LocalDate getRegDate() {
		return this.mRegDate;
	}

	// 등록일 문자열 얻기
	public String getRegDateStr() {
		return TimeUtils.localDateToStr(this.mRegDate);
	}

	// 등록일 문자열 sql패턴에 맞게 얻기
	public String getSqlRegDateStr() {
		return this.getRegDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}

	// 이름 얻기
	public String getName() {
		return this.mName;
	}

	// 가격 얻기
	public int getPrice() {
		return this.mPrice;
	}

	// 설명 얻기
	public String getDesc() {
		return this.mDesc;
	}

	// -------------------------------------------------------------------------

	public static ProductItem[] getPredefinedProductData() {
		try {
			ProductItem[] data = {
					// 과자
					// new ProductItem(-1, new ProductCode("S001"), LocalDate.now(), "빼빼로", 1200,
					// ""),
					// new ProductItem(-1, new ProductCode("S002"), LocalDate.now(), "오감자", 1400,
					// ""),
					// new ProductItem(-1, new ProductCode("S003"), LocalDate.now(), "썬칩", 1400,
					// ""),
					// new ProductItem(-1, new ProductCode("S004"), LocalDate.now(), "포카칩", 1400,
					// ""),
					// new ProductItem(-1, new ProductCode("S005"), LocalDate.now(), "에이스", 1400,
					// ""),
					//
					new ProductItem(-1, new ProductCode("S001"), LocalDate.now(), "빼빼로", 1000, ""),
					new ProductItem(-1, new ProductCode("S002"), LocalDate.now(), "오감자", 1000, ""),
					new ProductItem(-1, new ProductCode("S003"), LocalDate.now(), "썬칩", 1000, ""),
					new ProductItem(-1, new ProductCode("S004"), LocalDate.now(), "포카칩", 1000, ""),
					new ProductItem(-1, new ProductCode("S005"), LocalDate.now(), "에이스", 1000, ""),
					//
					new ProductItem(-1, new ProductCode("S006"), LocalDate.now(), "죠리퐁", 1200, ""),
					new ProductItem(-1, new ProductCode("S007"), LocalDate.now(), "바나나킥", 1600, ""),
					new ProductItem(-1, new ProductCode("S008"), LocalDate.now(), "새우깡", 1500, ""),
					new ProductItem(-1, new ProductCode("S009"), LocalDate.now(), "홈런볼", 1380, ""),
					new ProductItem(-1, new ProductCode("S010"), LocalDate.now(), "칸", 2000, ""),

					// 라면
					new ProductItem(-1, new ProductCode("R001"), LocalDate.now(), "진라면5봉", 3850, ""),
					new ProductItem(-1, new ProductCode("R002"), LocalDate.now(), "신라면5봉", 3900, ""),
					new ProductItem(-1, new ProductCode("R003"), LocalDate.now(), "삼양라면5봉", 3680, ""),
					new ProductItem(-1, new ProductCode("R004"), LocalDate.now(), "자파게티5봉", 4880, ""),
					new ProductItem(-1, new ProductCode("R005"), LocalDate.now(), "안성탕면5봉", 3700, ""),

					// 주류
					new ProductItem(-1, new ProductCode("D001"), LocalDate.now(), "소주", 1800, ""),
					new ProductItem(-1, new ProductCode("D002"), LocalDate.now(), "양주", 15000, ""),
					new ProductItem(-1, new ProductCode("D003"), LocalDate.now(), "막걸리", 1400, ""),
					new ProductItem(-1, new ProductCode("D004"), LocalDate.now(), "맥주", 1800, ""),
					new ProductItem(-1, new ProductCode("D005"), LocalDate.now(), "와인", 36000, ""),

					// 과일
					new ProductItem(-1, new ProductCode("F001"), LocalDate.now(), "바나나1송이", 5600, ""),
					new ProductItem(-1, new ProductCode("F002"), LocalDate.now(), "사과1개", 900, ""),
					new ProductItem(-1, new ProductCode("F003"), LocalDate.now(), "오렌지1개", 1000, ""),
					new ProductItem(-1, new ProductCode("F004"), LocalDate.now(), "파인애플1통(약2kg)", 7500, ""),
					new ProductItem(-1, new ProductCode("F005"), LocalDate.now(), "포도1송이", 6500, ""),

					// 채소
					new ProductItem(-1, new ProductCode("V001"), LocalDate.now(), "상추1kg", 2800, ""),
					new ProductItem(-1, new ProductCode("V002"), LocalDate.now(), "감자개당", 400, ""),
					new ProductItem(-1, new ProductCode("V003"), LocalDate.now(), "고구마개당", 600, ""),
					new ProductItem(-1, new ProductCode("V004"), LocalDate.now(), "무", 1700, ""),
					new ProductItem(-1, new ProductCode("V005"), LocalDate.now(), "배추1포기", 20800, ""),
					new ProductItem(-1, new ProductCode("V006"), LocalDate.now(), "브로콜리", 1100, ""),
					new ProductItem(-1, new ProductCode("V007"), LocalDate.now(), "애호박", 1600, ""),
					new ProductItem(-1, new ProductCode("V008"), LocalDate.now(), "당근", 800, ""),
					new ProductItem(-1, new ProductCode("V009"), LocalDate.now(), "양파", 400, ""),
					new ProductItem(-1, new ProductCode("V010"), LocalDate.now(), "파프리카", 1600, ""),
			};

			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		String s = String.format("id:%06d, prodCode:%s, regDate:%s, name:%-16s," +
				" price:%5d, desc:%s",
				this.getId(), this.getProdCodeStr(), this.getRegDateStr(), this.getName(),
				this.getPrice(), this.getDesc());
		return s;
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		var pi = (ProductItem) item;
		this.setValues(pi.getId(), pi.getProdCode(), pi.getRegDate(),
				pi.getName(), pi.getPrice(), pi.getDesc());
	}
}
