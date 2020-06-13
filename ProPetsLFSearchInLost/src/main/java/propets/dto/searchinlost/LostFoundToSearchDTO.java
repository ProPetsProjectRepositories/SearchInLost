package propets.dto.searchinlost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import propets.model.searchinlost.Address;
import propets.model.searchinlost.Coordinates;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = { "id" })
public class LostFoundToSearchDTO {

	String id;
	String userLogin;
	String type;
	String breed;
	String sex;
	Address address;
	Coordinates coordinates;
	String tags;

}
