import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ElevadorServiceImpl implements IElevadorService {

    private List<SolicitacaoElevador> solicitacoes;

    public ElevadorServiceImpl(String json) {
        Gson gson = new GsonBuilder().create();
        solicitacoes = gson.fromJson(json, new TypeToken<List<SolicitacaoElevador>>(){}.getType());
    }

    @Override
    public List<Integer> andarMenosUtilizado() {
        Map<Integer, Integer> contagem = new HashMap<>();
        for (SolicitacaoElevador solicitacao : solicitacoes) {
            Integer andar = solicitacao.getAndar();
            contagem.put(andar, contagem.getOrDefault(andar, 0) + 1);
        }
        Integer min = contagem.values().stream().min(Integer::compareTo).orElse(0);
        return contagem.entrySet().stream()
                .filter(entry -> entry.getValue().equals(min))
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> elevadorMaisFrequentado() {
        Map<Character, Integer> contagem = new HashMap<>();
        for (SolicitacaoElevador solicitacao : solicitacoes) {
            Character elevador = solicitacao.getElevador();
            contagem.put(elevador, contagem.getOrDefault(elevador, 0) + 1);
        }
        Integer max = contagem.values().stream().max(Integer::compareTo).orElse(0);
        return contagem.entrySet().stream()
                .filter(entry -> entry.getValue().equals(max))
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() {
        List<Character> elevadoresMaisFrequentados = elevadorMaisFrequentado();
        Map<Character, Map<Character, Integer>> contagem = new HashMap<>();
        for (SolicitacaoElevador solicitacao : solicitacoes) {
            Character elevador = solicitacao.getElevador();
            Character turno = solicitacao.getTurno();
            if (elevadoresMaisFrequentados.contains(elevador)) {
                Map<Character, Integer> periodoContagem = contagem.getOrDefault(elevador, new HashMap<>());
                periodoContagem.put(turno, periodoContagem.getOrDefault(turno, 0) + 1);
                contagem.put(elevador, periodoContagem);
            }
        }
        Map<Character, Integer> maxContagem = new HashMap<>();
        for (Character elevador : contagem.keySet()) {
            Map<Character, Integer> periodoContagem = contagem.get(elevador);
            Integer max = periodoContagem.values().stream().max(Integer::compareTo).orElse(0);
            maxContagem.put(elevador, max);
        }
        Integer max = maxContagem.values().stream().max(Integer::compareTo).orElse(0);
        return maxContagem.entrySet().stream()
                .filter(entry -> entry.getValue().equals(max))
                .map(Map.Entry::
getKey)
.sorted()
.collect(Collectors.toList());
}
@Override
public List<Character> periodoMaiorUtilizacaoConjuntoElevadores() {
    Map<Character, Map<Character, Integer>> contagem = new HashMap<>();
    for (SolicitacaoElevador solicitacao : solicitacoes) {
        Character elevador = solicitacao.getElevador();
        Character turno = solicitacao.getTurno();
        Map<Character, Integer> periodoContagem = contagem.getOrDefault(turno, new HashMap<>());
        periodoContagem.put(elevador, periodoContagem.getOrDefault(elevador, 0) + 1);
        contagem.put(turno, periodoContagem);
    }
    Map<Character, Integer> maxContagem = new HashMap<>();
    for (Character turno : contagem.keySet()) {
        Map<Character, Integer> periodoContagem = contagem.get(turno);
        Integer max = periodoContagem.values().stream().mapToInt(Integer::intValue).sum();
        maxContagem.put(turno, max);
    }
    Integer max = maxContagem.values().stream().max(Integer::compareTo).orElse(0);
    return maxContagem.entrySet().stream()
            .filter(entry -> entry.getValue().equals(max))
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.toList());
}
}
