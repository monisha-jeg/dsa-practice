#!/usr/bin/env bash
set -euo pipefail

# Run all tests in basics/tests using the project's simple TestUtils harness.
# Usage: bash basics/run_tests.sh

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"

mkdir -p out

echo "Compiling TestUtils, sources and tests..."
javac -d out TestUtils.java basics/*.java basics/tests/*.java

echo
echo "Running tests (exit on first failure)..."
for f in basics/tests/*.java; do
  cls=$(basename "$f" .java)
  echo
  echo "---- Running: basics.tests.$cls ----"
  java -cp out basics.tests."$cls"
done

echo
echo "All tests executed."
