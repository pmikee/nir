package com.pmikee.nir.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderPosition implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	String id;
	@ManyToOne(optional = false)
	Product product;
	BigDecimal quantity;
	BigDecimal totalPrice;
	@ManyToOne(optional = false)
	OrderHead orderHead;

	public OrderPosition() {
	}

	public OrderPosition(Product product, BigDecimal quantity, OrderHead orderHead) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.orderHead = orderHead;
	}

}
