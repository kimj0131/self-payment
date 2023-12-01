package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.common.modules.product.manager.ProductCode;
import ezen.project.first.team2.app.common.modules.product.manager.ProductItem;
import ezen.project.first.team2.app.common.modules.product.manager.ProductManager;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView0_OrderList;

public class PopUpView0_FruitsSelector extends PopupView {

	private static final Dimension VIEW_SIZE = new Dimension(600, 500);
	private static final int PADDING = 10;

	private static final String CANCEL_BTN_TEXT = "취소";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// Cancel Button
	private static final Font CANCEL_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color CANCEL_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color CANCEL_BTN_COLOR = new Color(3, 181, 208);
	
	// FV panel
	private static final Color FV_PANEL_COLOR = new Color(255, 255, 255);
	
	// 채소,과일 이름표
	private static final Font FV_LABEL_FONT = new Font("맑은 고딕", Font.BOLD, 15);
	private static final Color FV_LABEL_FONT_COLOR = new Color(54, 70, 81);

	private static final String BANANA_LABEL_TEXT = "바나나";
	private static final String APPLE_LABEL_TEXT = "사과";
	private static final String ORANGE_LABEL_TEXT = "오렌지";
	private static final String PINEAPPLE_LABEL_TEXT = "파인애플";
	private static final String GRAPE_LABEL_TEXT = "포도";
	private static final String LETTUCE_LABEL_TEXT = "상추";
	private static final String POTATO_LABEL_TEXT = "감자";
	private static final String SWEETPOTATO_LABEL_TEXT = "고구마";
	private static final String RADISH_LABEL_TEXT = "무";
	private static final String PEPPER_LABEL_TEXT = "고추";

	// 이미지 사이즈
	private int mScaled_img_width = 125;
	private int mScaled_img_height = 125;
	
	// 채소&과일 이름표
	private JLabel mBanana_label;
	private JLabel mApple_label;
	private JLabel mOrange_label;
	private JLabel mPineapple_label;
	private JLabel mGrape_label;

	private JLabel mLettuce_label;
	private JLabel mPotato_label;
	private JLabel mSweetPotato_label;
	private JLabel mRadish_label;
	private JLabel mPepper_label;

	// 과일 이미지
	private ImageIcon mBanana_img;
	private ImageIcon mApple_img;
	private ImageIcon mOrange_img;
	private ImageIcon mPineapple_img;
	private ImageIcon mGrape_img;

	// 채소 이미지
	private ImageIcon mLettuce_img;
	private ImageIcon mPotato_img;
	private ImageIcon mSweetPotato_img;
	private ImageIcon mRadish_img;
	private ImageIcon mPepper_img;

	// 과일 버튼
	private JButton mBanana_btn;
	private JButton mApple_btn;
	private JButton mOrange_btn;
	private JButton mPineapple_btn;
	private JButton mGrape_btn;

	// 채소 버튼
	private JButton mLettuce_btn;
	private JButton mPotato_btn;
	private JButton mSweetPotato_btn;
	private JButton mRadish_btn;
	private JButton mPepper_btn;

	// 과채 버튼을 담을 스크롤 패널
	private JPanel mFV_panel;
	private JScrollPane mScrolledFV_pane;

	// 취소 버튼
	private JButton mCancel_btn;

	// GridBagLayout 조절하기 위한 클래스
	private GridBagConstraints mGbc;

	//
	private ProductManager mProdMngr;
	private ProductOrderDetailsManager mProdOrderDetailsMngr;
	private ProductPurchasing mProdPurchasing;

	public PopUpView0_FruitsSelector() {
		super(MainPage.POPUP_VIEW_FRUITS_SELECTOR_NUM, VIEW_SIZE);
	}

	@Override
	protected void onInit() {
		super.onInit();

		// 채소&과일 이름표
		mBanana_label = makeVfLabel(BANANA_LABEL_TEXT, FV_LABEL_FONT);
		mApple_label = makeVfLabel(APPLE_LABEL_TEXT, FV_LABEL_FONT);
		mOrange_label = makeVfLabel(ORANGE_LABEL_TEXT, FV_LABEL_FONT);
		mPineapple_label = makeVfLabel(PINEAPPLE_LABEL_TEXT, FV_LABEL_FONT);
		mGrape_label = makeVfLabel(GRAPE_LABEL_TEXT, FV_LABEL_FONT);

		mLettuce_label = makeVfLabel(LETTUCE_LABEL_TEXT, FV_LABEL_FONT);
		mPotato_label = makeVfLabel(POTATO_LABEL_TEXT, FV_LABEL_FONT);
		mSweetPotato_label = makeVfLabel(SWEETPOTATO_LABEL_TEXT, FV_LABEL_FONT);
		mRadish_label = makeVfLabel(RADISH_LABEL_TEXT, FV_LABEL_FONT);
		mPepper_label = makeVfLabel(PEPPER_LABEL_TEXT, FV_LABEL_FONT);

		// 과일버튼
		mBanana_btn = makeVFbutton();
		mApple_btn = makeVFbutton();
		mOrange_btn = makeVFbutton();
		mPineapple_btn = makeVFbutton();
		mGrape_btn = makeVFbutton();

		// 채소버튼
		mLettuce_btn = makeVFbutton();
		mPotato_btn = makeVFbutton();
		mSweetPotato_btn = makeVFbutton();
		mRadish_btn = makeVFbutton();
		mPepper_btn = makeVFbutton();
		
		// 버튼에 이미지 세팅
		setImageIntoButton();

		// 취소 버튼
		mCancel_btn = new JButton(CANCEL_BTN_TEXT);
		
		mFV_panel = new JPanel();
		mScrolledFV_pane = new JScrollPane(mFV_panel);

		// GridBagLayout 조절하기 위한 클래스
		mGbc = new GridBagConstraints();

		mProdMngr = ProductManager.getInstance();
		mProdOrderDetailsMngr = ProductOrderDetailsManager.getInstance();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());

		mFV_panel.setLayout(new GridBagLayout());
		mFV_panel.setBackground(FV_PANEL_COLOR);
		mFV_panel.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		
		//
		mCancel_btn.setBackground(CANCEL_BTN_COLOR);
		mCancel_btn.setForeground(CANCEL_BTN_FONT_COLOR);
		mCancel_btn.setFont(CANCEL_BTN_FONT);
	}

	@Override
	protected void onAddCtrls() {
		
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 1;
		mGbc.weighty = 1;

		// 0번 행 <과일 버튼 0번>
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		mFV_panel.add(mBanana_btn, mGbc);

		mGbc.gridx = 1;
		mGbc.gridy = 0;
		mFV_panel.add(mApple_btn, mGbc);

		mGbc.gridx = 2;
		mGbc.gridy = 0;
		mFV_panel.add(mOrange_btn, mGbc);

		mGbc.gridx = 3;
		mGbc.gridy = 0;
		mFV_panel.add(mPineapple_btn, mGbc);

		
		// 1번 행 <과일 이름표 0번>
		mGbc.insets = new Insets(0, 0, 20, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		mFV_panel.add(mBanana_label, mGbc);

		mGbc.gridx = 1;
		mGbc.gridy = 1;
		mFV_panel.add(mApple_label, mGbc);

		mGbc.gridx = 2;
		mGbc.gridy = 1;
		mFV_panel.add(mOrange_label, mGbc);

		mGbc.gridx = 3;
		mGbc.gridy = 1;
		mFV_panel.add(mPineapple_label, mGbc);

		
		// 2번 행 <과일 버튼 1번>
		mGbc.insets = new Insets(0, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 2;
		mFV_panel.add(mGrape_btn, mGbc);

		// 3번 행 <과일 이름표 1번>
		mGbc.insets = new Insets(0, 0, 20, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 3;
		mFV_panel.add(mGrape_label, mGbc);

		// 4번 행 <채소 버튼 0번>
		mGbc.insets = new Insets(0, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 4;
		mFV_panel.add(mLettuce_btn, mGbc);

		mGbc.gridx = 1;
		mGbc.gridy = 4;
		mFV_panel.add(mPotato_btn, mGbc);

		mGbc.gridx = 2;
		mGbc.gridy = 4;
		mFV_panel.add(mSweetPotato_btn, mGbc);

		mGbc.gridx = 3;
		mGbc.gridy = 4;
		mFV_panel.add(mRadish_btn, mGbc);

		// 5번 행 <채소 이름표 0번>
		mGbc.insets = new Insets(0, 0, 20, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 5;
		mFV_panel.add(mLettuce_label, mGbc);

		mGbc.gridx = 1;
		mGbc.gridy = 5;
		mFV_panel.add(mPotato_label, mGbc);

		mGbc.gridx = 2;
		mGbc.gridy = 5;
		mFV_panel.add(mSweetPotato_label, mGbc);

		mGbc.gridx = 3;
		mGbc.gridy = 5;
		mFV_panel.add(mRadish_label, mGbc);

		// 6번 행 <채소 버튼 1번>
		mGbc.insets = new Insets(0, 0, 0, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 6;
		mFV_panel.add(mPepper_btn, mGbc);

		// 7번 행 <채소 이름표 1번>
		mGbc.insets = new Insets(0, 0, 20, 0);
		mGbc.gridx = 0;
		mGbc.gridy = 7;
		mFV_panel.add(mPepper_label, mGbc);

		/////////////////////////////////////////////////////////////
		
		mGbc.insets = new Insets(0, 0, 10, 0);
		
		mGbc.weightx = 1;
		mGbc.weighty = 1;
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mScrolledFV_pane, mGbc);
		
		mGbc.insets = new Insets(0, 0, 0, 0);
		
		mGbc.weighty = 0.05;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(mCancel_btn, mGbc);
		
	}

	@Override
	protected void onAddEventListeners() {
		mCancel_btn.addActionListener(e -> {
			performClose();
		});

		ActionListener listener = e -> {
			JButton btn = (JButton) e.getSource();
			try {
				if (btn == mBanana_btn) {
					ProductItem bananaItem = mProdMngr.findByProductCode(new ProductCode("F001"));
					interactToRightView0(bananaItem);
				} else if (btn == mApple_btn) {
					ProductItem appleItem = mProdMngr.findByProductCode(new ProductCode("F002"));
					interactToRightView0(appleItem);
				} else if (btn == mOrange_btn) {
					ProductItem orangeItem = mProdMngr.findByProductCode(new ProductCode("F003"));
					interactToRightView0(orangeItem);
				} else if (btn == mPineapple_btn) {
					ProductItem pineappleItem = mProdMngr.findByProductCode(new ProductCode("F004"));
					interactToRightView0(pineappleItem);
				} else if (btn == mGrape_btn) {
					ProductItem grapeItem = mProdMngr.findByProductCode(new ProductCode("F005"));
					interactToRightView0(grapeItem);
				} else if (btn == mLettuce_btn) {
					ProductItem lettuceItem = mProdMngr.findByProductCode(new ProductCode("V001"));
					interactToRightView0(lettuceItem);
				} else if (btn == mPotato_btn) {
					ProductItem potatoItem = mProdMngr.findByProductCode(new ProductCode("V002"));
					interactToRightView0(potatoItem);
				} else if (btn == mSweetPotato_btn) {
					ProductItem sweetpotatoItem = mProdMngr.findByProductCode(new ProductCode("V003"));
					interactToRightView0(sweetpotatoItem);
				} else if (btn == mRadish_btn) {
					ProductItem RadishItem = mProdMngr.findByProductCode(new ProductCode("V004"));
					interactToRightView0(RadishItem);
				} else if (btn == mPepper_btn) {

				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		};
		
		// 과일버튼
		mBanana_btn.addActionListener(listener);
		mApple_btn.addActionListener(listener);
		mOrange_btn.addActionListener(listener);
		mPineapple_btn.addActionListener(listener);
		mGrape_btn.addActionListener(listener);

		// 채소버튼
		mLettuce_btn.addActionListener(listener);
		mPotato_btn.addActionListener(listener);
		mSweetPotato_btn.addActionListener(listener);
		mRadish_btn.addActionListener(listener);
		mPepper_btn.addActionListener(listener);
	}

	@Override
	protected void onShow(boolean firstTime) {}

	@Override
	protected void onHide() {
	}
	
	private void setImageIntoButton() {
		// 과일&채소 이미지 세팅
		try {
			// BuffImage
			BufferedImage banana_buff = ImageIO.read(new File("resources/images/fruits&vegetables/banana.png"));
			BufferedImage apple_buff = ImageIO.read(new File("resources/images/fruits&vegetables/apple.png"));
			BufferedImage orange_buff = ImageIO.read(new File("resources/images/fruits&vegetables/orange.png"));
			BufferedImage pineapple_buff = ImageIO.read(new File("resources/images/fruits&vegetables/pineapple.jpg"));
			BufferedImage grape_buff = ImageIO.read(new File("resources/images/fruits&vegetables/grape.png"));

			BufferedImage lettuce_buff = ImageIO.read(new File("resources/images/fruits&vegetables/lettuce.png"));
			BufferedImage potato_buff = ImageIO.read(new File("resources/images/fruits&vegetables/potato.png"));
			BufferedImage sweetpotato_buff = ImageIO
					.read(new File("resources/images/fruits&vegetables/sweetpotato.png"));
			BufferedImage radish_buff = ImageIO.read(new File("resources/images/fruits&vegetables/radish.png"));
			BufferedImage pepper_buff = ImageIO.read(new File("resources/images/fruits&vegetables/pepper.png"));

			// ScaledImage
			Image banana_scaledImg = banana_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image apple_scaledImg = apple_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image orange_scaledImg = orange_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image pineapple_scaledImg = pineapple_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image grape_scaledImg = grape_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);

			Image lettuce_scaledImg = lettuce_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image potato_scaledImg = potato_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image sweetPotato_scaledImg = sweetpotato_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image radish_scaledImg = radish_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);
			Image pepper_scaledImg = pepper_buff.getScaledInstance(mScaled_img_width, mScaled_img_height,
					Image.SCALE_SMOOTH);

			//
			mBanana_img = new ImageIcon(banana_scaledImg);
			mApple_img = new ImageIcon(apple_scaledImg);
			mOrange_img = new ImageIcon(orange_scaledImg);
			mPineapple_img = new ImageIcon(pineapple_scaledImg);
			mGrape_img = new ImageIcon(grape_scaledImg);

			mLettuce_img = new ImageIcon(lettuce_scaledImg);
			mPotato_img = new ImageIcon(potato_scaledImg);
			mSweetPotato_img = new ImageIcon(sweetPotato_scaledImg);
			mRadish_img = new ImageIcon(radish_scaledImg);
			mPepper_img = new ImageIcon(pepper_scaledImg);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 버튼에 이미지 달기
		mBanana_btn.setIcon(mBanana_img);
		mApple_btn.setIcon(mApple_img);
		mOrange_btn.setIcon(mOrange_img);
		mPineapple_btn.setIcon(mPineapple_img);
		mGrape_btn.setIcon(mGrape_img);

		mLettuce_btn.setIcon(mLettuce_img);
		mPotato_btn.setIcon(mPotato_img);
		mSweetPotato_btn.setIcon(mSweetPotato_img);
		mRadish_btn.setIcon(mRadish_img);
		mPepper_btn.setIcon(mPepper_img);
	}
	
	// 버튼 눌렀을때 상호작용 메서드
	private void interactToRightView0(ProductItem prodItem) {
		try {
			MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
			RightView0_OrderList rv0 = (RightView0_OrderList) mainView.getViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

			// rightView0에 있는 테이블 초기화
			rv0.get_mTableModel().setNumRows(0);

			// 상세 구매내역에 상품 추가
			mProdPurchasing._2_addProduct(prodItem.getId(), 1);
			
			// 상품 추가됐으면 RightView0에 있는 결제하기 버튼 활성화
			rv0.activateButton();

			// rightView0에 있는 테이블에 상품 추가 / 합계 표시
			mProdOrderDetailsMngr.iterate((item, idx) -> {
				try {
					if (item.getProdOrderId() == mProdPurchasing.getProdOrderId()) {

						String prodCode = item.getProdItem().getProdCodeStr();
						String prodName = mProdMngr.findById(item.getProdId()).getName();
						String prodQty = String.valueOf(item.getQuantity());
						int prodPrice = mProdMngr.findById(item.getProdId()).getPrice() * item.getQuantity();

						String[] row = new String[] { prodCode, prodName, prodQty,
								UnitUtils.numToCurrencyStr(prodPrice) };

						rv0.get_mTableModel().addRow(row);
						rv0.get_mSum_tf().setText(
								UnitUtils.numToCurrencyStr(mProdPurchasing.getProdOrderItem().getOrgTotalPrice())
										+ "원");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				return true;
			});

			performClose();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	//
	private JButton makeVFbutton() {
		JButton vfBtn = new JButton();
		vfBtn.setMargin(new Insets(0, 0, 0, 0));
		vfBtn.setContentAreaFilled(false);
		vfBtn.setBorderPainted(false);
		return vfBtn;
	}
	
	private JLabel makeVfLabel(String text, Font font) {
		JLabel vfLabel = new JLabel(text);
		vfLabel.setFont(font);
		vfLabel.setForeground(FV_LABEL_FONT_COLOR);
		vfLabel.setHorizontalAlignment(JLabel.RIGHT);
		vfLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 7));
		return vfLabel;
	}
}