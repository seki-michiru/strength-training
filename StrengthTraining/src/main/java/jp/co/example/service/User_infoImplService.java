package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.dao.User_infoDao;
import jp.co.example.entity.User_info;
@Service
public class User_infoImplService implements User_infoService{
	@Autowired
	private User_infoDao user_infoDao;

	public List<User_info> selectAll(Integer id) {
		return user_infoDao.selectAll(id);
	}

	public List<User_info> select(Integer id){
		return user_infoDao.select(id);
	}

	public void insert(Integer weight, Integer height, Integer userId) {
		user_infoDao.insert(weight, height, userId);
	}

	public int delete(Integer id) {
		return user_infoDao.delete(id);
	}
}
