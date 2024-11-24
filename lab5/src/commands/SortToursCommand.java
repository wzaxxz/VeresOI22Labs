package commands;

import services.TourService;

public class SortToursCommand implements Command {
    private TourService tourService;

    public SortToursCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
    }
}
