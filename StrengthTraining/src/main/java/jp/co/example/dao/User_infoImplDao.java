package jp.co.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.entity.User_info;
@Repository
public class User_infoImplDao implements User_infoDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<User_info> selectAll(Integer id) {
		String sql = "SELECT info_id, date_time, weight, height , (weight * weight) / height AS bmi FROM user_info WHERE user_id = :userId ORDER BY info_id DESC";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", id);

		List<User_info> list = jdbcTemplate.query(sql, param , new BeanPropertyRowMapper<User_info>(User_info.class));
		return list.isEmpty() ? null : list;
	}

	public List<User_info> select(Integer id) {
		String sql = "SELECT * , (weight * weight) / height AS bmi FROM user_info WHERE user_id = :userId ORDER BY info_id DESC LIMIT 1";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", id);

		List<User_info> list = jdbcTemplate.query(sql, param , new BeanPropertyRowMapper<User_info>(User_info.class));
		return list.isEmpty() ? null : list;
	}

	public void insert(Integer weight, Integer height, Integer userId) {
		String sql = "INSERT INTO user_info(date_time, weight, height, user_id) VALUES(current_date, :weiGht, :heiGht, :user_Id)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("weiGht", weight);
		param.addValue("heiGht", height);
		param.addValue("user_Id", userId);

		jdbcTemplate.update(sql, param);
	}

	public int delete(Integer id) {
		String sql = "DELETE FROM user_info WHERE info_id = :infoId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("infoId", id);

		int num = jdbcTemplate.update(sql, param);

		return num;
	}
}
