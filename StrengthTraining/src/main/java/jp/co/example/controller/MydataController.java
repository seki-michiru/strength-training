package jp.co.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.controller.form.MydataUpdateForm;
import jp.co.example.controller.form.MydataaDeleteForm;
import jp.co.example.entity.User_info;
import jp.co.example.entity.Users;
import jp.co.example.service.User_infoService;
import jp.co.example.service.UsersService;

@Controller
public class MydataController {
	@Autowired
	HttpSession session;

	@Autowired
	private User_infoService user_infoService;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/mydata")
	public String config(Model model) {
		String loginId = (String)session.getAttribute("loginid");

		List<Users> userlist = usersService.findId(loginId);
		Integer userId = userlist.get(0).getUserId();

		List<User_info> user = user_infoService.select(userId);
		Integer weight = null;
		Integer height = null;
		Integer bmi = null;

		if(user != null) {
			weight = user.get(0).getWeight();
			height = user.get(0).getHeight();
			bmi = user.get(0).getBmi();
		}

		List<User_info> userInfo = user_infoService.selectAll(userId);

		model.addAttribute("userInfo", userInfo);
		session.setAttribute("weight", weight);
		session.setAttribute("height", height);
		session.setAttribute("bmi", bmi);



		return "mydata";
	}

	@RequestMapping("/mydataUpdate")
	public String mydataupdate(@ModelAttribute("mydata")  MydataUpdateForm form , Model model) {
		return "mydataUpdate";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Validated @ModelAttribute("mydata") MydataUpdateForm form ,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "mydataUpdate";
		}
		String loginId = (String)session.getAttribute("loginid");

		List<Users> userlist = usersService.findId(loginId);
		Integer userId = userlist.get(0).getUserId();
		Integer weight = form.getWeight();
		Integer height = form.getHeight();

		user_infoService.insert(weight, height, userId);


		return "mydataResult";
	}

	@RequestMapping("/mydataDelete")
	public String mydatadelete(@ModelAttribute("mydelete")  MydataaDeleteForm form , Model model) {
		String loginId = (String)session.getAttribute("loginid");

		List<Users> userlist = usersService.findId(loginId);
		Integer userId = userlist.get(0).getUserId();

		List<User_info> userInfo = user_infoService.selectAll(userId);

		model.addAttribute("userInfo", userInfo);
		return "mydataDelete";
	}


	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String update(@Validated @ModelAttribute("mydelete") MydataaDeleteForm form ,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "mydataDelete";
		}

		List<Integer> infoId = new ArrayList<>();

		for(int i = 0; i < form.getInfoId().length; i++) {
			infoId.add(form.getInfoId()[i]);
		}


		for(int i = 0; i < infoId.size(); i++) {
			user_infoService.delete(infoId.get(i));
		}

		String loginId = (String)session.getAttribute("loginid");
		List<Users> userlist = usersService.findId(loginId);
		Integer userId = userlist.get(0).getUserId();
		List<User_info> userInfo = user_infoService.selectAll(userId);

		model.addAttribute("userInfo", userInfo);

		return "menu";
	}


	@RequestMapping("/mydataResult")
	public String maydata(Model model) {
		return "mydataResult";
	}
}
