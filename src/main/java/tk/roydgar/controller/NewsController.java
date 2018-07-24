package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.roydgar.model.entity.News;
import tk.roydgar.model.service.NewsService;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsController {

    private NewsService newsService;

    @GetMapping("/news/{clientId}")
    public ResponseEntity<?> findByClientId(@PathVariable Long clientId) {
        return newsService.findByClientId(clientId);
    }

}
