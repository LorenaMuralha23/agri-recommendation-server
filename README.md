# Sobre o Projeto 👾

Desenvolvido em Java, a ideia da aplicação é oferecer um serviço que calcula o tipo e a quantidade de calcário necessários para a correção do solo, bem como a quantidade de adubo a ser aplicada no pré-plantio das seguintes espécies:

- **Macieira**
- **Consórcio de gramíneas e leguminosas de estação fria**

## Objetivo do Projeto 🎯

Como parte de um trabalho acadêmico da disciplina de Inteligência Artificial do curso de Ciência da Computação no IFSC, o objetivo do projeto foi aplicar e aprofundar o conhecimento sobre **Lógica Fuzzy** e sua aplicação em problemas reais.  
Além disso, o projeto buscou melhorar habilidades em lógica de programação, trabalho em equipe e integração de diferentes serviços.

Se tiver qualquer feedback para ajudar a melhorar o projeto, o código, ou a estrutura, ficarei muito grata! 💡

- **E-mail:** marialorenamuralhalima2301@gmail.com
- **LinkedIn:** [Clique aqui](www.linkedin.com/in/dev-maria-lorena)

Este projeto é **gratuito** para qualquer uso!

---

# Guia de Instalação e Execução ⚙

## Pré-requisitos 📜

- **Java:** Versão 17.0.10 ou superior
- **Python:** Versão 3.11.5 ou superior

Para uma experiência completa, você precisará instalar três serviços adicionais. Entretanto, para testar apenas o servidor, apenas um serviço é necessário.

### Serviços Necessários:

1. **API Fuzzy (Obrigatório):**

   - Guia de instalação disponível neste [repositório](https://github.com/MiguelSerea/trabalho_ia.git).

2. **Front-End (Opcional):**

   - Guia de instalação disponível neste [repositório](#).

3. **Serviço de Tratamento de Arquivos PDF (Opcional):**
   - Guia de instalação disponível neste [repositório](#).

### Como verificar se o Java está instalado

No terminal, digite:

```bash
java --version
```

Se o Java estiver instalado, você verá algo como:

```bash
java 17.0.10 2024-01-16 LTS
Java(TM) SE Runtime Environment (build 17.0.10+11-LTS-240)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.10+11-LTS-240, mixed mode, sharing)
```

### Como verificar se o Python está instalado

No terminal, digite:

```bash
python --version
```

Se o Python estiver instalado, você verá algo como:

```bash
Python 3.11.5
```

---

## Instalação 📥

1. **Clone o Repositório:**

   ```bash
   git clone https://github.com/LorenaMuralha23/agri-recommendation-server.git
   ```

2. **Acesse o Diretório Clonado:**  
   Substitua `seu-diretório` pelo caminho onde você salvou o repositório.

   ```bash
   cd seu-diretório\server
   ```

3. **Execute o Servidor:**
   ```bash
   java -jar demo-0.0.1-SNAPSHOT.jar
   ```

---

## Testando Apenas a API 🧪

Caso não deseje instalar o frontend ou o serviço de PDF, você pode testar a API diretamente utilizando uma ferramenta de requisições HTTP, como **Postman** ou outra de sua preferência.

### Requisitos:

- O Serviço de Fuzzy deve estar rodando localmente.
- Siga o guia de instalação da API Fuzzy disponível neste [repositório](https://github.com/MiguelSerea/trabalho_ia.git).

### Enviando Dados

1. Utilize o endereço abaixo para enviar um POST:

   ```bash
   http://localhost:8080/agri-server/recommendation/calculate
   ```

2. Envie um JSON com a seguinte estrutura:
   ```json
   {
     "tipoEspecie": 0,
     "area": 200,
     "smp": 7.0,
     "ctcPH7": 16.22,
     "argila": 31.0,
     "p": 9.6,
     "k": 117
   }
   ```

- **Tipos de Espécies:**
  - `0`: Consórcio de gramíneas e leguminosas de estação fria
  - `1`: Macieira

### Recebendo Dados

Ao enviar o JSON, a resposta será no formato:

```json
{
  "status": "success",
  "dose_calcario_hec": 23.0,
  "dose_calcario_total": 459.0,
  "valor_potassio_hectare": 47.22,
  "valor_potassio_total": 9444.0,
  "valor_fosforo_hectare": 130.0,
  "valor_fosforo_total": 26000.0
}
```

---

# Construído com 🛠️

- **Linguagem Principal:** Java
- **Linguagem Secundária:** Python
- **Framework:** Spring Framework

---

# Contribuindo 🤝

Contribuições são muito bem-vindas! Envie quantos **pull requests** (PRs) quiser; terei prazer em analisá-los.

- **E-mail:** marialorenamuralhalima2301@gmail.com
- **LinkedIn:** [Clique aqui](www.linkedin.com/in/dev-maria-lorena)

Muito obrigada! ❤️
