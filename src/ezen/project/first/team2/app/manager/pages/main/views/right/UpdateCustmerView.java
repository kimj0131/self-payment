package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class UpdateCustmerView extends View {

    JLabel mLabelInfo = new JLabel("회원 수정뷰 초기화면입니다");

    JTextArea mAttributes = new JTextArea(
            "[ID]\t[가입일]\t[이름]\t[생년월일]\t[전화번호]\t [비고]");
    JPanel mPanelAttributeList = new JPanel();
    JTextArea mCustmerList = new JTextArea();
    // 스크롤 삽입
    JScrollPane mScroll = new JScrollPane(mCustmerList);

    public UpdateCustmerView() {
        super(MainPage.VIEW_NUM_CUST_UPDATE);
    }

    @Override
    protected void onInit() {
        //
    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelAttributeList.setLayout(new BorderLayout());
    }

    @Override
    protected void onAddCtrls() {
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        //
        this.mAttributes.setEditable(false);
        this.mCustmerList.setEditable(false);

        this.mScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.mCustmerList.setBorder(
                BorderFactory.createEmptyBorder(0, 30, 0, 30));
        this.mAttributes.setBorder(
                BorderFactory.createEmptyBorder(0, 30, 0, 30));

        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelAttributeList, BorderLayout.CENTER);
        this.mPanelAttributeList.add(mAttributes, BorderLayout.NORTH);
        this.mPanelAttributeList.add(mScroll, BorderLayout.CENTER);
    }

    @Override
    protected void onAddEventListeners() {

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[UpdateCustmerView.onShow()]");

        CustomerManagerMem memMngr = CustomerManagerMem.getInstance();

        String custList = "";
        this.mCustmerList.setText(custList);
        try {
            memMngr.iterate(info -> {
                mCustmerList.append(String.format("%06d\t%s\t%s\t%s\t%s\t %s\n",
                        info.getId(), info.getJoinDate(), info.getName(),
                        info.getBirthday(), info.getPhoneNumber(), info.getRemark()));

                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onHide() {
        System.out.println("[UpdateCustmerView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb = (JLabel) this.getComponents()[0];
        lb.setFont(main.mFont2);
        mCustmerList.setFont(main.mFont2);

        for (int i = 0; i < this.mPanelAttributeList.getComponents().length; i++) {
            JComponent cp = (JComponent) this.mPanelAttributeList.getComponents()[i];
            cp.setFont(main.mFont2);
        }

    }
}