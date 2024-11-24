package commands;

import services.TourService;

public class DisplayToursCommand implements Command {
    private TourService tourService;

    public DisplayToursCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
    }
}
