package ezen.project.first.team2.app.manager.pages.main.views.right.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxType;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AddCustomerView extends View {

    CustomerManager custMngr = CustomerManager.getInstance();

    JLabel mLabelInfo = new JLabel("고객 데이터 추가");

    // 고객리스트, 고객추가 패널
    JPanel mPanelListAdd = new JPanel();
    // 고객리스트 패널
    JPanel mPanelList = new JPanel();
    // 고객추가 패널
    JPanel mPanelAdd = new JPanel();

    JPanel mPanelPanelInfo = new JPanel();
    JLabel mLabelPanelInfo = new JLabel("고객 정보 입력");

    // 고객정보 기입란 컴포넌트들
    // 각 속성들의 패널로 텍스트필드 왼쪽에 속성이 들어갈수 있게설정
    JPanel mPanelAddIdName = new JPanel();
    JLabel mLabelAddId = new JLabel("고객번호");
    JTextField mTextFieldAddId = new JTextField(5);
    JLabel mLabelAddName = new JLabel("* 고객명");
    JTextField mTextFieldAddName = new JTextField("", 10);

    JPanel mPanelAddBirthdayPhoneNum = new JPanel();
    JLabel mLabelAddBirthday = new JLabel("* 생년월일");
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    JFormattedTextField mTextFieldAddBirthday = new JFormattedTextField(format);
    JLabel mLabelAddPhoneNum = new JLabel("* 휴대폰번호");
    JTextField mTextFieldAddPhoneNum = new JTextField(10);

    JPanel mPanelAddPointRemark = new JPanel();
    JLabel mLabelAddPoint = new JLabel("보유 포인트");
    JTextField mTextFieldAddPoint = new JTextField(10);
    JLabel mLabelAddRemark = new JLabel("비 고");
    JTextField mTextFieldAddRemark = new JTextField(20);

    // 고객 추가 버튼
    JPanel mPanelAddBtn = new JPanel();
    JButton mBtnAddCustComplete = new JButton("고객 추가");
    // 테스트용
    JButton mBtnAddTextFieldCust = new JButton("[테스트]자동기입");

    // 고객 리스트 테이블
    JTable mTableCustList;
    JScrollPane mScroll;

    public AddCustomerView() {
        super(MainPage.VIEW_NUM_CUST_ADD);
    }

    @Override
    protected void onInit() {
        // 테이블 설정
        try {
            Object[] mPropertyColumn = {
                    "고객번호", "가입일", "고객명", "생년월일", "휴대폰번호", "보유 포인트", "비고"
            };
            Object[][] mProdListRows = new Object[mPropertyColumn.length][10];

            DefaultTableModel model = new DefaultTableModel(mProdListRows, mPropertyColumn) {
                // 셀 내용 수정 불가
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                };
            };

            mTableCustList = new JTable(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelList.setLayout(new BorderLayout());
        this.mPanelListAdd.setLayout(new GridLayout(2, 1));
        this.mPanelAdd.setLayout(new BoxLayout(mPanelAdd, BoxLayout.Y_AXIS));

        this.mPanelAddIdName.setLayout(new GridLayout(1, 2));
        this.mPanelAddBirthdayPhoneNum.setLayout(new GridLayout(1, 2));
        this.mPanelAddPointRemark.setLayout(new GridLayout(1, 2));

    }

    @Override
    protected void onAddCtrls() {
        // 컴포넌트 설정
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);
        // 테이블
        this.mTableCustList.getTableHeader().setReorderingAllowed(false);
        // 스크롤 설정
        this.mScroll = new JScrollPane(mTableCustList);
        this.mScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.mScroll.setBorder(
                BorderFactory.createEmptyBorder(10, 30, 30, 30));
        // 고객번호, 포인트 접근 비활성화
        this.mTextFieldAddId.setEnabled(false);
        this.mTextFieldAddPoint.setEnabled(false);

        this.mTextFieldAddBirthday.setColumns(10);
        this.mTextFieldAddBirthday.setText("ex)19001231");
        this.mTextFieldAddBirthday.setToolTipText("ex)19001231");
        this.mTextFieldAddPoint.setText("0");
        try {
            // 고객번호는 자동으로 다음번호를 받는다.
            this.mTextFieldAddId.setText(String.valueOf(custMngr.getNextID()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 버튼설정
        this.mBtnAddCustComplete.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.mBtnAddTextFieldCust.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 라벨설정
        this.mLabelPanelInfo.setHorizontalAlignment(JLabel.CENTER);
        this.mLabelAddId.setHorizontalAlignment(JLabel.CENTER);
        this.mLabelAddName.setHorizontalAlignment(JLabel.CENTER);
        this.mLabelAddBirthday.setHorizontalAlignment(JLabel.CENTER);
        this.mLabelAddPhoneNum.setHorizontalAlignment(JLabel.CENTER);
        this.mLabelAddPoint.setHorizontalAlignment(JLabel.CENTER);
        this.mLabelAddRemark.setHorizontalAlignment(JLabel.CENTER);

        // 패널설정
        this.mPanelAdd.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.mPanelPanelInfo.setBorder(
                BorderFactory.createEmptyBorder(10, 200, 10, 200));
        this.mPanelAddIdName.setBorder(
                BorderFactory.createEmptyBorder(10, 200, 10, 200));
        this.mPanelAddBirthdayPhoneNum.setBorder(
                BorderFactory.createEmptyBorder(10, 200, 10, 200));
        this.mPanelAddPointRemark.setBorder(
                BorderFactory.createEmptyBorder(10, 200, 10, 200));

        // 컴포넌트 추가
        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelListAdd, BorderLayout.CENTER);

        this.mPanelListAdd.add(mPanelList);
        this.mPanelListAdd.add(mPanelAdd);
        this.mPanelList.add(mScroll);

        // 고객 추가 패널
        this.mPanelAdd.add(mPanelPanelInfo);
        this.mPanelPanelInfo.add(mLabelPanelInfo);

        this.mPanelAdd.add(mPanelAddIdName);
        this.mPanelAdd.add(mPanelAddBirthdayPhoneNum);
        this.mPanelAdd.add(mPanelAddPointRemark);

        this.mPanelAddIdName.add(mLabelAddId);
        this.mPanelAddIdName.add(mTextFieldAddId);
        this.mPanelAddIdName.add(mLabelAddName);
        this.mPanelAddIdName.add(mTextFieldAddName);

        this.mPanelAddBirthdayPhoneNum.add(mLabelAddBirthday);
        this.mPanelAddBirthdayPhoneNum.add(mTextFieldAddBirthday);
        this.mPanelAddBirthdayPhoneNum.add(mLabelAddPhoneNum);
        this.mPanelAddBirthdayPhoneNum.add(mTextFieldAddPhoneNum);

        this.mPanelAddPointRemark.add(mLabelAddPoint);
        this.mPanelAddPointRemark.add(mTextFieldAddPoint);
        this.mPanelAddPointRemark.add(mLabelAddRemark);
        this.mPanelAddPointRemark.add(mTextFieldAddRemark);

        this.mPanelAdd.add(mPanelAddBtn);
        this.mPanelAddBtn.add(mBtnAddCustComplete);
        //
        this.mPanelAddBtn.add(mBtnAddTextFieldCust);
        //

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
        mTextFieldAddBirthday.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                mTextFieldAddBirthday.setText("");
            }

        });

        ActionListener listener = e -> {

            // 테스트용 자동기입
            if (e.getSource() == mBtnAddTextFieldCust) {
                this.testCustAddTextField();
            }

            // Remark, Id를 제외한 TextField를 채웠는지 확인
            if (mTextFieldAddName.getText().length() > 0 &&
                    mTextFieldAddPhoneNum.getText().length() > 0 &&
                    mTextFieldAddBirthday.getText().length() > 0) {

                if (e.getSource() == mBtnAddCustComplete) {
                    // 입력, 추가 완료 후 TextField 초기화
                    try {
                        CustomerItem customerItem = new CustomerItem();

                        int custId = custMngr.getNextID();
                        int defaultPoint = 0;
                        LocalDate joinCustDate = LocalDate.now();

                        String dateStr = mTextFieldAddBirthday.getText();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                "yyyyMMdd");
                        LocalDate custBirthDate = LocalDate.parse(dateStr, formatter);

                        customerItem.setValues(custId, joinCustDate, mTextFieldAddName.getText(),
                                custBirthDate, mTextFieldAddPhoneNum.getText(),
                                defaultPoint, mTextFieldAddRemark.getText());

                        custMngr.add(customerItem);
                        System.out.println("cust Add");
                        this.mTextFieldAddId.setText(String.valueOf(custMngr.getNextID()));

                        // 추가한 고객(item) 컬럼에 추가
                        DefaultTableModel m = (DefaultTableModel) mTableCustList.getModel();
                        Object[] item = { customerItem.getId(), customerItem.getJoinDate(), customerItem.getName(),
                                customerItem.getBirthday(), customerItem.getPhoneNumber(), customerItem.getPoint(),
                                customerItem.getRemark() };
                        m.addRow(item);

                        // 텍스트필드를 초기화
                        initializeTextField();

                        UiUtils.showMsgBox("추가 완료", "");

                    } catch (Exception ex) {
                        UiUtils.showMsgBox("입력하신 휴대폰번호는 이미 등록되어있습니다.",
                                "", MsgBoxType.Warn);
                        // e.printStackTrace();
                    }

                }
            } else {
                // 필수입력사항 미입력시 경고메세지박스 출력
                UiUtils.showMsgBox("* 는 필수입력사항입니다",
                        "", MsgBoxType.Warn);
            }
        };

        // 테스트용 자동기입
        this.mBtnAddTextFieldCust.addActionListener(listener);
        // 입력된 데이터를 저장
        this.mBtnAddCustComplete.addActionListener(listener);

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[AddCustmerView.onShow()]");

        insertItemsIntoTable();
    }

    @Override
    protected void onHide() {
        System.out.println("[AddCustmerView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont0);
    }

    // 텍스트필드 비우기
    private void initializeTextField() {
        this.mTextFieldAddBirthday.setText("ex)19001231");
        this.mTextFieldAddPoint.setText("0");

        this.mTextFieldAddName.setText("");
        this.mTextFieldAddPhoneNum.setText("");
        this.mTextFieldAddRemark.setText("");
    }

    // 고객목록 테이블에 추가하는 메소드
    private void insertItemsIntoTable() {
        DefaultTableModel m = (DefaultTableModel) mTableCustList.getModel();
        m.setRowCount(0);
        try {
            custMngr.iterate((info, idx) -> {
                m.addRow(new Object[] {
                        info.getId(), info.getJoinDate(),
                        info.getName(), info.getBirthday(),
                        info.getPhoneNumber(), info.getPoint(), info.getRemark()
                });

                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        mTableCustList.updateUI();
    }

    // 테스트용 자동기입 메소드
    private void testCustAddTextField() {

        try {
            int nextNum = custMngr.getNextID();
            this.mTextFieldAddId.setText(String.valueOf(nextNum));
            this.mTextFieldAddName.setText(nName());
            this.mTextFieldAddBirthday.setText("20001010");
            int randomNum = (int) (Math.random() * 9999);
            int randomNum2 = (int) (Math.random() * 9999);
            this.mTextFieldAddPhoneNum.setText(String.format("010-%04d-%04d", randomNum, randomNum2));
            this.mTextFieldAddRemark.setText("test Custmer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 테스트용 랜덤 이름 생성
    public static String nName() {
        List<String> lastName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권",
                "황",
                "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차",
                "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염",
                "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕",
                "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> firstName = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남",
                "노", "누",
                "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바",
                "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙",
                "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월",
                "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주",
                "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현",
                "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을",
                "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸",
                "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직",
                "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(lastName);
        Collections.shuffle(firstName);
        return lastName.get(0) + firstName.get(0) + firstName.get(1);
    }

}