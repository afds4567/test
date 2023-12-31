package com.mapbefine.mapbefine.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.mapbefine.mapbefine.entity.Coordinate;
import com.mapbefine.mapbefine.entity.Location;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @DisplayName("범위 내의 Pin을 검색한다.")
    @ParameterizedTest
    @MethodSource(value = "coordinates_Success")
    void findAllByRectangle_Success(BigDecimal latitude, BigDecimal longitude) {
        // given
        BigDecimal distance = BigDecimal.valueOf(0.0001);
        Coordinate coordinate = new Coordinate(latitude, longitude);
        Location location = new Location(
                "parcel",
                "road",
                coordinate,
                "legalDongCode"
        );
        locationRepository.save(location);

        // when
        List<Location> locations = locationRepository.findAllByRectangle(
                BigDecimal.valueOf(35),
                BigDecimal.valueOf(127),
                distance
        );

        // then
        assertThat(locations).hasSize(1);
    }

    static Stream<Arguments> coordinates_Success() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(34.9999), BigDecimal.valueOf(126.9999)),
                Arguments.of(BigDecimal.valueOf(35.0001), BigDecimal.valueOf(126.9999)),
                Arguments.of(BigDecimal.valueOf(34.9999), BigDecimal.valueOf(127.0001)),
                Arguments.of(BigDecimal.valueOf(35.0001), BigDecimal.valueOf(127.0001))
        );
    }

    @DisplayName("범위 내의 Pin을 검색한다.")
    @ParameterizedTest
    @MethodSource(value = "coordinates_Fail")
    void findAllByRectangle_Fail(BigDecimal latitude, BigDecimal longitude) {
        // given
        BigDecimal distance = BigDecimal.valueOf(0.0001);
        Coordinate coordinate = new Coordinate(latitude, longitude);
        Location location = new Location(
                "parcel",
                "road",
                coordinate,
                "legalDongCode"
        );
        locationRepository.save(location);

        // when
        List<Location> locations = locationRepository.findAllByRectangle(BigDecimal.valueOf(35),
                BigDecimal.valueOf(127), distance);

        // then
        assertThat(locations).isEmpty();
    }

    static Stream<Arguments> coordinates_Fail() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(34.9999), BigDecimal.valueOf(126.9998)),
                Arguments.of(BigDecimal.valueOf(35.0001), BigDecimal.valueOf(126.9998)),
                Arguments.of(BigDecimal.valueOf(34.9998), BigDecimal.valueOf(127.0001)),
                Arguments.of(BigDecimal.valueOf(35.0002), BigDecimal.valueOf(127.0001))
        );
    }

}
