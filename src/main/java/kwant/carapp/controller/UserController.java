package kwant.carapp.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kwant.carapp.model.User;
import kwant.carapp.service.UserService;

@RestController
@RequestMapping("api/v1/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public List<User> list() {
		return userService.findAll();
	}
 
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userService.save(user);
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public User get(@PathVariable Long id) {
		return userService.findUser(id);
	} 

	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	public User update(@PathVariable Long id, @RequestBody User user) {
		User existinguser = userService.findUser(id);
		BeanUtils.copyProperties(user, existinguser);
		return userService.save(existinguser);
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	public User delete(@PathVariable Long id) {
		User user = userService.findUser(id); 
		userService.delete(id);
		return user;
	}

}
