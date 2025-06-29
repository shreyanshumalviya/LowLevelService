package com.make73.annotation_processor.lld_drivers;

import com.make73.annotation_processor.annotation.ExposeClass;
import com.make73.annotation_processor.annotation.ExposeMethod;
import com.make73.annotation_processor.lld_drivers.BookMyShow.BookingService;
import com.make73.annotation_processor.lld_drivers.BookMyShow.dtos.*;
import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Booking;
import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Movie;
import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Show;
import com.make73.annotation_processor.lld_drivers.BookMyShow.exceptions.SeatsExhaustedException;
import com.make73.annotation_processor.lld_drivers.BookMyShow.managers.CityManager;
import com.make73.annotation_processor.lld_drivers.BookMyShow.managers.GlobalShowManager;
import com.make73.annotation_processor.lld_drivers.BookMyShow.managers.MovieManager;
import com.make73.annotation_processor.lld_drivers.BookMyShow.managers.UserManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ExposeClass(
        name = "BookMyShowDriver",
        description = "This is an app that allows theatre to list their shows and provides user to find all shows in their city in a single place",
        link = "BookMyShowDriver",
        stack = "Java"
)
public class BookMyShowDriver {
    private CityManager cityManager;
    private MovieManager movieManager;
    private UserManager userManager;

    public BookMyShowDriver() {
        this.cityManager = new CityManager();
        this.movieManager = new MovieManager();
        this.userManager = new UserManager();

        initializeWithDomeyData();
    }

    private void initializeWithDomeyData() {
        createCity("Bhopal");
        createCity("Indore");

        createTheatre(0, "PVR", 200);
        createTheatre(0, "Cinepolis", 150);
        createTheatre(1, "Inox", 250);
        createTheatre(1, "Cinepolis", 100);

        createMovie("Dhamaal");
        createMovie("Hungama");
        createMovie("Hera Pheri");

        createShow(0, 0, 0, LocalDateTime.now());
        createShow(0, 0, 1, LocalDateTime.now());
        createShow(0, 1, 2, LocalDateTime.now());
        createShow(0, 1, 0, LocalDateTime.now());
        createShow(1, 0, 0, LocalDateTime.now());
        createShow(1, 0, 1, LocalDateTime.now());
        createShow(1, 1, 2, LocalDateTime.now());
        createShow(1, 1, 0, LocalDateTime.now());

        createUser("Shreyanshu");

    }

    // create city
    @ExposeMethod(name = "Create City", link = "createCity", description = "Create a new city where you will be adding shows")
    public void createCity(String name) {
        cityManager.addCity(name);
    }

    // list cities
    @ExposeMethod(name = "List Cities", link = "listCities", description = "List all cities")
    public List<CityDTO> listCities() {
        return cityManager
                .getCities()
                .stream()
                .map(city ->
                        new CityDTO(city.getId(), city.getName())
                ).collect(Collectors.toList());
    }

    // create theatre
    @ExposeMethod(name = "Create Theatre", link = "createTheatre", description = "Create a new theatre in a city, pass cityId")
    public void createTheatre(int cityId, String name, int seats) {
        cityManager.getCity(cityId).addTheatre(name, seats);
    }

    // create movie
    @ExposeMethod(name = "Create Movie", link = "createMovie", description = "Create a new movie")
    public void createMovie(String name) {
        movieManager.addMovie(name);
    }

    // list movies
    @ExposeMethod(name = "List Movies", link = "listMovies", description = "List all movies")
    public List<MovieDTO> listMovies() {
        return movieManager.getMovies().stream().map(movie -> new MovieDTO(movie.getId(), movie.getName())).collect(Collectors.toList());
    }

    // create show
    @ExposeMethod(name = "Create Show", link = "createShow", description = "Create a new show in a theatre, pass theatreId and movieId")
    public void createShow(int cityId, int theatreId, int movieId, LocalDateTime startTime) {
        Movie movie = movieManager.getMovie(movieId);
        cityManager.getCity(cityId).getTheatre(theatreId).addShow(movie, startTime);
    }

    // create user
    @ExposeMethod(name = "Create User", link = "createUser", description = "Create a new user")
    public void createUser(String name) {
        userManager.addUser(name);
    }

    // listUsers
    @ExposeMethod(name = "List Users", link = "listUsers", description = "List all users")
    public List<UserDTO> listUsers() {
        return userManager.getUsers().stream().map(user -> new UserDTO(user.getId(), user.getName())).collect(Collectors.toList());
    }

    // list theatres in city
    @ExposeMethod(name = "List Theatres", link = "listTheatres", description = "List all theatres in a city, pass cityId")
    public List<TheatreDTO> listTheatres(int cityId) {
        return cityManager.getCity(cityId).getTheatres().stream().map(theatre -> new TheatreDTO(theatre.getId(), theatre.getName())).collect(Collectors.toList());
    }

    // list shows in city
    @ExposeMethod(name = "List Shows", link = "listShows", description = "List all shows in a city, pass cityId")
    public List<ShowDTO> listShows(int cityId) {
        List<Show> shows = new ArrayList<>();

        cityManager.getCity(cityId).getTheatres().forEach(theatre -> shows.addAll(theatre.getShows()));
        return shows.stream().map(show -> new ShowDTO(show.getId(), show.getTheatre().getName(), show.getMovie().getName(), show.getStartTime())).collect(Collectors.toList());
    }

    // book a show
    @ExposeMethod(name = "Book Show", link = "bookShow", description = "Book a show, pass userId and showId")
    public String bookShow(int userId, int showId) {
        try {
            BookingService.createBooking(userManager.getUser(userId), GlobalShowManager.getShow(showId));
            return "Booking Successful";
        } catch (SeatsExhaustedException e) {
            return "Seats Expired";
        }
    }

    // cancel a show
    // list bookings of a user
    @ExposeMethod(name = "List Bookings", link = "listBookings", description = "List all bookings of a user, pass userId")
    public List<ShowDTO> listBookings(int userId) {
        return (userManager.getUser(userId).getBookings()).stream().map(Booking::getShow).map(show -> new ShowDTO(show.getId(), show.getTheatre().getName(), show.getMovie().getName(), show.getStartTime())).collect(Collectors.toList());
    }

}
