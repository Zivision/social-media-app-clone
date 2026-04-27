(ns social-media-app-clone.handler
  (:require [reitit.ring :as ring]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.coercion :as coercion]
            [reitit.coercion.malli :as malli]
            [muuntaja.core :as m]
            [ring.util.response :refer [resource-response content-type]]))

(def router-config
  {:data {:coercion   malli/coercion
          :muuntaja   m/instance
          :middleware [muuntaja/format-middleware        ; content negotiation
                       exception/exception-middleware    ; error handling
                       coercion/coerce-request-middleware
                       coercion/coerce-response-middleware]}})

(def routes
  [""
   ;; API routes
   ["/api"
    ["/hello" {:get {:summary "Say hello"
                     :handler (fn [_]
                                {:status 200
                                 :body   {:message "Hello from Reitit!"}})}}]

    ["/echo" {:post {:summary    "Echo the request body"
                     :parameters {:body [:map [:text :string]]}
                     :handler    (fn [req]
                                   (let [body (get-in req [:parameters :body])]
                                     {:status 200
                                      :body   body}))}}]

    ["/users/:id" {:get {:summary    "Get user by ID"
                         :parameters {:path [:map [:id :int]]}
                         :handler    (fn [req]
                                       (let [id (get-in req [:parameters :path :id])]
                                         {:status 200
                                          :body   {:id id :name "Alice"}}))}}]]

   ;; Serve SPA for everything else
   ["/" {:get {:handler (fn [_]
                          (-> (resource-response "public/index.html")
                              (content-type "text/html")))}}]])

(def app
  (ring/ring-handler
   (ring/router routes router-config)
    ;; Default handlers: serve static files + 404
   (ring/routes
    (ring/create-resource-handler {:path "/"})
    (ring/create-default-handler))))
