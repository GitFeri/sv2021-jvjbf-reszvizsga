package cinema;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private LocalDateTime date;
    private int maxSpaces;
    private int freeSpaces;

    public void reserveSpacesForMovie(int spaces) {
        if (0 < spaces && spaces <= freeSpaces) {
            freeSpaces -= spaces;
        } else throw new IllegalStateException("There is no enough seat!");
    }
}
