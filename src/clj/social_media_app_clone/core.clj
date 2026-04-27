(ns social-media-app-clone.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [social-media-app-clone.handler :refer [app]])
  (:gen-class))

(defn -main
  "Run the API."
  [& args]
  (run-jetty app {:port 3000 :join? true}))
