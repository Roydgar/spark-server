package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.News;
import tk.roydgar.model.repository.NewsRepository;
import tk.roydgar.util.Utils;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsService {

    private NewsRepository newsRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<News> findByClientId(Long clientId) {
        return newsRepository.findAllByClientId(clientId);
    }

}
