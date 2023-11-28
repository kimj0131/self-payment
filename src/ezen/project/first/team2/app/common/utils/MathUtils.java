////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231128TUE_164200] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils;

import java.util.Arrays;

public class MathUtils {
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min + 1)) + min);
	}

	public static int[] gerRandomNumbers(int min, int max, int count,
			boolean duplication, boolean sort) {

		int[] nums = new int[count];

		int i = 0;
		int len = nums.length;

		while (i < len) {
			nums[i] = getRandomNumber(min, max);

			// 중복 허용 안 함
			if (!duplication) {
				// i : 현재 선택된 번호
				// j : 지금까지 선택된 번호
				int j = 0;
				boolean duplicated = false;

				while (j < i) {
					if (nums[j] == nums[i]) {
						duplicated = true;

						break;
					}

					j++;
				} // while (j < i)

				if (!duplicated)
					i++;
			} else {
				i++;
			} // if (!duplication)

		} // while (i < len)

		if (sort)
			Arrays.sort(nums);

		return nums;
	}
}
