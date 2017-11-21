#!/bin/bash

set -e

# Run autodoc-collect
git -C ../../repo checkout clojure-1.6.0
rm -f analysis.edn
echo "Analyzing"
cat collect.clj | clojure -C:collect -

# Run autodoc
echo "Building"
cat build.clj | clojure -R:build -