# Código de exebição de conhecimento

Esta documento é para apresentação da capacidade da geração de codigo em Java utilizando Selenium, para realizaçãoa de uma automação de processo no qual a ferramenta alimenta uma planilha separada por mês onde ele registrado todos os serviços executados seguindo a separação por equipamento, visita e garantia.
Esta planilha também demonstra receita total e separada por prestador como também contém formulares para realização dessas divisões.

# Gestão de Pagamento dos Prestadores

Painel de importação dos serviços prestados, todas as informações geradas provem do portal de prestadores da porto seguro.

## Explicação sobre o sistema

O Sistema quando é executado da ao usuário 3 opções de de ação, incluir novo dia na planilha, configurações e finalizar o sistema. 

Para a inclusão de novos serviços, ele utiliza como base o ano cadastrado no seu banco de dados, e o mês que é pedido pelo sistema assim ele analista a planilha para verificação do ultimo dia inserido, podendo iniciar um novo mês ou continuar o que já estava, 
em sequência ele avalia no portal da porto quantos serviços foram executados naquele dia para voltar na planilha e verificar se existem linhas limpas para que ele faça esta inclusão, caso não haja ele cria todas ou só o que for necessario,
após isso ele pega linha a linha dos serviços mostrado na visão resumida dele, validando também se o serviço não esta cancelado, não estando ele acessa o detalha verifica se o serviço é um retorno para conclusão pois em seu sistema ele valida se já havia sido pago uma visita e apenas paga o diferencial.
Após isso ele analisa na mensagem que o prestador deixou, se o serviço é uma conclusão ou apenas uma visita, também validando se existe modelo do equipamento que esta sendo prestado serviço, pois cada equipamento tem um valor diferente. tudo esta correto ele inseri todas as informações necessarias na planilha e segue ele fluxo até 1 dia antes do dia solicitado ou até o ultimo dia do mês.

## Informações do sistema

```
- Linguagem de programação Java
- Importação das depêndencias via Maven
- Interação com um pequeno banco de dados em JSON usando GSON
- Navegador utilizado Google Chrome
- Utlização de Excel como extrator de relatórios
- Utilização dos Google Drive para hospedagem da planilha gerada
- Utilização do Selenium para interação com portal

É utilizaçao sistema de automação de processo para execução das tarefas com Selenium WebDriver, para acesso aos sistema da Porto.
```

## Criador

- Abdiel Pereira Cordeiro
- E-mail: abdiel.cordeiro@outlook.com
