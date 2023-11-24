////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231124FRI_105600] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.purchasing;

import java.time.LocalDateTime;

import ezen.project.first.team2.app.common.modules.base.ListActionListener;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManagerMem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailItem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManagerMem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManagerMem;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManagerMem;

public class ProductPurchasing {
	// -------------------------------------------------------------------------

	private ProductPurchasingActionListener mActionListener;

	// 고객 관리자
	private CustomerManagerMem mCustMngr = CustomerManagerMem.getInstance();

	// 상품 관리자
	// private ProductManagerMem mProdMngr = ProductManagerMem.getInstance();

	// 상품 재고 관리자
	private ProductStocksManagerMem mProdStocksMngr = ProductStocksManagerMem.getInstance();

	// 상품 할인 관리자
	private ProductDiscountsManagerMem mProdDiscntMngr = ProductDiscountsManagerMem.getInstance();

	// 구매 내역 관리자
	private ProductOrdersManagerMem mProdOrdersMngr = ProductOrdersManagerMem.getInstance();

	// 상세 구매 내역 관리자
	private ProductOrderDetailsManagerMem mProdOrderDetailMngr = ProductOrderDetailsManagerMem.getInstance();

	// 구매 내역(영수증) ID
	private int mProdOrderId = -1;

	// 구매 내역(영수증) 아이템
	private ProductOrderItem mProdOrderItem;

	// -------------------------------------------------------------------------

	// 생성자
	public ProductPurchasing() {
	}

	// -------------------------------------------------------------------------

	// 구매 내역(영수증) ID 얻기
	public int getProdOrderId() {
		return this.mProdOrderId;
	}

	// 구매 내역(영수증) 아이템 얻기
	public ProductOrderItem getProdOrderItem() {
		return this.mProdOrderItem;
	}

	// 액션 리스너 설정
	public void setActionListener(ProductPurchasingActionListener listener) {
		this.mActionListener = listener;
	}

	// -------------------------------------------------------------------------

	// 1. 구매 내역(영수증) 생성
	public void _1_makeProdOrder() throws Exception {
		// 구매 내역(영수증) ID 발급
		this.mProdOrderId = this.mProdOrdersMngr.getNextID();

		// 구매 내역(영수증) 아이템 생성 => 비회원 + 사용할 포인트 0
		this.mProdOrderItem = new ProductOrderItem(this.mProdOrderId, LocalDateTime.now());
		this.mProdOrdersMngr.add(this.mProdOrderItem);

		// 상세 구매 내역 액션 리스너
		this.mProdOrderDetailMngr.setActionListener(new ListActionListener<ProductOrderDetailItem>() {

			@Override
			public void onInitialized(ListManager<ProductOrderDetailItem> mngr) {
			}

			@Override
			public void onDeinitializing(ListManager<ProductOrderDetailItem> mngr) {
			}

			// 상세 구매 내역이 추가되면 해당 상품 재고 수량 감소
			// => 해당 상품에 재고가 없더라도 일단 수량 감소
			@Override
			public void onAdded(ListManager<ProductOrderDetailItem> mngr, ProductOrderDetailItem item) {
				try {
					mProdStocksMngr.decQuantityByProdId(item.getProdId(), item.getQuantity());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onUpdated(ListManager<ProductOrderDetailItem> mngr,
					ProductOrderDetailItem oldItem, ProductOrderDetailItem newItem) {
			}

			@Override
			public void onDeleted(ListManager<ProductOrderDetailItem> mngr, ProductOrderDetailItem item) {
			}

		});
	}

	// 2. 구매 내역(영수증)에 상품 추가
	public void _2_addProduct(int prodId, int quantity) throws Exception {
		// 상품 할인 아이템
		var pdi = this.mProdDiscntMngr.getItemByProdId(prodId);

		// 구매 내역(영수증)에서 아이템 찾기
		var podi = this.mProdOrderDetailMngr.getItemByProdOrderIdAndProdId(this.mProdOrderId, prodId);

		// 상품이 있다면 수량만 증가
		if (podi != null) {
			int qty = podi.incQuantity(quantity);
			this.mProdOrderDetailMngr.updateQuantity(this.mProdOrderId, prodId, qty);

			// 재고 감소
			this.mProdStocksMngr.decQuantityByProdId(prodId, quantity);

			// 액션 리스너 호출
			if (this.mActionListener != null) {
				this.mActionListener.onUpdatedProduct(this.mProdOrderId, prodId, qty);
			}
		}
		// 상품이 없다면 추가
		else {
			podi = new ProductOrderDetailItem(-1, this.mProdOrderId, prodId, pdi.getId(), quantity);
			this.mProdOrderDetailMngr.add(podi);

			// 액션 리스너 호출
			if (this.mActionListener != null) {
				this.mActionListener.onAddedProduct(this.mProdOrderId, prodId, quantity);
			}
		}
	}

	// 3. 회원 포인트 적립
	public void _3_applyCustomerPoint(int custId, int usedPoint) throws Exception {
		var ci = this.mCustMngr.findById(custId);

		// 고객 ID가 잘못된 경우
		if (ci == null) {
			String msg = String.format("[ProductPurchasing._3_applyCustomerPoint()]" +
					" Invalid customer id(%d)!",
					ci);
			throw new Exception(msg);
		}

		// 비회원인 경우
		if (ci.getId() == CustomerItem.GUEST_ID) {
			String msg = String.format("[ProductPurchasing._3_applyCustomerPoint()]" +
					" Customer id(%d) is guest!",
					CustomerItem.GUEST_ID);
			throw new Exception(msg);
		}

		// 가용 포인트 확인
		if (ci.getPoint() < usedPoint) {
			String msg = String.format("[ProductPurchasing._3_applyCustomerPoint()]" +
					" ci.getPoint()<%d> < usedPoint<%d>",
					ci.getPoint(), usedPoint);
			throw new Exception(msg);
		}

		// 고객 ID 설정
		this.mProdOrderItem.setCustId(custId);

		// 사용할 포인트 설정
		this.mProdOrderItem.setUsedPoint(usedPoint);

		// 사용할 포인트 감소
		ci.decPoint(usedPoint);

		// 적립될 포인트 계산 및 증가
		int earnedPoint = this.mProdOrderItem.calcEarnedPoint();
		ci.incPoint(earnedPoint);
	}

	// 4. 취소
	public void _4_rollback() throws Exception {
		// 회원인 경우 포인트 관련 처리
		var ci = this.mProdOrderItem.getCustItem();
		if (ci != null) {
			// 사용한 포인트 증가
			ci.incPoint(this.mProdOrderItem.getUsedPoint());

			// 적립된 포인트 감소
			ci.decPoint(this.mProdOrderItem.getEarnedPoint());
		}

		// 재고 수량 복구
		this.mProdOrderDetailMngr.iterate((item, idx) -> {
			try {
				if (item.getProdOrderId() == this.mProdOrderId) {
					var prodId = item.getProdId();
					var qty = item.getQuantity();
					this.mProdStocksMngr.incQuantityByProdId(prodId, qty);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		});

		// 상세 구매 내역 폐기
		this.mProdOrderDetailMngr.deleteItems((item, idx) -> {
			return item.getProdOrderId() == this.mProdOrderId;
		});

		// 구매 내역(영수증) 폐기
		this.mProdOrdersMngr.deleteById(this.mProdOrderId);
		this.mProdOrderId = -1;
		this.mProdOrderItem = null;
	}

	// 5. 완료
	public void _5_commit() throws Exception {
		//
	}

	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------
}
