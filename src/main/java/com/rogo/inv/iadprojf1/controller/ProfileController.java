package com.rogo.inv.iadprojf1.controller;

import com.rogo.inv.iadprojf1.entity.*;
import com.rogo.inv.iadprojf1.entity.race.Race;
import com.rogo.inv.iadprojf1.entity.race.RaceRegistration;
import com.rogo.inv.iadprojf1.entity.storage.CarcaseStorage;
import com.rogo.inv.iadprojf1.entity.storage.ChassisStorage;
import com.rogo.inv.iadprojf1.entity.storage.ElectronicsStorage;
import com.rogo.inv.iadprojf1.entity.storage.EngineStorage;
import com.rogo.inv.iadprojf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private RaceResultService raceResultService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ConstrCupResultService constrCupResultService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private RaceRegistrationService raceRegistrationService;

    @Autowired
    private CarcaseStorageService carcaseStorageService;

    @Autowired
    private ChassisStorageService chassisStorageService;

    @Autowired
    private EngineStorageService engineStorageService;

    @Autowired
    private ElectronicsStorageService electronicsStorageService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String toProfile(ModelMap map, Authentication authentication, @Param("id") Integer id) {

        User user;

        if (id != null) {
             user = userService.findById(id);
        } else {
            user = userService.findByLogin(authentication.getName());
        }

        map.addAttribute("user",user);

        String name = "Панель администратора";

        if (userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) {
           name = sponsorService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName();
        }

        if (!(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.SPONSOR)) && !(userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.ADMIN))) {
            name = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getName() + " " +
                    teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getSurname();
        }
        map.addAttribute("name", name);

        if (user.getSpec().equals(User.Spec.RACER)) {

            //Racer stats

            Object pC = teamMemberService.pointsCount(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (pC == null) {
                pC = "в этом сезоне ещё нет очков";
            }
            map.addAttribute("pointsCount", pC);

            Object rC = teamMemberService.racingsCount(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (rC == null) {
                rC = "в этом сезоне ещё нет гонок";
            }
            map.addAttribute("racingsCount", rC);

            Object aRC = teamMemberService.allRaceCount(user.getId());
            if (aRC == null) {
                aRC = "пока ни одной гонки";
            }
            map.addAttribute("allRaceCount", aRC);

            Object cW = teamMemberService.cupsWon(user.getId());
            if (cW == null) {
                cW = "ни одного кубка";
            }
            map.addAttribute("cupsWon", cW);

            Object cC = teamMemberService.champCount(user.getId());
            if (cC == null) {
                cC = "ни одного чемпионата";
            }
            map.addAttribute("champCount", cC);

            Object aPAA = teamMemberService.avergePlaceAtAll(user.getId());
            if (aPAA == null) {
                aPAA = "пока ни одной гонки";
            }
            map.addAttribute("averagePlaceAtAll", aPAA);

            Object aPAS = teamMemberService.avergePlaceAtSeason(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (aPAS == null) {
                aPAS = "ещё ни одной гонки за сезон";
            }
            map.addAttribute("averagePlaceAtSeason", aPAS);

            Object bP = teamMemberService.bestPlace(user.getId(), seasonService.findTopByOrderByYearDesc().getYear());
            if (bP == null) {
                bP = "пока ни одной гонки";
            }
            map.addAttribute("bestPlace", bP);

            Object gBTT = teamMemberService.getBestTrackTime(user.getId());
            if (gBTT == null) {
                gBTT = "пока ни одной гонки";
            }
            map.addAttribute("getBestTrackTime", gBTT);

            // Champs stats

            List<Object[]> gRPT = raceResultService.getRacerProfileTable(user.getId());
            map.addAttribute("getRacerProfileTable", gRPT);
        }

            // Name and Surname

            String nameSurname = teamMemberService.findByUserId(user.getId()).getName() + " " +
                    teamMemberService.findByUserId(user.getId()).getSurname();
            map.addAttribute("nameSurname",nameSurname);

            // Team name

            Team team = teamMemberService.findByUserId(user.getId()).getTeam();
            try { map.addAttribute("team",team.getName()); } catch (NullPointerException n) {
                map.addAttribute("team","Нет команды");
            }

            // Spec

            switch (user.getSpec()) {
                case RACER: {
                    map.addAttribute("spec","Гонщик");
                    break;
                }
                case MECHANIC: {
                    map.addAttribute("spec","Механик");
                    break;
                }
                case CONSTRUCTOR: {
                    map.addAttribute("spec","Конструктор");
                    break;
                }
                case MANAGER: {
                    map.addAttribute("spec","Менеджер");
                    break;
                }
            }

           try { List<Object[]> teamAch = constrCupResultService.getProfileAchievs(team.getId());
            map.addAttribute("ach",teamAch); } catch (NullPointerException n) {map.addAttribute("ach",null);}

            map.addAttribute("currName",authentication.getName());

     try {
         TeamMember teamMember = teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId());
         Team teamCurr = teamMember.getTeam();
         map.addAttribute("currUserSpec",userService.findByLogin(authentication.getName()).getSpec());
         map.addAttribute("currUserTeam", teamCurr.getId());
         if (userService.findById(teamMemberService.findByUserId(teamMember.getUserId()).getUserId()).getBuyStatus().equals(AcceptStatus.ON_REVIEW)) {
             map.addAttribute("ifCanBuy", null);
         } else {
         map.addAttribute("ifCanBuy", teamMember.getCanBuy()); }

    } catch (NullPointerException x) {
         map.addAttribute("currUserSpec", null);
        // map.addAttribute("currUserTeam", null);
         map.addAttribute("ifCanBuy", null);
     }

        map.addAttribute("currUserId", userService.findByLogin(authentication.getName()).getId());

     if (id == null && userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.MANAGER)) {

         try {
             Race currRace = raceService.findTopByOrderByDateTimeDesc();
             RaceRegistration raceRegistration = raceRegistrationService.findById(teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),
                     currRace);

             if ((raceRegistration) != null) {

                 if (raceRegistration.getStatus().equals(AcceptStatus.ACCEPTED)) {
                     map.addAttribute("currRaceRegStatus", "Заявка принята. Комментарий: " + raceRegistration.getComment());
                 }

                 if (raceRegistration.getStatus().equals(AcceptStatus.ON_REVIEW)) {
                     map.addAttribute("currRaceRegStatus", "Заявка ещё на рассмотрении");
                 }

                 if (raceRegistration.getStatus().equals(AcceptStatus.REFUSED)) {
                     map.addAttribute("currRaceRegStatus", "Заявка отклонена. Комментарий: " + raceRegistration.getComment());
                 }


             } else {
                 map.addAttribute("currRaceRegStatus", null);
             }
         } catch (NullPointerException c) {
             map.addAttribute("currRaceRegStatus", null);
         }

         List<Team> offeredTeams = teamService.getAllBySender(userService.findByLogin(authentication.getName()));
         List<Object[]> acceptedNames = new ArrayList<>();
         List<Object[]> refusedTeams = new ArrayList<>();

         for (Team teamName: offeredTeams) {

             Object[] teamNameInfo = { teamName.getName(), teamName.getComments() };

             if (teamName.getStatus().equals(AcceptStatus.ACCEPTED)) {
                 acceptedNames.add(teamNameInfo);
             }

             if (teamName.getStatus().equals(AcceptStatus.REFUSED)) {
                refusedTeams.add(teamNameInfo);
             }

         }

         map.addAttribute("acceptedNames", acceptedNames);
         map.addAttribute("refusedNames", refusedTeams);

         try {

             List<Object[]> toBeGivenBuy = teamMemberService.getAllConstrAndMech(
                     teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()).getId());

             map.addAttribute("constrsAndMechs", toBeGivenBuy);

             List<Object[]> requestsToGiveBuyInfo = new ArrayList<>();

             for (Object[] mem: toBeGivenBuy) {
                 if (mem[3].equals("ON_REVIEW")) {
                     requestsToGiveBuyInfo.add(mem);
                 }
             }

             map.addAttribute("constrsAndMechsRev", requestsToGiveBuyInfo);

         }  catch (NullPointerException ex) {

         }

         try {

             List<Car> carsToConfirm = carService.findAllByTeamAndStatus(
                     teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),AcceptStatus.ON_REVIEW);
             map.addAttribute("carsToConfirm", carsToConfirm);

             map.addAttribute("budget", teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()).getBudget());

             List<ChassisStorage> chassis = new ArrayList<>();
             List<EngineStorage> engines = new ArrayList<>();
             List<CarcaseStorage> carcases = new ArrayList<>();
             List<ElectronicsStorage> electronics = new ArrayList<>();

             for (Car car: carsToConfirm) {
                 chassis.add(car.getCurrentChassis());
                 engines.add(car.getCurrentEngine());
                 carcases.add(car.getCurrentCarcase());
                 electronics.add(car.getCurrentElectronics());
             }

             map.addAttribute("carsCarc", carcases);
             map.addAttribute("carsChass", chassis);
             map.addAttribute("carsEng", engines);
             map.addAttribute("carsElec", electronics);

             List<CarcaseStorage> carcToConfirm = carcaseStorageService.findAllByTeamAndStatus(
                     teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),AcceptStatus.ON_REVIEW);
             map.addAttribute("carcToConfirm", carcToConfirm);

             List<ChassisStorage> chassToConfirm = chassisStorageService.findAllByTeamAndStatus(
                     teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),AcceptStatus.ON_REVIEW);
             map.addAttribute("chassToConfirm", chassToConfirm);

             List<EngineStorage> engToConfirm = engineStorageService.findAllByTeamAndStatus(
                     teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),AcceptStatus.ON_REVIEW);
             map.addAttribute("engToConfirm", engToConfirm);

             List<ElectronicsStorage> elecToConfirm = electronicsStorageService.findAllByTeamAndStatus(
                     teamService.findById(teamMemberService.findByUserId(userService.findByLogin(authentication.getName()).getId()).getTeam().getId()),AcceptStatus.ON_REVIEW);
             map.addAttribute("elecToConfirm", elecToConfirm);

         } catch (NullPointerException n) {

         }

     }

       if (id == null && (userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.MECHANIC) || (
                userService.findByLogin(authentication.getName()).getSpec().equals(User.Spec.CONSTRUCTOR)
                ))) {

            User mechOrConU = userService.findByLogin(authentication.getName());
            TeamMember mechOrConTm = teamMemberService.findByUserId(mechOrConU.getId());

            try {

           if (mechOrConU.getBuyStatus().equals(AcceptStatus.ON_REVIEW)) {
               map.addAttribute("bpStatus", "Заявка на покупку ещё на рассмотрении");
           } else

           if (mechOrConU.getBuyStatus().equals(AcceptStatus.REFUSED)) {
               if (mechOrConU.getComments() != null)
               map.addAttribute("bpStatus", "Заявка на покупку отклонена. Комментарий: " + mechOrConU.getComments());
               if (mechOrConU.getComments() == null)
                   map.addAttribute("bpStatus", "Заявка на покупку отклонена");
           } else
           if (mechOrConU.getBuyStatus().equals(AcceptStatus.ACCEPTED)) {
               if (mechOrConU.getComments() != null)
               map.addAttribute("bpStatus", "Заявка на покупку одобрена. Комментарий: " + mechOrConU.getComments());
               if (mechOrConU.getComments() == null)
                   map.addAttribute("bpStatus", "Заявка на покупку одобрена");
           } else { map.addAttribute("bpStatus",null); }
            }
           catch (NullPointerException c) { map.addAttribute("bpStatus",null); }

           try {

               List<Car> myCars = carService.findAllBySender(userService.findByLogin(authentication.getName()));
               List<String> myCarsConfirmInfo = new ArrayList<>();

               for (Car car : myCars) {
                   String info = "Болид " + car.getLabel() + " " + car.getModel() + " всё ещё на рассмотрении";
                   if (car.getStatus().equals(AcceptStatus.ACCEPTED)) {
                       if (car.getComment() != null) {
                           info = "Болид " + car.getLabel() + " " + car.getModel() + " одобрен. Комментарий: " + car.getComment(); } else {
                           info = "Болид " + car.getLabel() + " " + car.getModel() + " одобрен.";
                       }
                   }
                   if (car.getStatus().equals(AcceptStatus.REFUSED)) {
                       if (car.getComment() != null) {
                           info = "Болид " + car.getLabel() + " " + car.getModel() + " не одобрен. Комментарий: " + car.getComment(); } else {
                           info = "Болид " + car.getLabel() + " " + car.getModel() + " не одобрен.";
                       }
                   }
                   myCarsConfirmInfo.add(info);
               }

               map.addAttribute("carsSt",myCarsConfirmInfo);

           } catch (NullPointerException p) { map.addAttribute("carsSt", null); }

        }

        return "UserProfilePage";
    }

    @RequestMapping(value = "/profile/confirmPermission", method = RequestMethod.POST)
    @ResponseBody
    public void confPerm(HttpServletRequest request) {

        Integer id = Integer.parseInt(request.getParameter("id"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String comment = request.getParameter("comment");

        User user = userService.findById(id);
        user.setComments(comment);
        if (status) { user.setBuyStatus(AcceptStatus.ACCEPTED); teamMemberService.findByUserId(id).setCanBuy(true);}
        else { user.setBuyStatus(AcceptStatus.REFUSED); teamMemberService.findByUserId(id).setCanBuy(false);}

        userService.save(user);

    }

    @RequestMapping(value = "/profile/askPermission", method = RequestMethod.POST)
    @ResponseBody
    public void askPerm(HttpServletRequest request) {

        String name = request.getParameter("name");
        String comment = request.getParameter("comment");
        User user = userService.findByLogin(name);

        user.setComments(comment);
        user.setBuyStatus(AcceptStatus.ON_REVIEW);

        userService.save(user);

    }

    @RequestMapping(value = "/profile/addTeam", method = RequestMethod.POST)
    @ResponseBody
    public String addTeam(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String id = request.getParameter("id");

        if (teamService.findByName(name) != null) { return "busy"; }

        Team team = new Team(name, 0, AcceptStatus.ON_REVIEW, null, userService.findById(Integer.parseInt(id)));
        teamService.save(team);

        return "ok";
    }

    @RequestMapping(value = "/profile/givePermission", method = RequestMethod.POST)
    @ResponseBody
    public void givePermission(HttpServletRequest request) {

        Integer userId = Integer.parseInt(request.getParameter("user"));
        TeamMember candidate = teamMemberService.findByUserId(userId);
        candidate.setCanBuy(true);
        teamMemberService.save(candidate);

    }

    @RequestMapping(value = "/profile/confirmCar", method = RequestMethod.POST)
    @ResponseBody
    public void confirmCar(HttpServletRequest request) {

        Integer id = Integer.parseInt(request.getParameter("id"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String comment = request.getParameter("comment");

        Car car = carService.findById(id);
        car.setComment(comment);

        if (status) { car.setStatus(AcceptStatus.ACCEPTED); } else {
            car.setStatus(AcceptStatus.REFUSED);
            car.setCurrentEngine(null);
            car.setCurrentChassis(null);
            car.setCurrentCarcase(null);
            car.setCurrentElectronics(null);
        }

        carService.save(car);

    }

}