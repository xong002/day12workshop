package sg.nus.iss.visa.ssf.day12workshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import sg.nus.iss.visa.ssf.day12workshop.model.Image;

@Controller
public class NumberController {

    @GetMapping("/home")
    public String landingPage(){
        return "home";
    }

    // @GetMapping("/get")
    // public String generateRanNumbers(Model model, HttpServletRequest request){

    //     int number = Integer.parseInt(request.getParameter("number"));

    //     System.out.println("Input no. is: " + number);

    //     if (number < 1 || number > 30){
    //         String errorMessage = "Invalid Number: " + number;
    //         model.addAttribute("errorMessage", errorMessage);
    //         return "home";
    //     }
    //     return "display";
    // }

    @GetMapping(path = "/get", produces = { "text/html" })
    public String getRandomNumbers(Model model, HttpServletRequest request) {

        int number = Integer.parseInt(request.getParameter("number"));

        if (number < 1 || number > 30){
            String errorMessage = "Invalid Number: " + number;
            model.addAttribute("errorMessage", errorMessage);
            return "home";
        }

            model.addAttribute("number", number);
            List<String> list = new ArrayList<>();

            for (int i = 0; i < number; i++) {
                Random random = new Random();
                Integer randomNumber = 0;
                do {
                    randomNumber = random.nextInt(31);
                } while (randomNumber == 0 || list.contains(randomNumber.toString()));
                list.add(randomNumber.toString());
                // list.add(new Image(randomNumber.toString(), "@{images/"+randomNumber+".png}"));
            }
            System.out.println(list.toString());

            model.addAttribute("list", list);


        return "result";
    }
}
