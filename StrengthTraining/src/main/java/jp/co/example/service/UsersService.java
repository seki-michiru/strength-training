package jp.co.example.service;

import java.util.List;

import jp.co.example.entity.Users;

public interface UsersService {
	public List<Users> findId(String id);

	public List<Users> findId(String id, String pass);

	public void regist(String id, String name, String pass);

	public void updateConfig(String id, Integer training, Integer interval, Integer set);

}
