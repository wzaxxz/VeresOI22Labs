package commands;

import services.TourService;

public class DisplayTourInfoCommand implements Command {
    private TourService tourService;

    public DisplayTourInfoCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
    }
}
