package com.testing.healthcare.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name = "product")
public class Product {
	@Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MA_PRODUCT_ID_S")
    @SequenceGenerator(name = "MA_PRODUCT_ID_S", allocationSize = 1, sequenceName = "MA_PRODUCT_ID_S")
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "PRODUCT_NAME", length=50)
	private String productName;
	
	@Column(name = "PRODUCT_SKU", unique = true)
	private String productSku;
	
	@Column(name = "PRODUCT_IMG")
	private String productImg;
	
	@Column(name = "PRODUCT_PRICE", precision = 10, scale = 2)
	private BigDecimal productPrice;
	
	@Column(name = "PRODUCT_DESCRIPTION", length=2000)
	private String productDescription;
	
	@ManyToMany
	private List<Category> categories;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	
	 
	
}
