Relatório de testes

# RELATÓRIO DE TESTES UNITÁRIOS

## 1. Introdução

Este relatório apresenta os testes unitários desenvolvidos para o sistema de hospedagem utilizando a biblioteca JUnit 5. O objetivo dos testes foi validar as principais regras de negócio do sistema, garantindo o correto funcionamento dos cálculos, validações, tratamento de exceções e operações relacionadas às hospedagens.

Os testes foram executados sobre as entidades e serviços responsáveis pelo gerenciamento de quartos, aluguéis e hospedagens.

---

## 2. Ferramentas Utilizadas

* Java
* JUnit 5
* Mockito
* Spring Boot
* Maven

---

## 3. Testes Realizados

### 3.1 Testes da Classe QuartoIndividual

Objetivo: validar o cálculo da diária do quarto individual.

#### Cenário: Cálculo da diária

* Valor base: R$ 200,00
* Quantidade de camas: 3
* Resultado esperado: R$ 300,00
* Resultado obtido: R$ 300,00
* Status: Aprovado

Resultado: o cálculo da diária foi realizado corretamente de acordo com as regras definidas para o quarto individual.

---

### 3.2 Testes da Classe QuartoFamilia

Objetivo: validar o cálculo da diária considerando a quantidade de hóspedes e a aplicação de descontos.

#### Cenário 1 – Sem desconto

* Valor base: R$ 200,00
* Quantidade de hóspedes: 4
* Resultado esperado: R$ 480,00
* Resultado obtido: R$ 480,00
* Status: Aprovado

#### Cenário 2 – Com desconto

* Valor base: R$ 200,00
* Quantidade de hóspedes: 5
* Resultado esperado: R$ 495,00
* Resultado obtido: R$ 495,00
* Status: Aprovado

Resultado: foi validado o funcionamento correto da regra de desconto aplicada para grupos com cinco ou mais hóspedes.

---

### 3.3 Testes da Classe QuartoDuplo

Objetivo: validar o cálculo da diária considerando o tipo de cama e a utilização de berço.

#### Cenário 1 – Cama Queen sem berço

* Resultado esperado: R$ 280,00
* Resultado obtido: R$ 280,00
* Status: Aprovado

#### Cenário 2 – Cama Queen com berço

* Resultado esperado: R$ 320,00
* Resultado obtido: R$ 320,00
* Status: Aprovado

#### Cenário 3 – Cama King sem berço

* Resultado esperado: R$ 350,00
* Resultado obtido: R$ 350,00
* Status: Aprovado

#### Cenário 4 – Cama King com berço

* Resultado esperado: R$ 390,00
* Resultado obtido: R$ 390,00
* Status: Aprovado

Resultado: os testes confirmaram o correto funcionamento das regras relacionadas ao tipo de cama e à utilização de berço no cálculo da diária.

---

### 3.4 Testes da Classe QuartoCapacidadeTest

Objetivo: validar a capacidade máxima permitida para cada tipo de quarto.

#### Cenário 1 – Quarto Individual

* Capacidade esperada: 3 hóspedes
* Resultado obtido: 3 hóspedes
* Status: Aprovado

#### Cenário 2 – Quarto Duplo sem berço

* Capacidade esperada: 2 hóspedes
* Resultado obtido: 2 hóspedes
* Status: Aprovado

#### Cenário 3 – Quarto Duplo com berço

* Capacidade esperada: 3 hóspedes
* Resultado obtido: 3 hóspedes
* Status: Aprovado

#### Cenário 4 – Quarto Família

* Capacidade esperada: 5 hóspedes
* Resultado obtido: 5 hóspedes
* Status: Aprovado

Resultado: todas as regras de capacidade máxima dos quartos foram validadas com sucesso.

---

### 3.5 Testes da Classe QuartoDuploBercoTest

Objetivo: validar as regras relacionadas à solicitação de berço.

#### Cenário 1 – Solicitação de berço em quarto sem berço disponível

* Resultado esperado: lançamento de RecursoNaoPermitidoException
* Resultado obtido: exceção lançada corretamente
* Status: Aprovado

#### Cenário 2 – Solicitação de berço em quarto com berço disponível

* Resultado esperado: execução sem exceções
* Resultado obtido: execução concluída com sucesso
* Status: Aprovado

#### Cenário 3 – Nenhuma solicitação de berço

* Resultado esperado: execução sem exceções
* Resultado obtido: execução concluída com sucesso
* Status: Aprovado

Resultado: as regras de disponibilidade e utilização de berço foram validadas corretamente.

---

### 3.6 Testes da Classe AluguelTest

Objetivo: validar o cálculo do valor final da hospedagem e o status inicial do aluguel.

#### Cenário 1 – Cálculo do valor final

* Quarto individual com ar-condicionado e hidromassagem
* Quantidade de diárias: 3
* Resultado esperado: R$ 840,00
* Resultado obtido: R$ 840,00
* Status: Aprovado

#### Cenário 2 – Status inicial do aluguel

* Resultado esperado: StatusAluguel.ATIVO
* Resultado obtido: StatusAluguel.ATIVO
* Status: Aprovado

Resultado: o cálculo financeiro e a inicialização do status do aluguel foram validados com sucesso.

---

### 3.7 Testes da Classe HospedagemServiceTest

Objetivo: validar as principais regras de negócio relacionadas ao gerenciamento das hospedagens.

#### Registro de aluguel

##### Quarto indisponível

* Resultado esperado: QuartoIndisponivelException
* Status: Aprovado

##### Data inválida

* Resultado esperado: DataInvalidaException
* Status: Aprovado

##### Capacidade excedida

* Resultado esperado: CapacidadeExcedidaException
* Status: Aprovado

##### Solicitação de berço não permitida

* Resultado esperado: RecursoNaoPermitidoException
* Status: Aprovado

##### Registro realizado com sucesso

* Resultado esperado:

  * Status ATIVO
  * Quarto indisponível após reserva
  * Dados persistidos corretamente
* Status: Aprovado

#### Cancelamento de aluguel

##### Cancelamento realizado com sucesso

* Resultado esperado:

  * Status CANCELADO
  * Quarto liberado
* Status: Aprovado

##### Cancelamento de aluguel já cancelado

* Resultado esperado: RecursoNaoPermitidoException
* Status: Aprovado

##### Aluguel inexistente

* Resultado esperado: NoSuchElementException
* Status: Aprovado

#### Histórico de clientes

##### Cliente inexistente

* Resultado esperado: NoSuchElementException
* Status: Aprovado

##### Histórico encontrado

* Resultado esperado: retorno da lista de hospedagens do cliente
* Status: Aprovado

Resultado: todas as regras de negócio do serviço de hospedagem foram validadas com sucesso.

---

### 3.8 Testes da Classe QuartoServiceTest

Objetivo: validar os serviços de consulta e busca de quartos disponibilizados pelo sistema.

#### Cenário 1 – Listagem de todos os quartos

* Regra validada: retorno de todos os quartos cadastrados.
* Resultado esperado: lista contendo todos os quartos registrados.
* Resultado obtido: lista retornada corretamente.
* Status: Aprovado.

#### Cenário 2 – Listagem de quartos disponíveis

* Regra validada: retorno apenas dos quartos disponíveis para locação.
* Resultado esperado: lista contendo somente quartos disponíveis.
* Resultado obtido: lista retornada corretamente.
* Status: Aprovado.

#### Cenário 3 – Busca por quartos do tipo Individual

* Regra validada: consulta de quartos individuais.
* Resultado esperado: retorno da lista de quartos individuais.
* Resultado obtido: lista retornada corretamente.
* Status: Aprovado.

#### Cenário 4 – Busca por quartos do tipo Casal

* Regra validada: consulta de quartos duplos.
* Resultado esperado: retorno da lista de quartos do tipo casal.
* Resultado obtido: lista retornada corretamente.
* Status: Aprovado.

#### Cenário 5 – Busca com tipo nulo

* Regra validada: impedir consultas sem especificação do tipo de quarto.
* Resultado esperado: lançamento de RecursoNaoPermitidoException.
* Resultado obtido: exceção lançada corretamente.
* Status: Aprovado.

#### Cenário 6 – Busca com tipo vazio

* Regra validada: impedir consultas com parâmetro vazio.
* Resultado esperado: lançamento de RecursoNaoPermitidoException.
* Resultado obtido: exceção lançada corretamente.
* Status: Aprovado.

#### Cenário 7 – Busca com tipo inválido

* Regra validada: impedir consultas com tipos de quarto não suportados pelo sistema.
* Resultado esperado: lançamento de RecursoNaoPermitidoException.
* Resultado obtido: exceção lançada corretamente.
* Status: Aprovado.

Resultado: os testes confirmaram o correto funcionamento dos serviços de consulta de quartos, bem como a validação dos parâmetros de entrada e o tratamento adequado de situações inválidas.


## 4. Conclusão

Os testes unitários desenvolvidos permitiram validar as principais funcionalidades do sistema de hospedagem, incluindo cálculos de diárias, controle de capacidade dos quartos, regras de utilização de berço, gerenciamento de aluguéis e operações de hospedagem.

Foram executados testes de cenários positivos e negativos, incluindo validações de regras de negócio e tratamento de exceções. Todos os testes apresentaram os resultados esperados, sem falhas, demonstrando que as funcionalidades implementadas atendem aos requisitos definidos para o sistema.

Dessa forma, conclui-se que o sistema apresentou comportamento consistente e confiável nos cenários testados.
