package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountMatchRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.addAccountRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AssociacaoContaDTO;
import br.com.jrr.apiTest.domain.RiotGames.League.LeagueEntry;
import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.exception.AccountAlreadyAssociatedException;
import br.com.jrr.apiTest.exception.AccountRiotNotFoundException;
import br.com.jrr.apiTest.exception.AuthenticationException;
import br.com.jrr.apiTest.exception.UserNotFoundException;
import br.com.jrr.apiTest.infra.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountRiotWebService {

    @Autowired
    private AccountRiotRepository Repository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatchLolRiotRepository matchRepository;

    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();
   // private final String LINK = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/BepoIV/0264?api_key=RGAPI-52760f41-fb67-441f-aca0-5790dee926f5";
   private final String API_KEY = "?api_key=RGAPI-d873ca05-959e-41c3-b085-5b9c4a0cb10b";
   // private final String Region = "americas.";
  //  private final String LINK2 = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id";
    private final String BASE_URL = ".api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
    private final String BaseURLRiotSummonner = ".api.riotgames.com/lol/summoner/v4/summoners/";
    private final String BaseURLRiotMatch = ".api.riotgames.com/lol/match/v5/matches/";

    public List<AccountRiotDTO> getAccount() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountRiotDTO(
                        s.getId(),
                        s.getPuuid(),
                        s.getGameName(),
                        s.getTagLine(),
                        s.getAccountId(),
                        s.getIdRiot(),
                        s.getProfileIconId(),
                        s.getRevisionDate(),
                        s.getSummonerLevel()

                ))
                .collect(Collectors.toList());  // Coletando a lista de AccountRiotDTO
    }


    public List<AccountMatchRiotDTO> getHistoric() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountMatchRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine()))
                .collect(Collectors.toList());
    }

    public AccountRiotDTO getById(String id) {
        Optional<AccountRiot> optionalPlayer = Repository.findById(id);
        return optionalPlayer.map(s -> new AccountRiotDTO(
                s.getId(),
                s.getPuuid(),
                s.getGameName(),
                s.getTagLine(),
                s.getAccountId(),
                s.getIdRiot(),
                s.getProfileIconId(),
                s.getRevisionDate(),
                s.getSummonerLevel()


        )).orElse(null); // Se não encontrado, retorna null
    }


    public AssociacaoContaDTO registerByAPI(DataAccountRegistrationAPI data) {
        // Extraindo os dados de entrada
        String gameName = data.gameName();
        String tagLine = data.tagLine();
        String region = data.region();

        String continente = "americas";

        // Definindo a região
        switch (region) {
            case "BR1": // Região Brasil
            case "NA1": // Região América do Norte
                continente = "americas";  // Ambas as regiões mapeiam para 'americas'
                break;
            case "KR":  // Região Coreia
                continente = "asia";      // Mapeando para 'asia'
                break;
            default:
                System.out.println("Região não suportada: " + region);
                return null;  // Retorna null se a região não for reconhecida
        }

        try {
            // Verificar se a conta já existe pelo gameName/tagLine
            Optional<AccountRiot> existingAccountByName = Repository.findByGameNameContainingAndTagLineContaining(gameName, tagLine);
            if (existingAccountByName.isPresent()) {
                System.out.println("Conta com o mesmo gameName e tagLine já registrada.");
                // Retorna DTO da conta já existente, sem necessidade de fazer as requisições
                AccountRiot existingPlayer = existingAccountByName.get();
                LeagueEntry firstLeagueEntry = existingPlayer.getLeagueEntries().isEmpty() ? null : existingPlayer.getLeagueEntries().get(0);

                return new AssociacaoContaDTO(
                        existingPlayer.getId(),
                        existingPlayer.getGameName(),
                        existingPlayer.getTagLine(),
                        existingPlayer.getProfileIconId(),
                        existingPlayer.getSummonerLevel(),
                        firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getWins()) : "0", // Exemplo, assume-se que exista pelo menos uma LeagueEntry
                        firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getLosses()) : "0",
                        firstLeagueEntry != null ? firstLeagueEntry.getTier() + " " + firstLeagueEntry.getElo() : "Unranked",
                        firstLeagueEntry != null ? firstLeagueEntry.getQueueType() : "Empty",
                        firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getLeaguePoints()) : "0"
                );
            }

            // Se a conta não existir, faz as requisições à API para coletar os dados
            String urlConta = buildAccountUrl(continente, gameName, tagLine);
            String json = get.obterDados(urlConta);

            // Verifique se a resposta é válida antes de tentar desserializar
            if (json == null || json.contains("error")) {
                throw new RuntimeException("Erro ao obter dados da conta.");
            }

            // Checando se o JSON é um array e removendo os colchetes
            if (json.startsWith("[") && json.endsWith("]")) {
                // Remover os colchetes e processar o primeiro item
                json = json.substring(1, json.length() - 1);
            }

            // Agora o JSON está no formato correto para deserializar
            ObjectMapper objectMapper = new ObjectMapper();
            DataAccountAPI dataAccountAPI = objectMapper.readValue(json, DataAccountAPI.class);

            // Verificar se já existe uma conta com o mesmo puuid
            Optional<AccountRiot> existingAccountByPuuid = Optional.ofNullable(Repository.findByPuuid(dataAccountAPI.puuid()));
            if (existingAccountByPuuid.isPresent()) {
                System.out.println("Conta com o mesmo puuid já registrada.");
                // Retorna DTO da conta já existente, sem necessidade de fazer as requisições
                AccountRiot existingPlayer = existingAccountByPuuid.get();
                LeagueEntry firstLeagueEntry = existingPlayer.getLeagueEntries().isEmpty() ? null : existingPlayer.getLeagueEntries().get(0);

                return new AssociacaoContaDTO(
                        existingPlayer.getId(),
                        existingPlayer.getGameName(),
                        existingPlayer.getTagLine(),
                        existingPlayer.getProfileIconId(),
                        existingPlayer.getSummonerLevel(),
                        firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getWins()) : "0", // Exemplo, assume-se que exista pelo menos uma LeagueEntry
                        firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getLosses()) : "0",
                        firstLeagueEntry != null ? firstLeagueEntry.getTier() + " " + firstLeagueEntry.getElo() : "Unranked",
                        firstLeagueEntry != null ? firstLeagueEntry.getQueueType() : "Empty",
                        firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getLeaguePoints()) : "0"
                );
            }

            // Continuar o processamento para salvar o novo jogador, se não encontrado na base

            // Obtenção do Summoner (informações adicionais)
            String urlSummoner = buildSummonerUrl(region, dataAccountAPI.puuid());
            String json2 = get.obterDados(urlSummoner);
            DataAccountAPI dataAccountAPI2 = convert.getDate(json2, DataAccountAPI.class);

            // Obtenção da Liga (informações adicionais)
            String urlLeague = buildLeagueUrl(region, dataAccountAPI2.id());
            String json4 = get.obterDados(urlLeague);

            // Deserializando a resposta da liga como uma lista de LeagueEntry
            List<LeagueEntry> leagueEntries = objectMapper.readValue(json4, new TypeReference<List<LeagueEntry>>() {});

            // Verifique se a resposta da liga está correta
            if (leagueEntries == null || leagueEntries.isEmpty()) {
                throw new RuntimeException("Nenhuma liga encontrada.");
            }

            // Obtendo as partidas do jogador
            String urlMatches = buildMatchesUrl(continente, dataAccountAPI.puuid());
            String matchJson = get.obterDados(urlMatches);

            // Verifique se a resposta das partidas está correta
            List<String> dataMatch = convert.getDateAsList(matchJson);
            if (dataMatch == null || dataMatch.isEmpty()) {
                throw new RuntimeException("Nenhuma partida encontrada.");
            }

            // Criando uma lista de partidas
            List<Match> matchesSaved = new ArrayList<>();
            for (String matchId : dataMatch) {
                Match matchSaved = new Match();
                matchSaved.setMatchId(matchId);
                matchesSaved.add(matchSaved);
            }

            // Criando o objeto AccountRiot com todos os dados obtidos
            AccountRiot player = new AccountRiot(dataAccountAPI, dataAccountAPI2, matchesSaved, leagueEntries);

            // Associando o accountRiot com as LeagueEntries
            for (LeagueEntry leagueEntry : leagueEntries) {
                leagueEntry.setAccountRiot(player); // Atribuindo a referência do AccountRiot
            }

            // Salvando a conta no repositório
            AccountRiot savedPlayer = Repository.save(player);
            System.out.println("Conta salva com sucesso: " + savedPlayer);

            // A partir daqui, em vez de retornar um AccountRiotDTO, retornamos um AssociacaoContaDTO

            // Extrair dados relevantes da liga (usando o primeiro LeagueEntry como exemplo)
            LeagueEntry firstLeagueEntry = leagueEntries.isEmpty() ? null : leagueEntries.get(0);

            String elo = firstLeagueEntry != null ? firstLeagueEntry.getTier() + " " + firstLeagueEntry.getElo() : "Unranked";
            String wins = firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getWins()) : "0";
            String loses = firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getLosses()) : "0";
            String queueType = firstLeagueEntry != null ? firstLeagueEntry.getQueueType() : "Empty";
            String points = firstLeagueEntry != null ? String.valueOf(firstLeagueEntry.getLeaguePoints()) : "0";

            // Criando o DTO de associação
            return new AssociacaoContaDTO(
                    savedPlayer.getId(),
                    savedPlayer.getGameName(),
                    savedPlayer.getTagLine(),
                    savedPlayer.getProfileIconId(),
                    savedPlayer.getSummonerLevel(),
                    wins,
                    loses,
                    elo,
                    queueType,
                    points
            );

        } catch (Exception e) {
            System.out.println("Erro ao registrar conta: " + e.getMessage());
            return null;
        }
    }

    // Método que faz a busca parcial por gameName e tagLine
    public List<AccountRiot> searchAccounts(String gameName, String tagLine) {
        // Caso o gameName e tagLine sejam fornecidos
        if (gameName != null && !gameName.isEmpty() && tagLine != null && !tagLine.isEmpty()) {
            List<AccountRiot> result = Repository.findByGameNameContainingIgnoreCaseAndTagLineContainingIgnoreCase(gameName, tagLine);
            return result.isEmpty() ? List.of() : result;  // Retorna lista vazia se não houver resultados
        }
        // Caso apenas o gameName seja fornecido
        else if (gameName != null && !gameName.isEmpty()) {
            List<AccountRiot> result = Repository.findByGameNameContainingIgnoreCaseAndTagLineContainingIgnoreCase(gameName, "");
            return result.isEmpty() ? List.of() : result;  // Retorna lista vazia se não houver resultados
        }
        // Caso apenas a tagLine seja fornecida
        else if (tagLine != null && !tagLine.isEmpty()) {
            List<AccountRiot> result = Repository.findByGameNameContainingIgnoreCaseAndTagLineContainingIgnoreCase("", tagLine);
            return result.isEmpty() ? List.of() : result;  // Retorna lista vazia se não houver resultados
        }
        // Caso nenhum dos parâmetros seja fornecido, retorna uma lista vazia
        else {
            return List.of();  // Retorna lista vazia
        }
    }
    // Novo método para retornar o DTO simplificado
    public AssociacaoContaDTO getAccountAssociation(String id) {
        Optional<AccountRiot> accountOptional = Repository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new RuntimeException("Conta não encontrada.");
        }

        AccountRiot account = accountOptional.get();

        // Pegando as informações básicas
        String gameName = account.getGameName();
        String tagLine = account.getTagLine();
        String profileIconId = account.getProfileIconId();
        String summonerLevel = account.getSummonerLevel();

        // Pegando a primeira LeagueEntry para extrair vitórias, derrotas, elo, tipo de fila e pontos
        LeagueEntry firstLeagueEntry = account.getLeagueEntries().isEmpty() ? null : account.getLeagueEntries().get(0);

        String wins = (firstLeagueEntry != null) ? firstLeagueEntry.getWins() : "0";
        String loses = (firstLeagueEntry != null) ? firstLeagueEntry.getLosses() : "0";

        // Adicionando as informações de Elo, tipo de fila e LP (pontos)
        String elo = (firstLeagueEntry != null) ? firstLeagueEntry.getTier() + " " + firstLeagueEntry.getElo() : "Unranked";
        String queueType = (firstLeagueEntry != null) ? firstLeagueEntry.getQueueType() : "Unranked";
        String points = (firstLeagueEntry != null) ? firstLeagueEntry.getLeaguePoints() : "0";

        // Retorna o DTO simplificado com todas as informações
        return new AssociacaoContaDTO(id,gameName, tagLine, profileIconId, summonerLevel, wins, loses, elo, queueType, points);
    }


    // Métodos auxiliares para construir as URLs
    private String buildAccountUrl(String continente, String gameName, String tagLine) {
        return "https://" + continente + BASE_URL + gameName + "/" + tagLine + "?api_key=" + ApiKeyManager.getApiKey();
    }

    private String buildSummonerUrl(String region, String puuid) {
        return "https://" + region + BaseURLRiotSummonner + "by-puuid/" + puuid + "?api_key=" + ApiKeyManager.getApiKey();
    }

    private String buildMatchesUrl(String continente, String puuid) {
        return "https://" + continente + BaseURLRiotMatch + "by-puuid/" + puuid + "/ids?start=0&count=20&api_key=" + ApiKeyManager.getApiKey();
    }

    private String buildLeagueUrl(String region, String accountId) {
        return "https://" + region + ".api.riotgames.com/lol/league/v4/entries/by-summoner/" + accountId + "?api_key=" + ApiKeyManager.getApiKey();
    }



    public String addAccountRiot(addAccountRiotDTO data) {

        // Obter a autenticação do usuário atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificar se o usuário está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User not authenticated");
        }

        // Obter o usuário autenticado a partir da autenticação
        User authenticatedUser = (User) authentication.getPrincipal();
        if (authenticatedUser == null) {
            throw new UserNotFoundException("User not found");
        }

        // Log para verificar os dados do usuário
        System.out.println("Autenticado: " + authenticatedUser);

        // Buscar a conta Riot pelo ID fornecido
        Optional<AccountRiot> optionalAccountRiot = Repository.findById(data.id());
        if (optionalAccountRiot.isEmpty()) {
            throw new AccountRiotNotFoundException("Account Riot not found");
        }

        AccountRiot accountRiot = optionalAccountRiot.get();

        // Log para verificar os dados da conta Riot
        System.out.println("Conta Riot: " + accountRiot);

        // Verificar se o usuário já tem uma conta associada
        if (authenticatedUser.getAccountRiot() != null) {
            throw new AccountAlreadyAssociatedException("This user already has an associated Account Riot.");
        }

        // Associar a conta Riot ao usuário autenticado
        authenticatedUser.setAccountRiot(accountRiot);

        // Salvar o usuário com a nova associação
        try {
            userRepository.save(authenticatedUser);  // Salvar o usuário
        } catch (Exception e) {
            // Log de erro e lançar exceção customizada caso ocorra algum erro ao salvar o usuário
            System.err.println("Erro ao salvar o usuário: " + e.getMessage());
            throw new RuntimeException("Error saving user: " + e.getMessage());
        }

        // Relacionamento bidirecional
        accountRiot.setUser(authenticatedUser);  // Atualizar a conta Riot com o usuário

        try {
            // Salvar a conta Riot apenas se necessário
            if (accountRiot.getId() == null) {
                Repository.save(accountRiot);  // Salvar a conta Riot, caso ainda não esteja salva
            }
        } catch (Exception e) {
            // Log de erro e lançar exceção customizada caso ocorra algum erro ao salvar a conta Riot
            System.err.println("Erro ao salvar a conta Riot: " + e.getMessage());
            throw new RuntimeException("Error saving Account Riot: " + e.getMessage());
        }

        return "Account successfully associated.";
    }




}
