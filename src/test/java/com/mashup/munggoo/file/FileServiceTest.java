package com.mashup.munggoo.file;

import com.mashup.munggoo.exception.ConflictException;
import com.mashup.munggoo.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

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
        fileService.save(deviceId, reqFileDto);
    }

    @Test
    public void getFiles() {
        List<File> files = new ArrayList<>();
        files.add(File.from(deviceId, reqFileDto));
        files.add(File.from(2L, new ReqFileDto("Effective Java")));
        files.add(File.from(3L, new ReqFileDto("Test Driven Development")));
        given(fileRepository.findByDeviceId(any())).willReturn(files);
        List<ResFileDto> resFileDtos = fileService.getFiles(deviceId);
        for (int i = 0; i < resFileDtos.size(); i++) {
            assertThat(resFileDtos.get(i).getId()).isEqualTo(files.get(i).getId());
            assertThat(resFileDtos.get(i).getName()).isEqualTo(files.get(i).getName());
        }
    }

    @Test(expected = NotFoundException.class)
    public void getNoExistFile() {
        List<File> files = new ArrayList<>();
        given(fileRepository.findByDeviceId(any())).willReturn(files);
        fileService.getFiles(deviceId);
    }
}
