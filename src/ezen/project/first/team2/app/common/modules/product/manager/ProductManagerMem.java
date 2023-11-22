////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.manager;

import java.time.LocalDate;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductManagerMem extends ListManagerMem<ProductItem>
		implements ProductManagerHelper {
	// -------------------------------------------------------------------------

	private static ProductManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductManagerMem();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	@Override
	public ProductItem findByProductCode(ProductCode prodCode) throws Exception {
		return this.find((pi, idx) -> pi.getProdCode().equals(prodCode));
	}

	@Override
	public List<ProductItem> findByRegDate(LocalDate date) throws Exception {
		return this.findItems((pi, idx) -> pi.getRegDate().equals(date));
	}

	@Override
	public ProductItem findByName(String name) throws Exception {
		return this.find((pi, idx) -> pi.getName().equals(name));
	}

	@Override
	public List<ProductItem> findByPrice(int price) throws Exception {
		return this.findItems((pi, idx) -> pi.getPrice() == price);
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(ProductItem item) throws Exception {
		if (this.findByProductCode(item.getProdCode()) != null) {
			String msg = String.format("[ProductManagerMem.onAdd()]" +
					" You have same product code(%s)!", item.getProdCode().toString());

			return msg;
		}

		return super.onAdd(item);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id,
			ProductItem oldItem, ProductItem newItem) throws Exception {

		// Product Code 복사
		newItem.setProdCode((ProductCode) oldItem.getProdCode().clone());

		return super.onUpdateById(id, oldItem, newItem);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) throws Exception {
		return super.onDeleteById(id);
	}
}
