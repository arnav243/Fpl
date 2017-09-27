package fantasy.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fantasy.domain.FPL;
import fantasy.repository.FPLRepository;

@Service
public class DatabaseControllerImplementation {

	@Autowired
	FPLRepository fplRepository;

	@Value("${url}")
	private String fplUrl;

	private final String USER_AGENT = "Mozilla/5.0";

	public void save() throws Exception {

		URL url = new URL(fplUrl);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer responseFromFPL = new StringBuffer();

//		to build complete string from the api
		while ((inputLine = in.readLine()) != null) {
			responseFromFPL.append(inputLine);
			System.out.println(responseFromFPL);

		}
		in.close();
		connection.disconnect();

//		Creating a json object from string
		JSONObject responseJson = new JSONObject(responseFromFPL.toString());

		JSONArray info = responseJson.getJSONArray("elements");

		System.out.println("Loading...");
		
//		to extract all the data from json array
		for (int i = 0; i < info.length(); i++) {

			FPL fpl = new FPL();
			fpl.setName(info.getJSONObject(i).getString("first_name"));
			fpl.setlName(info.getJSONObject(i).getString("second_name"));
			String fname = fpl.getName() + " " + fpl.getlName();
			fpl.setFname(fname);

			fpl.setTeamCode(info.getJSONObject(i).getInt("team"));

			fpl.setTeam(getTeams().get(fpl.getTeamCode()));

			FPL Idc = fplRepository.findByFname(fname);
			if (Idc != null) {
				fpl.setId(Idc.getId());

			}
			fplRepository.save(fpl);
			System.out.println(fpl);

		}
	}

/**
 * to map teamCode with teams
 */
	public HashMap<Integer, String> getTeams() {

		HashMap<Integer, String> teamMap = new HashMap<Integer, String>();

		teamMap.put(1, "Arsenal");
		teamMap.put(2, "AFC Bournemouth");
		teamMap.put(3, "Brighton & Hove Albion");
		teamMap.put(4, "Burnley");
		teamMap.put(5, "Chelsea");
		teamMap.put(6, "Crystal Palace");
		teamMap.put(7, "Everton");
		teamMap.put(8, "Huddersfield Town");
		teamMap.put(9, "Leicester City");
		teamMap.put(10, "Liverpool");
		teamMap.put(11, "Manchester City");
		teamMap.put(12, "Manchester United");
		teamMap.put(13, "Newcastle United");
		teamMap.put(14, "Southampton");
		teamMap.put(15, "Stoke City");
		teamMap.put(16, "Swansea City");
		teamMap.put(17, "Tottenham Hotspur");
		teamMap.put(18, "Watford");
		teamMap.put(19, "West Bromwich Albion");
		teamMap.put(20, "West Ham United");

		return teamMap;

	}

	public List<String> getPlayersList(int teamCode) throws Exception {

		List<String> players = new ArrayList<>();

		List<FPL> teams = fplRepository.findByTeamCode(teamCode);
//		Extract players name list of each team from all team
		for (FPL team : teams) {
			players.add(team.getFname());
		}

		return players;
	}

}
