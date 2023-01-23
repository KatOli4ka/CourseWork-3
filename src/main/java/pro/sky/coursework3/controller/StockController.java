package pro.sky.coursework3.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.coursework3.model.SockGoods;
import pro.sky.coursework3.service.StockService;

@RestController
@RequestMapping("/api/socks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @Operation(summary = "ПРИХОД", description = "Приход носков на склад")
    @PostMapping
    public ResponseEntity<?> influx(@RequestBody SockGoods sockGoods) {
        stockService.influx(sockGoods);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "СПИСАНИЕ", description = "Списание носков со склада")
    @PutMapping
    public ResponseEntity<?> consumption(@RequestBody SockGoods sockGoods) {
        stockService.consumption(sockGoods);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "ПОЛУЧЕНИЕ", description = "Получение определенных носков")
    @GetMapping
    public ResponseEntity<Integer> amount(@RequestParam String color,
                                          @RequestParam int size,
                                          @RequestParam(required = false, defaultValue = "0") int cottonMin,
                                          @RequestParam(required = false, defaultValue = "100") int cottonMax) {
        int available = stockService.amount(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(available);
    }
    @Operation(summary = "УТИЛЬ", description = "Списание брака со склада")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody SockGoods sockGoods) {
        stockService.consumption(sockGoods);
        return ResponseEntity.ok().build();
    }

}
