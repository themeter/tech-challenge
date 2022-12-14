package io.dimitar.power.powerchallenge.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.dimitar.power.powerchallenge.model.Battery;
import io.dimitar.power.powerchallenge.model.BatteryRange;
import io.dimitar.power.powerchallenge.model.PostcodeRange;
import io.dimitar.power.powerchallenge.service.BatteryService;

/**
 *
 */
@RestController
@RequestMapping("/api/battery")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @GetMapping
    public ResponseEntity<BatteryRange> findBatteriesByPostcodeRange(@Validated @RequestBody PostcodeRange range) {
        return ResponseEntity.ok(batteryService.retriveBatteries(range.getStart(), range.getEnd()));
    }

    @PostMapping
    public ResponseEntity<List<Battery>> saveBatteries(@Validated @RequestBody List<Battery> batteries) {
        return ResponseEntity.ok(batteryService.storeBatteries(batteries));
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bad parameter in request")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badRequest() {
        // Do nothing
    }

}
