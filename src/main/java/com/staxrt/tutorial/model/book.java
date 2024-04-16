package com.staxrt.tutorial.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;
import java.util.TimeZone;
import java.time.LocalDateTime;    // import the LocalDateTime class
import java.text.ParseException;
import java.text.SimpleDateFormat; 

/**
 * The type book 
 *
 * @author 
 */
@Entity
@Table(name = "book")
public class book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /* 
     * @GeneratedValue: This annotation is used to specify how the primary key is generated. 
     * There are several strategies available, such as AUTO, IDENTITY, SEQUENCE, and TABLE. 
     * By default, the strategy is AUTO, which means that the persistence provider will choose the appropriate strategy based on the underlying database.
     */ 
    
    @Column(name = "title", nullable = false)
    private String title;
    
	@Column(name = "author", nullable = false)
    private String author;
    
    @Column(name = "publishdate", nullable = false)
    private  Date publishdate;

    
    /* setter and getter */ 
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date publishdate) throws ParseException {
		SimpleDateFormat estFormat = new SimpleDateFormat("yyyy-MM-dd");
		estFormat.setTimeZone(TimeZone.getTimeZone("EST"));
		//Date date = estFormat.parse("2023-12-21T05:05:05");
		//this.publishdate = estFormat.parse(publishdate.toString());
		this.publishdate = publishdate; 
	}


    public book() {

    }

	public book(String title, String author, Date publishdate) {
        this.title = title;
        this.author =  author;
        this.publishdate = publishdate;
        
    }
    
}

