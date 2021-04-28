package com.woloxgram.sharedalbum.service;

import java.util.List;

import com.woloxgram.sharedalbum.model.SharedAlbum;
import com.woloxgram.sharedalbum.model.User;
import com.woloxgram.sharedalbum.util.enums.AccessEnum;

public interface ISharedAlbumService {

	public SharedAlbum saveSharedAlbum(SharedAlbum sharedAlbum);
	public SharedAlbum updateUserAccess(Long albumId, User user);
	public List<User> getUsersByAlbumAndAccess(Long albumId, AccessEnum access);
}
