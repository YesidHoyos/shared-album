package com.woloxgram.sharedalbum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woloxgram.sharedalbum.model.SharedAlbum;
import com.woloxgram.sharedalbum.model.User;
import com.woloxgram.sharedalbum.service.ISharedAlbumService;
import com.woloxgram.sharedalbum.util.enums.AccessEnum;
import com.woloxgram.sharedalbum.util.exception.AccessTypeNotAllowed;

@RestController
@RequestMapping("/sharedalbums")
public class SharedAlbumController {

	private static final String ACCESS_TYPE_NOT_VALID = "Tipo de permiso de acceso no válido";
	private ISharedAlbumService sharedAlbumService;

	public SharedAlbumController(ISharedAlbumService sharedAlbumService) {
		this.sharedAlbumService = sharedAlbumService;
	}
	
	@PostMapping
	public SharedAlbum saveSharedAlbum(@RequestBody(required = true) SharedAlbum sharedAlbum) {
		return sharedAlbumService.saveSharedAlbum(sharedAlbum);
	}
	
	@PutMapping("/{albumId}")
	public SharedAlbum updateUserAccess(@PathVariable(required = true) long albumId, @RequestBody(required = true) User user) {
		return sharedAlbumService.updateUserAccess(albumId, user);
	}
	
	@GetMapping("/{albumId}")
	public List<User> getUsersByAlbumAndAccess(@PathVariable(required = true) long albumId, @RequestParam(required = true) String access){
		AccessEnum accessEnum = null;
		switch (access) {
		case "write":
			accessEnum = AccessEnum.WRITE;
			break;
		case "read":
			accessEnum = AccessEnum.READ;
			break;
		default:
			throw new AccessTypeNotAllowed(ACCESS_TYPE_NOT_VALID);
		}
		return sharedAlbumService.getUsersByAlbumAndAccess(albumId, accessEnum);
	}
	
}
