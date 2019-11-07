package Backend.API.Database;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TwitterRepository extends PagingAndSortingRepository<TwitterDB, Long> {

        TwitterDB findByLocation(String location);
}
