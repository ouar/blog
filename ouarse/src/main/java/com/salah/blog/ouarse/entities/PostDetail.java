package com.salah.blog.ouarse.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
@Entity
@NamedQuery(name = "Postdetail.findAll", query = "SELECT p FROM PostDetail p")
@Table(name = "postdetail")
public class PostDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String body;

	@Column(name = "created_at")
	private Instant createdAt;

	private byte published;

	private String title;

	@Column(name = "updated_at")
	private Instant updatedAt;

	// bi-directional many-to-one association to Post
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_post")
	private Post post;

}