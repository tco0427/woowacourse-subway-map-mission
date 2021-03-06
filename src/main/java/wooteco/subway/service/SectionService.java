package wooteco.subway.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wooteco.subway.dao.SectionDao;
import wooteco.subway.domain.Section;
import wooteco.subway.domain.Sections;
import wooteco.subway.dto.SectionRequest;

@Service
@Transactional
public class SectionService {

    private final SectionDao sectionDao;

    public SectionService(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    public void save(Long lineId, SectionRequest request) {
        final Sections sections = new Sections(sectionDao.findByLineId(lineId));
        final Section section = new Section(lineId, request.getUpStationId(), request.getDownStationId(),
                request.getDistance());

        List<Section> result = sections.add(section);
        updateSection(lineId, result);
    }

    public void delete(Long lineId, Long stationId) {
        final Sections sections = new Sections(sectionDao.findByLineId(lineId));

        List<Section> result = sections.delete(stationId);
        updateSection(lineId, result);
    }

    private void updateSection(Long lineId, List<Section> sections) {
        sectionDao.deleteByLineId(lineId);

        sectionDao.saveAll(sections);
    }
}
