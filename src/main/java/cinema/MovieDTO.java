package cinema;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MovieDTO {

    private Long id;
    private String title;
    private LocalDateTime date;
    private int freeSpaces;

}
