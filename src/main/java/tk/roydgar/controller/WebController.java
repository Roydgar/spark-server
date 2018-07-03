package tk.roydgar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.get;

@Controller
public class WebController {

    private IndexPageController indexPageController;

    @Autowired
    public WebController(IndexPageController indexPageController) {
        this.indexPageController = indexPageController;
    }

    public void setupRoutes() {
        get("/", indexPageController.handle, new VelocityTemplateEngine());
    }

}
