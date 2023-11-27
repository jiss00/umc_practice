package umc.spring.study.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.domain.Enum.MissionStatus;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository;
import umc.spring.study.repository.MissionRepository;
import umc.spring.study.service.StoreService.StoreQueryService;
import umc.spring.study.validation.annotation.ExistMission;
import umc.spring.study.validation.annotation.ExistStore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<MemberMission> target = memberMissionRepository.findById(value);
        System.out.println("value = " + value);

        if (!target.isEmpty()&&target.get().getStatus()== MissionStatus.CHALLENGING){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
