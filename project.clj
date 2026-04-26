(defproject social-media-app-clone "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://www.github.com/Zivision/social-media-app-clone"
  :dependencies [[org.clojure/clojure "1.12.2"]]
  :main ^:skip-aot social-media-app-clone.core
  :target-path "target/%s"
  :source-paths ["src/clj"]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
