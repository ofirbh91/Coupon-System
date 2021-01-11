package com.jb.couponsys.login;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jb.couponsys.service.ClientService;

import javassist.NotFoundException;
import lombok.Getter;

@Component
@Getter
public class TokensManager {
	@Autowired
	private Map<String, UserData> tokens;

	public String add(ClientService clientService) {
		UserData userData = new UserData(clientService, System.currentTimeMillis());
		String token = UUID.randomUUID().toString();
		userData.setClientService(clientService);
		tokens.put(token, userData);
		return token;
	}
	public UserData GetUserData(String token) {
			return tokens.get(token);
	}

	public boolean isTokenExist(String token) throws NotFoundException {
		if (tokens.containsKey(token)) {
			return true;
		}
		throw new NotFoundException("Sorry you're not recognized...");
	}

	public void deleteExpiredToken() {

		this.tokens.values().removeIf(x -> {
			Date now = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.MINUTE, -30);// 30
			now = calendar.getTime();

			Date tokenCreate = new Date(x.getTimeStamp());

			return now.after(tokenCreate);

		});
	}
}