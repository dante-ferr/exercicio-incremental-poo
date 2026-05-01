# FlatMateUni Makefile

JAVAC = javac
JAVA = java
SRC_DIR = src/main/java
TEST_DIR = src/test/java
BIN_DIR = bin

# Encontrar todos os arquivos .java
SOURCES = $(shell find $(SRC_DIR) $(TEST_DIR) -name "*.java")

all: compile

compile:
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)

run: compile
	$(JAVA) -cp $(BIN_DIR) universidade.Main

clean:
	rm -rf $(BIN_DIR)
