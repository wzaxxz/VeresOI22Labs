package commands;

import services.TourService;

public class BookTourCommand implements Command {
    private TourService tourService;

    public BookTourCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
    }
}
