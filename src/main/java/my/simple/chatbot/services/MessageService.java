package my.simple.chatbot.services;

import my.simple.chatbot.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class MessageService {

    public String createAnswer(String msg) {

        Random rnd = new Random();
        List<String> commonAnswers;
        List<String> elusiveAnswers;
        List<String> pQuestions;
        List<String> pAnswers;

        Map<String,String> questionPatterns;
        Map<String,String> answersPatterns;

        Pattern pattern;

        try {
            commonAnswers = FileUtils.readAnswersFile(ResourceUtils.getFile(this.getClass().getResource("/common_answers")));
            elusiveAnswers = FileUtils.readAnswersFile(ResourceUtils.getFile(this.getClass().getResource("/elusive_answers")));
            pQuestions = FileUtils.readAnswersFile(ResourceUtils.getFile(this.getClass().getResource("/questions_templates")));
            pAnswers = FileUtils.readAnswersFile(ResourceUtils.getFile(this.getClass().getResource("/answers_templates")));
            questionPatterns = FileUtils.getPatterns(pQuestions);
            answersPatterns = FileUtils.getPatterns(pAnswers);

            String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for(Map.Entry<String,String> o : questionPatterns.entrySet()) {
                pattern = Pattern.compile(o.getKey());
                if(pattern.matcher(message).find()) {
                    System.out.println("Нашли!");
                    return answersPatterns.get(o.getValue());
                }
            }

            return (msg.trim().endsWith("?"))?
                    elusiveAnswers.get(rnd.nextInt(elusiveAnswers.size())):
                    commonAnswers.get(rnd.nextInt(commonAnswers.size()));



            /*if (msg.trim().endsWith("?")) {
                String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
                    for(Map.Entry<String,String> o : questionPatterns.entrySet()) {
                        pattern = Pattern.compile(o.getKey());
                        System.out.println("Ключ: " + o.getKey() + " , паттерн: " + pattern);
                            if(pattern.matcher(message).find()) {
                                System.out.println("Нашли!");
                                return answersPatterns.get(o.getValue());
                            } //else return elusiveAnswers.get(rnd.nextInt(elusiveAnswers.size()));
                    }
            } else {
                return commonAnswers.get(rnd.nextInt(commonAnswers.size()));
            }*/

                /*{
                return (msg.trim().endsWith("?"))?
                        elusiveAnswers.get(rnd.nextInt(elusiveAnswers.size())):
                        commonAnswers.get(rnd.nextInt(commonAnswers.size()));
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void printMap(Map<String, String> map) {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "     " + entry.getValue());
        }
    }


}
