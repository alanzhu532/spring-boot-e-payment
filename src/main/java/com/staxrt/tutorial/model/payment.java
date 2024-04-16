package com.staxrt.tutorial.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

import java.time.LocalDateTime;    // import the LocalDateTime class


@Entity
@Table(name = "payment")
public class payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "customernumber", nullable = false)
    private Long customernumber;
    
    @Column(name = "checknumber", nullable = false)
    private String checknumber;
    
    @Column(name = "paymentdate")
    private  Date paymentdate;

	@Column(name = "amount")
    private double amount;
	

	// getters and setters 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(Long customernumber) {
		this.customernumber = customernumber;
	}

	public String getChecknumber() {
		return checknumber;
	}

	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}   
	
    public payment() {

    }

	public payment(long customernumber, String checknumber, Date paymentdate, double amount) {
        //this.id = id; 
		this.customernumber = customernumber; 
        this.checknumber = checknumber; 
        this.paymentdate = paymentdate; 
        this.amount = amount; 
    }

}
