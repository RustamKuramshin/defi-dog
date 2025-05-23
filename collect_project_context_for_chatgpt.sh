#!/bin/bash

# Путь к каталогу (по умолчанию — текущий)
INPUT_DIR=${1:-.}
ROOT_DIR=$(realpath "$INPUT_DIR")

if [ ! -d "$ROOT_DIR" ]; then
  echo "❗ Указанный каталог не существует: $ROOT_DIR"
  exit 1
fi

PROJECT_NAME="DeFi Dog"

# ====== Настройки фильтрации ======

# Каталоги, которые нужно исключить
EXCLUDED_DIRS_REGEX="(/build/|/out/|/node_modules/|/.git/|/.idea/|/target/|src/main/bundles|/.jmix|/src/main/java/ru/javaboys/defidog/integrations/sourcify/client|/src/main/java/ru/javaboys/defidog/integrations/sourcify/dto)"

# Расширения файлов для исключения
EXCLUDED_EXTENSIONS=("class" "jar" "png" "jpg" "jpeg" "gif" "ico" "log" "js" "ts" "ipynb")

# Разрешённые расширения
INCLUDED_EXTENSIONS=("java" "xml" "properties" "yml" "yaml" "html" "js" "ts" "gradle" "md")

# Паттерны строк, которые нужно удалить из содержимого файлов
EXCLUDED_LINE_PATTERNS=(
    '^\s*$'              # пустые строки
    '^\s*import\s'       # строки с import
#    '^\s*package\s'      # строки с package
)

# ====== Выходной файл ======
DIR_NAME=$(basename "$ROOT_DIR")
OUTPUT_FILE="$PWD/${PROJECT_NAME} - source code.txt"
> "$OUTPUT_FILE"

# ====== Обход файлов ======
find "$ROOT_DIR" -type f | while read -r file; do
  ext="${file##*.}"

  # Проверка расширения
  include=0
  for allowed in "${INCLUDED_EXTENSIONS[@]}"; do
    if [[ "$ext" == "$allowed" ]]; then
      include=1
      break
    fi
  done
  [ $include -eq 0 ] && continue

  # Проверка на исключённые каталоги
  if [[ "$file" =~ $EXCLUDED_DIRS_REGEX ]]; then
    continue
  fi

  # Проверка на исключённые расширения
  for excluded in "${EXCLUDED_EXTENSIONS[@]}"; do
    if [[ "$ext" == "$excluded" ]]; then
      continue 2
    fi
  done

  # Путь относительно корня
  REL_PATH="${file#$ROOT_DIR/}"

  # Вывод метаданных
#  echo -e "\n==== FILE: $REL_PATH ====" >> "$OUTPUT_FILE"

  # Применение фильтра к содержимому файла
  CONTENT=$(cat "$file")
  for pattern in "${EXCLUDED_LINE_PATTERNS[@]}"; do
    CONTENT=$(echo "$CONTENT" | grep -Ev "$pattern")
  done

  echo "$CONTENT" >> "$OUTPUT_FILE"
done

echo "✅ Сбор исходного кода завершён. Результат в: $OUTPUT_FILE"

# Загрузим также требования к проекту и сохраним их в файл

# ID документа (из URL)
DOC_ID="1-JOCyAptFwL30WpuQPYt4SX5FAIuRJt5nKTj6EHBL04"

# Имя временного и итогового файла
TEMP_REQUIREMENTS_FILE="temp_doc.txt"
OUTPUT_REQUIREMENTS_FILE="${PROJECT_NAME} - project requirements.txt"

# Скачивание документа в текстовом формате
curl -sL "https://docs.google.com/document/d/${DOC_ID}/export?format=txt" -o "${TEMP_REQUIREMENTS_FILE}"

# Удаление пустых строк и сохранение в итоговый файл
grep -v '^[[:space:]]*$' "${TEMP_REQUIREMENTS_FILE}" > "${OUTPUT_REQUIREMENTS_FILE}"

# Удаление временного файла
rm -f "${TEMP_REQUIREMENTS_FILE}"

# Сообщение об успехе
echo "✅ Требования сохранены в: ${OUTPUT_REQUIREMENTS_FILE}"
