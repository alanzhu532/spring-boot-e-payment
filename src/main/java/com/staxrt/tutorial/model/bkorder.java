package com.staxrt.tutorial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bkorder")
public class bkorder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	   
	@Column(name = "ordernumber", nullable = false)
    private Integer ordernumber;

	@Column(name = "orderdate", nullable = false)
    private Date orderdate;

	@Column(name = "requireddate", nullable = false)
    private Date requireddate;

	@Column(name = "shippeddate", nullable = false)
    private Date shippeddate;

	@Column(name = "status", nullable = false)
    private String status;

	@Column(name = "customernumber", nullable = false)
    private Integer customernumber;

	@Column(name = "comments", nullable = false) 
    private String comments;
	
    public bkorder() {

    }

	public bkorder(Integer ordernumber, Date orderdate, Date requireddate, Date shippeddate, String status, Integer customernumber, String comments) {
        this.ordernumber = ordernumber;
        this.orderdate = orderdate;
        this.requireddate = requireddate;
        this.shippeddate = shippeddate;
        this.status = status; 
        this.customernumber = customernumber;
        this.comments = comments; 
    }

    
    public Integer getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Integer ordernumber) {
        this.ordernumber = ordernumber;
    }

   
    public Date getOrderdate() {
        return orderdate;
    }
   
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    
    public Date getRequireddate() {
        return requireddate;
    }

    public void setRequireddate(Date requireddate) {
        this.requireddate = requireddate;
    }
   
    
    public Date getShippeddate() {
        return shippeddate;
    }

    public void setShippeddate(Date shippeddate) {
        this.shippeddate = shippeddate;
    }

    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    public Integer getCustomernumber() {
        return customernumber;
    }
    
    public void setCustomernumber(Integer customernumber) {
        this.customernumber = customernumber;
    }

    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
