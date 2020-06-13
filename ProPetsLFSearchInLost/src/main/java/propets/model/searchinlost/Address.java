package propets.model.searchinlost;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	String country;

	String city;

	String street;

	Integer building;
	
}
