package dqcup.repair.attr.impl;

/**
 * 
 * @author luochen
 * 
 */
public class StAddNumApmtValidator {

	private static final String POBox_Prefix = "po box";

	public boolean validate(String stAdd, String stNum, String apmt) {
		try {
			stAdd = stAdd.toLowerCase();
			if (stAdd.startsWith(POBox_Prefix)) {
				// PO BOX xxxx
				String[] addrs = stAdd.split("\\s");
				if (addrs.length != 3) {
					return false;
				}
				Integer.parseInt(addrs[2]);
				return (stNum == null || stNum.isEmpty()) && (apmt == null || apmt.isEmpty());
			} else {
				int len = stAdd.length();
				// verify STADD
				for (int i = 0; i < len; i++) {
					char c = stAdd.charAt(i);
					if (!Character.isLetter(c) && c != ' ' && c != ',' && c != '.') {
						return false;
					}
				}

				// verify STNUM
				len = stNum.length();
				if (len == 0 || len > 4) {
					return false;
				}
				for (int i = 0; i < len; i++) {
					char c = stNum.charAt(i);
					if (!Character.isDigit(c)) {
						return false;
					}
				}

				// verify APMT
				len = apmt.length();
				if (len != 3) {
					return false;
				}
				return Character.isDigit(apmt.charAt(0)) && Character.isLowerCase(apmt.charAt(1))
						&& Character.isDigit(apmt.charAt(2));
			}
		} catch (Exception e) {
			return false;
		}

	}
}