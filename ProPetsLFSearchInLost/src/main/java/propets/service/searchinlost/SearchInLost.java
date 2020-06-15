package propets.service.searchinlost;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import propets.configuration.searchinlost.SearchInLostConfiguration;
import propets.dao.searchinlost.LostToSearchRepository;
import propets.dto.searchinlost.LostFoundToSearchDTO;
import propets.model.searchinlost.LostToSearch;

@EnableBinding(Processor.class)
public class SearchInLost {

	@Autowired
	LostToSearchRepository foundRepository;

	@Autowired
	SearchInLostConfiguration configuration;

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Map<String, Set<String>> searchForMatches(LostFoundToSearchDTO lostFoundToSearch) {
		Set<LostToSearch> resultSearchFromBD = foundRepository.findPosts(lostFoundToSearch.getType(),
				lostFoundToSearch.getBreed(), lostFoundToSearch.getSex(),
				lostFoundToSearch.getCoordinates().getLat(),
				lostFoundToSearch.getCoordinates().getLon(), configuration.getRadiusSearch(),
				lostFoundToSearch.getTags(), configuration.getPercentToSearch());
		if (resultSearchFromBD != null) {
			Map<String, Set<String>> result = new HashMap<>();
			Set<String> post = new HashSet<>();
			post.add(lostFoundToSearch.getId());
			result.put(configuration.getKeyPosts(), post);
			result.put(configuration.getKeyUsers(),
					resultSearchFromBD.stream().map(p -> p.getUserLogin()).collect(Collectors.toSet()));
			return result;
		}
		return null;
	}

}
