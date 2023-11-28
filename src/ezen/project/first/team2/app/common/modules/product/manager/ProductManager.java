////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.manager;

import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManager;

public class ProductManager extends ListManager<ProductItem> {
	// -------------------------------------------------------------------------

	private static ProductManager mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductManager() {
	}

	// 인스턴스 얻기
	public static ProductManager getInstance() {
		if (mInstance == null) {
			mInstance = new ProductManager();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	public ProductItem findByProductCode(ProductCode prodCode) {
		return this.find((pi, idx) -> pi.getProdCode().equals(prodCode));
	}

	public List<ProductItem> findByRegDate(LocalDate date) {
		return this.findItems((pi, idx) -> pi.getRegDate().equals(date));
	}

	public List<ProductItem> findByName(String name) {
		return this.findItems((pi, idx) -> pi.getName().contains(name));
	}

	public List<ProductItem> findByPrice(int price) {
		return this.findItems((pi, idx) -> pi.getPrice() == price);
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(ProductItem item) {
		if (this.findByProductCode(item.getProdCode()) != null) {
			String msg = String.format("[ProductManager.onAdd()]" +
					" You have same product code(%s)!", item.getProdCode().toString());

			return msg;
		}

		return super.onAdd(item);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id, ProductItem oldItem, ProductItem newItem) {
		try {
			// Product Code 복사
			newItem.setProdCode((ProductCode) oldItem.getProdCode().clone());

			return super.onUpdateById(id, oldItem, newItem);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) {
		return super.onDeleteById(id);
	}
}
