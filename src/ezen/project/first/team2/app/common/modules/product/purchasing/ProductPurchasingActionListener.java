////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231124FRI_111200] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.purchasing;

public interface ProductPurchasingActionListener {
	// 상품이 추가된 경우
	public void onAddedProduct(int prodOrderId, int prodId, int quantity);

	// 상품이 업데이트된 경우. 수량 변화.
	public void onUpdatedProduct(int prodOrderId, int prodId, int quantity);
}