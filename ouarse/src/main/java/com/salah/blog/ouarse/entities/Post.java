package com.salah.blog.ouarse.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the posts database table.
 * 
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
@NamedEntityGraph(name = "Post.postDetails", attributeNodes = @NamedAttributeNode("postDetails"))
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String body;

	@Column(name = "created_at")
	private Instant createdAt;

	private byte published;

	private String slug;

	private String title;

	@Column(name = "updated_at")
	private Instant updatedAt;

	private int views;

	// bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Utilisateur utilisateur;

	// bi-directional many-to-one association to Postdetail
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostDetail> postDetails;

}