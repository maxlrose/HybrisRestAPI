package com.company.model;

import com.company.utils.MySet;
import com.company.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "sequenceGen", sequenceName = "seqProduct", allocationSize = 1, initialValue = 1)
@Table(
//		name = "Products"
)
public class Product extends BaseEntity
//		implements StringsArray
{
	@Column(length = 100, columnDefinition = "varchar(50) default 'Shampoo'", nullable = false) // ;)
	@JsonView(value = {View.UserView.External.class, View.UserView.Post.class})
	@Size(min = 5, max = 50)
	@NotBlank
	String name;

	@JsonView(value = {View.UserView.External.class, View.UserView.Post.class})
	@Min(1)
	int price;

//	@OneToOne
//	public Department department;

	//@Basic(optional = false)

	//@Enumerated
	@JsonView(value = {View.UserView.External.class, View.UserView.Post.class})
	ProdStatus status;


	public Product(String name, int price, ProdStatus status) {
		this.name = name;
		this.price = price;
		this.status = status;
	}

	@OneToMany(mappedBy = "product",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL//, orphanRemoval = true
	)
//	@JoinColumn(
//			name = "product_id"
//	//		, referencedColumnName = "id"
//	)
//	@PrimaryKeyJoinColumn//	@JoinTable(name = "order_items",
//			joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
//			inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
//	)
	//@JsonIgnore
	private Set<OrderItems> orderItems = new MySet<>();

//	@ManyToMany(//mappedBy = "products"
//	           )
//	@JoinTable(name = "order_items",
//			joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
//			inverseJoinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}
//	)
//	List<Order> orders;


//	public String[] toStringsArray() {
//		return new String[] {
//				String.valueOf(id), String.valueOf(name), String.valueOf(price),
//				String.valueOf(status), String.valueOf(created), String.valueOf(modified)
//		};
//	}

//	public static String[] getHeaders() {
//		return new String[] {String.valueOf(id), String.valueOf(username), String.valueOf(department), String.valueOf(created), String.valueOf(modified)};
//	}

	@Override
	public String toString() {
		return "Product{" +
				       id +
				       ", name='" + name + '\'' +
				       ", price=" + price +
				       ", status=" + status +
				       '}';
	}

}
