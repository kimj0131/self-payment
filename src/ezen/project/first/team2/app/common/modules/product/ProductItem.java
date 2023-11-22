////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class ProductItem extends ListItem {
	// -------------------------------------------------------------------------

	// 상품 ID.
	// private int mProdId = 0;
	// 상품 코드
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
	ProductItem() {
	}

	// 생성자
	ProductItem(int id, ProductCode prodCode, LocalDate regDate, String name,
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
		ProductItem[] data = {
				// 과자
				new ProductItem(-1, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductItem(-1, new ProductCode("S0002"), LocalDate.now(), "오감자", 1400, ""),
				new ProductItem(-1, new ProductCode("S0003"), LocalDate.now(), "썬칩", 1400, ""),
				new ProductItem(-1, new ProductCode("S0004"), LocalDate.now(), "포카칩", 1400, ""),
				new ProductItem(-1, new ProductCode("S0005"), LocalDate.now(), "에이스", 1400, ""),
				new ProductItem(-1, new ProductCode("S0006"), LocalDate.now(), "죠리퐁", 1200, ""),
				new ProductItem(-1, new ProductCode("S0007"), LocalDate.now(), "바나나킥", 1600, ""),
				new ProductItem(-1, new ProductCode("S0008"), LocalDate.now(), "새우깡", 1500, ""),
				new ProductItem(-1, new ProductCode("S0009"), LocalDate.now(), "홈런볼", 1380, ""),
				new ProductItem(-1, new ProductCode("S0010"), LocalDate.now(), "칸", 2000, ""),

				// 라면
				new ProductItem(-1, new ProductCode("R0001"), LocalDate.now(), "진라면5봉", 3850, ""),
				new ProductItem(-1, new ProductCode("R0002"), LocalDate.now(), "신라면5봉", 3900, ""),
				new ProductItem(-1, new ProductCode("R0003"), LocalDate.now(), "삼양라면5봉", 3680, ""),
				new ProductItem(-1, new ProductCode("R0004"), LocalDate.now(), "자파게티5봉", 4880, ""),
				new ProductItem(-1, new ProductCode("R0005"), LocalDate.now(), "안성탕면5봉", 3700, ""),

				// 주류
				new ProductItem(-1, new ProductCode("D0001"), LocalDate.now(), "소주", 1800, ""),
				new ProductItem(-1, new ProductCode("D0002"), LocalDate.now(), "양주", 15000, ""),
				new ProductItem(-1, new ProductCode("D0003"), LocalDate.now(), "막걸리", 1400, ""),
				new ProductItem(-1, new ProductCode("D0004"), LocalDate.now(), "맥주", 1800, ""),
				new ProductItem(-1, new ProductCode("D0005"), LocalDate.now(), "와인", 36000, ""),

				// 과일
				new ProductItem(-1, new ProductCode("F0001"), LocalDate.now(), "바나나1송이", 5550, ""),
				new ProductItem(-1, new ProductCode("F0002"), LocalDate.now(), "사과1개", 888, ""),
				new ProductItem(-1, new ProductCode("F0003"), LocalDate.now(), "오렌지1개", 980, ""),
				new ProductItem(-1, new ProductCode("F0004"), LocalDate.now(), "파인애플1통(약2kg)", 7500, ""),
				new ProductItem(-1, new ProductCode("F0005"), LocalDate.now(), "포도1송이", 6500, ""),

				// 채소
				new ProductItem(-1, new ProductCode("V0001"), LocalDate.now(), "상추1kg", 2750, ""),
				new ProductItem(-1, new ProductCode("V0002"), LocalDate.now(), "감자개당", 398, ""),
				new ProductItem(-1, new ProductCode("V0003"), LocalDate.now(), "고구마개당", 599, ""),
				new ProductItem(-1, new ProductCode("V0004"), LocalDate.now(), "무", 1700, ""),
				new ProductItem(-1, new ProductCode("V0005"), LocalDate.now(), "배추1포기", 20800, ""),
				new ProductItem(-1, new ProductCode("V0006"), LocalDate.now(), "브로콜리", 1058, ""),
				new ProductItem(-1, new ProductCode("V0007"), LocalDate.now(), "애호박", 1590, ""),
				new ProductItem(-1, new ProductCode("V0008"), LocalDate.now(), "당근", 750, ""),
				new ProductItem(-1, new ProductCode("V0009"), LocalDate.now(), "양파", 330, ""),
				new ProductItem(-1, new ProductCode("V0010"), LocalDate.now(), "파프리카", 1590, ""),
		};

		return data;
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
		ProductItem pi = (ProductItem) item;
		pi.setValues(pi.getId(), pi.getProdCode(), pi.getRegDate(),
				pi.getName(), pi.getPrice(), pi.getDesc());
	}
}
