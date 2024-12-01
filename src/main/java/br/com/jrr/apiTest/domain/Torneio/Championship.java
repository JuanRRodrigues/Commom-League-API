package br.com.jrr.apiTest.domain.Torneio;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.Team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Championship")
@Table(name = "Championships")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Championship {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String ranking;
    private boolean inTorneio;
    private Double prize;
    private String img;
    private String championship;  // Novo atributo
    private String winner;  // Novo atributo
    @ElementCollection
    private List<String> rounds = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Championships_teams",
            joinColumns = @JoinColumn(name = "Championship_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private List<Match> partidas = new ArrayList<>();

    public Championship(String nome, String ranking, boolean inTorneio, List<Team> teams, List<Match> partidas, double prize, String championship, String winner) {
        this.nome = nome;
        this.ranking = ranking;
        this.inTorneio = inTorneio;
        this.teams = teams;
        this.partidas = partidas;
        this.prize = prize;
        this.championship = championship;
        this.winner = winner;

    }

    // Método para adicionar o próximo round
    public void addNextRound(Championship nextRoundChampionship) {
        if (rounds.isEmpty()) {
            rounds.add("Fase de Grupos");
        } else {
            // Lógica para adicionar o próximo round
            String currentRound = rounds.get(rounds.size() - 1);
            if (currentRound.equals("Fase de Grupos")) {
                rounds.add("Quartas de Final");
            } else if (currentRound.equals("Quartas de Final")) {
                rounds.add("Semi-Final");
            } else if (currentRound.equals("Semi-Final")) {
                rounds.add("Final");
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public String getRanking() {
        return ranking;
    }

    // Getters e Setters para a lista de times

    public List<Team> getTimes() {
        return teams;
    }

    public void setTimes(List<Team> teams) {
        this.teams = teams;
    }

    public boolean isInTorneio() {
        return inTorneio;
    }

    public Double getPrize() {
        return prize;
    }

    public String getImg() {
        return img;
    }


    public String getChampionship() {
        return championship;
    }

    public String getWinner() {
        return winner;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Match> getPartidas() {
        return partidas;
    }

    public Championship() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public void setInTorneio(boolean inTorneio) {
        this.inTorneio = inTorneio;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setPartidas(List<Match> partidas) {
        this.partidas = partidas;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Championship{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", ranking='" + ranking + '\'' +
                ", inTorneio=" + inTorneio +
                ", prize=" + prize +
                ", img='" + img + '\'' +
                ", championship='" + championship + '\'' +
                ", winner='" + winner + '\'' +
                ", partidas=" + partidas +
                '}';
    }

    public void addMatch(Match match) {
            // Adiciona a partida à lista de partidas
            this.partidas.add(match);

            // Associa o campeonato à partida
            match.setTournament(this);

            // Aqui você pode salvar a partida no banco de dados, se necessári
    }

    public List<String> getRounds() {
        return rounds;
    }

    public void setRounds(List<String> rounds) {
        this.rounds = rounds;
    }

    public void setRound(String round) {
        if (!rounds.contains(round)) {
            rounds.add(round);
        } else {
            // Caso deseje substituir ou manipular de outra forma
            int index = rounds.indexOf(round);
            rounds.set(index, round); // Atualiza o round existente
        }
    }
}
