package com.lisz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lisz.mapper.User;
import com.lisz.mapper.UserExample;
import com.lisz.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
	
	public void create(User user) {
		mapper.insert(user);
	}

	public List<User> findAll() {
		return mapper.selectByExample(null);
	}

	public List<User> findAllByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return findAll();
	}

	public User findById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	public List<User> findByUsername(String username) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username);
		return mapper.selectByExample(example);
	}

	public void deleteById(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void update(User user) {
		mapper.updateByPrimaryKey(user);
	}

}
