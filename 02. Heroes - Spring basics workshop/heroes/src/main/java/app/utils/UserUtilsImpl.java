package app.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class UserUtilsImpl implements UserUtils{

	@Override
	public String hashPassword(String password) {
		return DigestUtils.sha256Hex(password);
	}
}
