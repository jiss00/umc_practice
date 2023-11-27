package umc.spring.study.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.TempHandler;

/**
 * 인증입니다
 */
@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService{

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}