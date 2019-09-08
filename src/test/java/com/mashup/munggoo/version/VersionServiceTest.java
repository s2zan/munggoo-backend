package com.mashup.munggoo.version;

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
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VersionServiceTest {
    @Autowired
    private VersionService versionService;

    @MockBean
    private VersionRepository versionRepository;

    private Version version;

    private List<Version> versionList;

    private ResVersionDto resVersionDto;

    @Before
    public void setUp() {
        version = new Version("1.0.0", "1");
        versionList = new ArrayList<>();
        versionList.add(version);
        resVersionDto = new ResVersionDto(version);
    }

    @Test
    public void getVersion() {
        given(versionRepository.findAll()).willReturn(versionList);
        assertThat(versionService.getVersion().getVersion()).isEqualTo(resVersionDto.getVersion());
        assertThat(versionService.getVersion().getLink()).isEqualTo(resVersionDto.getLink());
    }
}
