package com.example.project1.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.project1.domain.User;
import com.example.project1.domain.UserCheck;
import com.example.project1.domain.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepo;

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("/addNewUser")
	public String addNewUser(Model model) {
		model.addAttribute("userCheck", new UserCheck());
		return "newUserInfo";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "saveNewUser")
	public String saveNewUser(@Valid @ModelAttribute("userCheck") UserCheck userc, BindingResult bindingResult) {
//start of cp-block
		System.out.println("saveUser " + userc.getPassword());
		if (!bindingResult.hasErrors()) { // validation errors
			if (userc.getPassword().equals(userc.getPasswordCheck())) { // check password match

				String pwd = userc.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);
				User newUser = new User();
				newUser.setPassword(hashPwd);
				newUser.setUsername(userc.getUsername());
				newUser.setRole(userc.getRole().toUpperCase());
				if (userRepo.findByUsername(userc.getUsername()) == null) { // Check if user exists
					System.out.println("ei ole samannimistä useria");
					userRepo.save(newUser);
				} else {
					System.out.println("on jo samanniminen user");
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "newUserInfo";
				}
			} else {
				System.out.println("salasanat eivät täsmää keskenään");
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");

				return "newUserInfo";
			}
		} else {
			System.out.println("KATO: " + bindingResult.toString());
			System.out.println("KATO1: " + bindingResult.getFieldError());
			System.out.println("KATO1a: " + bindingResult.getFieldError().getField());
			System.out.println("KATO2: " + bindingResult.getFieldErrors());
			System.out.println("KATO3: " + bindingResult.getFieldValue("username"));

			if (bindingResult.getFieldError().getField().equalsIgnoreCase("username")) {
				System.out.println("käyttäjätunnuksesi on liian lyhyt");
				bindingResult.rejectValue("username", "err.usernameCheck", "Username too short");
			} else if (bindingResult.getFieldError().getField().equalsIgnoreCase("passwordCheck")) {
				System.out.println("salasanasi on liian lyhyt");
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Password too short");

			}
			return "newUserInfo";
		}
		return "redirect:/users";
	}
	// end of cp-block
	/*
	 * String passwordRaw = userc.getPassword(); BCryptPasswordEncoder bCrypt = new
	 * BCryptPasswordEncoder(); String passwordHash = bCrypt.encode(passwordRaw);
	 * User user = new User(userc.getUsername(), passwordHash,
	 * userc.getRole().toUpperCase()); userRepo.save(user);
	 */

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long userId, Model model) {
		userRepo.deleteById(userId);
		return "redirect:../users";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editUser/{username}")
	public String editProduct(@PathVariable("username") String username, Model model) {
		model.addAttribute("user", userRepo.findByUsername(username));
		return "editUser";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("/users")
	public String products(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userList";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@Valid User user) {
		userRepo.save(user);
		return "users";
	}

}
