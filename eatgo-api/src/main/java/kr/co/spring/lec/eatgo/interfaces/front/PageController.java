package kr.co.spring.lec.eatgo.interfaces.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/restaurants")
@RequestMapping("/index")
public class PageController {

    @RequestMapping("")
    public ModelAndView restaurants(){
        return new ModelAndView("/pages/index.html")
                ;
    }

}
