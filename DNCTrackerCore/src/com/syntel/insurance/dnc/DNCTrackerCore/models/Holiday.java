package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HOLIDAY database table.
 * 
 */
@Entity
@NamedQuery(name="Holiday.findAll", query="SELECT h FROM Holiday h")
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int month;

	private String dates;

	public Holiday() {
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getDates() {
		return this.dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

}