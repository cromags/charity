package pl.bw.charity.service;

import org.springframework.stereotype.Service;
import pl.bw.charity.domain.model.Good;
import pl.bw.charity.domain.repository.GoodRepository;

import java.util.List;

@Service
public class GoodService {

    private final GoodRepository goodRepository;

    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    public List<Good> getAllGoods(){
        return goodRepository.findAll();
    }
}
