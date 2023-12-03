////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231124FRI_105600] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.purchasing;

import java.time.LocalDateTime;

import ezen.project.first.team2.app.common.modules.base.ListActionAdapter;
import ezen.project.first.team2.app.common.modules.base.ListManager;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.discounts.ProductDiscountsManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailItem;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManager;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;

public class ProductPurchasing {
	// -------------------------------------------------------------------------

	private ProductPurchasingActionListener mActionListener;

	// 고객 관리자
	private CustomerManager mCustMngr = CustomerManager.getInstance();

	// 상품 관리자
	// private ProductManagerMem mProdMngr = ProductManager.getInstance();

	// 상품 재고 관리자
	private ProductStocksManager mProdStocksMngr = ProductStocksManager.getInstance();

	// 상품 할인 관리자
	private ProductDiscountsManager mProdDiscntMngr = ProductDiscountsManager.getInstance();

	// 구매 내역 관리자
	private ProductOrdersManager mProdOrdersMngr = ProductOrdersManager.getInstance();

	// 상세 구매 내역 관리자
	private ProductOrderDetailsManager mProdOrderDetailMngr = ProductOrderDetailsManager.getInstance();

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
		this.mProdOrderId = this.mProdOrdersMngr.getNextIdFromDb("prod_order_id");

		// 상세 구매 내역 관리자에 마지막 구매 상품 추가
		int lastId = this.mProdOrderDetailMngr.getMaxValueFromDb("prod_order_detail_id");
		// this.mProdOrderDetailMngr.deleteAllItems();
		this.mProdOrderDetailMngr.doSelectQuery(null, null, "prod_order_detail_id=" + lastId, null);
		// var podi = this.mProdOrderDetailMngr.getFirstItem();
		// if (podi != null) {
		// this.mProdOrderDetailMngr.add(podi);
		// this.mProdOrderDetailMngr.iterate();
		// }

		// 구매 내역(영수증) 아이템 생성 => 비회원 + 사용할 포인트 0
		this.mProdOrderItem = new ProductOrderItem(this.mProdOrderId, LocalDateTime.now());
		this.mProdOrdersMngr.add(this.mProdOrderItem);

		// 상세 구매 내역 액션 리스너
		this.mProdOrderDetailMngr.setActionListener(new ListActionAdapter<ProductOrderDetailItem>() {
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
			// int podiId =
			// this.mProdOrderDetailMngr.getNextIdFromDb("prod_order_detail_id");
			int podiId = this.mProdOrderDetailMngr.getNextID();
			podi = new ProductOrderDetailItem(podiId, this.mProdOrderId, prodId, pdi.getId(), quantity);
			this.mProdOrderDetailMngr.add(podi);

			// 액션 리스너 호출
			if (this.mActionListener != null) {
				this.mActionListener.onAddedProduct(this.mProdOrderId, prodId, quantity);
			}
		}
	}

	// 3. 회원 포인트 적립
	public void _3_applyCustomerPoint(int custId) throws Exception {
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

		// 고객 ID 설정
		this.mProdOrderItem.setCustId(custId);
	}

	// 4. 사용할 포인트 설정
	public void _4_setUsedPoint(int point) throws Exception {
		var ci = this.mProdOrderItem.getCustItem();
		if (ci == null) {
			String msg = String.format("[ProductPurchasing._3_applyCustomerPoint()]" +
					" You are not a member!!");
			throw new Exception(msg);
		}

		// 가용 포인트 확인
		if (ci.getPoint() < point) {
			String msg = String.format("[ProductPurchasing._4_setUsedPoint()]" +
					" ci.getPoint()<%d> < usedPoint<%d>",
					ci.getPoint(), point);
			throw new Exception(msg);
		}

		// 사용할 포인트 설정
		this.mProdOrderItem.setUsedPoint(point);
	}

	// 4. 취소
	public void _5_rollback() throws Exception {
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
	public void _6_commit() throws Exception {
		// 포인트 처리
		var ci = this.mProdOrderItem.getCustItem();
		if (ci.getId() != CustomerItem.GUEST_ID) {
			// 포인트 감소
			ci.decPoint(this.mProdOrderItem.getUsedPoint());

			// 적립될 포인트 계산 및 증가
			int earnedPoint = this.mProdOrderItem.calcEarnedPoint();
			ci.incPoint(earnedPoint);
		}

		// -----------------------------------------------------------
		// DB 저장
		// -----------------------------------------------------------

		// 고객 정보 저장
		if (ci != null) {
			this.mCustMngr.doUpdateQuery(ci, null, "cust_id=" + ci.getId());
		}

		// 상세 구매 내역, 재고 정보 저장
		this.mProdOrderDetailMngr.iterate((item, idx) -> {
			try {
				// 상세 구매 내역
				if (item.getProdOrderId() == this.mProdOrderId) {
					// 상세 구매 내역 저장
					this.mProdOrderDetailMngr.doInsertQuery(item);

					// 재고 정보 저장
					int prodId = item.getProdId();
					var psi = this.mProdStocksMngr.getItemByProdId(prodId);
					this.mProdStocksMngr.doUpdateQuery(psi, null, "prod_id=" + prodId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		});

		// 구매 내역(영수증) 저장
		this.mProdOrdersMngr.doInsertQuery(this.mProdOrderItem);
	}

	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------
}
