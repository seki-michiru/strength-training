package jp.co.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.controller.form.RegistForm;
import jp.co.example.entity.Users;
import jp.co.example.service.UsersService;
@Controller
public class RegistController {
	@Autowired
	private UsersService usersService;

	@RequestMapping("/regist")
	public String index(@ModelAttribute("test") RegistForm form , Model model) {
		return "regist";
	}

	@RequestMapping(value="regist", method=RequestMethod.POST)
	public String insert(@Validated @ModelAttribute("test") RegistForm form ,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "regist";
		}
		String id = form.getId();
		String name = form.getName();
		String pass = form.getPass();


		List<Users> list = usersService.findId(id);

		if(list != null) {
			model.addAttribute("msg", "※IDが重複しています");
			return "regist";
		}else {
			usersService.regist(id, name, pass);
			return "registResult";
		}



	}
}
