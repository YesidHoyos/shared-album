package com.woloxgram.sharedalbum.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.woloxgram.sharedalbum.model.SharedAlbum;
import com.woloxgram.sharedalbum.repository.ISharedAlbumRepository;
import com.woloxgram.sharedalbum.repository.dao.ISharedAlbumDao;
import com.woloxgram.sharedalbum.repository.entity.SharedAlbumEntity;

@Repository
public class SharedAlbumRespository implements ISharedAlbumRepository {
	
	private ISharedAlbumDao sharedAlbumDao;

	public SharedAlbumRespository(ISharedAlbumDao sharedAlbumDao) {
		this.sharedAlbumDao = sharedAlbumDao;
	}

	@Override
	public SharedAlbum save(SharedAlbum sharedAlbum) {
		SharedAlbumEntity sharedAlbumEntity = new SharedAlbumEntity(sharedAlbum.getId(), sharedAlbum.getAlbumId(), sharedAlbum.getOwnerUserId(), sharedAlbum.getGuestUsers());
		sharedAlbumEntity = sharedAlbumDao.save(sharedAlbumEntity);
		return new SharedAlbum(sharedAlbumEntity.getId(), sharedAlbumEntity.getAlbumId(), sharedAlbumEntity.getOwnerUserId(), sharedAlbumEntity.getGuestUsers());
	}

	@Override
	public Optional<SharedAlbum> findByAlbumId(Long albumId) {
		Optional<SharedAlbumEntity> sharedAlbumEntityOptional = sharedAlbumDao.findByAlbumId(albumId).stream().findFirst();
		if(sharedAlbumEntityOptional.isPresent()) {
			SharedAlbumEntity sharedAlbumEntity = sharedAlbumEntityOptional.get();
			return Optional.of(new SharedAlbum(sharedAlbumEntity.getId(), sharedAlbumEntity.getAlbumId(), sharedAlbumEntity.getOwnerUserId(), sharedAlbumEntity.getGuestUsers()));
		} else
			return Optional.empty();
	}
	
	
}
