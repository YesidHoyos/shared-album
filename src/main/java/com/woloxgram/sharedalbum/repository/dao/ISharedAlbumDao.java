package com.woloxgram.sharedalbum.repository.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.woloxgram.sharedalbum.repository.entity.SharedAlbumEntity;

public interface ISharedAlbumDao extends MongoRepository<SharedAlbumEntity, String> {

	public List<SharedAlbumEntity> findByAlbumId(long albumId);
}
