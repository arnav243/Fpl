package fantasy.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fantasy.domain.FPL;

public interface FPLRepository extends MongoRepository<FPL, Serializable> {

	public FPL findByFname(String fname);
	
	public 	List<FPL> findByTeamCode(int teamCode);

}
