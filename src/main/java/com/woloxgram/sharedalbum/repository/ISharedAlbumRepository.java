package com.woloxgram.sharedalbum.repository;

import java.util.Optional;

import com.woloxgram.sharedalbum.model.SharedAlbum;

public interface ISharedAlbumRepository {

	public SharedAlbum save(SharedAlbum sharedAlbum);
	public Optional<SharedAlbum> findByAlbumId(Long albumId);
}
