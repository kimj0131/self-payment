////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231122WED_103400] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.modules.product.manager;

import java.time.LocalDate;
import java.util.List;

public interface ProductManagerHelper {
	public ProductItem findByProductCode(ProductCode prodCode) throws Exception;

	public List<ProductItem> findByRegDate(LocalDate date) throws Exception;

	public List<ProductItem> findByName(String name) throws Exception;

	public List<ProductItem> findByPrice(int price) throws Exception;
}