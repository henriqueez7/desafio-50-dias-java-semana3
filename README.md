Projeto desenvolvido durante a **Semana 3 do Desafio 50 Dias de Código**.  
O objetivo desta semana é consumir APIs públicas, tratar dados reais e lidar com erros comuns do mundo real.

---

## 🎯 Objetivo do Projeto

Criar uma aplicação **CLI (terminal)** em Java que consome a **API ViaCEP**, permitindo que o usuário:

- Consulte endereços a partir de um CEP
- Visualize os dados formatados no terminal
- Trate erros como CEP inválido ou inexistente
- Interaja através de um menu simples

---

## 🌐 API Utilizada

- **ViaCEP**
- Endpoint: `https://viacep.com.br/ws/{cep}/json/`
- API pública, gratuita e sem necessidade de autenticação

---

### 📂 Responsabilidade de cada classe

- **Main.java**
    - Exibe o menu
    - Lê a entrada do usuário
    - Controla o fluxo da aplicação

- **CepService.java**
    - Faz a requisição HTTP para a API
    - Valida o CEP
    - Trata erros de resposta
    - Converte o JSON para objeto Java

- **Endereco.java**
    - Classe modelo (POJO)
    - Representa os dados retornados pela API

---