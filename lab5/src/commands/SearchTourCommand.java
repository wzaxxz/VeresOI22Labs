package commands;

import services.TourService;

public class SearchTourCommand implements Command {
    private TourService tourService;

    public SearchTourCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
    }
}
