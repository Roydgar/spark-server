package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.service.ClientService;
import tk.roydgar.util.ModelAndViewUtil;
import tk.roydgar.util.ValidatorUtil;
import tk.roydgar.util.constants.ParameterNames;
import tk.roydgar.util.constants.TemplatePaths;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@Scope("prototype")
public class ClientRegistrationController {

    private ClientService clientService;

    @Autowired
    public ClientRegistrationController(ClientService clientService) {
        this.clientService = clientService;
    }

    TemplateViewRoute handle = (Request request, Response response) -> {
        Optional<Client> client = getClientFromRequest(request);

        if (!client.isPresent()) {
            return ModelAndViewUtil.createModelAndView(TemplatePaths.CLIENT_REGISTRATION_FROM);
        }

        clientService.save(client.get());
        return ModelAndViewUtil.createModelAndView(TemplatePaths.INDEX);
    };

    private Optional<Client> getClientFromRequest(Request request) {
        String login = request.queryParams(ParameterNames.LOGIN);
        String password = request.queryParams(ParameterNames.PASSWORD);
        String name = request.queryParams(ParameterNames.NAME);
        String phone = request.queryParams(ParameterNames.PHONE);
        String email = request.queryParams(ParameterNames.EMAIL);
        String address = request.queryParams(ParameterNames.ADDRESS);

        if (ValidatorUtil.stringIsEmptyOrNull(login, password, name, password, email, address)) {
            return Optional.empty();
        }

        return Optional.of(Client.builder().login(login)
                .password(password).name(name).phone(phone)
                .email(email).address(address).build());
    }

}
