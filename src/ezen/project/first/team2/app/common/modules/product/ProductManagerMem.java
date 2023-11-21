////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product;

import ezen.project.first.team2.app.common.modules.base.ListManagerMem;

public class ProductManagerMem extends ListManagerMem<ProductInfo> {
	// -------------------------------------------------------------------------

	private static ProductManagerMem mInstance = null;

	// -------------------------------------------------------------------------

	// 생성자
	ProductManagerMem() {
	}

	// 인스턴스 얻기
	public static ProductManagerMem getInstance() {
		if (ProductManagerMem.mInstance == null) {
			ProductManagerMem.mInstance = new ProductManagerMem();
		}

		return ProductManagerMem.mInstance;
	}

	// -------------------------------------------------------------------------

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onAdd(ProductInfo info) {
		try {
			// 제품 코드 검색
			var info3 = this.find((info2, idx) -> info2.getProdCode().equals(info.getProdCode()));
			if (info3 != null) {
				String msg = String.format("[ProductmanagerMem.onAdd()]" +
						" You have same product code(%s)!", info.getProdCode().toString());

				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.onAdd(info);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onUpdateById(int id, ProductInfo info) {
		return super.onUpdateById(id, info);
	}

	// -> 성공: 빈 문자열 리턴, 실패: 예외 에러 메시지 리턴
	@Override
	protected String onDeleteById(int id) {
		return super.onDeleteById(id);
	}
}
