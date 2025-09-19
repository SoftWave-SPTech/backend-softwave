#!/bin/bash
set -e

# Navega para o diretório do app
cd /home/ec2-user/app

# Para e remove containers antigos
if [ $(docker ps -q --filter "name=backend_softwave" | wc -l) -gt 0 ]; then
  docker compose down
fi

# Atualiza imagens (se necessário)
docker compose pull

# Sobe os containers em modo detached e faz build se necessário
docker compose up -d --build

echo "Deploy realizado com sucesso!"