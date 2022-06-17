### Task - ADS
> Gerenciador de pacientes

### Groups
```
1. Alan
2. Henrique
3. Bruno
4. Raiza
5. Kelner
```

### API de terceiro utilizada.
- VIA CEP

# Documentação

## Como instalar?
> 1. git clone https://github.com/alandev2/firc-project.git
> 2. Implementar banco de dados usando query no mysql.sql
> 3. execute o projeto
> 4. teste :D 


# **Routers**
## Patients

**CONSULTAR PACIENTES**
```js
[ GET ]
http://localhost:8080/patients?
	field_name={NOME DO CAMPO}&
	field_value={VALOR DE FILTRO}


{ //  Lista de pacientes | 200  //
	"message": "Lista de pacientes",
	"status": "success"
	"data": [
		{
			"name": "julinha",
			"cpf": "1231111",
			"rg": "123",
			"data_nasc": "11/11/1111",
			"tipo_sanguinio": "O+",
			"cep": "50110535",
			"uf": "PE",
			"localidade": "Recife",
			"bairro": "Santo Amaro",
			"rua": "Rua aurora",
			"numero": 123
		}
	],
}
```

**CADASTRAR PACIENTE**
```js
[ POST ]
http://localhost:8080/patients


//  Corpo da requisição   //
{
	"name": "julinha",
	"cpf": "1231111",
	"rg": "123",
	"data_nasc": "11/11/1111",
	"tipo_sanguinio": "O+",
	"cep": "50110535",
	"uf": "PE",  // Necessário ser igual ao do cep
	"localidade": "Recife",  // Necessário ser igual ao do cep
	"bairro": "Santo Amaro", // Necessário ser igual ao do cep
	"rua": "Rua aurora",
	"numero": 123
}

// ## RETORNOS ##

{ //   Paciente cadastrado | 201  //
	"message": "Paciente cadastrado com sucesso",
	"status": "success",
	"data": null
}

{ //   Campo invalido | 400  //
	"message": "Bairro diferente do consultado no cep",
	"status": "error",
	"data": null
}

{ //   Erro interno | 500  //
	"message": "Erro interno",
	"status": "error",
	"data": null
}
```

**ATUALIZAR PACIENTE**
```js
[ PUT ]
http://localhost:8080/patients/{ID DO PACIENTE}


//  Corpo da requisição   //
{
	"name": "julinha",
	"cpf": "1231111",
	"rg": "123",
	"data_nasc": "11/11/1111",
	"tipo_sanguinio": "O+",
	"cep": "50110535",
	"uf": "PE",  // Necessário ser igual ao do cep
	"localidade": "Recife",  // Necessário ser igual ao do cep
	"bairro": "Santo Amaro", // Necessário ser igual ao do cep
	"rua": "Rua aurora",
	"numero": 123
}

// ## RETORNOS ##

{ //   Paciente atualizado | 200  //
	"message": "Paciente atualizado com sucesso",
	"status": "success",
	"data": null
}

{ //   Campo invalido | 400  //
	"message": "Bairro/Cidade/UF diferente do consultado no cep",
	"status": "error",
	"data": null
}

{ //   Paciente não encontrado | 404  //
	"message": "Paciente não encontrado",
	"status": "error",
	"data": null
}

{ //   Erro interno | 500  //
	"message": "Erro interno",
	"status": "error",
	"data": null
}
```
___

### Exams

**LIST**
> [ GET ] http://localhost:8080/exams?field_name={NOME DO CAMPO}&field_value={VALOR DE FILTRO}

**CRIAR EXAME ATRELADO A PACIENTE**
```js

> [ POST ] http://localhost:8080/exams


{ // Corpo da requisição
	"patient_id": "ID DO PACIENTE",
	"type": "TIPO DE EXAME",
	"description": "DESCRIÇÃO DO EXAME",
	"comments": "Observações",
	"result": "Status atual do exame"
}

// ## RETORNOS ##

{ //   Exame criado | 201  //
	"message": "Exame criado com sucesso",
	"status": "success",
	"data": null
}

{ //   Erro interno | 500  //
	"message": "Erro interno",
	"status": "error",
	"data": null
}
```