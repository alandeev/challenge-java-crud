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


# Documentation

## How to install?
> 1. git clone https://github.com/alandev2/firc-project.git
> 2. implements database using query's in mysql.sql
> 3. execute the project
> 4. test :D 


## **Routers**
### Patients

**CONSULTAR PACIENTES**
> [ GET ] http://localhost:8080/patients?field_name={NOME DO CAMPO}&field_value={VALOR DE FILTRO}

**CRIAR PACIENTES**
> [ POST ] http://localhost:8080/patients
```json
{
	"name": "julinha",
	"cpf": "12345678910",
	"rg": "123",
	"data_nasc": "11/11/1111",
	"endereco": "Rua aurora",
	"bairro": "Santo Amaro",
	"cep": "10545106",
	"tipo_sanguinio": "O+",
	"alergia_medicacao": false
}
```

**ATUALIZAR PACIENTE**
> [ PUT ] http://localhost:8080/patients/3
```json
  {
    "name": "julinha",
    "cpf": "12345678910",
    "rg": "123",
    "data_nasc": "11/11/1111",
    "endereco": "Rua aurora",
    "bairro": "Santo Amaro",
    "cep": "10545106",
    "tipo_sanguinio": "O+",
    "alergia_medicacao": false
  }
```

___
### Exams

**LIST**
> [ GET ] http://localhost:8080/exams?field_name={NOME DO CAMPO}&field_value={VALOR DE FILTRO}

**CRIAR EXAME ATRELADO A PACIENTE**
> [ POST ] http://localhost:8080/exams
```json
{
	"patient_id": "ID DO PACIENTE",
	"type": "TIPO DE EXAME",
	"description": "DESCRIÇÃO DO EXAME",
	"comments": "Observações",
	"result": "Status atual do exame"
}
```