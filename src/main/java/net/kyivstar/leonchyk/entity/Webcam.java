package net.kyivstar.leonchyk.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Entity
public class Webcam implements Serializable {

	private static final long serialVersionUID = 8468472952895965603L;

	@Id
	@Column(nullable = false, unique = true)
	private String identifier;

	private String location;

	@OneToMany(mappedBy = "webcam", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "webcam", allowSetters=true)
	private List<Picture> pictures = new ArrayList<>();

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

	@Override
	public String toString() {
		return "Webcam{" +
				"identifier='" + identifier + '\'' +
				", location='" + location + '\'' +
				", pictures=" + pictures +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Webcam webcam = (Webcam) o;

		if (identifier != null ? !identifier.equals(webcam.identifier) : webcam.identifier != null) return false;
		if (location != null ? !location.equals(webcam.location) : webcam.location != null) return false;
		return pictures != null ? pictures.equals(webcam.pictures) : webcam.pictures == null;
	}

	@Override
	public int hashCode() {
		int result = identifier != null ? identifier.hashCode() : 0;
		result = 31 * result + (location != null ? location.hashCode() : 0);
		result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
		return result;
	}
}
