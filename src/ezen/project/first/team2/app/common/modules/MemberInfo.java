////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_101000] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules;

import java.time.LocalDate;

import ezen.project.first.team2.app.common.utils.TimeUtils;

public class MemberInfo extends ListItem {
	// -------------------------------------------------------------------------

	public enum DummyDataIndex {
		_0_SiGwanLEE,
		_1_GeunYoungGil,
		_2_HyunWooJo,
		_3_JunHyungKim,
		_4_CheolJinPark,
		_5_BinSeo
	}

	// -------------------------------------------------------------------------

	// 회원 번호. 0번은 비회원.
	// private int mId = 0;
	// 가입일. YYYYY.MM.DD.
	private LocalDate mJoinDate = LocalDate.now();
	// 이름
	private String mName = "";
	// 생년월일. YYYY.MM.DD.
	private LocalDate mBirthday = LocalDate.now();
	// 휴대폰. 010-0000-0000.
	private String mPhoneNum = "010-0000-0000";
	// 비고
	private String mRemark = "";

	// -------------------------------------------------------------------------

	// 생성자
	public MemberInfo() {
	}

	// -------------------------------------------------------------------------

	// 개별 값으로 값 설정
	public void setValues(int id, LocalDate joinDate, String name,
			LocalDate birthday, String phoneNum, String remark) {
		this.mId = id;
		this.mName = name;
		this.mBirthday = birthday;
		this.mPhoneNum = phoneNum;
		this.mRemark = remark;
	}

	// -------------------------------------------------------------------------

	// 이름 설정
	public void setName(String name) {
		this.mName = name;
	}

	// 생년월일 설정
	public void setBirthday(LocalDate date) {
		this.mBirthday = date;
	}

	// 휴대폰 번호 설정
	public void setPhoneNumber(String phoneNum) {
		this.mPhoneNum = phoneNum;
	}

	// 비고 설정
	public void setRemark(String remark) {
		this.mRemark = remark;
	}

	// -------------------------------------------------------------------------

	// 회원 번호 얻기
	// public int getId() {
	// return this.mId;
	// }

	// 가입일 얻기
	public LocalDate getJoinDate() {
		return this.mJoinDate;
	}

	public String getJoinDateStr() {
		return TimeUtils.localDateToStr(this.mJoinDate);
	}

	// 이름 얻기
	public String getName() {
		return this.mName;
	}

	// 생년월일 얻기
	public LocalDate getBirthday() {
		return this.mBirthday;
	}

	public String getBirthdayStr() {
		return TimeUtils.localDateToStr(this.mBirthday);
	}

	// 휴대폰 번호 얻기
	public String getPhoneNumber() {
		return this.mPhoneNum;
	}

	// 비고 얻기
	public String getRemark() {
		return this.mRemark;
	}

	// 더미 데이터 얻기
	public static MemberInfo getDummyData(DummyDataIndex idx) {
		MemberInfo info = new MemberInfo();

		switch (idx) {
			// 이시관
			case _0_SiGwanLEE:
				info.setValues(1, LocalDate.now(), "이시관", LocalDate.of(1983, 5, 9),
						"010-0000-8086", "");
				break;

			// 길근영
			case _1_GeunYoungGil:
				info.setValues(2, LocalDate.now(), "길근영", LocalDate.of(1983, 12, 19),
						"010-0000-2794", "");
				break;

			// 조현우
			case _2_HyunWooJo:
				info.setValues(3, LocalDate.now(), "조현우", LocalDate.of(1991, 1, 1),
						"010-0000-1606", "");
				break;

			// 김준형
			case _3_JunHyungKim:
				info.setValues(4, LocalDate.now(), "김준형", LocalDate.of(1993, 1, 1),
						"010-0000-4355", "");
				break;

			// 박철진
			case _4_CheolJinPark:
				info.setValues(5, LocalDate.now(), "박철진", LocalDate.of(1999, 1, 1),
						"010-0000-0009", "");
				break;

			// 서빈
			case _5_BinSeo:
				info.setValues(6, LocalDate.now(), "서  빈", LocalDate.of(2002, 1, 1),
						"010-0000-5629", "");
				break;
		}

		return info;
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		String s = String.format("id:%06d, join:%s, name:%s, birthday:%s, phone:%s, remark:%s",
				this.getId(), this.getJoinDateStr(), this.getName(), this.getBirthdayStr(),
				this.getPhoneNumber(), this.getRemark());
		return s;
	}

	@Override
	protected void onSetValuesFrom(ListItem item) {
		MemberInfo info = (MemberInfo) item;
		this.setValues(info.getId(), info.getJoinDate(), info.getName(),
				info.getBirthday(), info.getPhoneNumber(), info.getRemark());
	}
}
