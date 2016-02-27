package com.pmikee.nir.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	String id;
	String name;
	String description;
	BigDecimal stock;
	@Column(name = "unitprice")
	BigDecimal unitPrice;

	protected Product() {
	}

	public Product(String name, String description, BigDecimal stock, BigDecimal sellPrice) {
		super();
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.unitPrice = sellPrice;
	}

	@Override
	public String toString() {
		return "(" + id + ")" + name + ", leírás: " + description + ", készleten: " + stock + ", ára: " + unitPrice;
	}

}
