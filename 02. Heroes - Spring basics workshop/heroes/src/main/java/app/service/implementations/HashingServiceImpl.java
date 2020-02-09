package app.service.implementations;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import app.service.HashingService;

@Service
public class HashingServiceImpl implements HashingService {

	@Override
	public String hashPassword(String password) {
		return DigestUtils.sha256Hex(password);
	}

}
