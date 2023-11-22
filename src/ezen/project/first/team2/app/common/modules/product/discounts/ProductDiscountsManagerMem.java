////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.discounts;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductDiscountsManagerMem extends ListManagerMem<ProductDiscountItem> {
	// -------------------------------------------------------------------------

	private static ProductDiscountsManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	private ProductDiscountsManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductDiscountsManagerMem getInstance() {
		if (mInstance == null) {
			mInstance = new ProductDiscountsManagerMem();
		}

		return mInstance;
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(ProductDiscountItem item) throws Exception {
		return super.onAdd(item);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id, ProductDiscountItem item) throws Exception {
		return super.onUpdateById(id, item);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) throws Exception {
		return super.onDeleteById(id);
	}

}
