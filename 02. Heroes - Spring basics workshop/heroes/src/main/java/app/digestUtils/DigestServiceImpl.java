package app.digestUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class DigestServiceImpl implements DigestService{

	@Override
	public String hashPassword(String password) {
		return DigestUtils.sha256Hex(password);
	}

}
