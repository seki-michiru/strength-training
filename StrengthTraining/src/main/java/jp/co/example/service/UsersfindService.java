package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.dao.UsersDao;
import jp.co.example.entity.Users;

@Service
public class UsersfindService implements UsersService{

	@Autowired
	private UsersDao usersDao;

	public List<Users> findId(String id) {
		return usersDao.findId(id);
	}


	public List<Users> findId(String id, String pass) {
		return	usersDao.findId(id, pass);
	}

	public void regist(String id, String name, String pass) {
		usersDao.regist(id, name, pass);
	}

	public void updateConfig(String id, Integer training, Integer interval, Integer set) {
		usersDao.updateConfig(id, training, interval, set);
	}
}
