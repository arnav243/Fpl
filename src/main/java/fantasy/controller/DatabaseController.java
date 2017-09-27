package fantasy.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fantasy.service.DatabaseControllerImplementation;

@RestController
@RequestMapping("/fpl")
public class DatabaseController {

	@Autowired
	DatabaseControllerImplementation databaseControllerImplementation;

	@RequestMapping(value = "/team/{teamCode}", method = RequestMethod.GET)
	public List<String> teamId(@Valid @PathVariable("teamCode") int teamCode) throws Exception {

		return databaseControllerImplementation.getPlayersList(teamCode);
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ResponseEntity<HttpStatus> save() throws Exception {

		databaseControllerImplementation.save();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public HashMap<Integer, String> team() throws Exception {

		return databaseControllerImplementation.getTeams();

	}
}
