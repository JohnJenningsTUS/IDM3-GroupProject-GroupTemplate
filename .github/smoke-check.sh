#!/usr/bin/env bash
# Smoke check: waits for the app, then requests each page below and checks
# that it contains its expected text. CI runs it on every pull request.
#
# Each line in PAGES is: the page's path | text the page must contain.
#   New page?            Add its line in the same pull request.
#   Changed on purpose?  Update the expected text, and say so in the PR.
#   Never delete a line to make CI pass.
#
# Run it yourself against the app on your laptop:
#   bash .github/smoke-check.sh
# or against another address:
#   BASE_URL=http://<server-ip> bash .github/smoke-check.sh

set -u
BASE_URL="${BASE_URL:-http://localhost:8080}"

PAGES=(
  "/actuator/health|UP"
  "/|Recently Added Projects"
  "/MainGallery/Login|<h1>Login</h1>"
  "/MainGallery/Register|<h1>Register</h1>"
)

echo "Waiting for $BASE_URL/actuator/health"
for i in $(seq 1 60); do
  if curl -fs "$BASE_URL/actuator/health" | grep -q UP; then
    break
  fi
  if [ "$i" = 60 ]; then
    echo "FAIL the app did not start within 2 minutes"
    exit 1
  fi
  sleep 2
done

failed=0
for entry in "${PAGES[@]}"; do
  path="${entry%%|*}"
  expected="${entry#*|}"
  if ! body=$(curl -fsSL "$BASE_URL$path"); then
    echo "FAIL $path did not load"
    failed=1
  elif grep -qF -- "$expected" <<< "$body"; then
    echo "PASS $path contains \"$expected\""
  else
    echo "FAIL $path does not contain \"$expected\""
    failed=1
  fi
done

exit $failed
