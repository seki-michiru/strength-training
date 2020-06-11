package jp.co.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.entity.Users;
@Repository
public class UsersfindDao implements UsersDao{
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Users> findId(String id) {
		String sql = "SELECT * FROM users WHERE login_id = :loginId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", id);

		List<Users> list = jdbcTemplate.query(sql, param , new BeanPropertyRowMapper<Users>(Users.class));

		return list.isEmpty() ? null : list;
	}


	public List<Users> findId(String id, String pass){
		String sql = "SELECT * FROM users WHERE login_id = :loginId AND pass = :paSs";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", id);
		param.addValue("paSs", pass);

		List<Users> list = jdbcTemplate.query(sql, param , new BeanPropertyRowMapper<Users>(Users.class));

		return list.isEmpty() ? null : list;
	}

	@Override
	public void regist(String id, String name, String pass) {
		String sql = "INSERT INTO users(login_id, user_name, pass, training_time, interval_time, set_num) VALUES(:loginId, :userName, :paSs, 1, 30, 3)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", id);
		param.addValue("userName", name);
		param.addValue("paSs", pass);

		jdbcTemplate.update(sql, param);

	}

	public void updateConfig(String id, Integer training, Integer interval, Integer set) {
		String sql = "UPDATE  users SET training_time = :trainingTime, interval_time = :intervalTime, set_num = :setNum WHERE login_id = :loginId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("trainingTime", training);
		param.addValue("intervalTime", interval);
		param.addValue("setNum", set);
		param.addValue("loginId", id);

		jdbcTemplate.update(sql, param);
	}

}
