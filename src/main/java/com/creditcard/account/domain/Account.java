package com.creditcard.account.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// @Entity annotation specifies that the class is mapped to a database table.
@Entity
@SequenceGenerator(name="account_id_seq", initialValue=1, allocationSize=100)
public class Account {

	// @Id annotation specifies the primary key of an entity.
	// @GeneratedValue provides the generation strategy specification for the primary key values.
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="account_id_seq")
	private int id;
	private String name;
	@Size(max = 19, message = "Credit card numbers length should be 19 characters")
	@Pattern(regexp = "^[0-9]*$", message = "Credit card numbers should always be numeric")
	private String card;
	private int balance;
	private int maxlimit;

	// Default constructor.
	public Account() {	}

	// Parameterized constructor.
	public Account(int id, String name, String card , int balance , int maxlimit) {
		this.id = id;
		this.name = name;
		this.card = card;
		this.balance = balance;
		this.maxlimit = maxlimit;
	}

	// Getters.
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public String getCard() {
		return card;
	}

	public int getMaxlimit() {
		return maxlimit;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		final Account account = (Account) o;

		return new EqualsBuilder()
				.append(id, account.id)
				.append(name, account.name)
				.append(balance, account.balance)
				.append(card, account.card)
				.append(maxlimit, account.maxlimit)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(id)
				.append(name)
				.append(balance)
				.append(card)
				.append(maxlimit)
				.toHashCode();
	}

	@Override
	public String toString() {
		return "Account{"
				+ "id='"
				+ id
				+ '\''
				+ ", name='"
				+ name
				+ '\''
				+ ", balance='"
				+ balance
				+ '\''
				+ ", card='"
				+ card
				+ '\''
				+ ", maxlimit='"
				+ maxlimit
				+ '\''
				+ '}';
	}

}
