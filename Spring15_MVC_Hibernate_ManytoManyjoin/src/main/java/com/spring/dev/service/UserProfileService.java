package com.spring.dev.service;

import java.util.List;

import com.spring.dev.model.UserProfile;

public interface UserProfileService
{

	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();

}
