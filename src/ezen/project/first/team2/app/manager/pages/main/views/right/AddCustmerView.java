package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxType;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AddCustmerView extends View {

    CustomerManagerMem memMngr = CustomerManagerMem.getInstance();

    JLabel mLabelInfo = new JLabel("고객 추가뷰 초기화면입니다");

    // 고객정보 기입란 패널
    JPanel mPanelAddCust = new JPanel();
    // 고객정보 기입란 왼쪽패널
    JPanel mPanelAddCustLeft = new JPanel();
    // 고객정보 기입란 오른쪽패널
    JPanel mPanelAddCustRight = new JPanel();

    JLabel mLabelSignUp = new JLabel("<html><br>고객정보<br><br><br></html>");

    /////////////////////////////////////////////////
    // ## 가입일 비활성화된 TextField 추가?
    // ## >> setText로 미리 채워넣고, 입력을 비활성화
    /////////////////////////////////////////////////
    // 고객정보 기입란 컴포넌트들
    // 가입일은 현재시간으로 자동 추가
    // 각 속성들의 패널로 텍스트필드 왼쪽에 속성이 들어갈수 있게설정
    JPanel mPanelAddId = new JPanel();
    JLabel mLabelAddId = new JLabel("　　고객번호 : ");
    JTextField mTextFieldAddId = new JTextField(10);

    JPanel mPanelAddName = new JPanel();
    JLabel mLabelAddName = new JLabel("　　* 고객명 : ");
    JTextField mTextFieldAddName = new JTextField("", 10);

    JPanel mPanelAddBirthday = new JPanel();
    JLabel mLabelAddBirthday = new JLabel("　* 생년월일 : ");
    //
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    JFormattedTextField mTextFieldAddBirthday = new JFormattedTextField(format);

    JPanel mPanelAddPhoneNum = new JPanel();
    JLabel mLabelAddPhoneNum = new JLabel("* 휴대폰번호 : ");
    JTextField mTextFieldAddPhoneNum = new JTextField(10);

    JPanel mPanelAddRemark = new JPanel();
    JLabel mLabelAddRemark = new JLabel("비 고 : ");
    JTextField mTextFieldAddRemark = new JTextField(20);

    JButton mBtnAddCust = new JButton("고객 추가");
    JButton mBtnAddTextFieldCust = new JButton("[테스트]자동기입");

    public AddCustmerView() {
        super(MainPage.VIEW_NUM_CUST_ADD);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelAddCust.setLayout(new GridLayout(1, 2));
        this.mPanelAddCustLeft.setLayout(new BoxLayout(mPanelAddCustLeft, BoxLayout.Y_AXIS));
        this.mPanelAddCustRight.setLayout(new BoxLayout(mPanelAddCustRight, BoxLayout.Y_AXIS));
    }

    @Override
    protected void onAddCtrls() {

        // 컴포넌트 설정
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        this.mTextFieldAddBirthday.setColumns(10);
        try {
            this.mTextFieldAddId.setText(memMngr.getNextID() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mTextFieldAddId.setEnabled(false);

        // 컴포넌트 삽입
        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelAddCust, BorderLayout.CENTER);
        this.mPanelAddCust.add(mPanelAddCustLeft);
        this.mPanelAddCust.add(mPanelAddCustRight);

        this.mPanelAddCustLeft.add(mLabelSignUp);
        this.mPanelAddCustLeft.add(mPanelAddId);
        this.mPanelAddId.add(mLabelAddId);
        this.mPanelAddId.add(mTextFieldAddId);

        this.mPanelAddCustLeft.add(mPanelAddName);
        this.mPanelAddName.add(mLabelAddName);
        this.mPanelAddName.add(mTextFieldAddName);

        this.mPanelAddCustLeft.add(mPanelAddBirthday);
        this.mPanelAddBirthday.add(mLabelAddBirthday);
        this.mPanelAddBirthday.add(mTextFieldAddBirthday);

        this.mPanelAddCustLeft.add(mPanelAddPhoneNum);
        this.mPanelAddPhoneNum.add(mLabelAddPhoneNum);
        this.mPanelAddPhoneNum.add(mTextFieldAddPhoneNum);

        this.mPanelAddCustLeft.add(mPanelAddRemark);
        this.mPanelAddRemark.add(mLabelAddRemark);
        this.mPanelAddRemark.add(mTextFieldAddRemark);

        this.mPanelAddCustRight.add(mBtnAddCust, BorderLayout.SOUTH);
        this.mPanelAddCustRight.add(mBtnAddTextFieldCust, BorderLayout.SOUTH);
    }

    @Override
    protected void onAddEventListeners() {
        mTextFieldAddBirthday.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9') || (ch == KeyEvent.VK_BACK_SPACE) ||
                        (ch == KeyEvent.VK_DELETE))) {
                    UiUtils.showMsgBox("유효한 날짜를 입력하세요", "");
                    e.consume();
                }
            }
        });

        ActionListener listener = e -> {

            // 테스트용 자동기입
            if (e.getSource() == mBtnAddTextFieldCust) {
                this.setTestCustValue();
            }

            ////////////////////////////////////////////////////
            // ## 231121 KJH >> 휴대폰번호 중복확인도 필요할까요?
            ////////////////////////////////////////////////////
            // Remark를 제외한 TextField를 채웠는지 확인
            if (mTextFieldAddName.getText().length() > 0 &&
                    mTextFieldAddPhoneNum.getText().length() > 0 &&
                    mTextFieldAddBirthday.getText().length() > 0
            // && mTextFieldAddId.getText().length() > 0
            ) {

                if (e.getSource() == mBtnAddCust) {
                    this.setCustValue();
                    // 입력, 추가 완료 후 TextField 초기화
                    try {
                        this.mTextFieldAddId.setText(memMngr.getNextID() + "");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    this.mTextFieldAddName.setText("");
                    this.mTextFieldAddPhoneNum.setText("");
                    this.mTextFieldAddBirthday.setText("");
                    this.mTextFieldAddRemark.setText("");
                }

            } else {
                // 필수입력사항 미입력시 경고메세지박스 출력
                UiUtils.showMsgBox("* 는 필수입력사항입니다",
                        "", MsgBoxType.Warn);
            }

        };

        // 입력된 데이터를 저장
        this.mBtnAddCust.addActionListener(listener);
        // 테스트용 자동기입
        this.mBtnAddTextFieldCust.addActionListener(listener);

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[AddCustmerView.onShow()]");

    }

    @Override
    protected void onHide() {
        System.out.println("[AddCustmerView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont2);

        JPanel[] panels = { mPanelAddCust, mPanelAddId, mPanelAddBirthday,
                mPanelAddName, mPanelAddPhoneNum, mPanelAddRemark };
        for (JPanel Panel : panels) {
            for (int i = 0; i < Panel.getComponents().length; i++) {
                JComponent cp = (JComponent) Panel.getComponents()[i];
                cp.setFont(main.mFont2);
            }
        }
    }

    // textField에 작성한 내용을 추가
    private void setCustValue() {
        CustomerItem customerItem = new CustomerItem();

        try {

            int defaultPoint = 0;
            int custId = memMngr.getNextID();
            LocalDate joinCustDate = LocalDate.now();

            // 생년월일 LocalDate로 변환
            String dateStr = mTextFieldAddBirthday.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "yyyyMMdd");
            LocalDate custBirthDate = LocalDate.parse(dateStr, formatter);

            customerItem.setValues(custId, joinCustDate, mTextFieldAddName.getText(),
                    custBirthDate, mTextFieldAddPhoneNum.getText(),
                    defaultPoint, mTextFieldAddRemark.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            memMngr.add(customerItem);
            System.out.println("add 완료");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // System.out.println("추가된 정보" + memberInfo.toString());
    }

    // 테스트용 자동기입 메소드
    private void setTestCustValue() {
        try {
            int nextNum = memMngr.getNextID();
            this.mTextFieldAddId.setText("" + nextNum);
            this.mTextFieldAddName.setText("김철수");
            this.mTextFieldAddBirthday.setText("20001010");
            this.mTextFieldAddPhoneNum.setText("010-0000-0001");
            this.mTextFieldAddRemark.setText("test Custmer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}