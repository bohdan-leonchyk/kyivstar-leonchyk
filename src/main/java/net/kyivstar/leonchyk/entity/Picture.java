package net.kyivstar.leonchyk.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Entity
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "picture_id")
	private Integer id;

	@Column(name = "created_time")
	@JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
	private LocalDateTime createdTime;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_identifier")
	@JsonIgnoreProperties(value = "pictures", allowSetters = true)
	private Webcam webcam;

	public Picture() {}

	public Picture(String name, Webcam webcam) {
		this.createdTime = LocalDateTime.now();
		this.name = name;
		this.webcam = webcam;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Webcam getWebcam() {
		return webcam;
	}

	public void setWebcam(Webcam webcam) {
		this.webcam = webcam;
	}
}
