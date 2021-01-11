package com.jb.couponsys.login;

import com.jb.couponsys.service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	private ClientService clientService;
	private long timeStamp;
}