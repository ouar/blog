package com.salah.blog.ouarse.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the postdetail database table.
 * 
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDetailDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Long id;

	private String body;

	private Instant createdAt;

	private String image;

	private byte published;

	private String title;

	private Instant updatedAt;

	/**
	 * @return the id
	 */
	@JsonProperty
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@JsonIgnore
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	

}