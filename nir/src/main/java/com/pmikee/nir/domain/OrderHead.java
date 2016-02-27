package com.pmikee.nir.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderHead implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	String id;
	@ManyToOne(optional = false)
	Customer customer;
	@Temporal(TemporalType.TIMESTAMP)
	Date date;

	public OrderHead() {
	}

	public OrderHead(Customer customer, Date date) {
		super();
		this.customer = customer;
		this.date = date;
	}

}
