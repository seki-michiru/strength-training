package jp.co.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.entity.Users;
import jp.co.example.service.UsersService;

@Controller
public class MappingController {

	@Autowired
	HttpSession session;

	@Autowired
	private UsersService usersService;


	@RequestMapping("/timer")
	public String index(Model model) {
//		Integer trainingTime = (Integer)session.getAttribute("trainingTime");
//		Integer intervalTime = (Integer)session.getAttribute("intervalTime");
//		Integer setNum = (Integer)session.getAttribute("setNum");
//		Integer training = null;
//		Integer interval = null;
//		Integer set = null;
//		if(trainingTime != null && intervalTime != null && setNum != null) {
//			training = Integer.parseInt(trainingTime);
//			interval = Integer.parseInt(intervalTime);
//			set = Integer.parseInt(setNum);
//		}
		String loginId = (String)session.getAttribute("loginid");
		System.out.println(loginId);

		List<Users> list = usersService.findId(loginId);
		session.setAttribute("list", list);

		System.out.println(list);
//		session.setAttribute("trainingTime", trainingTime);
//		session.setAttribute("intervalTime", intervalTime);
//		session.setAttribute("setNum", setNum);

		return "timer";
	}

	@RequestMapping("/finish")
	public String finish(Model model) {
		return "finish";
	}

	@RequestMapping("/menu")
	public String menu(Model model) {
		return "menu";
	}

	@RequestMapping("/configRegist")
	public String configRegist(Model model) {
		return "configRegist";
	}



}
