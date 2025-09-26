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

echo "Verificando se o container subiu..."
sleep 10
if [ $(docker ps -q --filter "name=backend_softwave" | wc -l) -eq 0 ]; then
  echo "❌ Deploy falhou!"
  exit 1
fi
echo "✅ Backend rodando!"
