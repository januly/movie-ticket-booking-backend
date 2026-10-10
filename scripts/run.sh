#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
if [[ -f .env ]]; then
    set -a
    source .env
    set +a
fi
if command -v mvn >/dev/null 2>&1; then
    exec mvn spring-boot:run "$@"
fi
exec ./mvnw spring-boot:run "$@"
