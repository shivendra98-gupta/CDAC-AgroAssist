package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "products")
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
public class Category  extends BaseEntity{
   
	@Column(name = "category_name", length = 100, unique=true)
	private String categoryName;

	@Column(name = "category_description", length = 500)
	private String categoryDescription;

	@Column(name = "category_image", length = 100)
	private String categoryImage;
	
	
	//Category Relationship with the Product : One-To-Many
	//Owning Side(Foreign Key) : Product (category_id)
	//Inverse Side (Non-Owning Side): Category (products)
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Product> products =new ArrayList<Product>();
	
	//Helper Method To Add Product In The List Of Products :
	public void addProduct(Product product) {
		product.setCategory(this);
		this.products.add(product);
	}
	
	//Helper Method To Remove a Product from the Category
	public void removeProduct(Product product) {
		product.setCategory(null);
		this.products.remove(product);
	}
}
