package com.mashup.munggoo.file;

import com.mashup.munggoo.exception.ConflictException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    @MockBean
    private FileRepository fileRepository;

    private Long deviceId;

    private ReqFileDto reqFileDto;

    @Before
    public void setUp() {
        deviceId = 1L;
        reqFileDto = new ReqFileDto("Clean Code");
    }

    @Test
    public void saveFile() {
        given(fileRepository.existsByName(reqFileDto.getName())).willReturn(Boolean.FALSE);
        given(fileRepository.save(any())).willReturn(File.from(deviceId, reqFileDto));
        assertThat(fileService.save(deviceId, reqFileDto).getDeviceId()).isEqualTo(deviceId);
        assertThat(fileService.save(deviceId, reqFileDto).getName()).isEqualTo(reqFileDto.getName());
    }

    @Test(expected = ConflictException.class)
    public void saveDuplicatedFileName() {
        given(fileRepository.existsByName(reqFileDto.getName())).willReturn(Boolean.TRUE);
        when(fileService.save(deviceId, reqFileDto)).thenThrow(ConflictException.class);
    }
}
