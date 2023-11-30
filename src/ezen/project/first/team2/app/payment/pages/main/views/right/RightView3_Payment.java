package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.SystemUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView3_Payment extends View {

	private static final int PADDING = 20;
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);

	private boolean mIsPaymentComplete = false;

	private JButton mPaymentCompleted_btn;
	private JPanel mBackground_panel;
	private JLabel mCardImg1_Label;
	private JLabel mCardImg2_Label;
	private JLabel mReceiptImg_Label;
	
	private JLabel mArrowImg1_Label;
	private JLabel mArrowImg2_Label;
	
	private ImageIcon mCardImg1_imgIcon;
	private ImageIcon mCardImg2_imgIcon;
	private ImageIcon mReceiptImg_imgIcon;
	private ImageIcon mArrowImg1_imgIcon;
	private ImageIcon mArrowImg2_imgIcon;
	
	
	
	private ProductPurchasing mProdPurchasing;

	
	public RightView3_Payment() {
		super(MainPage.RIGHT_VIEW_PAYMENT_NUM);
	}

	@Override
	protected void onInit() {
		
		mPaymentCompleted_btn = new JButton("결제완료");
		mBackground_panel = new JPanel();
		mCardImg1_Label = new JLabel();
		mCardImg2_Label = new JLabel();
		mReceiptImg_Label = new JLabel();
		mArrowImg1_Label = new JLabel();
		mArrowImg2_Label = new JLabel();
		
		
		// 이미지 설정
		try {
			BufferedImage card1_buff = ImageIO.read(new File("resources/images/rightView3/card1_480x300.png"));
			BufferedImage card2_buff = ImageIO.read(new File("resources/images/rightView3/card2_480x300.png"));
			BufferedImage receipt_buff = ImageIO.read(new File("resources/images/rightView3/receipt_980x960.png"));
			BufferedImage arrow1_buff = ImageIO.read(new File("resources/images/rightView3/arrow1_512x512.png"));
			BufferedImage arrow2_buff = ImageIO.read(new File("resources/images/rightView3/arrow2_512x512.png"));
			
			Image card1_si = card1_buff.getScaledInstance(240, 150, Image.SCALE_SMOOTH);
			Image card2_si = card2_buff.getScaledInstance(240, 150, Image.SCALE_SMOOTH);
			Image receipt_si = receipt_buff.getScaledInstance(245, 240, Image.SCALE_SMOOTH);
			Image arrow1_si = arrow1_buff.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Image arrow2_si = arrow2_buff.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			mCardImg1_imgIcon = new ImageIcon(card1_si);
			mCardImg2_imgIcon = new ImageIcon(card2_si);
			mReceiptImg_imgIcon = new ImageIcon(receipt_si);
			mArrowImg1_imgIcon = new ImageIcon(arrow1_si);
			mArrowImg2_imgIcon = new ImageIcon(arrow2_si);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;

	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridLayout(1, 1));
		
		mBackground_panel.setBackground(Color.WHITE);
		mBackground_panel.setLayout(new BoxLayout(mBackground_panel, BoxLayout.Y_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		//mBackground_panel.add(mPaymentCompleted_btn);
		mBackground_panel.add(mCardImg1_Label);
		mBackground_panel.add(mArrowImg2_Label);
		mBackground_panel.add(mCardImg2_Label);
		mBackground_panel.add(mReceiptImg_Label);
		
		this.add(mBackground_panel);
	}

	@Override
	protected void onAddEventListeners() {
		mPaymentCompleted_btn.addActionListener(e -> {

			mIsPaymentComplete = true;
			RightView0_OrderList.RECEIPT_ISSUANCE = false;

			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

				RightView0_OrderList rv0 = (RightView0_OrderList) mainView
						.getViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

				// 결제 완료가 됐다면 RightView0_OrdetList에 테이블 초기화
				rv0.get_mTableModel().setNumRows(0);
				// 결제 완료가 됐다면 RightView0_OrdetList에 총금액 텍스트필드 초기화
				rv0.get_mSum_tf().setText("");
				// 결제 완료가 됐다면 RightView0_OrdetList에 결제하기 버튼 비활성화
				rv0.deactivateButton();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// 콘솔에 영수증 출력
			System.out.println("right3 - 결제완료버튼 누름");
			System.out.println("right3 - 영수증");
			try {
				ProductOrderDetailsManager.getInstance().iterate((item, idx) -> {
					if (item.getProdOrderId() == mProdPurchasing.getProdOrderId()) {
						System.out.println(item);
					}
					return true;
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
		mCardImg1_Label.setIcon(mCardImg1_imgIcon);
		mCardImg2_Label.setIcon(mCardImg2_imgIcon);
		mReceiptImg_Label.setIcon(mReceiptImg_imgIcon);
		mArrowImg1_Label.setIcon(mArrowImg1_imgIcon);
		mArrowImg2_Label.setIcon(mArrowImg2_imgIcon);
	}

	@Override
	protected void onHide() {
		// 결제완료 버튼을 눌러 결제가 완료되었다면 커밋한다
		if (mIsPaymentComplete) {
			try {
				System.out.println("right3 - 커밋되었습니다");
				mProdPurchasing._6_commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mIsPaymentComplete = false;
		}
	}

}
