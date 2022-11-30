package cinema.controller;

import cinema.dao.RoleDao;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectTestData {
    private AuthenticationService authenticationService;
    private CinemaHallService cinemaHallService;
    private MovieService movieService;
    private MovieSessionService movieSessionService;
    private ShoppingCartService shoppingCartService;
    private OrderService orderService;
    private RoleDao roleDao;

    public InjectTestData(AuthenticationService authenticationService,
                          CinemaHallService cinemaHallService,
                          MovieService movieService,
                          MovieSessionService movieSessionService,
                          ShoppingCartService shoppingCartService,
                          OrderService orderService,
                          RoleDao roleDao) {
        this.authenticationService = authenticationService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.roleDao = roleDao;
    }

    @GetMapping
    public String injectTestData() {
        /*Role roleAdmin = new Role();
        roleAdmin.setRoleName(Role.RoleName.ADMIN);
        roleDao.add(roleAdmin);
        Role roleUser = new Role();
        roleUser.setRoleName(Role.RoleName.USER);
        roleDao.add(roleUser);*/
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(100);
        cinemaHall1.setDescription("Hall #1");
        cinemaHall1 = cinemaHallService.add(cinemaHall1);
        Movie movie1 = new Movie();
        movie1.setTitle("Terminator 1");
        movie1.setDescription("The movie about robot war against people");
        movie1 = movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("Terminator 2");
        movie2.setDescription("The movie about robot war against people");
        movieService.add(movie2);
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie1);
        movieSession1.setCinemaHall(cinemaHall1);
        movieSession1.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession1);
        User user1 = authenticationService.register("ihor@westukrtrans.com",
                "12345678", "ADMIN");
        User user2 = authenticationService.register("ihor2@westukrtrans.com",
                "12345678", "USER");
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession1, user1);
        orderService.completeOrder(shoppingCartService.getByUser(user1));
        return "All data are injected";
    }

    @GetMapping("/test")
    public String test() {
        return roleDao.getByName("admin").get().getRoleName().name();
    }
}
