package com.model;

public class Bill {
	private int bid;
	private String bdescription;
	private double bamount;
	private String bby;
	public String getBby() {
		return bby;
	}
	public void setBby(String bby) {
		this.bby = bby;
	}
	@Override
	public String toString() {
		return "Bill [bid=" + bid + ", bdescription=" + bdescription + ", bamount=" + bamount + "]";
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBdescription() {
		return bdescription;
	}
	public void setBdescription(String bdescription) { 
		this.bdescription = bdescription;
	}
	public double getBamount() {
		return bamount;
	}
	public void setBamount(double bamount) {
		this.bamount = bamount;
	}

}

