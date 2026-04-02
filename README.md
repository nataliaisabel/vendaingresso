# Sistema de Venda de Ingressos 

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos.

## Funcionalidades

- Cadastro de compra de ingressos
- Controle de limite por setor
- Persistência de dados com Serialização
- Tratamento de Exceções personalizadas
- Geração de relatório em .txt
- Simulação de compras concorrentes com Threads

## Conceitos aplicados

- POO (Entidades, Services, UI)
- Enum para setores
- Exceptions customizadas
- Serialização e desserialização
- Threads com Runnable
- Método synchronized para controle de concorrência

## Simulação de Concorrência

Na classe `Principal`, múltiplas Threads simulam compradores tentando adquirir ingressos simultaneamente no mesmo setor.

O método `comprarIngresso` é `synchronized`, impedindo que o limite do setor seja ultrapassado.

## Estrutura

- `entities` → Classe Ingresso
- `enums` → SetorEnum
- `exceptions` → Exceções personalizadas
- `services` → Regras de negócio e arquivos
- `ui` → Telas do sistema

## Como executar

Executar a classe `Principal`.
