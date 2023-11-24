////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231122WED_102100] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.customer;

import java.time.LocalDate;
import java.util.List;

public interface CustomerManagerHelper {
	public CustomerItem findByName(String name) throws Exception;

	public List<CustomerItem> findByBirthday(LocalDate date) throws Exception;

	public CustomerItem findByPhoneNumber(String phoneNumber) throws Exception;
}
