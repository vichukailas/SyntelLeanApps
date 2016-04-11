package com.syntel.insurance.dnc.DNCTrackerCore.list.generics;

import java.util.ArrayList;

public class GenericList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;

	private Class<T> genericType;

	public GenericList(Class<T> c) {
		this.genericType = c;
	}

	public Class<T> getGenericType() {
		return genericType;
	}

}
