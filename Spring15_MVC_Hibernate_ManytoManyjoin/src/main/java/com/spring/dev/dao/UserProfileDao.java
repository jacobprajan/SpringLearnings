package com.spring.dev.dao;

import java.util.List;

import com.spring.dev.model.UserProfile;

public interface UserProfileDao
{

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
