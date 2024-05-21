package br.com.jrr.apiTest.domain.Team;


import br.com.jrr.apiTest.domain.Match.MetadadoEntity;
import br.com.jrr.apiTest.domain.Team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, String> {
}
