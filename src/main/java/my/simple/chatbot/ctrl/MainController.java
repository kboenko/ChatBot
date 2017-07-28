package my.simple.chatbot.ctrl;

import my.simple.chatbot.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public String chatBot() {
        return "chatbot";
    }

    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(@RequestParam("myMessage") String msg) {
        return messageService.createAnswer(msg);
    }
}
