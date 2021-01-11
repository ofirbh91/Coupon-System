package com.jb.couponsys.bean;

import java.util.List;
import java.util.stream.Collector;

public enum Category {
	food, electronics, resturant, vacation, gaming, cloths;

	Category collect(Collector<Object, ?, List<Object>> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
