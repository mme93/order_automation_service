package automation_order.backend.account.service;

import automation_order.backend.account.model.dto.RollDto;
import automation_order.backend.account.model.entity.RollEntity;
import automation_order.backend.account.repository.RollRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RollService {

    private final RollRepository rollRepository;

    public RollService(RollRepository rollRepository) {
        this.rollRepository = rollRepository;
    }

    public boolean createRoll(RollDto rollDto) {
        for (RollEntity rollEntity : rollRepository.findAll()) {
            if (rollEntity.getName().equals(rollDto.getName())) {
                return false;
            }
        }
        rollRepository.save(new RollEntity(rollDto.getName()));
        return true;
    }

    public List<RollDto> getAll() {
        List<RollDto> rollDtos = new ArrayList<>();
        for (RollEntity rollEntity : this.rollRepository.findAll()) {
            rollDtos.add(new RollDto(rollEntity.getName()));
        }
        return rollDtos;
    }

}
