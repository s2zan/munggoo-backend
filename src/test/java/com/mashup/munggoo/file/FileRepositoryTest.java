package com.mashup.munggoo.file;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;

    private File file;

    private Long deviceId;

    private ReqFileDto reqFileDto;

    @Before
    public void setUp() {
        deviceId = 1L;
        reqFileDto = new ReqFileDto("Clean Code");
        file = File.from(deviceId, reqFileDto);
    }

    @Test
    public void existsFileByFileName() {
        fileRepository.save(file);
        assertThat(fileRepository.existsByName(reqFileDto.getName())).isTrue();
    }

    @Test
    public void findFileByDeviceId() {
        fileRepository.save(file);
        List<File> files = fileRepository.findByDeviceId(deviceId);
        assertThat(files.get(0).getDeviceId()).isEqualTo(deviceId);
        assertThat(files.get(0).getName()).isEqualTo(reqFileDto.getName());
    }
}
