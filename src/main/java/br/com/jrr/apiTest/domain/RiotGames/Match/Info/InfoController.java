package br.com.jrr.apiTest.domain.RiotGames.Match.Info;

import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;

import br.com.jrr.apiTest.domain.RiotGames.Match.Info.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping
    public ResponseEntity<List<Info>> getAllInfo() {
        List<Info> infos = infoService.getAllInfos(); // Altere para getAllInfos()
        return ResponseEntity.ok(infos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Info> getInfoById(@PathVariable String id) {
        Optional<Info> info = infoService.getInfoById(id); // Altere para getInfoById()
        return info.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Novo endpoint para buscar por MatchID
    @GetMapping("/match/{matchId}")
    public ResponseEntity<Info> getInfoByMatchId(@PathVariable String matchId) {
        Optional<Info> info = infoService.getInfoByMatchId(matchId); // Altere para getInfoByMatchId()
        return info.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint com paginação para buscar por PUUID
    @GetMapping("/by-puuid/{puuid}")
    public ResponseEntity<Page<Info>> getInfosByPuuid(
            @PathVariable String puuid,
            @RequestParam(defaultValue = "0") int page,   // Página começa em 0
            @RequestParam(defaultValue = "5") int size    // Tamanho da página (10 por padrão)
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Info> infos = infoService.getInfosByPuuid(puuid, pageable);
        return infos.hasContent() ? ResponseEntity.ok(infos) : ResponseEntity.notFound().build();
    }


}
