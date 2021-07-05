package cinema;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateMovieCommand {

    @NotBlank(message = "Name cannot be blank!")
    private String title;

    private LocalDateTime date;

    @Min(value = 20,message = "The maximum space may not be less than 20!")
    private int maxSpaces;

    public CreateMovieCommand(String title, LocalDateTime date, int maxSpaces) {
        this.title = title;
        this.date = date;
        this.maxSpaces = maxSpaces;
    }
}
