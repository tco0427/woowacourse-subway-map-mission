package wooteco.subway.dao;

import wooteco.subway.domain.Section;

public interface SectionDao {

    Section save(Section section);

    int deleteById(Long id);
}