package org.itacademy.exam5.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.itacademy.exam5.dto.ParkingDto;
import org.itacademy.exam5.dto.ParkingSpotDto;
import org.itacademy.exam5.enums.ParkingSpotType;
import org.itacademy.exam5.service.ParkingSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "ParkingSpot", description = "API для парковочных мест")
@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-spots")
public class ParkingSpotController {
    private final ParkingSpotService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех парковочных мест успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class)))})
    })
    @Operation(summary = "Просмотр списка парковочных мест")
    @GetMapping
    public ResponseEntity<List<ParkingSpotDto>> getAll() {
        try {
            List<ParkingSpotDto> parkingSpots = service.getAll();
            if (!parkingSpots.isEmpty()) return new ResponseEntity<>(parkingSpots, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно найдено",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class)))})
    })
    @Operation(summary = "Поиск парковочных мест по номеру")
    @GetMapping("/{spotNumber}")
    public ResponseEntity<ParkingSpotDto> getBySpotNumber(@PathVariable String spotNumber) {
        try {
            ParkingSpotDto parkingSpot = service.getParkingSpotBySpotNumber(spotNumber);
            return new ResponseEntity<>(parkingSpot, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Парковочное место успешно создано",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class)))})
    })
    @Operation(summary = "Создание парковочных мест")
    @PostMapping
    public ResponseEntity<ParkingSpotDto> create(@RequestBody ParkingSpotDto dto) {
        try {
            service.create(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные парковочного места успешно обновлены",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class)))})
    })
    @Operation(summary = "Обновление данных парковочного места")
    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpotDto> update(@PathVariable Long id, @RequestBody ParkingSpotDto dto) {
        try {
            service.update(id, dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно удалено",
                    content = {
                            @Content(mediaType = "application/json")})
    })
    @Operation(summary = "Удаление парковочного места по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно забронировано",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class)))})
    })
    @Operation(summary = "Бронирование парковочного места")
    @PostMapping("/book")
    public ResponseEntity<ParkingDto> book(@RequestBody ParkingDto dto) {
        try {
            service.book(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Парковочное место успешно освобождено",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class)))})
    })
    @Operation(summary = "Освобождение парковочного места")
    @PostMapping("/release")
    public ResponseEntity<ParkingDto> release(@RequestBody ParkingDto dto) {
        try {
           service.release(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список парковочных мест по типу успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ParkingSpotDto.class)))})
    })
    @Operation(summary = "Просмотр списка парковочных мест по типу")
    @GetMapping("/byType")
    public ResponseEntity<List<ParkingSpotDto>> getByType(@RequestParam ParkingSpotType type) {
        try {
            List<ParkingSpotDto> parkingSpots = service.getByType(type);
            if (!parkingSpots.isEmpty()) return new ResponseEntity<>(parkingSpots, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
