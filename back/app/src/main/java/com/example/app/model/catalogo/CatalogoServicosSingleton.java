package com.example.app.model.catalogo;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.app.model.ServicoAdicional;

/**
 * Padrão de projeto Singleton (requisito obrigatório da Sprint).
 *
 * Representa o catálogo global de serviços adicionais ativos no sistema
 * (ex.: Café da manhã, Passeios turísticos, Transporte, Lavanderia,
 * Traslado, ...), usado tanto pela montagem de pacotes de hospedagem
 * (Opção 6) quanto, futuramente, por outras telas que precisem consultar
 * quais serviços estão disponíveis para contratação.
 *
 * Justificativa da unicidade: o catálogo é um recurso compartilhado por
 * todo o sistema. Se cada parte da aplicação mantivesse sua própria cópia
 * em memória dos serviços ativos, poderíamos ter versões divergentes do
 * catálogo (por exemplo, um serviço desativado em um cadastro e ainda
 * "ativo" em outro cache), além de desperdiçar consultas repetidas ao
 * banco de dados. Garantindo uma única instância, todo o sistema enxerga
 * exatamente a mesma versão do catálogo e ela pode ser atualizada em um
 * único ponto sempre que um serviço é criado, editado ou desativado.
 *
 * Implementação com inicialização "eager" (instância criada de forma
 * estática) e acesso somente pelo método getInstance(), evitando a
 * criação de múltiplas instâncias.
 */
public final class CatalogoServicosSingleton {

    private static final CatalogoServicosSingleton INSTANCIA = new CatalogoServicosSingleton();

    // Map por id para permitir busca rápida O(1) ao montar pacotes.
    private final Map<Long, ServicoAdicional> servicosAtivos = new LinkedHashMap<>();

    private CatalogoServicosSingleton() {
        // Construtor privado: impede a criação de novas instâncias
        // fora desta classe, garantindo que exista apenas um catálogo
        // compartilhado por toda a aplicação.
    }

    public static CatalogoServicosSingleton getInstance() {
        return INSTANCIA;
    }

    /** Substitui o conteúdo do catálogo pela lista de serviços ativos informada. */
    public synchronized void atualizar(List<ServicoAdicional> servicos) {
        servicosAtivos.clear();
        if (servicos != null) {
            for (ServicoAdicional servico : servicos) {
                if (servico.isAtivo()) {
                    servicosAtivos.put(servico.getId(), servico);
                }
            }
        }
    }

    /** Inclui/atualiza um único serviço no catálogo (ou remove, se estiver inativo). */
    public synchronized void registrar(ServicoAdicional servico) {
        if (servico == null || servico.getId() == null) {
            return;
        }
        if (servico.isAtivo()) {
            servicosAtivos.put(servico.getId(), servico);
        } else {
            servicosAtivos.remove(servico.getId());
        }
    }

    public synchronized Optional<ServicoAdicional> buscarPorId(Long id) {
        return Optional.ofNullable(servicosAtivos.get(id));
    }

    public synchronized List<ServicoAdicional> listarTodos() {
        return Collections.unmodifiableList(List.copyOf(servicosAtivos.values()));
    }

    public synchronized boolean contemTodos(List<Long> ids) {
        return ids != null && ids.stream().allMatch(servicosAtivos::containsKey);
    }
}