package com.elearning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int videoId;
	private String videoName;
	@Column(length = 3000)
	private String videoDesc;
	private String videoPath;
	
	@OneToMany(targetEntity = Videostatus.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="videoId", referencedColumnName = "videoId")
	List<Videostatus> videostatus;
	
	public List<Videostatus> getVideostatus() {
		return videostatus;
	}

	public void setVideostatus(List<Videostatus> videostatus) {
		this.videostatus = videostatus;
	}

	public Video() {
		super();
	}

	public int getVideoId() {
		return videoId;
	}

	public Video(int videoId, String videoName, String videoDesc, String videoPath) {
		super();
		this.videoId = videoId;
		this.videoName = videoName;
		this.videoDesc = videoDesc;
		this.videoPath = videoPath;
	}

	public Video setVideoId(int videoId) {
		this.videoId = videoId;
		return this;
	}

	public String getVideoName() {
		return videoName;
	}

	public Video setVideoName(String videoName) {
		this.videoName = videoName;
		return this;
	}

	public String getVideoDesc() {
		return videoDesc;
	}

	public Video setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
		return this;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public Video setVideoPath(String videoPath) {
		this.videoPath = videoPath;
		return this;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoName=" + videoName + ", videoDesc=" + videoDesc + ", videoPath="
				+ videoPath + "]";
	}

	

	public Video(int videoId, String videoName, String videoDesc, String videoPath, List<Videostatus> videostatus) {
		super();
		this.videoId = videoId;
		this.videoName = videoName;
		this.videoDesc = videoDesc;
		this.videoPath = videoPath;
		this.videostatus = videostatus;
	}

	public Video build() {
		return new Video(videoId,videoName,videoDesc,videoPath,videostatus);
	}

	
	
	
}
