package com.woloxgram.sharedalbum.repository.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.woloxgram.sharedalbum.model.User;

@Document("sharedalbums")
public class SharedAlbumEntity {

	@Id
	private String id;
	
	@Indexed(unique = true)
	private long albumId;
	private long ownerUserId;
	private List<User> guestUsers;
	
	public SharedAlbumEntity() {}

	public SharedAlbumEntity(String id, long albumId, long ownerUserId, List<User> guestUsers) {
		this.id = id;
		this.albumId = albumId;
		this.ownerUserId = ownerUserId;
		this.guestUsers = guestUsers;
	}
	
	public SharedAlbumEntity(long albumId, long ownerUserId, List<User> guestUsers) {
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

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public long getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public List<User> getGuestUsers() {
		return guestUsers;
	}

	public void setGuestUsers(List<User> guestUsers) {
		this.guestUsers = guestUsers;
	}
}
