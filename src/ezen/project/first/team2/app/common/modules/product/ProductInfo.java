////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.modules.base.ListItem;
import ezen.project.first.team2.app.common.utils.TimeUtils;

public class ProductInfo extends ListItem {
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
	ProductInfo() {
	}

	// 생성자
	ProductInfo(int id, ProductCode prodCode, LocalDate regDate, String name,
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

	public static ProductInfo[] getPredefinedData() {
		final ProductInfo[] data = {
				// 과자
				new ProductInfo(0, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(1, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(2, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(3, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(4, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(5, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(6, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(7, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(8, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, ""),
				new ProductInfo(9, new ProductCode("S0001"), LocalDate.now(), "빼빼로", 1200, "")
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
		ProductInfo info = (ProductInfo) item;
		info.setValues(info.getId(), info.getProdCode(), info.getRegDate(),
				info.getName(), info.getPrice(), info.getDesc());
	}
}
