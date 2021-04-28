package com.woloxgram.sharedalbum.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.woloxgram.sharedalbum.model.SharedAlbum;
import com.woloxgram.sharedalbum.model.User;
import com.woloxgram.sharedalbum.repository.ISharedAlbumRepository;
import com.woloxgram.sharedalbum.service.ISharedAlbumService;
import com.woloxgram.sharedalbum.util.enums.AccessEnum;
import com.woloxgram.sharedalbum.util.exception.AlreadyExistException;
import com.woloxgram.sharedalbum.util.exception.NotFoundException;
import com.woloxgram.sharedalbum.util.exception.NullFieldException;

@Service
public class SharedAlbumService implements ISharedAlbumService {

	private static final String OWNER_USER_ERROR = "El usuario dueño de un album no puede ser un usuario invitado para el mismo album";
	private static final String USER_ID_NULL = "El atributo userId no puede ser nulo";
	private static final String OWNER_USER_NULL = "El atributo ownerUserId no puede ser nulo";
	private static final String ALBUM_ID_NULL = "El atributo albumId no puede ser nulo";
	private static final String USER_ALREADY_EXIST = "ya existe un usuario con id %s con permisos para el album con id %s";
	private static final String SHARED_ALBUM_NOT_FOUND = "No existe album compartido para el album con id %s";
	private static final String USER_NOT_FOUND = "No existe album con id %s compartido con el usuario con id %s";

	private ISharedAlbumRepository sharedAlbumRepository;

	public SharedAlbumService(ISharedAlbumRepository sharedAlbumRepository) {
		this.sharedAlbumRepository = sharedAlbumRepository;
	}

	@Override
	public SharedAlbum saveSharedAlbum(SharedAlbum sharedAlbum) {
		validateSharedAlbum(sharedAlbum);
		validateUserOwner(sharedAlbum);
		Optional<SharedAlbum> sharedAlbumSaved = sharedAlbumRepository.findByAlbumId(sharedAlbum.getAlbumId());
		if(sharedAlbumSaved.isPresent()) {
			validateExistingUser(sharedAlbum, sharedAlbumSaved.get());
			sharedAlbumSaved.get().getGuestUsers().addAll(sharedAlbum.getGuestUsers());
		}
		return sharedAlbumRepository.save(sharedAlbumSaved.orElse(sharedAlbum));
	}

	@Override
	public SharedAlbum updateUserAccess(Long albumId, User user) {
		validateUser(user);
		SharedAlbum sharedAlbumSaved = sharedAlbumRepository.findByAlbumId(albumId)
									  		.orElseThrow(() -> new NotFoundException(String.format(SHARED_ALBUM_NOT_FOUND, albumId)));
		if(!sharedAlbumSaved.getGuestUsers().contains(user))
			throw new NotFoundException(String.format(USER_NOT_FOUND, albumId, user.getUserId()));
		sharedAlbumSaved.getGuestUsers().forEach(userSaved -> {
			if(userSaved.equals(user)) {
				userSaved.setReader(user.getIsReader());
				userSaved.setWriter(user.getIsWriter());
			}
		});
		return sharedAlbumRepository.save(sharedAlbumSaved);
	}

	@Override
	public List<User> getUsersByAlbumAndAccess(Long albumId, AccessEnum access) {
		SharedAlbum sharedAlbumSaved = sharedAlbumRepository.findByAlbumId(albumId)
		  									.orElseThrow(() -> new NotFoundException(String.format(SHARED_ALBUM_NOT_FOUND, albumId)));
		
		if(access.value.equals("write"))
			return sharedAlbumSaved.getGuestUsers().stream().filter(User::getIsWriter).collect(Collectors.toList());
		else
			return sharedAlbumSaved.getGuestUsers().stream().filter(User::getIsReader).collect(Collectors.toList());
	}

	private void validateExistingUser(SharedAlbum sharedAlbum, SharedAlbum sharedAlbumSaved) {
		sharedAlbumSaved.getGuestUsers().forEach(userSaved -> {
			if(sharedAlbum.getGuestUsers().contains(userSaved))
				throw new AlreadyExistException(String.format(USER_ALREADY_EXIST, userSaved.getUserId(), sharedAlbum.getAlbumId()));
		});
	}
	
	private void validateSharedAlbum(SharedAlbum sharedAlbum) {
		if(sharedAlbum.getAlbumId() == null)
			throw new NullFieldException(ALBUM_ID_NULL);
		if(sharedAlbum.getOwnerUserId() == null)
			throw new NullFieldException(OWNER_USER_NULL);
		if(sharedAlbum.getGuestUsers() == null)
			throw new NullFieldException(OWNER_USER_NULL);
		sharedAlbum.getGuestUsers().forEach(this::validateUser);
	}

	private void validateUser(User user) {
		if(user.getUserId() == null)
			throw new NullFieldException(USER_ID_NULL);
	}
	
	private void validateUserOwner(SharedAlbum sharedAlbum) {
		sharedAlbum.getGuestUsers().forEach(user -> {
			if(user.getUserId().equals(sharedAlbum.getOwnerUserId()))
				throw new AlreadyExistException(OWNER_USER_ERROR);
		});
	}

}
