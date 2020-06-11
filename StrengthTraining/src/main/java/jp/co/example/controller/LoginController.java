package jp.co.example.controller;

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

import jp.co.example.controller.form.LoginForm;
import jp.co.example.entity.Users;
import jp.co.example.service.UsersService;
@Controller
public class LoginController {

	@Autowired
	private UsersService usersService;

	@Autowired
	HttpSession session;

	@RequestMapping("/top")
	public String index(@ModelAttribute("loginid") LoginForm form , Model model) {
		return "top";
	}

	@RequestMapping(value="login", method=RequestMethod.POST)
	public String insert(@Validated @ModelAttribute("loginid") LoginForm form ,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "top";
		}
		String id = form.getId();
		String pass = form.getPass();

		List<Users> list = usersService.findId(id, pass);
		if(list == null) {
			model.addAttribute("msg", "※IDまたはPASSが間違っています");
			return "top";
		}
		String name =list.get(0).getUserName();
		String loginid = list.get(0).getLoginId();
		Integer userId = list.get(0).getUserId();

		session.setAttribute("loginid", loginid);
		session.setAttribute("name", name);
		session.setAttribute("userId", userId);

		return "menu";
	}

	@RequestMapping("/logout")
	public String logout(@ModelAttribute("loginid") LoginForm form , Model model) {

		session.invalidate();

		return "top";
	}


}
