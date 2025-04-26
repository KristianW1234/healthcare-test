package com.testing.healthcare.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MA_CATEGORY_ID_S")
	@SequenceGenerator(name = "MA_CATEGORY_ID_S", allocationSize = 1, sequenceName = "MA_CATEGORY_ID_S")
	@Column(name = "ID")
	private Long id;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
