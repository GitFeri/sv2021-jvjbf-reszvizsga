package cinema;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final List<Movie> movies = new ArrayList<>();
    private AtomicLong id = new AtomicLong();
    private final ModelMapper modelMapper;

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieDTO findMovieById(long id) {
        Movie movie = findById(id);
        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieDTO CreateMovieCommand(CreateMovieCommand command) {
        Movie movie = new Movie(id.incrementAndGet(),
                command.getTitle(),
                command.getDate(),
                command.getMaxSpaces(),
                command.getMaxSpaces());
        movies.add(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    public List<MovieDTO> getMovies(Optional<String> title) {
        Type targetListType = new TypeToken<List<MovieDTO>>() {
        }.getType();

        List<Movie> filtered = movies.stream()
                .filter(i -> title.isEmpty() || i.getTitle().equalsIgnoreCase(title.get()))
                .collect(Collectors.toList());

        return modelMapper.map(filtered, targetListType);
    }

    public MovieDTO updateDate(long id, UpdateDateCommand command) {
        Movie movie = findById(id);
        movie.setDate(command.getDate());
        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieDTO createReservation(long id, CreateReservationCommand command) {
        Movie movie = findById(id);
        movie.reserveSpacesForMovie(command.getSpaces());
        return modelMapper.map(movie, MovieDTO.class);
    }

    public void deleteAll() {
        movies.clear();
        id = new AtomicLong();
    }

    private Movie findById(long id) throws IllegalArgumentException {
        return movies.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find."));
    }
}