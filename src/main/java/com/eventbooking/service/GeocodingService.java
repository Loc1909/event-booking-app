package com.eventbooking.service;

import com.eventbooking.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
public class GeocodingService {
    public Optional<BigDecimal[]> getCoordinates(String location)  {
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" +
                URLEncoder.encode(location, StandardCharsets.UTF_8);

        URI uri = URI.create(url);
        try (InputStream is = uri.toURL().openStream()) {
            String json = new String(is.readAllBytes());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode arr = mapper.readTree(json);

            if (!arr.isArray() || arr.isEmpty()) return Optional.empty();

            JsonNode obj = arr.get(0);
            BigDecimal lat = obj.get("lat").asDecimal();
            BigDecimal lon = obj.get("lon").asDecimal();

            return Optional.of(new BigDecimal[]{lat, lon});
        } catch (IOException e){
            log.error("Error fetching coordinates for location: {}", location, e);
            throw new BadRequestException("Location not exist");
        }
    }
}
