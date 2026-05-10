# FlatMateUni - Sistema de Gerenciamento de Moradia Universitária

Este projeto é um sistema de gerenciamento de locação de imóveis para estudantes universitários, desenvolvido como parte de um exercício incremental de Programação Orientada a Objetos (POO).

## Requisitos

- Java JDK 11 ou superior.
- Make (opcional, para facilitar a execução do projeto).

## Como Executar

### Usando o Makefile (Recomendado)

Na raiz da pasta `exercicio-incremental-poo`, utilize os seguintes comandos:

1.  **Compilar o projeto**:
    ```bash
    make
    ```
2.  **Executar os testes**:
    ```bash
    make run
    ```
3.  **Limpar arquivos temporários**:
    ```bash
    make clean
    ```

### Manualmente (Sem Make)

Caso não possua o Make instalado, você pode compilar e executar manualmente:

1.  **Compilar**:
    ```bash
    mkdir -p bin
    javac -d bin src/main/java/universidade/*.java src/test/java/universidade/Main.java
    ```
2.  **Executar**:
    ```bash
    java -cp bin universidade.Main
    ```

## Observações

- A fim de facilitar a validação do código, utilizei IA para gerar um código de teste em src/test/java/universidade/Main.java.