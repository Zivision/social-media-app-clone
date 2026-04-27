(defproject social-media-app-clone "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://www.github.com/Zivision/social-media-app-clone"
  :dependencies [[org.clojure/clojure "1.12.2"]
                 [ring/ring-core "1.15.4"]
                 [ring/ring-jetty-adapter "1.15.4"]
                 [metosin/reitit "0.10.1"]
                 [metosin/reitit-core "0.10.1"]
                 [metosin/muuntaja "0.6.11"]
                 [metosin/malli "0.20.1"]]

  :main ^:skip-aot social-media-app-clone.core
  :target-path "target/%s"
  :source-paths ["src/clj"]
  :profiles {:dev {:dependencies [[ring/ring-mock "0.6.2"]]
                   :plugins [[lein-ring "0.12.6"]]
                   :ring {:handler social-media-app-clone.handler/app}}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
