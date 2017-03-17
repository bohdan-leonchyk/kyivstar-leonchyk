package net.kyivstar.leonchyk.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Entity
public class Picture implements Serializable {

	private static final long serialVersionUID = 4973739802394038858L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "picture_id")
	private Integer id;

	@Column(name = "created_time")
	@JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
	private LocalDateTime createdTime;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne
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

	@Override
	public String toString() {
		return "Picture{" +
				"id=" + id +
				", createdTime=" + createdTime +
				", name='" + name + '\'' +
				", webcam=" + webcam +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Picture picture = (Picture) o;

		if (id != null ? !id.equals(picture.id) : picture.id != null) return false;
		if (createdTime != null ? !createdTime.equals(picture.createdTime) : picture.createdTime != null) return false;
		if (name != null ? !name.equals(picture.name) : picture.name != null) return false;
		return webcam != null ? webcam.equals(picture.webcam) : picture.webcam == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (webcam != null ? webcam.hashCode() : 0);
		return result;
	}
}
