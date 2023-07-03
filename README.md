<div align="center">
    <img src="https://github.com/lariandrade/TCC-FindUs/assets/44838761/a25488f0-ef9d-421f-baef-7fff76749d10" weight="250" height="200"/>
    <h1 align="center">Find Us - Digitalização de Negócios</h1>
</div>

<div align="center">
<img src="http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge"/>
<img src="http://img.shields.io/static/v1?label=NOTA&message=10&color=WHITE&style=for-the-badge"/>
</div>


## Descrição do Projeto
<p style="text-justify">
  O Find Us é uma plataforma inovadora que visa facilitar a conexão entre clientes e prestadores de serviços de forma eficiente. Com o objetivo de otimizar a busca por serviços qualificados, essa solução prática e inteligente centraliza diversas opções em um único lugar. Os usuários economizam um tempo valioso ao encontrar exatamente o que precisam para impulsionar o crescimento de seus negócios.
Digitalização de negócios se torna uma realidade acessível e eficiente com o Find US. 

## Caso de Uso
<div align="center">
    <img src="https://github.com/lariandrade/TCC-FindUs/assets/44838761/a12c9b65-d60f-44f7-8fcb-500194c6c219"/>
</div>

## Diagrama de Classe
<div align="center">
 <img src="https://github.com/lariandrade/TCC-FindUs/assets/44838761/0dd4c8f7-8a4d-458c-9afc-c886fcacb371"/>
    
</div>

## Funcionalidades
- `Funcionalidade 1`: CRUD Cliente
- `Funcionalidade 2`: CRUD Prestador
- `Funcionalidade 2`: CRUD Projetos
- `Funcionalidade 3`: Atribuição de notas aos projetos
- `Funcionalidade 4`: Denúncia de conteúdo inadequado.
- `Funcionalidade 5`: Envio de e-mails para prestador.


## Screenshots
### Prestador
#### Projetos
![image](https://github.com/lariandrade/TCC-FindUs/assets/44838761/7e170c75-76e1-483b-bd59-ab6abad6c260)
![image](https://github.com/lariandrade/TCC-FindUs/assets/44838761/58f7aacb-ace5-4269-8378-3490dafbe62b)
![image](https://github.com/lariandrade/TCC-FindUs/assets/44838761/90e93453-8b0d-47d1-ac12-5d5129ecd104)

#### Perfil
![image](https://github.com/lariandrade/TCC-FindUs/assets/44838761/d4958cb3-8f76-4bd9-86d1-c2a74f1465df)
![image](https://github.com/lariandrade/TCC-FindUs/assets/44838761/84bfe71a-cefd-4f0f-a6ac-c59836c2b71f)

### Home
![image](https://github.com/lariandrade/TCC-FindUs/assets/44838761/14b0d21d-2538-4552-b4bf-72e5358bef4f)

##  :warning: Pré-requisitos
- Java 17
- MySQL 8.0

## :arrow_forward: Rodar a aplicação

Siga as instruções abaixo para executar a aplicação:

**1. Baixe o projeto:**
```
git clone https://github.com/lariandrade/TCC-FindUs.git
```
<p>Abra o projeto na IDE.</p>

**Recomendo o uso do IntelliJ IDEA, mas você pode usar qualquer outra IDE que seja compatível com projetos Spring Boot.**

**2. Configurando o Banco de Dados**

<p>Antes de executar a aplicação, certifique-se de criar o banco de dados necessário no MySQL. 
Use o seguinte comando para criar o banco de dados:</p>

```
CREATE DATABASE dbfindus;
```
**3. Configurando as Credenciais de Acesso ao Banco de Dados**

No arquivo application.properties, localize as configurações de acesso ao banco de dados e insira suas próprias credenciais:
```
spring.datasource.username=<insira seu nome de usuario>
spring.datasource.password=<insira sua senha>
```
**4. Configurando as Credenciais de Email**

No mesmo arquivo application.properties, localize as configurações de email e altere o email a ser usado para enviar os emails:

```
spring.mail.username=<insira o endereço de email>
spring.mail.password=<insira a senha>
```

**Observação:** eu recomendado utilizar o hotmail como provedor de email.

A aplicação estará funcionando no endereço http://localhost:8090/.

## ✅ Tecnologias utilizadas

O projeto foi construído utilizando as seguintes ferramentas e tecnologias:

- Springboot
- Thymeleaf
- Java 17
- Mysql
- Bootstrap 5
- CSS3
- HTML5
- JavaScript
