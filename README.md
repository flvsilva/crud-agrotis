# crud-agrotis

## CI no travis-ci
[![Build Status](https://app.travis-ci.com/flvsilva/crud-agrotis.svg?branch=main)](https://app.travis-ci.com/flvsilva/crud-agrotis)

https://app.travis-ci.com/github/flvsilva/crud-agrotis


## CD no Heroku

https://dashboard.heroku.com/apps/crud-agrotis

## Api disponível em:
https://crud-agrotis.herokuapp.com/

### Funções da API

#### Métodos implementados

* **Pessoas** - https://crud-agrotis.herokuapp.com/pessoas/

| Método | Função | Path |
|---|---|---|
| GET | Carga inicial para testes | cadastro/cargaInicial |
| GET | Listar todas as pessoas cadastradas | retornaTodos |
| GET | Busca por nome | buscaPorNome/{nome} |
| GET | Remoção por nome | remover/{nome} |
| POST | Cadastrar pessoa | cadastro |

* **Propriedades** - https://crud-agrotis.herokuapp.com/propriedades/

| Método | Função | Path |
|---|---|---|
| GET | Listar todas as propriedades | retornaTodas |
| POST | Cadastrar propriedade | cadastro |

* **Laboratorios** - https://crud-agrotis.herokuapp.com/laboratorios/

| Método | Função | Path |
|---|---|---|
| GET | Listar todos os laboratorios | retornaTodos |
| POST | Cadastrar laboratorio | cadastro |

#### Respostas da API

| Código | Descrição |
|---|---|
| `200` | Requisição executada com sucesso (success).|
| `400` | Erros de validação ou os campos informados não existem no sistema.|
| `404` | Função/caminho inválido (Not found).|

#### Formatos JSON (para POST)

+ Pessoa (application/json)

```
{
    "nome": "Jon doe",
    "dataInicial": "2022-02-02T17:41:44Z",
    "dataFinal": "2022-02-02T17:41:44Z",
    "infosPropriedade": {
        "id": 12345
    },
    "laboratorio": {
        "id": 1234
    },
    "observacao": "Usuario de teste JSON"
}

```

+ Propriedade (application/json)

```
{
   "id": 12345,
	 "nome": "Nome Exemplo da fazenda",
	 "cnpj": "12.345.678/0001-10"
}
```

+ Laboratorio (application/json)

```
{
     "id": 1234,
	 "nome": "Laboratorio exemplo"
}
```
