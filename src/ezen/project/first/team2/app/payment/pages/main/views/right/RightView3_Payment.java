package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.modules.product.stocks.ProductStocksManager;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView3_Payment extends View {

	private static final int PADDING = 20;
	
	private static final String PAYMENT_INSERT_CARD_TEXT = "카드를 리더기에 삽입해주세요";
	private static final String	PAYMENT_PROCESSING_TEXT = "결제 처리중 입니다";
	private static final String	PAYMENT_COMPLETED_TEXT = "결제가 완료되었습니다";
	private static final String	PAYMENT_RECEIPT_PROCESSING_TEXT = "영수증 발급중 입니다";
	private static final String	PAYMENT_RECEIPT_ISSUANCE_TEXT = "영수증이 발급되었습니다";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// PaymentText Label
	private static final Font PAYMENT_TEXT_LABEL_FONT = new Font("맑은 고딕", Font.BOLD, 40);
	private static final Color PAYMENT_TEXT_LABEL_FONT_COLOR = new Color(103, 121, 133);

	
	private boolean mIsPaymentComplete = false;

	private JPanel mBackground_panel;
	private JButton mPaymentCompleted_btn;
	
	private JLabel mPaymentIcon_Label;
	private JLabel mPaymentText_Label;
	
	private ImageIcon mCardMachine_imgIcon;
	private ImageIcon mCardImg1_imgIcon;
	private ImageIcon mCardImg2_imgIcon;
	private ImageIcon mReceiptImg_imgIcon;
	private ImageIcon mArrowImg1_imgIcon;
	private ImageIcon mArrowImg2_imgIcon;
	
	private int mProgressValue;
	private Timer mTimer;
	
	GridBagConstraints mGbc;
	
	private ProductPurchasing mProdPurchasing;
	
	
	public RightView3_Payment() {
		super(MainPage.RIGHT_VIEW_PAYMENT_NUM);
	}

	@Override
	protected void onInit() {
		
		mPaymentCompleted_btn = new JButton("결제완료");
		mBackground_panel = new JPanel();
		
		mPaymentIcon_Label = new JLabel();
		mPaymentText_Label = new JLabel();

		
		// 이미지 설정
		try {
			
			BufferedImage cardMachine_buff = ImageIO.read(new File("resources/images/rightView3/cardMachine.png"));
			BufferedImage card1_buff = ImageIO.read(new File("resources/images/rightView3/card1_480x300.png"));
			BufferedImage card2_buff = ImageIO.read(new File("resources/images/rightView3/card2_480x300.png"));
			BufferedImage receipt_buff = ImageIO.read(new File("resources/images/rightView3/receipt_980x960.png"));
			BufferedImage arrow1_buff = ImageIO.read(new File("resources/images/rightView3/arrow1_512x512.png"));
			BufferedImage arrow2_buff = ImageIO.read(new File("resources/images/rightView3/arrow2_512x512.png"));
			
			Image cardMachine_si = cardMachine_buff.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			Image card1_si = card1_buff.getScaledInstance(312, 195, Image.SCALE_SMOOTH);
			Image card2_si = card2_buff.getScaledInstance(312, 195, Image.SCALE_SMOOTH);
			Image receipt_si = receipt_buff.getScaledInstance(245, 240, Image.SCALE_SMOOTH);
			Image arrow1_si = arrow1_buff.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Image arrow2_si = arrow2_buff.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			
			mCardMachine_imgIcon = new ImageIcon(cardMachine_si);
			mCardImg1_imgIcon = new ImageIcon(card1_si);
			mCardImg2_imgIcon = new ImageIcon(card2_si);
			mReceiptImg_imgIcon = new ImageIcon(receipt_si);
			mArrowImg1_imgIcon = new ImageIcon(arrow1_si);
			mArrowImg2_imgIcon = new ImageIcon(arrow2_si);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mGbc = new GridBagConstraints();
		
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
		mBackground_panel.setLayout(new GridBagLayout());
		
		// 디자인 관련 설정
		mPaymentText_Label.setFont(PAYMENT_TEXT_LABEL_FONT);
		mPaymentText_Label.setForeground(PAYMENT_TEXT_LABEL_FONT_COLOR);
	}

	@Override
	protected void onAddCtrls() {
		//mBackground_panel.add(mPaymentCompleted_btn);
		
	//	mGbc.fill = GridBagConstraints.BOTH;
		
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		mBackground_panel.add(mPaymentIcon_Label, mGbc);
		
		mGbc.insets = new Insets(40, 0, 0, 0);
		
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		mBackground_panel.add(mPaymentText_Label, mGbc);
		
		this.add(mBackground_panel);
		
		mPaymentIcon_Label.setIcon(mCardMachine_imgIcon);
		mPaymentText_Label.setText(PAYMENT_INSERT_CARD_TEXT);
		
		
		// 타이머 설정
		mTimer = new Timer(10, e -> {
			if (mProgressValue <= 70) {
                if (mProgressValue == 0) {
                	mPaymentIcon_Label.setIcon(mCardImg1_imgIcon);
            		mPaymentText_Label.setText(PAYMENT_PROCESSING_TEXT);
                } else if (mProgressValue == 10) {
                	mPaymentIcon_Label.setIcon(mArrowImg1_imgIcon);
            		mPaymentText_Label.setText(PAYMENT_PROCESSING_TEXT);
                } else if (mProgressValue == 20) {
                	mPaymentIcon_Label.setIcon(mArrowImg2_imgIcon);
            		mPaymentText_Label.setText(PAYMENT_PROCESSING_TEXT);
                } else if (mProgressValue == 30) {
                	mPaymentIcon_Label.setIcon(mCardImg2_imgIcon);
            		mPaymentText_Label.setText(PAYMENT_COMPLETED_TEXT);
                } else if (mProgressValue == 40) {
                	mPaymentIcon_Label.setIcon(mArrowImg1_imgIcon);
            		mPaymentText_Label.setText(PAYMENT_RECEIPT_PROCESSING_TEXT);
                } else if (mProgressValue == 50) {
                	mPaymentIcon_Label.setIcon(mArrowImg2_imgIcon);
            		mPaymentText_Label.setText(PAYMENT_RECEIPT_PROCESSING_TEXT);
                } else if (mProgressValue == 60) {
                	mPaymentIcon_Label.setIcon(mReceiptImg_imgIcon);
            		mPaymentText_Label.setText(PAYMENT_RECEIPT_ISSUANCE_TEXT);
                }
            } else {
                mTimer.stop();
    			try {
    				MainPage mainPage = (MainPage) this.getPage();
    				mainPage.showPopupViewByNum(MainPage.POPUP_VIEW_RECEIPT_NUM);
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
            }
			mProgressValue++;
		});
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

		});
		
		// 백그라운드를 마우스로 클릭하면 결제 진행
		mBackground_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mTimer.start();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
		mPaymentIcon_Label.setIcon(mCardMachine_imgIcon);
		mPaymentText_Label.setText(PAYMENT_INSERT_CARD_TEXT);
		mProgressValue = 0;
	}

	@Override
	protected void onHide() {
		// 결제완료 후 초기화
		if (mIsPaymentComplete) {
			try {
				System.out.println("right3 - 커밋되었습니다");
				// 결제 완료 커밋
				mProdPurchasing._6_commit();
				
//				// customers -> point 변경
//				var fieldset = new String[] { "point" };
//				var custItem = mProdPurchasing.getProdOrderItem().getCustItem();
//				var custMngr = CustomerManager.getInstance();
//				var _where = String.format("cust_id=%d", custItem.getId());
//				custMngr.doUpdateQuery(custItem, fieldset, _where);
//				
//				// product_stoks -> quantity 변경
//				fieldset = new String[] { "quantity" };
//				var prodDtlItems = mProdPurchasing.getProdOrderItem().getProdOrderDetailItems();
//				var prodStocksMngr = ProductStocksManager.getInstance();
//				for (var prodDtlItem : prodDtlItems) {
//					var prodStocksItem = prodStocksMngr.getItemByProdId(prodDtlItem.getProdId());
//					
//				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			mIsPaymentComplete = false;
		}
	}

}
