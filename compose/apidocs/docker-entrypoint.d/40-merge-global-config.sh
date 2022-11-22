#!/bin/sh

which yq >/dev/null || apk --no-cache add yq

for file in /usr/share/nginx/html/openapi/*.yaml; do
  yq -i eval-all 'select(filename == "/tmp/global-config.yaml") * select(fileIndex == 0)' ${file} /tmp/global-config.yaml
done
