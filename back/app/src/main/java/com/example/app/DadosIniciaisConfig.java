package com.example.app;

import com.example.app.model.PacoteHospedagem;
import com.example.app.model.ServicoAdicional;
import com.example.app.model.TipoCobrancaServico;
import com.example.app.model.catalogo.CatalogoServicosSingleton;
import com.example.app.repository.PacoteHospedagemRepository;
import com.example.app.repository.ServicoAdicionalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DadosIniciaisConfig {

    @Bean
    CommandLineRunner popularServicosEPacotes(
            ServicoAdicionalRepository servicoRepository,
            PacoteHospedagemRepository pacoteRepository) {
        return args -> {
            ServicoAdicional cafe = criarServicoSeNecessario(
                    servicoRepository,
                    "Cafe da manha",
                    "Buffet diario servido na hospedagem.",
                    35.0,
                    TipoCobrancaServico.POR_DIARIA
            );

            ServicoAdicional passeios = criarServicoSeNecessario(
                    servicoRepository,
                    "Passeios turisticos",
                    "Roteiros guiados por pontos turisticos locais.",
                    120.0,
                    TipoCobrancaServico.POR_HOSPEDE
            );

            ServicoAdicional transporte = criarServicoSeNecessario(
                    servicoRepository,
                    "Transporte",
                    "Transporte local durante a estadia.",
                    80.0,
                    TipoCobrancaServico.UNICA
            );

            ServicoAdicional lavanderia = criarServicoSeNecessario(
                    servicoRepository,
                    "Lavanderia",
                    "Servico de lavagem de roupas durante a estadia.",
                    45.0,
                    TipoCobrancaServico.UNICA
            );

            ServicoAdicional traslado = criarServicoSeNecessario(
                    servicoRepository,
                    "Traslado aeroporto-hospedagem",
                    "Deslocamento entre aeroporto e hospedagem.",
                    100.0,
                    TipoCobrancaServico.UNICA
            );

            // Criando pacotes base
            PacoteHospedagem pacoteEconomico = criarPacoteSeNecessario(
                    pacoteRepository,
                    "Pacote Economico",
                    "Cafe da manha e lavanderia para uma estadia simples.",
                    false,
                    List.of(cafe, lavanderia),
                    List.of()
            );

            criarPacoteSeNecessario(
                    pacoteRepository,
                    "Pacote Familia",
                    "Servicos pensados para grupos e familias.",
                    false,
                    List.of(cafe, transporte, lavanderia),
                    List.of()
            );

            // Demonstracao do padrao Composite
            criarPacoteSeNecessario(
                    pacoteRepository,
                    "Pacote Premium",
                    "Tudo do Pacote Economico, mais passeios e traslado para uma experiencia completa.",
                    false,
                    List.of(passeios, traslado, transporte),
                    List.of(pacoteEconomico)
            );

            criarPacoteSeNecessario(
                    pacoteRepository,
                    "Pacote Personalizado",
                    "Escolha livremente os servicos adicionais desejados.",
                    true,
                    List.of(),
                    List.of()
            );

            // Garante que o catalogo global (Singleton) reflita os servicos ativos
            CatalogoServicosSingleton.getInstance().atualizar(servicoRepository.findByAtivoTrue());
        };
    }

    private ServicoAdicional criarServicoSeNecessario(
            ServicoAdicionalRepository repository,
            String nome,
            String descricao,
            double preco,
            TipoCobrancaServico tipoCobranca) {
        if (repository.existsByNome(nome)) {
            return repository.findAll().stream()
                    .filter(servico -> nome.equals(servico.getNome()))
                    .findFirst()
                    .orElseThrow();
        }

        return repository.save(new ServicoAdicional(nome, descricao, preco, tipoCobranca));
    }

    private PacoteHospedagem criarPacoteSeNecessario(
            PacoteHospedagemRepository repository,
            String nome,
            String descricao,
            boolean personalizado,
            List<ServicoAdicional> servicos,
            List<PacoteHospedagem> subPacotes) {
        
        if (repository.existsByNome(nome)) {
            return repository.findAll().stream()
                    .filter(pacote -> nome.equals(pacote.getNome()))
                    .findFirst()
                    .orElseThrow();
        }

        return repository.save(new PacoteHospedagem(nome, descricao, personalizado, servicos, subPacotes));
    }
}