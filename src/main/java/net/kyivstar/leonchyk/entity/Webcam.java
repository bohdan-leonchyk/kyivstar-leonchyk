package net.kyivstar.leonchyk.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Entity
public class Webcam {

	@Id
	@Column(nullable = false)
	private String identifier;

	private String location;

	@OneToMany(mappedBy = "webcam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "webcam",allowSetters=true)
	private List<Picture> pictures;

	public Webcam() {}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}
