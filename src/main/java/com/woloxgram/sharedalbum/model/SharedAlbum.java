package com.woloxgram.sharedalbum.model;

import java.util.List;

public class SharedAlbum {

	private String id;
	private Long albumId;
	private Long ownerUserId;
	private List<User> guestUsers;
	
	public SharedAlbum() {}

	public SharedAlbum(String id, Long albumId, Long ownerUserId, List<User> guestUsers) {
		this.id = id;
		this.albumId = albumId;
		this.ownerUserId = ownerUserId;
		this.guestUsers = guestUsers;
	}
	
	public SharedAlbum(Long albumId, Long ownerUserId, List<User> guestUsers) {
		this.albumId = albumId;
		this.ownerUserId = ownerUserId;
		this.guestUsers = guestUsers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Long getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(Long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public List<User> getGuestUsers() {
		return guestUsers;
	}

	public void setGuestUsers(List<User> guestUsers) {
		this.guestUsers = guestUsers;
	}
}
