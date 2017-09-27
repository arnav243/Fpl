package fantasy.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player")

public class FPL implements Serializable {

	private static final long serialVersionUID = -8659328823272962990L;

	@Id
	public String id;

	public String name;

	public String lName;

	public int teamCode;

	public String team;

	public String fname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(int teamCode) {
		this.teamCode = teamCode;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Override
	public String toString() {
		return "FPL [id=" + id + ", name=" + name + ", lName=" + lName + ", teamCode=" + teamCode + ", team=" + team
				+ ", fname=" + fname + "]";
	}

}
