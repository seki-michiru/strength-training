package jp.co.example.service;

import java.util.List;

import jp.co.example.entity.User_info;

public interface User_infoService {
	public List<User_info> selectAll(Integer id);

	public List<User_info> select(Integer id);

	public void insert(Integer weight, Integer height, Integer userId) ;

	public int delete(Integer id);

}
