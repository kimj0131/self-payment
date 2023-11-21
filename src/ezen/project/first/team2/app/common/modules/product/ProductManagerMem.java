////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_172400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product;

import java.util.ArrayList;
import java.util.List;

import ezen.project.first.team2.app.common.modules.base.ListManager;

public class ProductManagerMem extends ListManager<ProductInfo> {
	// -------------------------------------------------------------------------

	private static ProductManagerMem mInstance = null;

	private List<ProductInfo> mProdList = new ArrayList<>();

	// -------------------------------------------------------------------------

	// 생성자
	ProductManagerMem() {
		//
	}

	// 인스턴스 얻기
	public static ProductManagerMem getInstance() {
		if (ProductManagerMem.mInstance == null) {
			ProductManagerMem.mInstance = new ProductManagerMem();
		}

		return ProductManagerMem.mInstance;
	}

	// -------------------------------------------------------------------------

	@Override
	protected void onInit() {
		System.out.println("[MembermanagerMem.onInit()]");
	}

	@Override
	protected void onDeinit() {
		System.out.println("[MembermanagerMem.onDeinit()]");
	}

	@Override
	protected int onGetNextID() {
		return this.mProdList.size();
	}

	@Override
	protected void onAdd(ProductInfo info) {
		this.mProdList.add(info);
	}

	@Override
	protected void onDeleteById(int id) {
		this.mProdList.removeIf(info -> info.getId() == id);
	}

	@Override
	protected void onUpdateById(int id, ProductInfo info) {
		for (var _info : this.mProdList) {
			if (_info.getId() == id) {
				_info.setValuesFrom(info);

				return;
			}
		}
	}

	@Override
	protected boolean onIsDuplicatedId(int id) {
		for (var info : this.mProdList) {
			if (info.getId() == id)
				return true;
		}

		return false;
	}

	@Override
	protected int onGetCount() {
		return this.mProdList.size();
	}

	@Override
	protected void onIterate(Iterator<ProductInfo> iterator) {
		for (var info : this.mProdList) {
			if (!iterator.onGetItem(info))
				break;
		}
	}
}
