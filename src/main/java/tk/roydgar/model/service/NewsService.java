package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.News;
import tk.roydgar.model.repository.NewsRepository;
import tk.roydgar.util.Utils;

import java.util.List;

import static tk.roydgar.util.ResponseEntityUtil.responseEntityFromList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsService {

    private NewsRepository newsRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findByClientId(Long clientId) {
        return responseEntityFromList(newsRepository.findAllByClientId(clientId));
    }

}
