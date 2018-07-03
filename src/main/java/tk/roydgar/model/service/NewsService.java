package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.News;
import tk.roydgar.model.repository.NewsRepository;
import tk.roydgar.util.Utils;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsService {

    private NewsRepository newsRepository;

    public News findByClientId(String clientId) {
        Long id = Utils.parseId(clientId);
        if (id == null) {
            return null;
        }

        return newsRepository.findByClientId(id);
    }
}
