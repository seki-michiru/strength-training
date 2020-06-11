package jp.co.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.controller.form.ConfirmForm;
import jp.co.example.entity.Users;
import jp.co.example.service.UsersService;


@Controller
public class ConfirnController {


	@Autowired
	HttpSession session;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/config")
	public String config(Model model) {
		String loginid = (String)session.getAttribute("loginid");

		List<Users> users = usersService.findId(loginid);
		Integer training = users.get(0).getTrainingTime();
		Integer interval = users.get(0).getIntervalTime();
		Integer set = users.get(0).getSetNum();

		session.setAttribute("trainingTime", training);
		session.setAttribute("intervalTime", interval);
		session.setAttribute("setNum", set);

		System.out.println(users.get(0).getIntervalTime());


		return "config";
	}

	@RequestMapping("/confirm")
	public String index(@ModelAttribute("confirm") ConfirmForm form , Model model) {
		return "confirm";
	}


	@RequestMapping(value="/config", method=RequestMethod.POST)
	public String confirm(@ModelAttribute("confirm") ConfirmForm form , Model model) {
		String id = (String)session.getAttribute("loginid");
		Integer training = form.getTraining();
		Integer interval = form.getInterval();
		Integer set = form.getSetnum();

		usersService.updateConfig(id, training, interval, set);
		return "configResult";
	}
}
