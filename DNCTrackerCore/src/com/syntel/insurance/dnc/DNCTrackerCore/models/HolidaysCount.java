package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HOLIDAYS_COUNT database table.
 * 
 */
@Entity
@Table(name="HOLIDAYS_COUNT")
@NamedQuery(name="HolidaysCount.findAll", query="SELECT h FROM HolidaysCount h")
public class HolidaysCount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int month;

	@Column(name="NO_OF_HOLIDAYS")
	private int noOfHolidays;

	public HolidaysCount() {
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getNoOfHolidays() {
		return this.noOfHolidays;
	}

	public void setNoOfHolidays(int noOfHolidays) {
		this.noOfHolidays = noOfHolidays;
	}

}