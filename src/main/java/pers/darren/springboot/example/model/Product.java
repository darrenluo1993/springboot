package pers.darren.springboot.example.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 产品信息
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Oct 15, 2019 9:48:16 AM
 */
@Getter
@Setter
@ToString
public class Product implements Serializable {

	private static final long serialVersionUID = 7241915439741270646L;

	public static final String defaultUser = "Darren Luo";

	private String name;

	private String category;

	private String supplier;

	private int quantity;

	private BigDecimal price;

	private boolean featured;

	private String description;

	private transient String createdBy;

	private transient Date createdTime;

	private transient String modifiedBy;

	private transient Date modifiedTime;
}